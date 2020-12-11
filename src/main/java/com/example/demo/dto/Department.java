package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Department {
    private long id;
    private String name;
    private List<Employee> employeeList;
}
