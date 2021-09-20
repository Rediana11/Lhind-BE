package com.lhind.project.annualleave.mapper;

import com.lhind.project.annualleave.dto.PersonDTO;
import com.lhind.project.annualleave.entity.PersonEntity;
import org.mapstruct.Mapper;

@Mapper
public interface PersonMapper extends CommonDataMapper<PersonDTO, PersonEntity>{

}
