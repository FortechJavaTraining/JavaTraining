package com.example.demo.service;

import com.example.demo.dto.Project;
import com.example.demo.entities.ProjectEntity;
import com.example.demo.exeption.ProjectNotFoundException;
import com.example.demo.repository.ProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProjectService {
    private final ProjectRepository projectRepository;

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
        ProjectEntity projectEntity = projectRepository.findByExternalID(externalId).orElseThrow(() -> new ProjectNotFoundException(externalId));
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
        return project;
    }
}
