package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.Employee;
import com.thoughtworks.springbootemployee.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeRepository employeeRepository;

    @BeforeEach
    void setUp() {
        employeeRepository.deleteAll();
    }

    @Test
    void should_return_all_employees_when_find_all_given_two_employees() throws Exception {
        //given
        Employee employee1 = new Employee("vincentAC1", 18, "male", 12345);
        Employee employee2 = new Employee("vincent2AC1", 18, "male", 12345);
        employeeRepository.createEmployee(employee1);
        employeeRepository.createEmployee(employee2);
        //when
        ResultActions resultActions = mockMvc.perform(get("/employees"));

        //then
        String expected = "[\n" +
                "    {\n" +
                "        \"id\": 1,\n" +
                "        \"name\": \"vincentAC1\",\n" +
                "        \"age\": 18,\n" +
                "        \"gender\": \"male\",\n" +
                "        \"salary\": 12345\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 2,\n" +
                "        \"name\": \"vincent2AC1\",\n" +
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
        Employee employee = new Employee("vincentAC2", 18, "male", 12345);
        employeeRepository.createEmployee(employee);

        //when
        ResultActions resultActions = mockMvc.perform(get("/employees/1"));

        //then
        String expected =
                "    {\n" +
                        "        \"id\": 1,\n" +
                        "        \"name\": \"vincentAC2\",\n" +
                        "        \"age\": 18,\n" +
                        "        \"gender\": \"male\",\n" +
                        "        \"salary\": 12345\n" +
                        "    }\n";
        resultActions.andExpect(status().isOk()).andExpect(content().json(expected));
    }

    @Test
    void should_return_male_employees_when_find_gender_given_one_male_employee_and_one_female_employee() throws Exception {
        //given
        Employee employee1 = new Employee("vincentAC3", 18, "male", 12345);
        Employee employee2 = new Employee("vincent2AC3", 18, "female", 12345);
        employeeRepository.createEmployee(employee1);
        employeeRepository.createEmployee(employee2);
        //when
        ResultActions resultActions = mockMvc.perform(get("/employees?gender=male"));

        //then
        String expected =
                "   [ {\n" +
                        "        \"id\": 1,\n" +
                        "        \"name\": \"vincentAC3\",\n" +
                        "        \"age\": 18,\n" +
                        "        \"gender\": \"male\",\n" +
                        "        \"salary\": 12345\n" +
                        "    }]";
        resultActions.andExpect(status().isOk()).andExpect(content().json(expected));
    }

    //    @Test
//    void should_return_employee_page_when_find_page_given_page_and_page_size() throws Exception {
//        //given
//        Employee employee = new Employee("vincentAC3", 18, "male", 12345);
//        employeeRepository.createEmployee(employee);
//        //when
//        ResultActions resultActions = mockMvc.perform(get("/employees?page=1&pageSize=5"));
//
//        //then
//        String expected =
//                "   [ {\n" +
//                        "        \"id\": 1,\n" +
//                        "        \"name\": \"vincentAC3\",\n" +
//                        "        \"age\": 18,\n" +
//                        "        \"gender\": \"male\",\n" +
//                        "        \"salary\": 12345\n" +
//                        "    }]";
//        resultActions.andExpect(status().isOk()).andExpect(content().json(expected));
//    }
    @Test
    void should_add_one_employee_when_post_given_employee_info() throws Exception {
        //given
//        Employee employee = new Employee("vincentAC4", 18, "male", 12345);
        String employee = "{\n" +
                "        \"name\": \"vincentAC4\",\n" +
                "        \"age\": 18,\n" +
                "        \"gender\": \"male\",\n" +
                "        \"salary\": 12345\n" +
                "    }";
//        employeeRepository.createEmployee(employee);

        //when
        ResultActions resultActions = mockMvc.perform(post("/employees").
                contentType(MediaType.APPLICATION_JSON)
                .content(employee));

        //then
        String expected =
                "    {\n" +
                        "        \"id\": 1,\n" +
                        "        \"name\": \"vincentAC4\",\n" +
                        "        \"age\": 18,\n" +
                        "        \"gender\": \"male\",\n" +
                        "        \"salary\": 12345\n" +
                        "    }\n";
        resultActions.andExpect(status().isOk()).andExpect(content().json(expected));
    }

    @Test
    void should_update_one_employee_when_put_given_employee_info() throws Exception {
        //given
        Employee employee1 = new Employee("vincent2AC3", 18, "male", 12345);
        employeeRepository.createEmployee(employee1);
        String employee = "{\n" +
                "        \"name\": \"vincentAC5\",\n" +
                "        \"age\": 18,\n" +
                "        \"gender\": \"male\",\n" +
                "        \"salary\": 12345\n" +
                "    }";
//        employeeRepository.createEmployee(employee);

        //when
        ResultActions resultActions = mockMvc.perform(put("/employees/1").
                contentType(MediaType.APPLICATION_JSON)
                .content(employee));

        //then
        String expected =
                "    {\n" +
                        "        \"id\": 1,\n" +
                        "        \"name\": \"vincentAC5\",\n" +
                        "        \"age\": 18,\n" +
                        "        \"gender\": \"male\",\n" +
                        "        \"salary\": 12345\n" +
                        "    }\n";
        resultActions.andExpect(status().isOk()).andExpect(content().json(expected));
    }

    @Test
    void should_delete_one_employee_when_put_given_employee_id() throws Exception {
        //given
        Employee employee1 = new Employee("vincent2AC3", 18, "male", 12345);
        employeeRepository.createEmployee(employee1);


        //when
        ResultActions resultActions = mockMvc.perform(delete("/employees/1"));

        //then

        resultActions.andExpect(status().isNoContent());
    }
}
