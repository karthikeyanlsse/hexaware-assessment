package com.organization.leavemanagement.model;

import java.time.LocalDate;

public class LeaveRequest {
    private String employeeId;
    private LeaveType leaveType;
    private int numberOfDays;
    private String reason;
    private LocalDate requestDate;

    public LeaveRequest(String employeeId, LeaveType leaveType, int numberOfDays, String reason,
            LocalDate requestDate) {
        this.employeeId = employeeId;
        this.leaveType = leaveType;
        this.numberOfDays = numberOfDays;
        this.reason = reason;
        this.requestDate = requestDate;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public LeaveType getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(LeaveType leaveType) {
        this.leaveType = leaveType;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }

    @Override
    public String toString() {
        return "LeaveRequest{"
                + "employeeId='" + employeeId + '\''
                + ", leaveType=" + leaveType
                + ", numberOfDays=" + numberOfDays
                + ", reason='" + reason + '\''
                + ", requestDate=" + requestDate
                + '}';
    }
}
