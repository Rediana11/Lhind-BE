package com.lhind.project.annualleave.service;

import com.lhind.project.annualleave.dto.LeaveTypeDTO;

import java.util.List;

public interface LeaveTypeService {

    List<LeaveTypeDTO> findAllLeaveTypeNames();
}
