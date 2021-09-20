package com.lhind.project.annualleave.repository.custom.impl;

import com.lhind.project.annualleave.entity.RequestedLeaveEntity;
import com.lhind.project.annualleave.repository.custom.RequestedLeaveRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Repository
public class RequestedLeaveRepositoryCustomImpl implements RequestedLeaveRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<RequestedLeaveEntity> findAllUserLeaves(String username, String dateFrom, String dateTo, Integer pageNo, Integer pageSize) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate from = LocalDate.now();
        LocalDate to = LocalDate.now();

        String query = "SELECT r FROM RequestedLeaveEntity r " +
                "JOIN LeaveTypeEntity lt on r.leaveType =lt.id\n" +
                "JOIN LeaveStatusEntity ls on r.leaveStatus=ls.id\n" +
                "JOIN LeaveInfoEntity l on r.leave=l.id\n" +
                "JOIN PersonEntity p on l.person=p.id\n" +
                "WHERE l.person.username = ?1 AND r.isDeleted=false ";

        if (dateFrom != null && dateTo != null) {
            query += " AND r.dateFrom >= ?2 AND r.dateTo <= ?3 ";
            from = LocalDate.parse(dateFrom, formatter);
            to = LocalDate.parse(dateTo, formatter);
        }
        query += " ORDER BY r.dateFrom";
        TypedQuery<RequestedLeaveEntity> typedQuery = entityManager.createQuery(query,
                RequestedLeaveEntity.class);
        typedQuery.setParameter(1, username);
        if (dateFrom != null && dateTo != null) {
            typedQuery.setParameter(2, from);
            typedQuery.setParameter(3, to);
        }
        List<RequestedLeaveEntity> list = typedQuery.getResultList();
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<RequestedLeaveEntity> page = new PageImpl<>(list, paging, list.size());

        return page;
    }
}
