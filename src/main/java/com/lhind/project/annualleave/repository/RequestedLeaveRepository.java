package com.lhind.project.annualleave.repository;

import com.lhind.project.annualleave.entity.RequestedLeaveEntity;
import com.lhind.project.annualleave.repository.custom.RequestedLeaveRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RequestedLeaveRepository extends JpaRepository<RequestedLeaveEntity,Long>, RequestedLeaveRepositoryCustom {

    @Query(value = "SELECT * FROM requested_leave r  \n" +
            "  JOIN leave_type lt on r.leave_type_id=lt.id \n" +
            "                 JOIN leave_status ls on r.leave_status_id=ls.id \n" +
            "                 JOIN leave l on r.leave_id=l.id \n" +
            "                 JOIN person p on l.person_id=p.id \n" +
            "                 WHERE ls.code='P' AND r.is_deleted=false\n" ,nativeQuery = true)
    List<RequestedLeaveEntity> findAllPendingLeaves();


}
