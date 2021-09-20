package com.lhind.project.annualleave.service.impl;

import com.lhind.project.annualleave.dto.LeaveStatusDTO;
import com.lhind.project.annualleave.dto.RequestedLeaveDTO;
import com.lhind.project.annualleave.entity.LeaveInfoEntity;
import com.lhind.project.annualleave.entity.LeaveStatusEntity;
import com.lhind.project.annualleave.entity.PersonEntity;
import com.lhind.project.annualleave.entity.RequestedLeaveEntity;
import com.lhind.project.annualleave.exception.EntityNotFoundException;
import com.lhind.project.annualleave.mapper.LeaveMapper;
import com.lhind.project.annualleave.mapper.LeaveStatusMapper;
import com.lhind.project.annualleave.mapper.PersonMapper;
import com.lhind.project.annualleave.mapper.RequestedLeaveMapper;
import com.lhind.project.annualleave.repository.LeaveInfoRepository;
import com.lhind.project.annualleave.repository.LeaveStatusRepository;
import com.lhind.project.annualleave.repository.PersonRepository;
import com.lhind.project.annualleave.repository.RequestedLeaveRepository;
import com.lhind.project.annualleave.service.RequestedLeaveService;
import com.lhind.project.annualleave.util.RequestedLeaveHelperMapper;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RequestedLeaveServiceImpl implements RequestedLeaveService {

    private RequestedLeaveRepository leaveRepository;

    private LeaveStatusRepository leaveStatusRepository;

    private LeaveInfoRepository leaveInfoRepository;

    private PersonRepository personRepository;

    private UserService userService;

    private PersonEntity personEntity;

    private RequestedLeaveHelperMapper helperMapper;

    private RequestedLeaveMapper mapper
            = Mappers.getMapper(RequestedLeaveMapper.class);

    private LeaveStatusMapper statusMapper
            = Mappers.getMapper(LeaveStatusMapper.class);

    private PersonMapper personMapper
            = Mappers.getMapper(PersonMapper.class);

    private LeaveMapper leaveMapper
            = Mappers.getMapper(LeaveMapper.class);

    public static Logger logger = LoggerFactory.getLogger(RequestedLeaveServiceImpl.class);

    public RequestedLeaveServiceImpl(RequestedLeaveRepository leaveRepository, LeaveStatusRepository leaveStatusRepository, PersonRepository personRepository, UserService userService, RequestedLeaveHelperMapper helperMapper, LeaveInfoRepository leaveInfoRepository) {
        this.leaveRepository = leaveRepository;
        this.leaveStatusRepository = leaveStatusRepository;
        this.personRepository = personRepository;
        this.userService = userService;
        this.helperMapper = helperMapper;
        this.leaveInfoRepository = leaveInfoRepository;
    }

    public Page<RequestedLeaveDTO> getAllUserLeaves(String dateFrom, String dateTo, Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        this.personEntity = personRepository.findPersonByUsername(userService.getUserDetails().getUsername());
        Page<RequestedLeaveEntity> leaveEntities = leaveRepository.findAllUserLeaves(this.personEntity.getUsername(), dateFrom, dateTo, pageNo, pageSize);
        return helperMapper.toDto(leaveEntities);
    }

    public List<RequestedLeaveDTO> getAllPendingLeaves() {
        List<RequestedLeaveEntity> leaveEntities = leaveRepository.findAllPendingLeaves();
        return mapper.toDto(leaveEntities);
    }

    public RequestedLeaveDTO getRequestedLeaveById(Long id) {

        Optional<RequestedLeaveEntity> leave = leaveRepository.findById(id);

        return mapper.toDto(leave.orElseThrow(() -> new EntityNotFoundException("Not valid Id: " + id)));
    }

    public RequestedLeaveDTO updateRequestedLeave(RequestedLeaveDTO leaveDTO) {
        if (leaveRepository.existsById(leaveDTO.getId())) {
            Optional<RequestedLeaveEntity> model = leaveRepository.findById(leaveDTO.getId());
            leaveDTO.setCreatedOn(model.get().getCreatedOn());
            leaveDTO.setLeaveStatus(statusMapper.toDto(model.get().getLeaveStatus()));
            leaveDTO.setLeave(leaveMapper.toDto(model.get().getLeave()));
            leaveDTO.setUpdatedOn(LocalDate.now());
            RequestedLeaveEntity updatedLeave = leaveRepository.save(mapper.toEntity(leaveDTO));
            return mapper.toDto(updatedLeave);
        } else {
            throw new EntityNotFoundException("Not valid Id: " + leaveDTO.getId());
        }
    }

    public RequestedLeaveDTO applyForLeave(RequestedLeaveDTO leaveDTO) {

        RequestedLeaveEntity application = new RequestedLeaveEntity();
        //if (hasAvailableLeaveDays(leaveDTO) && areDatesValid(leaveDTO)) {
        // if (hasPassedProbationPeriod(leaveDTO)) {
        LeaveInfoEntity leaveInfoEntity = leaveInfoRepository.findByPersonId(this.personEntity.getId());
        leaveDTO.setRequestedLeaveDays(findRequestedDays(leaveDTO.getDateFrom(), leaveDTO.getDateTo()));
        logger.info(this.personEntity.toString());
        leaveDTO.setLeave(leaveMapper.toDto(leaveInfoEntity));
        leaveDTO.getLeave().setPerson(personMapper.toDto(this.personEntity));
        leaveDTO.setCreatedOn(LocalDate.now());
        leaveDTO.setDeleted(false);
        leaveDTO.setRequestedLeaveDays(findRequestedDays(leaveDTO.getDateFrom(), leaveDTO.getDateTo()));
        setPendingStatus(leaveDTO);
        application = leaveRepository.save(mapper.toEntity(leaveDTO));

        //}
        // }
        return mapper.toDto(application);
    }

    private boolean areDatesValid(RequestedLeaveDTO leaveDTO) {
        if (leaveDTO.getDateFrom().isBefore(leaveDTO.getDateTo()) && leaveDTO.getDateFrom().isAfter(LocalDate.now().minusDays(1))) {
            return true;
        }
        return false;
    }

    private int findRequestedDays(LocalDate dateFrom, LocalDate dateTo) {
        int elapsedDays = (int) ChronoUnit.DAYS.between(dateFrom, dateTo);
        return elapsedDays;
    }

    private void setPendingStatus(RequestedLeaveDTO leaveDTO) {
        LeaveStatusEntity statusEntity = leaveStatusRepository.findByCode("P");
        LeaveStatusDTO statusDTO = statusMapper.toDto(statusEntity);
        leaveDTO.setLeaveStatus(statusDTO);
    }

    private boolean hasAvailableLeaveDays(RequestedLeaveDTO leaveDTO) {
        if (leaveDTO.getRequestedLeaveDays() <= (leaveDTO.getLeave().getAvailableLeaveDays())) {
            return true;
        }
        return false;
    }

    private void decreaseAvailableLeaveDays(Long requestedLeaveId) {
        Optional<RequestedLeaveEntity> requestedLeave = leaveRepository.findById(requestedLeaveId);
        requestedLeave.get().getLeave().setAvailableLeaveDays(requestedLeave.get().getLeave().getTotalLeaveDays() - requestedLeave.get().getRequestedLeaveDays());
    }

    private boolean hasPassedProbationPeriod(RequestedLeaveDTO leaveDTO) {
        LocalDate currentDate = LocalDate.now();
        if (currentDate.isAfter(leaveDTO.getLeave().getFirstWorkDay().plusDays(90))) {
            return true;
        }
        return false;
    }

    public void approveOrRejectApplication(Long requestedLeaveId, boolean value) {
        Optional<RequestedLeaveEntity> requestedLeave = leaveRepository.findById(requestedLeaveId);
        LeaveStatusEntity approvedStatus = leaveStatusRepository.findByCode("A");
        LeaveStatusEntity rejectedStatus = leaveStatusRepository.findByCode("R");
        if (value) {
            requestedLeave.get().setLeaveStatus(approvedStatus);
            decreaseAvailableLeaveDays(requestedLeaveId);

        } else {
            requestedLeave.get().setLeaveStatus(rejectedStatus);
        }
        leaveRepository.save(requestedLeave.get());
    }

    public void deleteRequestedLeaveById(Long id) {
        if (leaveRepository.existsById(id)) {
            Optional<RequestedLeaveEntity> leave = leaveRepository.findById(id);
            leave.get().setDeleted(true);
            leaveRepository.save(leave.get());
            logger.info("Leave deleted");
        } else {
            throw new EntityNotFoundException("Not valid Id: " + id);
        }
    }


}
