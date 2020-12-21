package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Employee {
    private long id;
    private String name;
    private String job;
    private long departmentId;
    private String departmentName;
    private long teamId;
}
