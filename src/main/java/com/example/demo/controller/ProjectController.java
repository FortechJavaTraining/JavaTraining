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

    //requires only the name in the body of the request
    @PostMapping("/project")
    Project saveProject(@RequestBody Project project) {
        return service.saveProject(project);
    }

    @GetMapping(value = "/projects", produces = APPLICATION_JSON_VALUE)
    List<Project> getAllProjects() {
        return service.getAllProjects();
    }

    //requires externalId in the path
    @GetMapping(value = "/project/{id}", produces = APPLICATION_JSON_VALUE)
    Project getProjectById(@PathVariable String id) {
        return service.getProjectById(id);
    }

    //requires name and externalId in the body
    @PutMapping("/project")
    Project updateProject(@RequestBody Project project) {
        return service.updateProject(project);
    }

    //requires externalId in the path
    @DeleteMapping("/project/{id}")
    void deleteProject(@PathVariable String id) {
        service.deleteProject(id);
    }
}