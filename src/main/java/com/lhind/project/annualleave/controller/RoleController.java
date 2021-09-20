package com.lhind.project.annualleave.controller;

import com.lhind.project.annualleave.dto.RoleDTO;
import com.lhind.project.annualleave.service.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class RoleController {

    private RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("role/list")
    public ResponseEntity<List<RoleDTO>> getAllRoles() {
        List<RoleDTO> list = roleService.findAllNames();
        return ResponseEntity.ok(list);
    }

}
