package com.thoughtworks.springbootemployee.advice;

import com.thoughtworks.springbootemployee.exception.CompanyNotFoundException;
import com.thoughtworks.springbootemployee.exception.EmployeeNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvice {
    @ExceptionHandler({CompanyNotFoundException.class, EmployeeNotFoundException.class})
    public ErrorResponse notFoundExceptionHandle(Exception exception){
        return new ErrorResponse(010, "Global Advice Something Not Found");
    }
}
