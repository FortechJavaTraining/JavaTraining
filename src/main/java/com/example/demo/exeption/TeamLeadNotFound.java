package com.example.demo.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TeamLeadNotFound extends RuntimeException{
    public TeamLeadNotFound(Long id) { System.out.println("Employee with id " + id + " is not a teamLead."); }
}
