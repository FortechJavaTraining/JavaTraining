package com.example.demo.service;

import com.example.demo.dto.Employee;
import com.example.demo.dto.Team;
import com.example.demo.entities.*;
import com.example.demo.dto.Project;
import com.example.demo.entities.ProjectEntity;
import com.example.demo.exeption.ProjectNotFoundException;
import com.example.demo.exeption.TeamNotFoundException;
import com.example.demo.repository.ProjectRepository;
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
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final TeamRepository teamRepository;

    public List<Team> getTeamsByProjectId(String id) {
        ProjectEntity projectEntity = getProjectEntityById(id);
        List<TeamEntity> teamEntityList = projectEntity.getTeamEntityList();
        return convertTeamEntityToTeam(teamEntityList);
    }

    private List<Team> convertTeamEntityToTeam(List<TeamEntity> teamEntityList) {
        List<Team> teamList = new ArrayList<>();
        for (TeamEntity teamEntity : teamEntityList) {
            Team team = new Team();
            team.setId(teamEntity.getId());
            if (teamEntity.getName() != null)
                team.setName(teamEntity.getName());
            if (teamEntity.getExternalId() != null)
                team.setExternalId(teamEntity.getExternalId());
            if (teamEntity.getTeamLead() != null)
                team.setTeamLead(teamEntity.getTeamLead().getId());
            team.setTeamMembers(convertTeamEntityToEmployee(teamEntity));
            teamList.add(team);
        }
        return teamList;
    }

    private List<Employee> convertTeamEntityToEmployee(TeamEntity teamEntity) {
        List<Employee> employees = new ArrayList<>();
        for (EmployeeEntity employeeEntity : teamEntity.getEmployeeEntityList()) {
            Employee employee = convertEntityToEmployee(employeeEntity);
            employees.add(employee);
        }
        return employees;
    }

    private ProjectEntity getProjectEntityById(String id) {
        return projectRepository.findByExternalID(id).orElseThrow(() -> new ProjectNotFoundException(id));
    }

    public List<Project> getTeamsAndProjects() {
        return StreamSupport.stream(projectRepository.findAll().spliterator(), false)
                .map(this::convertProjectEntityToProject).collect(Collectors.toList());
    }

    public Employee convertEntityToEmployee(EmployeeEntity employeeEntity) {
        Employee employee = new Employee();
        employee.setJob(employeeEntity.getJob());
        employee.setId(employeeEntity.getId());
        employee.setName(employeeEntity.getName());
        employee.setDepartmentId(employeeEntity.getDepartmentEntity().getId());
        employee.setDepartmentName(employeeEntity.getDepartmentEntity().getName());
        return employee;
    }

    public Project saveProject(Project project) {
        ProjectEntity projectEntity = new ProjectEntity();
        setProjectEntity(project, projectEntity);
        ProjectEntity projectEntity1 = projectRepository.save(projectEntity);
        return convertProjectEntityToProject(projectEntity1);
    }

    public List<Project> getAllProjects() {
        return StreamSupport.stream(projectRepository.findAll().spliterator(), false)
                .map(this::convertProjectEntityToProject).collect(Collectors.toList());
    }

    public Project getProjectById(String externalId) {
        ProjectEntity projectEntity = getProjectEntityById(externalId);
        return convertProjectEntityToProject(projectEntity);
    }

    public Project updateProject(Project project) {
        String externalId = project.getExternalID();
        ProjectEntity projectEntity = projectRepository.findByExternalID(externalId).orElseThrow(() -> new ProjectNotFoundException(externalId));
        projectEntity.setName(project.getName());
        ProjectEntity projectEntity1 = projectRepository.save(projectEntity);
        return convertProjectEntityToProject(projectEntity1);
    }

    public Project deleteProject(String externalId) {
        ProjectEntity projectEntity = projectRepository.findByExternalID(externalId).orElseThrow(() -> new ProjectNotFoundException(externalId));
        projectRepository.deleteById(projectEntity.getId());
        return convertProjectEntityToProject(projectEntity);
    }

    private void setProjectEntity(Project project, ProjectEntity projectEntity) {
        projectEntity.setName(project.getName());
    }

    private Project convertProjectEntityToProject(ProjectEntity projectEntity) {
        Project project = new Project();
        project.setName(projectEntity.getName());
        project.setExternalID(projectEntity.getExternalID());
        project.setTeamList(convertProjectEntityToTeam(projectEntity));
        return project;
    }

    private List<Team> convertProjectEntityToTeam(ProjectEntity projectEntity) {
        List<Team> teams = new ArrayList<>();
        for (TeamEntity teamEntity : projectEntity.getTeamEntityList()) {
            Team team = convertTeamEntityToTeam(teamEntity);
            teams.add(team);
        }
        return teams;
    }

    private Team convertTeamEntityToTeam(TeamEntity teamEntity) {
        Team team = new Team();
        team.setId(teamEntity.getId());
        if (teamEntity.getTeamLead() != null)
            team.setTeamLead(teamEntity.getTeamLead().getId());
        if (teamEntity.getExternalId() != null)
            team.setExternalId(teamEntity.getExternalId());
        if (teamEntity.getName() != null)
            team.setName(teamEntity.getName());
        team.setTeamMembers(convertTeamEntityToEmployee(teamEntity));
        return team;
    }

    public void deleteTeamsByProjectId(String id, List<Team> teamList) {
        for (Team team : teamList) {
            TeamEntity teamEntity = getTeamEntity(team.getId());
            teamEntity.setProjectEntity(null);
            teamRepository.save(teamEntity);
        }
    }

    private TeamEntity getTeamEntity(Long id) {
        return teamRepository.findById(id).orElseThrow(() -> new TeamNotFoundException(id));
    }

    public void saveTeamsByProjectId(String id, List<Team> teamList) {
        for (Team team : teamList) {
            TeamEntity teamEntity = getTeamEntity(team.getId());
            teamEntity.setProjectEntity(getProjectEntityById(id));
            teamRepository.save(teamEntity);
        }
    }

    public void updateTeamsByProjectId(String id, List<Team> teamList) {
        List<TeamEntity> teamEntityList = teamRepository.findByProjectEntity(projectRepository.findByExternalID(id));
        for (TeamEntity teamEntity : teamEntityList) {
            teamEntity.setProjectEntity(null);
        }
        for (Team team : teamList) {
            TeamEntity teamEntity = getTeamEntity(team.getId());
            teamEntity.setProjectEntity(getProjectEntityById(id));
            teamRepository.save(teamEntity);
        }
    }
}