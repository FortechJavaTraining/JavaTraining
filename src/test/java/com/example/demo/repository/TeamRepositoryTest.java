package com.example.demo.repository;

import com.example.demo.entities.DepartmentEntity;
import com.example.demo.entities.TeamEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class TeamRepositoryTest {
    @Autowired
    private TeamRepository teamRepository;

    @Before
    public void saveTeam() {
        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setName("Norbeboy");

        teamRepository.save(teamEntity);
    }

    private List<TeamEntity> getTeamEntities() {
        return StreamSupport.stream(teamRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    private TeamEntity getTeamEntity() {
        return teamRepository.findById(1L).get();
    }

    @Test
    public void findAllTeams() {
        List<TeamEntity> teamEntities = getTeamEntities();

        assertEquals(teamEntities.size(), 1);
    }

    @Test
    public void findTeamById() {
        TeamEntity teamEntity = getTeamEntity();

        assertEquals(teamEntity.getName(), "Norbeboy");
    }

    @Test
    public void updateTeamById() {
        TeamEntity teamEntity = getTeamEntity();
        teamEntity.setName("Norbeboy111");
        teamRepository.save(teamEntity);

        List<TeamEntity> teamEntities = getTeamEntities();

        assertEquals(teamEntities.get(0).getName(), "Norbeboy111");
    }

    @Test
    public void deleteTeamById() {
        teamRepository.deleteById(1L);

        List<TeamEntity> teamEntities = getTeamEntities();

        assertEquals(teamEntities.size(), 0);
    }
}