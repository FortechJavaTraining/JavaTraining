package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Team {
    private long id;
    private String name;
    private long ext_id;
    private long team_lead;
    private List<Employee> team_members;
}
