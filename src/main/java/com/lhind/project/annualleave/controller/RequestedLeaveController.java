package com.lhind.project.annualleave.controller;

import com.lhind.project.annualleave.dto.RequestedLeaveDTO;
import com.lhind.project.annualleave.service.RequestedLeaveService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/application/leave")
public class RequestedLeaveController {


    private RequestedLeaveService leaveService;

    public RequestedLeaveController(RequestedLeaveService leaveService) {
        this.leaveService = leaveService;
    }

    @GetMapping("/list")
    public ResponseEntity<Page<RequestedLeaveDTO>> getAllUserLeaves( String dateFrom, String dateTo,
                                                                    @RequestParam(defaultValue = "0") Integer pageNo,
                                                                    @RequestParam(defaultValue = "2") Integer pageSize) {
        Page<RequestedLeaveDTO> list = leaveService.getAllUserLeaves(dateFrom, dateTo,pageNo,pageSize);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/pending-list")
    public ResponseEntity<List<RequestedLeaveDTO>> getAllUPendingLeaves() {
        List<RequestedLeaveDTO> list = leaveService.getAllPendingLeaves();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RequestedLeaveDTO> getRequestedLeaveById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(leaveService.getRequestedLeaveById(id));

    }

    @PutMapping
    public ResponseEntity<RequestedLeaveDTO> updateRequestedLeave(@RequestBody RequestedLeaveDTO leaveDTO) {
        RequestedLeaveDTO leaveUpdated = leaveService.updateRequestedLeave(leaveDTO);
        return ResponseEntity.ok().body(leaveUpdated);
    }

    @PutMapping(value = "/request")
    public ResponseEntity<Void> approveOrRejectApplication(@RequestParam Long id, @RequestParam boolean value) {
        leaveService.approveOrRejectApplication(id, value);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<RequestedLeaveDTO> applyForLeave(@RequestBody RequestedLeaveDTO leaveDTO) {
        RequestedLeaveDTO applicationLeave = leaveService.applyForLeave(leaveDTO);
        return ResponseEntity.ok().body(applicationLeave);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRequestedLeaveById(@PathVariable("id") Long id) {
        leaveService.deleteRequestedLeaveById(id);
        return ResponseEntity.ok().build();
    }
}
