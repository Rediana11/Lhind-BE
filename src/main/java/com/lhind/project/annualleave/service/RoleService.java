package com.lhind.project.annualleave.service;

import com.lhind.project.annualleave.dto.RoleDTO;

import java.util.List;

public interface RoleService {

    List<RoleDTO> findAllNames();
}
