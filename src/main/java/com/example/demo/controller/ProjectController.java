package com.example.demo.controller;

import com.example.demo.dto.Project;
import com.example.demo.service.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProjectController {
    private final ProjectService service;

    @PostMapping("/project")
    Project saveProject(@RequestBody Project project) {
        return service.saveProject(project);
    }

    @GetMapping(value = "/projects", produces = APPLICATION_JSON_VALUE)
    List<Project> getAllProjects() {
        return service.getAllProjects();
    }

    @GetMapping(value = "/project/{id}", produces = APPLICATION_JSON_VALUE)
    Project getProjectById(@PathVariable String id) {
        return service.getProjectById(id);
    }

    @PutMapping("/project")
    Project updateProject(@RequestBody Project project) {
        return service.updateProject(project);
    }

    @DeleteMapping("/project/{id}")
    void deleteProject(@PathVariable String id) {
        service.deleteProject(id);
    }
}