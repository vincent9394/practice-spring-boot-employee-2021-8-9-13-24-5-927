package com.thoughtworks.springbootemployee.mapper;

import com.thoughtworks.springbootemployee.dto.EmployeeRequest;
import com.thoughtworks.springbootemployee.dto.EmployeeResponse;
import com.thoughtworks.springbootemployee.entity.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {
    public Employee toEntity(EmployeeRequest employeeRequest){
        Employee employee = new Employee();

        employee.setCompanyId(employeeRequest.getCompanyId());
        employee.setAge(employeeRequest.getAge());
        employee.setName(employeeRequest.getName());
        employee.setSalary(employeeRequest.getSalary());
        employee.setCompanyId(employeeRequest.getCompanyId());

        return employee;
    }

    public Employee toResponse(Employee employee){
        EmployeeResponse employeeResponse = new EmployeeResponse();

        employeeResponse.setCompanyId(employee.getCompanyId());
        employeeResponse.setAge(employee.getAge());
        employeeResponse.setName(employee.getName());
        employeeResponse.setSalary(employee.getSalary());
        employeeResponse.setCompanyId(employee.getCompanyId());

        return employee;
    }
}
