package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
public class TeamLeadDto {
    private long teamLeadId;
    private List<Long>employees;
}
