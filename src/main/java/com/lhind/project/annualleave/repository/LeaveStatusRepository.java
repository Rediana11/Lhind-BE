package com.lhind.project.annualleave.repository;

import com.lhind.project.annualleave.entity.LeaveStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveStatusRepository extends JpaRepository<LeaveStatusEntity,Long> {

    @Query
    LeaveStatusEntity findByCode(String code);

}
