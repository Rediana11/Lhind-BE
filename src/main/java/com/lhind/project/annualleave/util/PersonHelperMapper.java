package com.lhind.project.annualleave.util;

import com.lhind.project.annualleave.dto.PersonDTO;
import com.lhind.project.annualleave.entity.PersonEntity;
import com.lhind.project.annualleave.mapper.PersonMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonHelperMapper {

    private final PersonMapper mapper = Mappers.getMapper(PersonMapper.class);

    public Page<PersonDTO> toDto(Page<PersonEntity> entityList) {
        if (entityList == null) {
            return null;
        }

        List<PersonDTO> dtoList = mapper.toDto(entityList.getContent());

        return new PageImpl<PersonDTO>(dtoList, entityList.getPageable(), entityList.getTotalElements());
    }

}
