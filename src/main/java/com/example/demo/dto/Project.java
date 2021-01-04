package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Project {
    private String name;
    private String externalID;
    private List<Team> teamList;
}