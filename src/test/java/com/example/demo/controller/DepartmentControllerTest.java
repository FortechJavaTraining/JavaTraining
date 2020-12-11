package com.example.demo.controller;

import com.example.demo.dto.Department;
import com.example.demo.exeption.DepartmentNotFoundException;
import com.example.demo.service.DepartmentService;
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
public class DepartmentControllerTest {
    private static final String PATH = "/department";
    private MockMvc mockMvc;
    private String requestJson;
    private Department department;
    @InjectMocks
    private DepartmentController employeeController;
    @Mock
    private DepartmentService service;

    @Before
    public void setUp() throws Exception {
        File file = ResourceUtils.getFile("classpath:model/Department.json");
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
        ObjectMapper objectMapper = new ObjectMapper();
        Department departmentMapper = objectMapper.readValue(file, Department.class);
        requestJson = objectMapper.writeValueAsString(departmentMapper);
        department = new Department();
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
        List<Department> departments = new ArrayList<>();
        departments.add(new Department());

        when(service.getAllDepartments()).thenReturn(departments);

        mockMvc.perform(get(PATH + "s")
                .characterEncoding("UTF-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andReturn();
    }

    @Test
    public void getEmployeeById_expectSuccess() throws Exception {
        when(service.getDepartmentById(1L)).thenReturn(department);

        mockMvc.perform(get(PATH + "/1")
                .characterEncoding("UTF-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andReturn();
    }

    @Test
    public void getEmployeeById_expectException() throws Exception {
        when(service.getDepartmentById(1L)).thenThrow(new DepartmentNotFoundException(1L));

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
}
