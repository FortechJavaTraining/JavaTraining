package com.example.demo.controller;

import com.example.demo.dto.Project;
import com.example.demo.dto.Team;
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

    @GetMapping(value = "project/{id}/teams", produces = APPLICATION_JSON_VALUE)
    List<Team> getTeamsByProjectId(@PathVariable String id) {
        return service.getTeamsByProjectId(id);
    }

    @GetMapping(value = "projects/teams", produces = APPLICATION_JSON_VALUE)
    List<Project> getProjectsTeams() {
        return service.getTeamsAndProjects();
    }

    @PostMapping(value = "project/{id}/teams")
    void saveTeamsByProjectId(@PathVariable String id, @RequestBody List<Team> teamList) {
        service.saveTeamsByProjectId(id, teamList);
    }

    @PutMapping(value = "project/{id}/teams")
    void updateTeamsByProjectId(@PathVariable String id, @RequestBody List<Team> teamList) {
        service.updateTeamsByProjectId(id, teamList);
    }

    @DeleteMapping(value = "project/{id}/teams")
    void deleteTeamsByProjectId(@PathVariable String id, @RequestBody List<Team> teams) {
        service.deleteTeamsByProjectId(id, teams);
    }
}