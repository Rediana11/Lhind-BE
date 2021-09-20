package com.lhind.project.annualleave.service.impl;

import com.lhind.project.annualleave.dto.RoleDTO;
import com.lhind.project.annualleave.entity.RoleEntity;
import com.lhind.project.annualleave.mapper.RoleMapper;
import com.lhind.project.annualleave.repository.RoleRepository;
import com.lhind.project.annualleave.service.RoleService;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleMapper mapper
            = Mappers.getMapper(RoleMapper.class);

    private RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<RoleDTO> findAllNames() {
        List<RoleEntity> roles = roleRepository.findAllNames();
        return mapper.toDto(roles);
    }
}
