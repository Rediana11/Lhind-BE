package com.lhind.project.annualleave.repository;

import com.lhind.project.annualleave.entity.LeaveTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveTypeRepository extends JpaRepository<LeaveTypeEntity, Long> {

    @Query("SELECT l FROM LeaveTypeEntity l ")
    List<LeaveTypeEntity> findAllNames();
}
