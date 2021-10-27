package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.Employee;
import com.thoughtworks.springbootemployee.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    void should_return_all_employees_when_find_all_given_two_employees() throws Exception {
        //given
        Employee employee1 = new Employee("vincent", 18, "male", 12345);
        Employee employee2 = new Employee("vincent2", 18, "male", 12345);
        employeeRepository.createEmployee(employee1);
        employeeRepository.createEmployee(employee2);
        //when
        ResultActions resultActions = mockMvc.perform(get("/employees"));

        //then
        String expected = "[\n" +
                "    {\n" +
                "        \"id\": 1,\n" +
                "        \"name\": \"vincent\",\n" +
                "        \"age\": 18,\n" +
                "        \"gender\": \"male\",\n" +
                "        \"salary\": 12345\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 2,\n" +
                "        \"name\": \"vincent2\",\n" +
                "        \"age\": 18,\n" +
                "        \"gender\": \"male\",\n" +
                "        \"salary\": 12345\n" +
                "    }\n" +
                "]";
        resultActions.andExpect(status().isOk()).andExpect(content().json(expected));
    }

    @Test
    void should_return_one_employees_when_find_id_given_certain_id() throws Exception {
        //given
        Employee employee = new Employee("vincent", 18, "male", 12345);
        employeeRepository.createEmployee(employee);

        //when
        ResultActions resultActions = mockMvc.perform(get("/employees/1"));

        //then
        String expected =
                "    {\n" +
                "        \"id\": 1,\n" +
                "        \"name\": \"vincent\",\n" +
                "        \"age\": 18,\n" +
                "        \"gender\": \"male\",\n" +
                "        \"salary\": 12345\n" +
                "    },\n"
                ;
        resultActions.andExpect(status().isOk()).andExpect(content().json(expected));
    }

    @Test
    void should_return_male_employees_when_find_gender_given_one_male_employee_and_one_female_employee() throws Exception {
        //given
        Employee employee1 = new Employee("vincent", 18, "male", 12345);
        Employee employee2 = new Employee("vincent2", 18, "female", 12345);
        employeeRepository.createEmployee(employee1);
        employeeRepository.createEmployee(employee2);
        //when
        ResultActions resultActions = mockMvc.perform(get("/employees?gender=male"));

        //then
        String expected =
                "   [ {\n" +
                "        \"id\": 1,\n" +
                "        \"name\": \"vincent\",\n" +
                "        \"age\": 18,\n" +
                "        \"gender\": \"male\",\n" +
                "        \"salary\": 12345\n" +
                "    }]";
        resultActions.andExpect(status().isOk()).andExpect(content().json(expected));
    }
}
