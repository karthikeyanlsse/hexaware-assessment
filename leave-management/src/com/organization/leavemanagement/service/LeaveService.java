package com.organization.leavemanagement.service;

import com.organization.leavemanagement.exception.EmployeeNotFoundException;
import com.organization.leavemanagement.exception.InsufficientLeaveBalanceException;
import com.organization.leavemanagement.model.Employee;
import com.organization.leavemanagement.model.LeaveRecord;
import com.organization.leavemanagement.model.LeaveRequest;
import com.organization.leavemanagement.repository.EmployeeRepository;
import com.organization.leavemanagement.repository.LeaveRepository;
import com.organization.leavemanagement.util.ValidationUtil;
import java.util.List;

public class LeaveService {
    private final EmployeeRepository employeeRepository;
    private final LeaveRepository leaveRepository;

    public LeaveService(EmployeeRepository employeeRepository, LeaveRepository leaveRepository) {
        this.employeeRepository = employeeRepository;
        this.leaveRepository = leaveRepository;
    }

    public void addEmployee(Employee employee) {
        ValidationUtil.validateString(employee.getEmployeeId(), "Employee ID");
        ValidationUtil.validateString(employee.getName(), "Employee name");
        employeeRepository.save(employee);
    }

    public LeaveRecord applyLeave(LeaveRequest leaveRequest) {
        validateRequest(leaveRequest);

        Employee employee = employeeRepository.findById(leaveRequest.getEmployeeId())
                .orElseThrow(() -> new EmployeeNotFoundException(
                        "Employee not found for ID: " + leaveRequest.getEmployeeId()));

        if (leaveRequest.getNumberOfDays() > employee.getLeaveBalance()) {
            throw new InsufficientLeaveBalanceException(
                    "Requested leave days exceed leave balance for employee "
                            + employee.getEmployeeId() + ". Balance: " + employee.getLeaveBalance()
                            + ", Requested: " + leaveRequest.getNumberOfDays());
        }

        ValidationUtil.validateAnnualLimit(
                employee.getTotalLeaveUsedThisYear(),
                leaveRequest.getNumberOfDays(),
                employee.getEmployeeId());

        employee.deductLeave(leaveRequest.getNumberOfDays());

        LeaveRecord leaveRecord = new LeaveRecord(
                leaveRequest.getEmployeeId(),
                leaveRequest.getLeaveType(),
                leaveRequest.getNumberOfDays(),
                leaveRequest.getReason().trim(),
                leaveRequest.getRequestDate());
        leaveRepository.save(leaveRecord);
        return leaveRecord;
    }

    public List<LeaveRecord> getAllLeaveRecords() {
        return leaveRepository.findAll();
    }

    private void validateRequest(LeaveRequest leaveRequest) {
        ValidationUtil.validateString(leaveRequest.getEmployeeId(), "Employee ID");
        ValidationUtil.validateLeaveType(leaveRequest.getLeaveType());
        ValidationUtil.validatePositiveInt(leaveRequest.getNumberOfDays(), "Number of days");
        ValidationUtil.validateString(leaveRequest.getReason(), "Reason");
        ValidationUtil.validateSickLeaveLimit(
                leaveRequest.getLeaveType(), leaveRequest.getNumberOfDays());
    }
}
