package com.example.demo.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TeamNotFoundException extends RuntimeException {
    public TeamNotFoundException(Long id) {
        System.out.println("Team with id " + id + "not found");
    }
}