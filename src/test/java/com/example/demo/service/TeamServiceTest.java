package com.example.demo.service;

import com.example.demo.dto.Employee;
import com.example.demo.dto.PageRequestDto;
import com.example.demo.dto.Team;
import com.example.demo.entities.DepartmentEntity;
import com.example.demo.entities.EmployeeEntity;
import com.example.demo.entities.TeamEntity;
import com.example.demo.repository.TeamRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class TeamServiceTest {
    @InjectMocks
    private TeamService service;
    @Mock
    private TeamRepository teamRepository;

    @Test
    public void givenAnEntity_getTeamById_shouldReturnValidDto() {
        Team team = new Team();
        team.setId(1L);

        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setId(1L);

        when(teamRepository.findById(1L)).thenReturn(Optional.of(teamEntity));

        Team teamById = service.getTeamById(1L);

        assertEquals(team.getId(), teamById.getId());
    }

    @Test
    public void givenAnEntity_saveTeam_shouldReturnValidDto() {
        Team team = new Team();
        team.setName("Norbeee");
        team.setId(1L);

        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setName("Norbeee");
        teamEntity.setId(1L);

        when(teamRepository.save(Mockito.any(TeamEntity.class))).thenReturn(teamEntity);

        Team team2 = service.saveTeam(team);

        assertEquals(team.getName(), team2.getName());
    }

    @Test
    public void givenAnEntity_updateTeam_shouldReturnValidDto() {
        Team team = new Team();
        team.setName("Norbeee");
        team.setId(1L);

        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setName("Norbeee");
        teamEntity.setId(1L);

        when(teamRepository.findById(1L)).thenReturn(Optional.of(teamEntity));
        when(teamRepository.save(Mockito.any(TeamEntity.class))).thenReturn(teamEntity);

        Team team2 = service.updateTeam(team, 1L);

        assertEquals(team.getName(), team2.getName());
    }

    @Test
    public void givenAnEntity_getAllTeams_shouldReturnValidDto() {
        Team team = new Team();
        team.setName("Norbeee");
        team.setId(2L);
        Team team1 = new Team();
        team1.setName("Norbeee");
        team1.setId(2L);
        List<Team> teams = new ArrayList<>();
        teams.add(team);
        teams.add(team1);

        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setName("Norbeee");
        teamEntity.setId(2L);
        TeamEntity teamEntity1 = new TeamEntity();
        teamEntity1.setName("Norbeee");
        teamEntity1.setId(2L);
        List<TeamEntity> teamEntities = new ArrayList<>();
        teamEntities.add(teamEntity);
        teamEntities.add(teamEntity1);

        when(teamRepository.findAll()).thenReturn(teamEntities);

        List<Team> teams1 = service.getAllTeams();

        assertEquals(teams.get(0).getName(), teams1.get(0).getName());
    }

    @Test
    public void givenAnEntity_deleteTeam_shouldReturnValidDto() {
        Team team = new Team();
        team.setId(1L);

        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setId(1L);

        when(teamRepository.findById(1L)).thenReturn(Optional.of(teamEntity));

        Team teamByTd = service.deleteTeam(1L);

        assertEquals(team.getId(), teamByTd.getId());

        verify(teamRepository).deleteById(1L);
    }

    @Test
    public void givenAnEntity_convertEntityToEmployee_shouldReturnValidDto() {
        Employee employee = new Employee();
        employee.setId(1L);

        DepartmentEntity departmentEntity = new DepartmentEntity();
        departmentEntity.setId(1L);

        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setId(1L);
        employeeEntity.setDepartmentEntity(departmentEntity);

        Employee employee1 = service.convertEntityToEmployee(employeeEntity);

        assertEquals(employee1.getId(), employee.getId());
    }

    @Test
    public void givenAnEntity_getAllTeamsPage_shouldReturnValidDto() {
        Team team = new Team();
        team.setName("Norbeee");
        team.setId(2L);

        Team team1 = new Team();
        team1.setName("Norbeee");
        team1.setId(2L);

        List<Team> teams = new ArrayList<>();
        teams.add(team);
        teams.add(team1);

        Page<Team> teamPage = new PageImpl<>(teams);
        PageRequestDto pageRequestDto = new PageRequestDto();
        pageRequestDto.setPageNumber(1);
        pageRequestDto.setPageSize(2);

        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setName("Norbeee");
        teamEntity.setId(2L);

        TeamEntity teamEntity1 = new TeamEntity();
        teamEntity1.setName("Norbeee");
        teamEntity1.setId(2L);

        List<TeamEntity> teamEntities = new ArrayList<>();
        teamEntities.add(teamEntity);
        teamEntities.add(teamEntity1);

        Page<TeamEntity> teamEntityPage = new PageImpl<>(teamEntities);

        when(teamRepository.findAllByNameContainsOrExternalIdContains(team.getName(), team.getExternalId(), PageRequest.of(pageRequestDto.getPageNumber(), pageRequestDto.getPageSize())))
                .thenReturn(teamEntityPage);

        Page<Team> allTeamsPage = service.getAllTeamsPage(pageRequestDto, team.getName(), team.getExternalId());

        assertEquals(teamPage.getNumberOfElements(), allTeamsPage.getNumberOfElements());

    }
}