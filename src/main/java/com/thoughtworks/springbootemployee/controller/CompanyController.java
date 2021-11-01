package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public List<Company> findAllCompanies() {
        return this.companyService.findAll();
    }

    @GetMapping("/{id}")
    public Company findByCompanyId(@PathVariable Integer id) {
        return this.companyService.findById(id);
    }

    @GetMapping("/{id}/employees")
    public Company findAllEmployessBelongsToCompany(@PathVariable("id") Integer id) {
        return (Company) this.companyService.findById(id).getEmployees();
    }

    @PostMapping
    public Company createNewCompany(@RequestBody Company company) {
        return this.companyService.createCompany(company);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Integer id) {
        this.companyService.deleteById(id);
    }

    @PutMapping("/{id}")
    public Company updateCompany(@PathVariable Integer id, @RequestBody Company updateEmployee) {
        Company originEmployee = this.companyService.findById(id);
        return this.companyService.save(id, updateEmployee);
    }

    @GetMapping(params = {"page", "size"})
    public List<Company> getByPage(@RequestParam Long page,
                                   @RequestParam Long size) {
        return companyService.findPage(page, size);
    }
}
