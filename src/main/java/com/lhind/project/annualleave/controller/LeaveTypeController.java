package com.lhind.project.annualleave.controller;

import com.lhind.project.annualleave.dto.LeaveTypeDTO;
import com.lhind.project.annualleave.service.LeaveTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/leave-type")
public class LeaveTypeController {

    private LeaveTypeService leaveTypeService;

    public LeaveTypeController(LeaveTypeService leaveTypeService) {
        this.leaveTypeService = leaveTypeService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<LeaveTypeDTO>> findAllLeaveTypeNames() {
        List<LeaveTypeDTO> list = leaveTypeService.findAllLeaveTypeNames();
        return ResponseEntity.ok(list);
    }
}
