package com.example.demo.controller;

import com.example.demo.dto.Employee;
import com.example.demo.entities.EmployeeEntity;
import com.example.demo.exeption.EmployeeNotFoundException;
import com.example.demo.service.EmployeeService;
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
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeControllerTest {
    private static final String PATH = "/employee";
    private MockMvc mockMvc;
    private String requestJson;
    private Employee employee;
    @InjectMocks
    private EmployeeController employeeController;
    @Mock
    private EmployeeService service;

    @Before
    public void setUp() throws Exception {
        File file = ResourceUtils.getFile("classpath:model/Employee.json");
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
        ObjectMapper objectMapper = new ObjectMapper();
        Employee employeeMapper = objectMapper.readValue(file, Employee.class);
        requestJson = objectMapper.writeValueAsString(employeeMapper);
        employee = new Employee();
    }

    @Test
    public void saveEmployee_expectSuccess() throws Exception {
        mockMvc.perform(post(PATH)
                .contentType(APPLICATION_JSON)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void getAllEmployees_expectSuccess() throws Exception {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee());

        when(service.getAllEmployees()).thenReturn(employees);

        mockMvc.perform(get(PATH + "s")
                .characterEncoding("UTF-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andReturn();
    }

    @Test
    public void getEmployeeById_expectSuccess() throws Exception {
        when(service.getEmployeeById(1L)).thenReturn(employee);

        mockMvc.perform(get(PATH + "/1")
                .characterEncoding("UTF-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andReturn();
    }

    @Test
    public void getEmployeeById_expectException() throws Exception {
        when(service.getEmployeeById(1L)).thenThrow(new EmployeeNotFoundException(1L));

        mockMvc.perform(get(PATH + "/1")
                .characterEncoding("UTF-8"))
                .andDo(print())
                .andReturn();
    }

    @Test
    public void updateEmployee_expectSuccess() throws Exception {
        this.mockMvc
                .perform(put(PATH + "/1")
                 .contentType(APPLICATION_JSON)
                 .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void deleteEmployee_expectSuccess() throws Exception {
        this.mockMvc
                .perform(delete(PATH + "/1")
                 .contentType(APPLICATION_JSON)
                 .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void updateEmployeeTeamLead_expectSuccess() throws Exception {
        this.mockMvc
                .perform(put(PATH + "s/teamLead")
                 .contentType(APPLICATION_JSON)
                 .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void deleteTeamLeadId_expectSuccess() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = new String(Files.readAllBytes(Paths.get("src/test/resources/model/EmployeesId.json")));
        List<Long> longs = Arrays.asList(objectMapper.readValue(json, Long[].class));
        requestJson = objectMapper.writeValueAsString(longs);
        List<EmployeeEntity> employeeEntities = new ArrayList<>();
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setId(1L);
        employeeEntity.setJob("Gradinar");
        List<Long> employeeId = new ArrayList<>();
        employeeId.add(1L);

        this.mockMvc
                .perform(delete(PATH + "/teamLeadId")
                .contentType(APPLICATION_JSON)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();


    }
}
