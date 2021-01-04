package com.example.demo.controller;

import com.example.demo.dto.Employee;
import com.example.demo.dto.PageRequestDto;
import com.example.demo.dto.TeamLeadDto;
import com.example.demo.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeeController {
    private final EmployeeService service;


    @GetMapping(value = "/employees", produces = APPLICATION_JSON_VALUE)
    List<Employee> getAllEmployees() {
        return service.getAllEmployees();
    }

    @PostMapping(value = "employees/Page/{name}/{job}", produces = APPLICATION_JSON_VALUE)
    Page<Employee> getAllEmployeesPages(@RequestBody PageRequestDto pageRequestDto, @PathVariable String name, @PathVariable String job) {
        return service.getAllEmployeesPages(pageRequestDto, name, job);
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

    @PutMapping(value = "/employees/teamLead")
    void updateEmployeeTeamLead(@RequestBody TeamLeadDto teamLeadDto) {
        service.updateEmployeeTeamLead(teamLeadDto);
    }

    @DeleteMapping(value = "/employee/teamLeadId")
    void deleteTeamLeadId(@RequestBody List<Long> employeeId) {
        service.deleteTeamLeadId(employeeId);
    }
}
