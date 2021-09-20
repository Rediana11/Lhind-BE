package com.lhind.project.annualleave.repository.custom;

import com.lhind.project.annualleave.entity.RequestedLeaveEntity;
import org.springframework.data.domain.Page;


public interface RequestedLeaveRepositoryCustom {
    Page<RequestedLeaveEntity> findAllUserLeaves(String username,  String dateFrom, String dateTo,Integer pageNo, Integer pageSize);
}
