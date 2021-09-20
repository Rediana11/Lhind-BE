package com.lhind.project.annualleave.service.impl;

import com.lhind.project.annualleave.dto.LeaveInfoDTO;
import com.lhind.project.annualleave.entity.LeaveInfoEntity;
import com.lhind.project.annualleave.mapper.LeaveMapper;
import com.lhind.project.annualleave.repository.LeaveInfoRepository;
import com.lhind.project.annualleave.service.LeaveInfoService;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

@Service
public class LeaveInfoServiceImpl implements LeaveInfoService {

    private LeaveInfoRepository leaveInfoRepository;

    public LeaveInfoServiceImpl(LeaveInfoRepository leaveInfoRepository) {
        this.leaveInfoRepository = leaveInfoRepository;
    }

    private LeaveMapper mapper
            = Mappers.getMapper(LeaveMapper.class);
    @Override
    public LeaveInfoDTO findAvailableLeaveDays(Long personId) {
        LeaveInfoEntity leaveInfoEntity=leaveInfoRepository.findByPersonId(personId);
        return mapper.toDto(leaveInfoEntity);
    }
}
