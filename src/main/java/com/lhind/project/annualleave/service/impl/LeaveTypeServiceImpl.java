package com.lhind.project.annualleave.service.impl;

import com.lhind.project.annualleave.dto.LeaveTypeDTO;
import com.lhind.project.annualleave.entity.LeaveTypeEntity;
import com.lhind.project.annualleave.mapper.LeaveTypeMapper;
import com.lhind.project.annualleave.repository.LeaveTypeRepository;
import com.lhind.project.annualleave.service.LeaveTypeService;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveTypeServiceImpl implements LeaveTypeService {

    private LeaveTypeMapper mapper
            = Mappers.getMapper(LeaveTypeMapper.class);

    private LeaveTypeRepository leaveTypeRepository;

    public LeaveTypeServiceImpl(LeaveTypeRepository leaveTypeRepository) {
        this.leaveTypeRepository = leaveTypeRepository;
    }

    public static Logger logger = LoggerFactory.getLogger(LeaveTypeServiceImpl.class);

    public List<LeaveTypeDTO> findAllLeaveTypeNames() {
        List<LeaveTypeEntity> types = leaveTypeRepository.findAllNames();
        return mapper.toDto(types);
    }
}
