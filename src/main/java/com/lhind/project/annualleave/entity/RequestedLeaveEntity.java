package com.lhind.project.annualleave.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "requested_leave")
public class RequestedLeaveEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "requested_leave_days")
    private int requestedLeaveDays;

    @ManyToOne
    @JoinColumn(name = "leave_id")
    private LeaveInfoEntity leave;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "leave_type_id")
    private LeaveTypeEntity leaveType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "leave_status_id")
    private LeaveStatusEntity leaveStatus;

    @Column(name = "date_from")
    private LocalDate dateFrom;

    @Column(name = "date_to")
    private LocalDate dateTo;

    @Column(length = 300)
    private String note;

    @Column(name = "created_on")
    private LocalDate createdOn;

    @Column(name = "updated_on")
    private LocalDate updatedOn;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDate getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDate updatedOn) {
        this.updatedOn = updatedOn;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public LeaveInfoEntity getLeave() {
        return leave;
    }

    public void setLeave(LeaveInfoEntity leave) {
        this.leave = leave;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRequestedLeaveDays() {
        return requestedLeaveDays;
    }

    public void setRequestedLeaveDays(int requestedLeaveDays) {
        this.requestedLeaveDays = requestedLeaveDays;
    }


    public LeaveStatusEntity getLeaveStatus() {
        return leaveStatus;
    }

    public void setLeaveStatus(LeaveStatusEntity leaveStatus) {
        this.leaveStatus = leaveStatus;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LeaveTypeEntity getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(LeaveTypeEntity leaveType) {
        this.leaveType = leaveType;
    }
}
