package com.lhind.project.annualleave.mapper;

import com.lhind.project.annualleave.dto.LeaveTypeDTO;
import com.lhind.project.annualleave.entity.LeaveTypeEntity;
import org.mapstruct.Mapper;

@Mapper
public interface LeaveTypeMapper extends CommonDataMapper<LeaveTypeDTO, LeaveTypeEntity>{
}
