package com.example.demo.controller;

import com.example.demo.dto.PageRequestDto;
import com.example.demo.dto.Team;
import com.example.demo.exeption.TeamNotFoundException;
import com.example.demo.service.TeamService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class TeamControllerTest {
    private static final String PATH = "/team";
    private MockMvc mockMvc;
    private String requestJson;
    private Team team;
    @InjectMocks
    private TeamController teamController;
    @Mock
    private TeamService service;

    @Before
    public void setUp() throws Exception {
        File file = ResourceUtils.getFile("classpath:model/Team.json");
        mockMvc = MockMvcBuilders.standaloneSetup(teamController).build();
        ObjectMapper objectMapper = new ObjectMapper();
        Team teamMapper = objectMapper.readValue(file, Team.class);
        requestJson = objectMapper.writeValueAsString(teamMapper);
        team = new Team();
    }

    @Test
    public void saveTeam_expectSuccess() throws Exception {
        mockMvc.perform(post(PATH)
                .contentType(APPLICATION_JSON)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void getAllTeams_expectSuccess() throws Exception {
        List<Team> teams = new ArrayList<>();
        teams.add(new Team());

        when(service.getAllTeams()).thenReturn(teams);

        mockMvc.perform(get(PATH + "s")
                .characterEncoding("UTF-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andReturn();
    }

    @Test
    public void getTeamById_expectSuccess() throws Exception {
        when(service.getTeamById(1L)).thenReturn(team);

        mockMvc.perform(get(PATH + "/1")
                .characterEncoding("UTF-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andReturn();
    }

    @Test
    public void getTeamById_expectException() throws Exception {
        when(service.getTeamById(1L)).thenThrow(new TeamNotFoundException(1L));

        mockMvc.perform(get(PATH + "/1")
                .characterEncoding("UTF-8"))
                .andDo(print())
                .andReturn();
    }

    @Test
    public void updateTeam_expectSuccess() throws Exception {
        this.mockMvc
                .perform(put(PATH + "/1")
                        .contentType(APPLICATION_JSON)
                        .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void deleteTeam_expectSuccess() throws Exception {
        this.mockMvc
                .perform(delete(PATH + "/1")
                        .contentType(APPLICATION_JSON)
                        .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void getAllTeamsPage_expectSuccess() throws Exception {
        List<Team> teams = new ArrayList<>();
        teams.add(new Team());
        Page<Team> teamPage = new PageImpl<>(teams);
        PageRequestDto pageRequestDto = new PageRequestDto();
        pageRequestDto.setPageNumber(1);
        pageRequestDto.setPageSize(5);
        ObjectMapper objectMapper = new ObjectMapper();
        requestJson = objectMapper.writeValueAsString(pageRequestDto);

        when(service.getAllTeamsPage(any(PageRequestDto.class), any(String.class), any(String.class))).thenReturn(teamPage);

        mockMvc.perform(post("/teams/Page/Name/ExternalId")
                .contentType(APPLICATION_JSON).content(requestJson)
                .characterEncoding("UTF-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andReturn();
    }
}
