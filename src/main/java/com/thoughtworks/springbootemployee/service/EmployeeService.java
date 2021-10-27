package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.List;
@Service
public class EmployeeService {
    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee findById(Integer id) {
        return employeeRepository.findById(id);
    }

    public List<Employee> findByGender(String gender) {
        return employeeRepository.findByGender(gender);
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.createEmployee(employee);
    }

    public Employee updateEmployee(Integer id, Employee employeeNew) {
        return employeeRepository.updateEmployee(id, employeeNew);
    }

    public void deleteEmployee(Integer id) {
        this.employeeRepository.deleteById(id);
    }

    public void deleteAll() {
        this.employeeRepository.deleteAll();
    }

    public PageImpl<Employee> findPagingEmployees(Pageable pageable) {
        return this.employeeRepository.findPagingEmployees(pageable);
    }
}
