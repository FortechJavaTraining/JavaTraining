package com.example.demo.service;

import com.example.demo.dto.Employee;
import com.example.demo.dto.Team;
import com.example.demo.entities.EmployeeEntity;
import com.example.demo.entities.TeamEntity;
import com.example.demo.exeption.EmployeeNotFoundException;
import com.example.demo.exeption.TeamNotFoundException;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.TeamRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class TeamService {
    private final TeamRepository teamRepository;
    private final EmployeeRepository employeeRepository;

    public Team saveTeam(Team team) {
        TeamEntity teamEntity = new TeamEntity();
        setTeamEntity(team, teamEntity);
        TeamEntity teamEntity1 = teamRepository.save(teamEntity);
        return convertTeamEntityToTeam(teamEntity1);
    }

    public List<Team> getAllTeams() {
        return StreamSupport.stream(teamRepository.findAll().spliterator(), false)
                .map(this::convertTeamEntityToTeam).collect(Collectors.toList());
    }

    public Team getTeamById(Long id) {
        TeamEntity teamEntity = getTeamEntity(id);
        return convertTeamEntityToTeam(teamEntity);
    }

    public Team updateTeam(Team team, Long id) {
        TeamEntity teamEntity = setTeamEntityDetails(team, id);
        teamRepository.save(teamEntity);
        return convertTeamEntityToTeam(teamEntity);
    }

    public Team deleteTeam(Long id) {
        TeamEntity teamEntity = getTeamEntity(id);
        Team team = convertTeamEntityToTeam(teamEntity);
        teamRepository.deleteById(id);
        return team;
    }

    private Team convertTeamEntityToTeam(TeamEntity teamEntity) {
        Team team = new Team();
        team.setId(teamEntity.getId());
        if (teamEntity.getName() != null)
            team.setName(teamEntity.getName());
        if (teamEntity.getTeamLead() != null)
            team.setTeamLead(teamEntity.getTeamLead().getId());
        if (teamEntity.getExternalId() != null)
            team.setExternalId(teamEntity.getExternalId());
        team.setTeamMembers(convertTeamEntityToEmployee(teamEntity));
        return team;
    }

    private List<Employee> convertTeamEntityToEmployee(TeamEntity teamEntity) {
        List<Employee> employees = new ArrayList<>();
        for (EmployeeEntity employeeEntity : teamEntity.getEmployeeEntityList()) {
            Employee employee = convertEntityToEmployee(employeeEntity);
            employees.add(employee);
        }
        return employees;
    }

    public Employee convertEntityToEmployee(EmployeeEntity employeeEntity) {
        Employee employee = new Employee();
        employee.setId(employeeEntity.getId());
        employee.setJob(employeeEntity.getJob());
        employee.setName(employeeEntity.getName());
        employee.setDepartmentId(employeeEntity.getDepartmentEntity().getId());
        employee.setDepartmentName(employeeEntity.getDepartmentEntity().getName());
        return employee;
    }

    private TeamEntity setTeamEntityDetails(Team team, Long id) {
        TeamEntity teamEntity = getTeamEntity(id);
        setTeamEntity(team, teamEntity);
        return teamEntity;
    }

    private void setTeamEntity(Team team, TeamEntity teamEntity) {
        if (team.getId() != 0)
            teamEntity.setId(team.getId());
        if (team.getName() != null)
            teamEntity.setName(team.getName());
        if (team.getExternalId() != null)
            teamEntity.setExternalId(team.getExternalId());
        if (team.getTeamLead() != 0)
            teamEntity.setTeamLead(getEmployeeEntity(team.getTeamLead()));
    }

    private TeamEntity getTeamEntity(Long id) {
        return teamRepository.findById(id).orElseThrow(() -> new TeamNotFoundException(id));
    }

    private EmployeeEntity getEmployeeEntity(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
    }
}