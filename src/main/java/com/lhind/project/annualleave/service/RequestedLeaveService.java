package com.lhind.project.annualleave.service;

import com.lhind.project.annualleave.dto.RequestedLeaveDTO;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface RequestedLeaveService {

    Page<RequestedLeaveDTO> getAllUserLeaves( String dateFrom, String dateTo, Integer pageNo, Integer pageSize);

    List<RequestedLeaveDTO> getAllPendingLeaves();

    RequestedLeaveDTO getRequestedLeaveById(Long id);

    RequestedLeaveDTO updateRequestedLeave(RequestedLeaveDTO leaveDTO);

    RequestedLeaveDTO applyForLeave(RequestedLeaveDTO leaveDTO);

    void approveOrRejectApplication(Long requestedLeaveId, boolean value);

    void deleteRequestedLeaveById(Long id);


}
