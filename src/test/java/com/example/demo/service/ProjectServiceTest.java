package com.example.demo.service;

import com.example.demo.dto.Project;
import com.example.demo.entities.ProjectEntity;
import com.example.demo.repository.ProjectRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ProjectServiceTest {
    @InjectMocks
    private ProjectService service;
    @Mock
    private ProjectRepository projectRepository;

    @Test
    public void givenAnEntity_getProjectById_shouldReturnValidDto() {
        Project project = new Project();
        project.setExternalID("1AL");
        project.setName("Tzaneim");

        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId(1L);
        projectEntity.setExternalID("1AL");
        projectEntity.setName("Tzaneim");

//        when(projectRepository.findById(1L)).thenReturn(Optional.of(projectEntity));
        when(projectRepository.findByExternalID("1AL")).thenReturn(Optional.of(projectEntity));


        when(projectRepository.findByExternalID("1AL")).thenReturn(Optional.of(projectEntity));

        Project projectById = service.getProjectById("1AL");

        assertEquals(project.getExternalID(), projectById.getExternalID());
    }

    @Test
    public void givenAnEntity_saveProject_shouldReturnValidDto() {
        Project project = new Project();
        project.setName("Proj1");
        project.setExternalID("1L");

        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setName("Proj1");
        projectEntity.setExternalID("1L");

        when(projectRepository.save(Mockito.any(ProjectEntity.class))).thenReturn(projectEntity);

        Project project2 = service.saveProject(project);

        assertEquals(project.getName(), project2.getName());
    }

    @Test
    public void givenAnEntity_updateProject_shouldReturnValidDto() {
        Project project = new Project();
        project.setName("Proj1");
        project.setExternalID("1L");

        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setName("Proj1");
        projectEntity.setExternalID("1L");

        when(projectRepository.findByExternalID("1L")).thenReturn(Optional.of(projectEntity));
        when(projectRepository.save(Mockito.any(ProjectEntity.class))).thenReturn(projectEntity);

        Project project2 = service.updateProject(project);

        assertEquals(project.getName(), project2.getName());
    }

    @Test
    public void givenAnEntity_getAllProjects_shouldReturnValidDto() {
        Project project = new Project();
        project.setName("Proj1");
        project.setExternalID("2L");
        Project project1 = new Project();
        project1.setName("Proj1");
        project1.setExternalID("2L");
        List<Project> projects = new ArrayList<>();
        projects.add(project);
        projects.add(project1);

        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setName("Proj1");
        projectEntity.setExternalID("2L");
        ProjectEntity projectEntity1 = new ProjectEntity();
        projectEntity1.setName("Proj1");
        projectEntity1.setExternalID("2L");
        List<ProjectEntity> projectEntities = new ArrayList<>();
        projectEntities.add(projectEntity);
        projectEntities.add(projectEntity1);

        when(projectRepository.findAll()).thenReturn(projectEntities);

        List<Project> projects1 = service.getAllProjects();

        assertEquals(projects.get(0).getName(), projects1.get(0).getName());
    }

    @Test
    public void givenAnEntity_deleteProject_shouldReturnValidDto() {
        Project project = new Project();
        project.setExternalID("extID123");

        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setExternalID("extID123");

        when(projectRepository.findByExternalID("extID123")).thenReturn(Optional.of(projectEntity));

        Project projectByTd = service.deleteProject("extID123");

        assertEquals(project.getExternalID(), projectByTd.getExternalID());

        // verify(projectRepository).delete();
    }


}
