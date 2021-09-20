package com.lhind.project.annualleave.controller;

import com.lhind.project.annualleave.dto.LeaveInfoDTO;
import com.lhind.project.annualleave.service.LeaveInfoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/leave-info")
public class LeaveInfoController {

    private LeaveInfoService leaveInfoService;

    public LeaveInfoController(LeaveInfoService leaveInfoService) {
        this.leaveInfoService = leaveInfoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<LeaveInfoDTO> getAvailableLeaveDays(@PathVariable Long id) {
        LeaveInfoDTO infoDTO = leaveInfoService.findAvailableLeaveDays(id);
        return ResponseEntity.ok(infoDTO);
    }

}
