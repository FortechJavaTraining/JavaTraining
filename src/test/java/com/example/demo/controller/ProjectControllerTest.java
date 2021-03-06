package com.example.demo.controller;

import com.example.demo.dto.Project;
import com.example.demo.exeption.ProjectNotFoundException;
import com.example.demo.service.ProjectService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class ProjectControllerTest {

    private static final String PATH = "/project";
    private MockMvc mockMvc;
    private String requestJson;
    private Project project;
    @InjectMocks
    private ProjectController projectController;
    @Mock
    private ProjectService service;

    @Before
    public void setUp() throws Exception {
        File file = ResourceUtils.getFile("classpath:model/Project.json");
        mockMvc = MockMvcBuilders.standaloneSetup(projectController).build();
        ObjectMapper objectMapper = new ObjectMapper();
        Project projectMapper = objectMapper.readValue(file, Project.class);
        requestJson = objectMapper.writeValueAsString(projectMapper);
        project = new Project();
    }

    @Test
    public void saveProject_expectSuccess() throws Exception {
        mockMvc.perform(post(PATH)
                .contentType(APPLICATION_JSON)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void getAllProjects_expectSuccess() throws Exception {
        List<Project> projects = new ArrayList<>();
        projects.add(new Project());

        when(service.getAllProjects()).thenReturn(projects);

        mockMvc.perform(get(PATH + "s")
                .characterEncoding("UTF-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andReturn();
    }

    @Test
    public void getProjectById_expectSuccess() throws Exception {
        when(service.getProjectById("abc")).thenReturn(project);

        mockMvc.perform(get(PATH +"/abc")
                .characterEncoding("UTF-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andReturn();
    }

    @Test
    public void getProjectById_expectException() throws Exception {
        when(service.getProjectById("abc")).thenThrow(new ProjectNotFoundException("abc"));

        mockMvc.perform(get(PATH + "/abc")
                .characterEncoding("UTF-8"))
                .andDo(print())
                .andReturn();
    }

    @Test
    public void updateProject_expectSuccess() throws Exception {
        this.mockMvc
                .perform(put(PATH )
                        .contentType(APPLICATION_JSON)
                        .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void deleteProject_expectSuccess() throws Exception {
        this.mockMvc
                .perform(delete(PATH + "/abc")
                        .contentType(APPLICATION_JSON)
                        .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }
}
