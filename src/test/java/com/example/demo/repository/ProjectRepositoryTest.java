package com.example.demo.repository;

import com.example.demo.entities.ProjectEntity;
import com.example.demo.exeption.EmployeeNotFoundException;
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

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ProjectRepositoryTest {
    @Autowired
    private ProjectRepository projectRepository;

    @Before
    public void saveProject() {
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId(1L);
        projectEntity.setName("Money");
        projectRepository.save(projectEntity);
    }

    private List<ProjectEntity> getProjectEntities() {
        return StreamSupport.stream(projectRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    private ProjectEntity getProjectEntity() {
        return projectRepository.findById(1L).orElseThrow(() -> new EmployeeNotFoundException(1L));
    }

    @Test
    public void findAllTeams() {
        List<ProjectEntity> projectEntities = getProjectEntities();

        assertEquals(projectEntities.size(), 1);
    }

    @Test
    public void findProjectById() {
        ProjectEntity projectEntity = getProjectEntity();

        assertEquals(projectEntity.getName(), "Money");
    }

    @Test
    public void updateProjectById() {
        ProjectEntity employeeEntity = getProjectEntity();
        employeeEntity.setName("Money111");
        projectRepository.save(employeeEntity);

        List<ProjectEntity> projectEntities = getProjectEntities();

        assertEquals(projectEntities.get(0).getName(), "Money111");
    }

    @Test
    public void deleteProjectById() {
        projectRepository.deleteById(1L);

        List<ProjectEntity> projectEntities = getProjectEntities();

        assertEquals(projectEntities.size(), 0);
    }
}