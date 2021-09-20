package com.lhind.project.annualleave.mapper;

import com.lhind.project.annualleave.dto.AddUpdateRequestedLeaveDTO;
import com.lhind.project.annualleave.entity.RequestedLeaveEntity;
import org.mapstruct.Mapper;

@Mapper
public interface AddUpdateLeaveMapper extends CommonDataMapper<AddUpdateRequestedLeaveDTO, RequestedLeaveEntity>{
}
