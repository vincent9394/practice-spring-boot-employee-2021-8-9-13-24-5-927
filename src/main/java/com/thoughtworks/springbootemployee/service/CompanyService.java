package com.thoughtworks.springbootemployee.service;
import com.thoughtworks.springbootemployee.exception.EmployeeNotFoundException;
import com.thoughtworks.springbootemployee.exception.CompanyNotFoundException;
import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    private CompanyRepository companyRepository;


    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> findAll(){
        return this.companyRepository.findAll();
    }

    public Company findByCId(Integer id){
        return this.companyRepository.findById(id).orElseThrow(CompanyNotFoundException::new);
    }

    public Company findById(Integer id){
        return null;
    }

    public Company createCompany(Company company){
        return this.companyRepository.save(company);
    }

    public Company save(Integer id, Company updateCompany){
        return this.companyRepository.save(updateCompany);
    }

    public void deleteById(Integer id){
        this.companyRepository.deleteById(id);
    }
    public List<Company> findPage(Long page, Long size){
        return this.companyRepository.findAll();
    }



}

