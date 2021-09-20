package com.lhind.project.annualleave.repository;

import com.lhind.project.annualleave.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    RoleEntity findByCode(String code);

    @Query("SELECT r FROM RoleEntity r")
    List<RoleEntity> findAllNames();
}
