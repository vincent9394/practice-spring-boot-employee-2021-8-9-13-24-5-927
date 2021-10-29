package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.dto.EmployeeRequest;
import com.thoughtworks.springbootemployee.dto.EmployeeResponse;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.mapper.EmployeeMapper;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<EmployeeResponse> findAllEmployees() {
        return this.employeeService.findAll().stream()
                .map(employee -> employeeMapper.toResponse(employee))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public EmployeeResponse findById(@PathVariable Integer id) {
        return  employeeMapper.toResponse(this.employeeService.findById(id));
    }

    @GetMapping(params = "gender")
    public List<Employee> findByGender(@RequestParam String gender) {
        return this.employeeService.findAllByGender(gender);
    }

    @GetMapping(params = {"page", "size"})
    public PageImpl<Employee> findByPageAndPageSize(@PageableDefault Pageable pageable) {
        return this.employeeService.findPagingEmployees(pageable);
    }


    @PostMapping
    public Employee createEmployee(@RequestBody EmployeeRequest employeeRequest) {
        return this.employeeService.createEmployee(employeeMapper.toEntity(employeeRequest));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Integer id) {
        this.employeeService.deleteEmployee(id);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAllEmployees() {
        this.employeeService.deleteAllEmployee();
    }

    @PutMapping("/{id}")
    public Employee editEmployee(@PathVariable Integer id, @RequestBody Employee updatedEmployee) {

        return this.employeeService.updateEmployee(id, updatedEmployee);
    }
}
