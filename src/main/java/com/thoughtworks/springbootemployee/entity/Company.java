package com.thoughtworks.springbootemployee.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer companyId;
    private String companyName;

    @OneToMany(mappedBy = "company_id")
    private List<Employee> employees;

    public Company() {

    }

    public Company(Integer companyId, String companyName) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.employees = new ArrayList<>();
    }

    public void addEmployeeToCompany(List<Employee> employees) {
        this.employees.addAll(employees);
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
