package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;

import java.util.List;

public class EmployeeService {
    public EmployeeService(EmployeeRepository employeeRepository) {
    }

    public List<Employee> findall() {
        return null;
    }
}
