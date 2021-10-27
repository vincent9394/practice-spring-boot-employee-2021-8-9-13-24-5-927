package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.omg.CORBA.NO_IMPLEMENT;
import org.springframework.test.context.TestExecutionListeners;

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
            List<Employee> actual = employeeService.findAll();

            //then
            assertEquals(employees,actual);
        }
    @Test
    void should_return_employee_1_when_find_specific_employeeeee_given_employee_id(){
        //given
        EmployeeRepository employeeRepository= Mockito.mock(EmployeeRepository.class);
        EmployeeService employeeService = new EmployeeService(employeeRepository);
        Employee employee = new Employee("vincent",18, "male",100);
        when(employeeRepository.findById(1)).thenReturn(employee);
        //when
        Employee actual = employeeService.findById(1);
        //then
        assertEquals(employee,actual);
    }

    @Test
    void should_return_male_employee_when_find_employee_gender_given_employee(){
            //given
        EmployeeRepository employeeRepository = Mockito.mock(EmployeeRepository.class);
        EmployeeService employeeService = new EmployeeService(employeeRepository);
        List<Employee> employees = Arrays.asList(
                new Employee("vincentAC1", 18, "male", 12345),
                new Employee("vincent2AC1", 18, "female", 12345)
        );
        when(employeeRepository.findByGender("male")).thenReturn(employees);
        //when
        List<Employee> actual = employeeService.findByGender("male");
        //then
        assertEquals(employees,actual);
    }

    @Test
    void should_create_employee_when_add_employee_given_employee_Info(){
        //given
        EmployeeRepository employeeRepository = Mockito.mock(EmployeeRepository.class);
        EmployeeService employeeService = new EmployeeService(employeeRepository);
        Employee employee = new Employee("vincent",18, "male",100);
        when(employeeRepository.createEmployee(employee)).thenReturn(employee);
        //when
        Employee actual = employeeService.createEmployee(employee);
        //then
        assertEquals(employee,actual);
    }
    @Test
    void should_update_employee_when_update_employee_given_new_employee_Info_employee_id(){
        //given
        EmployeeRepository employeeRepository = Mockito.mock(EmployeeRepository.class);
        EmployeeService employeeService = new EmployeeService(employeeRepository);
        Employee employee = new Employee("vincent",18, "male",100);
        employeeRepository.createEmployee(employee);
        Employee employeeNew = new Employee("vincent",18, "male",100);

        Integer id = 1;
        when(employeeRepository.save(id,employeeNew)).thenReturn(employeeNew);
        //when
        Employee actual = employeeService.saveEmployee(id,employeeNew);
        //then
        assertEquals(employeeNew,actual);
    }






}