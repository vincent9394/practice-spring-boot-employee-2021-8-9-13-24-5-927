package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.exception.EmployeeNotFoundException;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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

    public List<Employee> findAllByGender(String gender) {
        return employeeRepository.findAllByGender(gender);
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Integer id, Employee updatedEmployee) {
        Employee originEmployee = this.employeeRepository.save(updatedEmployee);
        if (updatedEmployee.getName() != null) {
            originEmployee.setName(updatedEmployee.getName());
        }
        if (updatedEmployee.getAge() != null) {
            originEmployee.setAge(updatedEmployee.getAge());
        }
        if (updatedEmployee.getSalary() != null) {
            originEmployee.setSalary(updatedEmployee.getSalary());
        }
        return employeeRepository.save(originEmployee);
    }

    //
    public void deleteEmployee(Integer id) {
        this.employeeRepository.deleteById(id);
    }

    //
    public void deleteAllEmployee() {
        this.employeeRepository.deleteAll();
    }

    //
    public PageImpl<Employee> findPagingEmployees(Pageable pageable) {
        return this.employeeRepository.findAll(pageable);
    }
}
