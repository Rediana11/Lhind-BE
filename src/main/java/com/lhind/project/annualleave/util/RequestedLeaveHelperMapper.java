package com.lhind.project.annualleave.util;

import com.lhind.project.annualleave.dto.RequestedLeaveDTO;
import com.lhind.project.annualleave.entity.RequestedLeaveEntity;
import com.lhind.project.annualleave.mapper.RequestedLeaveMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RequestedLeaveHelperMapper {


    private final RequestedLeaveMapper mapper = Mappers.getMapper(RequestedLeaveMapper.class);

    public Page<RequestedLeaveDTO> toDto(Page<RequestedLeaveEntity> entityList) {
        if (entityList == null) {
            return null;
        }

        List<RequestedLeaveDTO> dtoList = mapper.toDto(entityList.getContent());

        return new PageImpl<RequestedLeaveDTO>(dtoList, entityList.getPageable(), entityList.getTotalElements());
    }
}
