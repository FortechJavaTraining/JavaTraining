package com.example.demo.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserAlreadyInUse extends RuntimeException {
    public UserAlreadyInUse(String userName) {
       super("User with name " + userName + " is already in use.You need a new User to create a Employee");

    }

}
