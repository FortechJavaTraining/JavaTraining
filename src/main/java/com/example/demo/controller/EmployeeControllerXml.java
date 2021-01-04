package com.example.demo.controller;

import com.example.demo.dto.Employee;
import com.example.demo.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeeControllerXml {
    private final EmployeeService service;

    @GetMapping(value = "/employeesx", produces = MediaType.APPLICATION_XML_VALUE)
    List<Employee> getAllEmployees() {
        return service.getAllEmployees();
    }

    @GetMapping(value = "/employeex/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    Employee getEmployeeById(@PathVariable Long id) {
        return service.getEmployeeById(id);
    }

    @PutMapping(value = "/employeex/{id}", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    Employee updateEmployee(@RequestBody Employee employee, @PathVariable Long id) {
        return service.updateEmployee(employee, id);
    }

    @DeleteMapping("/employeex/{id}")
    Employee deleteEmployee(@PathVariable Long id) {
        return service.deleteEmployee(id);
    }
}
