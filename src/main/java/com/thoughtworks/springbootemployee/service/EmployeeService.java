package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.exception.EmployeeNotFoundException;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

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
        return employeeRepository
                .findById(id)
                .orElseThrow(EmployeeNotFoundException::new);
    }

    public List<Employee> findByGender(String gender) {
        return employeeRepository.findAllByGender(gender);
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

//    public Employee updateEmployee(Integer id, Employee employeeNew) {
//        return employeeRepository.updateEmployee(id, employeeNew);
//    }
//
//    public void deleteEmployee(Integer id) {
//        this.employeeRepository.deleteById(id);
//    }
//
//    public void deleteAll() {
//        this.employeeRepository.deleteAll();
//    }
//
//    public PageImpl<Employee> findPagingEmployees(Pageable pageable) {
//        return this.employeeRepository.findPagingEmployees(pageable);
//    }
}
