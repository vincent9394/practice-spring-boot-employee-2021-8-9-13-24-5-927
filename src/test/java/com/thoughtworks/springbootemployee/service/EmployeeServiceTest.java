package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class EmployeeServiceTest {
        @Test
    void should_return_all_employees_when_find_all_given_2_employees(){


            //given
            EmployeeRepository employeeRepository= Mockito.mock(EmployeeRepository.class);
            EmployeeService employeeService = new EmployeeService(employeeRepository);
            List<Employee> employees = Arrays.asList(
                    new Employee("vincentAC1", 18, "male", 12345),
                    new Employee("vincent2AC1", 18, "male", 12345)
            );
            when(employeeRepository.findAll()).thenReturn(employees);

            //when
            List<Employee> actual = employeeService.findall();

            //then
            assertEquals(employees,actual);
        }
}