package com.organization.leavemanagement.repository;

import com.organization.leavemanagement.model.Employee;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class EmployeeRepository {
    private final Map<String, Employee> employeeStore = new HashMap<>();

    public void save(Employee employee) {
        employeeStore.put(employee.getEmployeeId(), employee);
    }

    public Optional<Employee> findById(String employeeId) {
        return Optional.ofNullable(employeeStore.get(employeeId));
    }

    public boolean existsById(String employeeId) {
        return employeeStore.containsKey(employeeId);
    }

    public Collection<Employee> findAll() {
        return employeeStore.values();
    }
}
