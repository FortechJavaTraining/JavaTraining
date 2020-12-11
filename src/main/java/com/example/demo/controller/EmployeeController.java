package com.example.demo.controller;

import com.example.demo.dto.Employee;
import com.example.demo.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeeController {
    private final EmployeeService service;

    @PostMapping(value = "/employee")
    Employee saveEmployee(@RequestBody Employee employee) {
        return service.saveEmployee(employee);
    }

    @GetMapping(value = "/employees", produces = APPLICATION_JSON_VALUE)
    List<Employee> getAllEmployees() {
        return service.getAllEmployees();
    }

    @GetMapping(value = "/employee/{id}", produces = APPLICATION_JSON_VALUE)
    Employee getEmployeeById(@PathVariable Long id) {
        return service.getEmployeeById(id);
    }

    @PutMapping(value = "/employee/{id}")
    Employee updateEmployee(@RequestBody Employee employee, @PathVariable Long id) {
        return service.updateEmployee(employee, id);
    }

    @DeleteMapping(value = "/employee/{id}")
    Employee deleteEmployee(@PathVariable Long id) {
        return service.deleteEmployee(id);
    }
}
