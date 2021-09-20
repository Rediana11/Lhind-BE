package com.lhind.project.annualleave.repository;

import com.lhind.project.annualleave.entity.PersonEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Long> {

    PersonEntity findPersonByUsername(String username);

    Page<PersonEntity> findByIsDeleted(Pageable pageable,boolean deleted);
}
