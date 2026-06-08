package com.organization.leavemanagement.main;

import com.organization.leavemanagement.exception.AnnualLeaveLimitExceededException;
import com.organization.leavemanagement.exception.EmployeeNotFoundException;
import com.organization.leavemanagement.exception.InsufficientLeaveBalanceException;
import com.organization.leavemanagement.exception.InvalidLeaveRequestException;
import com.organization.leavemanagement.model.Employee;
import com.organization.leavemanagement.model.LeaveRequest;
import com.organization.leavemanagement.model.LeaveType;
import com.organization.leavemanagement.repository.EmployeeRepository;
import com.organization.leavemanagement.repository.LeaveRepository;
import com.organization.leavemanagement.service.LeaveService;
import java.time.LocalDate;

public class LeaveManagementApp {
    public static void main(String[] args) {
        EmployeeRepository employeeRepository = new EmployeeRepository();
        LeaveRepository leaveRepository = new LeaveRepository();
        LeaveService leaveService = new LeaveService(employeeRepository, leaveRepository);

        leaveService.addEmployee(new Employee("EMP101", "Anu", 12));
        leaveService.addEmployee(new Employee("EMP102", "Bala", 8));
        leaveService.addEmployee(new Employee("EMP103", "Charan", 25));

        processLeave(leaveService, new LeaveRequest(
                "EMP101", LeaveType.CASUAL, 3, "Family event", LocalDate.of(2026, 6, 1)));
        processLeave(leaveService, new LeaveRequest(
                "EMP101", LeaveType.SICK, 6, "Medical rest", LocalDate.of(2026, 6, 8)));
        processLeave(leaveService, new LeaveRequest(
                "EMP102", LeaveType.EARNED, 9, "Vacation", LocalDate.of(2026, 6, 10)));
        processLeave(leaveService, new LeaveRequest(
                "EMP999", LeaveType.CASUAL, 1, "Test request", LocalDate.of(2026, 6, 11)));
        processLeave(leaveService, new LeaveRequest(
                "EMP101", LeaveType.EARNED, 18, "Long trip", LocalDate.of(2026, 7, 1)));
        processLeave(leaveService, new LeaveRequest(
                "EMP103", LeaveType.EARNED, 18, "Project break", LocalDate.of(2026, 8, 1)));
        processLeave(leaveService, new LeaveRequest(
                "EMP103", LeaveType.CASUAL, 5, "Festival leave", LocalDate.of(2026, 9, 15)));
        processLeave(leaveService, new LeaveRequest(
                "EMP101", LeaveType.EARNED, 5, "Year-end vacation", LocalDate.of(2026, 10, 1)));

        System.out.println("Approved leave records:");
        leaveService.getAllLeaveRecords().forEach(System.out::println);
    }

    private static void processLeave(LeaveService leaveService, LeaveRequest leaveRequest) {
        try {
            System.out.println("Processing request: " + leaveRequest);
            System.out.println("Approved: " + leaveService.applyLeave(leaveRequest));
        } catch (InvalidLeaveRequestException
                | InsufficientLeaveBalanceException
                | EmployeeNotFoundException
                | AnnualLeaveLimitExceededException exception) {
            System.out.println("Request failed: " + exception.getMessage());
        }
        System.out.println();
    }
}
