package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Team {
    private long id;
    private String name;
    private String externalId;
    private long teamLead;
    private List<Employee> teamMembers;
}