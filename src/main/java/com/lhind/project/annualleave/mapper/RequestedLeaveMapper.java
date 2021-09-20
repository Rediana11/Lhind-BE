package com.lhind.project.annualleave.mapper;


import com.lhind.project.annualleave.dto.RequestedLeaveDTO;
import com.lhind.project.annualleave.entity.RequestedLeaveEntity;
import org.mapstruct.Mapper;

@Mapper
public interface RequestedLeaveMapper extends CommonDataMapper<RequestedLeaveDTO, RequestedLeaveEntity> {
}
