package com.lhind.project.annualleave.dto;


import java.time.LocalDate;

public class RequestedLeaveDTO {

    private Long id;

    private int requestedLeaveDays;

    private LeaveTypeDTO leaveType;

    private LeaveStatusDTO leaveStatus;

    private LeaveInfoDTO leave;

    private LocalDate dateFrom;

    private LocalDate dateTo;

    private String note;

    private LocalDate createdOn;

    private LocalDate updatedOn;

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

    public LeaveStatusDTO getLeaveStatus() {
        return leaveStatus;
    }

    public void setLeaveStatus(LeaveStatusDTO leaveStatus) {
        this.leaveStatus = leaveStatus;
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

    public LeaveInfoDTO getLeave() {
        return leave;
    }

    public void setLeave(LeaveInfoDTO leave) {
        this.leave = leave;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LeaveTypeDTO getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(LeaveTypeDTO leaveType) {
        this.leaveType = leaveType;
    }
}
