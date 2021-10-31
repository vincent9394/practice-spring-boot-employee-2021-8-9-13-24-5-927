package com.thoughtworks.springbootemployee.mapper;

import com.thoughtworks.springbootemployee.dto.CompanyRequest;
import com.thoughtworks.springbootemployee.dto.CompanyResponse;
import com.thoughtworks.springbootemployee.dto.EmployeeRequest;
import com.thoughtworks.springbootemployee.dto.EmployeeResponse;
import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {
    public Company toEntity(CompanyRequest companyRequest){
        Company company = new Company();

        BeanUtils.copyProperties(companyRequest, company);

        return company;
    }

//    public CompanyResponse toResponse(Company company){
//        CompanyResponse companyResponse = new CompanyResponse();
//
//        companyResponse.setCompanyName();
//        companyResponse.setName(company.getName());
//        companyResponse.setEmployees(company.getEmployess().stream().map(employee -> cmployeeMapper.toResponse));
//
//        return companyResponse;
//    }
}
