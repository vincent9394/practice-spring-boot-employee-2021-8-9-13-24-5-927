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

    @OneToMany(mappedBy = "companyId")
    private List<Employee> employees;

    public Company() {

    }

    public Company(String name) {
        this.companyId = null;
        this.companyName = name;
        this.employees = new ArrayList<>();
    }

    public Company(String companyName, List<Employee> employeeList) {
        this.companyId = null;
        this.companyName = companyName;
        this.employees = employeeList;
    }


    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public List<Employee> getEmployees() {

        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }


    public void addEmployee(Employee employee) {
        employee.setCompanyId(this.companyId);
    }

    public void setAllEmployeeCompanyId() {
        for (int i = 0; i < employees.size(); i++) {
            employees.get(i).setCompanyId(companyId);
        }
    }


    public void addEmployeeToCompany(List<Employee> employees) {
        this.employees.addAll(employees);
    }

    public void updateData (Company company){
        this.companyName = company.companyName;
        this.employees = company.employees;
    }
}
