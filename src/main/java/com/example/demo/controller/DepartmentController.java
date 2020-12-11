package com.example.demo.controller;

import com.example.demo.dto.Department;
import com.example.demo.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DepartmentController {
    private final DepartmentService service;

    @PostMapping("/department")
    Department saveDepartment(@RequestBody Department department) {
        return service.saveDepartment(department);
    }

    @GetMapping(value = "/departments", produces = APPLICATION_JSON_VALUE)
    List<Department> getAllDepartments() {
        return service.getAllDepartments();
    }

    @GetMapping(value = "/department/{id}", produces = APPLICATION_JSON_VALUE)
    Department getDepartmentById(@PathVariable long id) {
        return service.getDepartmentById(id);
    }

    @PutMapping("/department/{id}")
    Department updateDepartment(@PathVariable long id, @RequestBody Department department) {
        return service.updateDepartment(department, id);
    }

    @DeleteMapping("/department/{id}")
    Department deleteDepartment(@PathVariable long id) {
        return service.deleteDepartment(id);
    }
}