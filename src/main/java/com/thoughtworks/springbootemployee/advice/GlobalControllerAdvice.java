package com.thoughtworks.springbootemployee.advice;

import com.thoughtworks.springbootemployee.exception.CompanyNotFoundException;
import com.thoughtworks.springbootemployee.exception.EmployeeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler({
            CompanyNotFoundException.class,
            EmployeeNotFoundException.class
    })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse notFoundExceptionHandle(Exception exception){
        return new ErrorResponse(999, "Global Advice Something Not Found");
    }
}
