package com.example.demo.repository;

import com.example.demo.model.Employee;

public interface EmployeeRepository {

    public void printStatements();

    public long insertNewEmployee(Employee employee);

    public void insertNewDepartmentBatch();

    public void updateEmployeeId(String firstName,Long employeeId);

    public void deleteEmployeeWithId(Long employeeId);
}
