package com.lhind.project.annualleave.mapper;

import com.lhind.project.annualleave.dto.LeaveInfoDTO;
import com.lhind.project.annualleave.entity.LeaveInfoEntity;
import org.mapstruct.Mapper;

@Mapper
public interface LeaveMapper extends CommonDataMapper<LeaveInfoDTO, LeaveInfoEntity>{
}
