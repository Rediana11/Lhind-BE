package com.lhind.project.annualleave.repository;

import com.lhind.project.annualleave.entity.PersonEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Long> {

    @Query( "select p from PersonEntity p where p.username=:username ")
    PersonEntity findPersonByUsername(@Param("username") String username);

    Page<PersonEntity> findByIsDeleted(Pageable pageable,boolean deleted);
}
