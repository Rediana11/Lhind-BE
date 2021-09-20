package com.lhind.project.annualleave.dto;

import java.time.LocalDate;
import java.util.Date;

public class LeaveInfoDTO {

    private Long id;

    private String code;

    private PersonDTO person;

    private LocalDate firstWorkDay;

    private int totalLeaveDays;

    private int availableLeaveDays;

    private String note;

    private LocalDate createdOn;

    private LocalDate  updatedOn;

    private boolean isDeleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PersonDTO getPerson() {
        return person;
    }

    public void setPerson(PersonDTO person) {
        this.person = person;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getTotalLeaveDays() {
        return totalLeaveDays;
    }

    public void setTotalLeaveDays(int totalLeaveDays) {
        this.totalLeaveDays = totalLeaveDays;
    }

    public LocalDate getFirstWorkDay() {
        return firstWorkDay;
    }

    public void setFirstWorkDay(LocalDate firstWorkDay) {
        this.firstWorkDay = firstWorkDay;
    }

    public int getAvailableLeaveDays() {
        return availableLeaveDays;
    }

    public void setAvailableLeaveDays(int availableLeaveDays) {
        this.availableLeaveDays = availableLeaveDays;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

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
}
