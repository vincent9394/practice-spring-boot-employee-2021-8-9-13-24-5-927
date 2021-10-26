package com.thoughtworks.springbootemployee;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException() {
        super("Employee Not Found");
    }
}
