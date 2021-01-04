package com.example.demo.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmployeeExistInDepartment extends RuntimeException {
    public EmployeeExistInDepartment(Long id) {
        super("Department with id: " +id + " not deleted because you have employees in that department");
    }
}