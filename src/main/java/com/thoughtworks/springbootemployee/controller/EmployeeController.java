package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.dto.EmployeeRequest;
import com.thoughtworks.springbootemployee.dto.EmployeeResponse;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.mapper.EmployeeMapper;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
        return this.employeeService.findAll()
                .stream()
                .map(employee -> employeeMapper.toResponse(employee))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public EmployeeResponse findById(@PathVariable Integer id) {
        return employeeMapper.toResponse(
                this.employeeService.findById(id));
    }

    @GetMapping(params = "gender")
    public List<EmployeeResponse> findByGender(@RequestParam String gender) {
        return this.employeeService.findAllByGender(gender)
                .stream()
                .map(employee -> employeeMapper.toResponse(employee))
                .collect(Collectors.toList());

    }

    @GetMapping(params = {"page", "size"})
    public PageImpl<EmployeeResponse> findByPageAndPageSize(@PageableDefault Pageable pageable) {
        Page<Employee> employees = employeeService.findPagingEmployees(pageable);
        List<EmployeeResponse> employeeResponses = employees.getContent().stream()
                .map(employee -> employeeMapper.toResponse(employee))
                .collect(Collectors.toList());
        return new PageImpl<>(employeeResponses, employees.getPageable(), employees.getTotalElements());

    }


    @PostMapping
    public EmployeeResponse createEmployee(@RequestBody EmployeeRequest employeeRequest) {
        Employee employee = employeeService.createEmployee(employeeMapper.toEntity(employeeRequest));
        return employeeMapper.toResponse(employee);
    }

    @PutMapping("/{id}")
    public EmployeeResponse editEmployee(@PathVariable Integer id, @RequestBody Employee updatedEmployee) {

        Employee employee = employeeService.updateEmployee(id, updatedEmployee);
        return employeeMapper.toResponse(employee);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public EmployeeResponse deleteById(@PathVariable Integer id) {
        return employeeMapper.toResponse(employeeService.deleteEmployee(id));
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public List<EmployeeResponse> deleteAllEmployees() {
        return this.employeeService.deleteAllEmployee().stream()
                .map(employee -> employeeMapper.toResponse(employee))
                .collect(Collectors.toList());
    }

}
