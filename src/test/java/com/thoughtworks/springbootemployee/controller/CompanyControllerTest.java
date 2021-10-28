package com.thoughtworks.springbootemployee.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

class CompanyControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeRepository companyRepository;

    @Autowired
    ObjectMapper objectMapper;

    void setUp() {

        companyRepository.deleteAll();

    }
    @Test
    void findAllCompanies() {
        //given
        companyRepository.save(1,"agileexlab");


        //when


        //then
    }

    @Test
    void findByCompanyId() {
    }

    @Test
    void createNewCompany() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void updateCompany() {
    }

    @Test
    void getByPage() {
    }
}