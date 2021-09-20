package com.lhind.project.annualleave.dto;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

public class AddUpdateRequestedLeaveDTO {

    private Long id;

    @NotBlank(message = "Leave type is mandatory")
    private LeaveTypeDTO leaveType;

    @NotBlank(message = "Start leave day is mandatory")
    private LocalDate dateFrom;

    @NotBlank(message = "Last leave day is mandatory")
    private LocalDate dateTo;

    private String note;

    private LeaveInfoDTO leave;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LeaveTypeDTO getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(LeaveTypeDTO leaveType) {
        this.leaveType = leaveType;
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
}
