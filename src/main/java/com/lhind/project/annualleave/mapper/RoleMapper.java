package com.lhind.project.annualleave.mapper;

import com.lhind.project.annualleave.dto.RoleDTO;
import com.lhind.project.annualleave.entity.RoleEntity;
import org.mapstruct.Mapper;

@Mapper
public interface RoleMapper extends CommonDataMapper<RoleDTO, RoleEntity> {

}
