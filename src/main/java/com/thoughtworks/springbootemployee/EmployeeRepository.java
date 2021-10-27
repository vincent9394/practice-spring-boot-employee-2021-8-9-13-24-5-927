package com.thoughtworks.springbootemployee;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EmployeeRepository {
    private final List<Employee> employees = new ArrayList<>();

    public EmployeeRepository() {
    }

    public List<Employee> findAll() {
        return employees;
    }

    public Employee findById(Integer id) {
        return this.employees
                .stream()
                .filter(item -> id.equals(item.getId()))
                .findFirst()
                .orElseThrow(EmployeeNotFoundException::new);
    }

    public List<Employee> findByGender(String gender) {
        return this.employees
                .stream()
                .filter(item -> gender.equals(item.getGender()))
                .collect(Collectors.toList());
    }

    //TODO formatting
    public PageImpl<Employee> findPagingEmployees(Pageable pageable) {
        List<Employee> page = this.employees
                .stream()
                .skip((long) pageable.getPageNumber() * pageable.getPageSize()).limit(pageable.getPageSize()).collect(Collectors.toList());
        return new PageImpl<>(page, pageable, this.employees.size());
    }

    public Employee createEmployee(Employee employee) {
        int id = this.employees
                .stream()
                .mapToInt(Employee::getId)
                .max()
                .orElse(0) + 1;
        employee.setId(id);
        this.employees.add(employee);
        return employee;
    }

    //TODO delete function
    public void deleteById(Integer id) {
        Employee employee = this.findById(id);
        this.employees.remove(employee);
    }

    public Employee save(Integer id, Employee updatedEmployee) {
        this.deleteById(id);
        this.employees.add(updatedEmployee);
        return updatedEmployee;
    }

    public void deleteAll() {
        this.employees.clear();
    }
}
