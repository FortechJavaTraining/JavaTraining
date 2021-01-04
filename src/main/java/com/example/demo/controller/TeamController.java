package com.example.demo.controller;

import com.example.demo.dto.Project;
import com.example.demo.dto.Team;
import com.example.demo.service.TeamService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class TeamController {
    private final TeamService service;

    @PostMapping(value = "/team")
    Team saveTeam(@RequestBody Team team) {
        return service.saveTeam(team);
    }

    @GetMapping(value = "/teams", produces = APPLICATION_JSON_VALUE)
    List<Team> getAllTeams() {
        return service.getAllTeams();
    }

    @GetMapping(value = "/team/{id}", produces = APPLICATION_JSON_VALUE)
    Team getTeamById(@PathVariable Long id) {
        return service.getTeamById(id);
    }

    @PutMapping(value = "/team/{id}")
    Team updateTeam(@RequestBody Team team, @PathVariable Long id) {
        return service.updateTeam(team, id);
    }

    @DeleteMapping(value = "/team/{id}")
    Team deleteTeam(@PathVariable Long id) {
        return service.deleteTeam(id);
    }

    @GetMapping(value = "/team/project/{id}", produces = APPLICATION_JSON_VALUE)
    Project getProjectById(@PathVariable Long id) {
        return service.getProjectById(id);
    }
}