package com.thoughtworks.springbootemployee.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//TODO delete private
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class EmployeeControllerTest {
    Employee employee;
    Employee employee2;
    Employee employee3;
    Employee employee4;
    Employee employee5;
    String url = "/employees";

    @Autowired
    MockMvc mockMvc;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ObjectMapper objectMapper;

    //TODO 1/reset id /2
    @BeforeEach
    void setUp() {
        employeeRepository.deleteAll();
        employee = employeeRepository.save(new Employee("vincent1", 18, "Male", 12345));
        employee2 = employeeRepository.save(new Employee("vincent2", 18, "Female", 23456));
        employee3 = employeeRepository.save(new Employee("vincent3", 18, "Male", 34567));
        employee4 = employeeRepository.save(new Employee("vincent4", 18, "Female", 45678));
        employee5 = employeeRepository.save(new Employee("vincent5", 18, "Male", 56789));
    }

    @AfterEach
    void cleanUp() {
        employeeRepository.deleteAll();
    }

    @Test
    void should_return_all_employees_when_find_all_given_two_employees() throws Exception {
//        //given
//        //when
        ResultActions resultActions = this.mockMvc.perform(get(url));
//        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(employee.getId()))
                .andExpect(jsonPath("$[0].name").value(employee.getName()))
                .andExpect(jsonPath("$[0].age").value(employee.getAge()))
                .andExpect(jsonPath("$[0].gender").value(employee.getGender()))
                .andExpect(jsonPath("$[0].salary").value(employee.getSalary()))
                .andExpect(jsonPath("$[1].id").value(employee2.getId()))
                .andExpect(jsonPath("$[1].name").value(employee2.getName()))
                .andExpect(jsonPath("$[1].age").value(employee2.getAge()))
                .andExpect(jsonPath("$[1].gender").value(employee2.getGender()))
                .andExpect(jsonPath("$[1].salary").value(employee2.getSalary()))
        ;
    }

    @Test
    void should_return_one_employees_when_find_id_given_certain_id() throws Exception {
        //given
        String id = "/1";
//         //when
        ResultActions resultActions = mockMvc.perform(get(url + id));
//        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(employee.getId()))
                .andExpect(jsonPath("$.name").value(employee.getName()))
                .andExpect(jsonPath("$.age").value(employee.getAge()))
                .andExpect(jsonPath("$.gender").value(employee.getGender()))
                .andExpect(jsonPath("$.salary").value(employee.getSalary()))
        ;
    }

    @Test
    void should_return_male_employees_when_find_gender_given_two_female_employee_and_one_female_employee() throws Exception {
        //given
        String gender = "?gender=Female";
        //when
        ResultActions resultActions = mockMvc.perform(get(url + gender));
        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(employee2.getId()))
                .andExpect(jsonPath("$[0].name").value(employee2.getName()))
                .andExpect(jsonPath("$[0].age").value(employee2.getAge()))
                .andExpect(jsonPath("$[0].gender").value(employee2.getGender()))
                .andExpect(jsonPath("$[0].salary").value(employee2.getSalary()))
                .andExpect(jsonPath("$[1].id").value(employee4.getId()))
                .andExpect(jsonPath("$[1].name").value(employee4.getName()))
                .andExpect(jsonPath("$[1].age").value(employee4.getAge()))
                .andExpect(jsonPath("$[1].gender").value(employee4.getGender()))
                .andExpect(jsonPath("$[1].salary").value(employee4.getSalary()))
        ;
    }

    @Test
    void should_return_employee_page_when_find_page_given_page_and_page_size() throws Exception {
        //given
        String page = "?page=1&size=2";
        //when
        ResultActions resultActions = mockMvc.perform(get(url + page));
        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").value(employee3.getId()))
                .andExpect(jsonPath("$.content[0].name").value(employee3.getName()))
                .andExpect(jsonPath("$.content[0].age").value(employee3.getAge()))
                .andExpect(jsonPath("$.content[0].gender").value(employee3.getGender()))
                .andExpect(jsonPath("$.content[0].salary").value(employee3.getSalary()))
                .andExpect(jsonPath("$.content[1].id").value(employee4.getId()))
                .andExpect(jsonPath("$.content[1].name").value(employee4.getName()))
                .andExpect(jsonPath("$.content[1].age").value(employee4.getAge()))
                .andExpect(jsonPath("$.content[1].gender").value(employee4.getGender()))
                .andExpect(jsonPath("$.content[1].salary").value(employee4.getSalary()))
        ;
    }

    @Test
    void should_add_one_employee_when_post_given_employee_info() throws Exception {
        //given
        Employee newEmployee = employeeRepository.save(new Employee("vincent6", 18, "Male", 567890));
        //when
        ResultActions resultActions = mockMvc.perform(post(url).
                contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newEmployee)));
        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(newEmployee.getId() + 1))
                .andExpect(jsonPath("$.name").value(newEmployee.getName()))
                .andExpect(jsonPath("$.age").value(newEmployee.getAge()))
                .andExpect(jsonPath("$.gender").value(newEmployee.getGender()))
                .andExpect(jsonPath("$.salary").value(newEmployee.getSalary()))
        ;
    }

    @Test
    void should_update_one_employee_when_put_given_employee_info() throws Exception {
        //given
        Employee updatedEmployee = employee;
        updatedEmployee.setName("VincentLuk");
        String id = "/1";
        //when
        ResultActions resultActions = mockMvc.perform(put(url + id).
                contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedEmployee)));
        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(updatedEmployee.getId()))
                .andExpect(jsonPath("$.name").value(updatedEmployee.getName()))
                .andExpect(jsonPath("$.age").value(updatedEmployee.getAge()))
                .andExpect(jsonPath("$.gender").value(updatedEmployee.getGender()))
                .andExpect(jsonPath("$.salary").value(updatedEmployee.getSalary()))
        ;
    }

    @Test
    void should_delete_one_employee_when_delete_given_employee_id() throws Exception {
        //given
        String id = "/1";
        //when
        ResultActions resultActions = mockMvc.perform(delete(url + id));
        //then
        resultActions
                .andExpect(status().isNoContent())
        ;
    }

    @Test
    void should_delete_all_employee_when_delete_given_employees() throws Exception {
        //given
        //when
        ResultActions resultActions = mockMvc.perform(delete(url));
        //then
        resultActions
                .andExpect(status().isNoContent())
        ;
    }
}
