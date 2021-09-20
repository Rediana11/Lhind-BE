package com.lhind.project.annualleave.mapper;


import com.lhind.project.annualleave.dto.LeaveStatusDTO;
import com.lhind.project.annualleave.entity.LeaveStatusEntity;
import org.mapstruct.Mapper;

@Mapper
public interface LeaveStatusMapper extends CommonDataMapper<LeaveStatusDTO, LeaveStatusEntity>{
}
