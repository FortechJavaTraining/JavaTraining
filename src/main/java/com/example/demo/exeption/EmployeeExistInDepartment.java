package com.example.demo.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmployeeExistInDepartment extends RuntimeException {
    public EmployeeExistInDepartment(Long id) {
        System.out.println("Department with id: " +id + " not deleted because you have employees in that department");
    }
}