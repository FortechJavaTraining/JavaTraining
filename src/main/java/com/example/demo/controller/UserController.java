package com.example.demo.controller;

import com.example.demo.dto.Employee;
import com.example.demo.dto.User;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
    private final UserService service;

    @PostMapping(value = "/sign-up")
    String saveEmployee(@RequestBody User user) {
        return service.saveUser(user);
    }
}
