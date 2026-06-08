package com.organization.leavemanagement.repository;

import com.organization.leavemanagement.model.LeaveRecord;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LeaveRepository {
    private final List<LeaveRecord> leaveRecords = new ArrayList<>();

    public void save(LeaveRecord leaveRecord) {
        leaveRecords.add(leaveRecord);
    }

    public List<LeaveRecord> findAll() {
        return Collections.unmodifiableList(leaveRecords);
    }

    public List<LeaveRecord> findByEmployeeId(String employeeId) {
        return leaveRecords.stream()
                .filter(record -> record.getEmployeeId().equals(employeeId))
                .collect(Collectors.toList());
    }
}
