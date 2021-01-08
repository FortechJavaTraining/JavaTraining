package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Employee {
    private long id;
    private String name;
    private String job;
    private long departmentId;
    private String departmentName;
    private long teamLead;
    private long teamId;
    private String teamName;
    private List<Employee> subordinates = new ArrayList<>();
}