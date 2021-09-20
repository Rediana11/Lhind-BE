package com.lhind.project.annualleave.repository;

import com.lhind.project.annualleave.entity.LeaveInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveInfoRepository extends JpaRepository<LeaveInfoEntity, Long> {

    @Query(value = "SELECT * FROM leave r   \n" +
            "                 WHERE r.person_id=:id AND r.is_deleted=false\n", nativeQuery = true)
    LeaveInfoEntity findByPersonId(@Param("id") Long id);


}
