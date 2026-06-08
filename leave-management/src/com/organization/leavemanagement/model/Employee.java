package com.organization.leavemanagement.model;

public class Employee {
    private String employeeId;
    private String name;
    private int leaveBalance;
    private int totalLeaveUsedThisYear;

    public Employee(String employeeId, String name, int leaveBalance) {
        this.employeeId = employeeId;
        this.name = name;
        this.leaveBalance = leaveBalance;
        this.totalLeaveUsedThisYear = 0;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLeaveBalance() {
        return leaveBalance;
    }

    public void setLeaveBalance(int leaveBalance) {
        this.leaveBalance = leaveBalance;
    }

    public int getTotalLeaveUsedThisYear() {
        return totalLeaveUsedThisYear;
    }

    public void setTotalLeaveUsedThisYear(int totalLeaveUsedThisYear) {
        this.totalLeaveUsedThisYear = totalLeaveUsedThisYear;
    }

    public void deductLeave(int days) {
        leaveBalance -= days;
        totalLeaveUsedThisYear += days;
    }

    @Override
    public String toString() {
        return "Employee{"
                + "employeeId='" + employeeId + '\''
                + ", name='" + name + '\''
                + ", leaveBalance=" + leaveBalance
                + ", totalLeaveUsedThisYear=" + totalLeaveUsedThisYear
                + '}';
    }
}
