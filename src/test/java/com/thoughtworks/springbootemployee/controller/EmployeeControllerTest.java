package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
//        //given
        Employee employee1 = employeeRepository.save(new Employee("vincentAC1", 18, "male", 12345));
        Employee employee2 = employeeRepository.save(new Employee("vincentAC2", 18, "male", 12345));
//        //when
        ResultActions resultActions = mockMvc.perform(get("/employees"));
//        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(employee1.getId()))
                .andExpect(jsonPath("$[0].name").value(employee1.getName()))
                .andExpect(jsonPath("$[0].age").value(employee1.getAge()))
                .andExpect(jsonPath("$[0].gender").value(employee1.getGender()))
                .andExpect(jsonPath("$[0].salary").value(employee1.getSalary()))
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
        Employee employee = employeeRepository.save(new Employee("vincentAC2", 18, "male", 12345));
//        //when
        ResultActions resultActions = mockMvc.perform(get("/employees/" + employee.getId()));
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

//    @Test
//    void should_return_male_employees_when_find_gender_given_one_male_employee_and_one_female_employee() throws Exception {
//        //given
//        Employee employee1 = new Employee("vincentAC3", 18, "male", 12345);
//        Employee employee2 = new Employee("vincent2AC3", 18, "female", 12345);
//        employeeRepository.createEmployee(employee1);
//        employeeRepository.createEmployee(employee2);
//        //when
//        ResultActions resultActions = mockMvc.perform(get("/employees?gender=male"));
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
//
//
//    @Test
//    void should_return_employee_page_when_find_page_given_page_and_page_size() throws Exception {
//        //given
//        Employee employee1 = employeeRepository.createEmployee(new Employee("vincent1", 18, "male", 1000000));
//        Employee employee2 = employeeRepository.createEmployee(new Employee("vincent2", 18, "male", 1000000));
//        Employee employee3 = employeeRepository.createEmployee(new Employee("vincent3", 18, "male", 1000000));
//        //when
//        ResultActions resultActions = mockMvc.perform(get("/employees?page=1&size=2"));
//        //then
//        resultActions
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.content[0].id").value(employee3.getId()))
//                .andExpect(jsonPath("$.content[0].name").value(employee3.getName()))
//                .andExpect(jsonPath("$.content[0].age").value(employee3.getAge()))
//                .andExpect(jsonPath("$.content[0].gender").value(employee3.getGender()))
//                .andExpect(jsonPath("$.content[0].salary").value(employee3.getSalary()));
//
//    }
//
//    @Test
//    void should_add_one_employee_when_post_given_employee_info() throws Exception {
//        //given
////        Employee employee = new Employee("vincentAC4", 18, "male", 12345);
//        String employee = "{\n" +
//                "        \"name\": \"vincentAC4\",\n" +
//                "        \"age\": 18,\n" +
//                "        \"gender\": \"male\",\n" +
//                "        \"salary\": 12345\n" +
//                "    }";
////        employeeRepository.createEmployee(employee);
//        //when
//        ResultActions resultActions = mockMvc.perform(post("/employees").
//                contentType(MediaType.APPLICATION_JSON)
//                .content(employee));
//        //then
//        String expected =
//                "    {\n" +
//                        "        \"id\": 1,\n" +
//                        "        \"name\": \"vincentAC4\",\n" +
//                        "        \"age\": 18,\n" +
//                        "        \"gender\": \"male\",\n" +
//                        "        \"salary\": 12345\n" +
//                        "    }\n";
//        resultActions.andExpect(status().isOk()).andExpect(content().json(expected));
//    }
//
//    @Test
//    void should_update_one_employee_when_put_given_employee_info() throws Exception {
//        //given
//        Employee employee1 = new Employee("vincent2AC3", 18, "male", 12345);
//        employeeRepository.createEmployee(employee1);
//        String employee = "{\n" +
//                "        \"name\": \"vincentAC5\",\n" +
//                "        \"age\": 18,\n" +
//                "        \"gender\": \"male\",\n" +
//                "        \"salary\": 12345\n" +
//                "    }";
//        //when
//        ResultActions resultActions = mockMvc.perform(put("/employees/1").
//                contentType(MediaType.APPLICATION_JSON)
//                .content(employee));
//        //then
//        String expected =
//                "    {\n" +
//                        "        \"id\": 1,\n" +
//                        "        \"name\": \"vincentAC5\",\n" +
//                        "        \"age\": 18,\n" +
//                        "        \"gender\": \"male\",\n" +
//                        "        \"salary\": 12345\n" +
//                        "    }\n";
//        resultActions.andExpect(status().isOk()).andExpect(content().json(expected));
//    }
//
//    @Test
//    void should_delete_one_employee_when_put_given_employee_id() throws Exception {
//        //given
//        Employee employee1 = new Employee("vincent2AC3", 18, "male", 12345);
//        employeeRepository.createEmployee(employee1);
//        //when
//        ResultActions resultActions = mockMvc.perform(delete("/employees/1"));
//        //then
//        resultActions.andExpect(status().isNoContent());
//    }
}
