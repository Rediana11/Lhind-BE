package com.lhind.project.annualleave.service;

import com.lhind.project.annualleave.dto.LeaveInfoDTO;

public interface LeaveInfoService {

    LeaveInfoDTO findAvailableLeaveDays(Long personId);
}
