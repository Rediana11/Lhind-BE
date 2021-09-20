package com.lhind.project.annualleave.mapper;

import com.lhind.project.annualleave.dto.AddUpdatePersonDTO;
import com.lhind.project.annualleave.entity.PersonEntity;
import org.mapstruct.Mapper;

@Mapper
public interface AddUpdatePersonMapper  extends CommonDataMapper<AddUpdatePersonDTO, PersonEntity>{
}
