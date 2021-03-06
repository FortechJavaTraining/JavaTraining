package com.example.demo.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertNotNull;

public class EmployeeTest {
    private static final String JSON_PATH = "src/test/resources/model/";
    private static final String FILE = "Employee.json";
    private static final Class employeeClass = Employee.class;

    @Test
    public void jsonSerialization() throws IOException {
        String eventJson = new String(Files.readAllBytes(Paths.get(JSON_PATH + FILE)));
        ObjectMapper objectMapper = new ObjectMapper();
        Object object = objectMapper.readValue(eventJson, employeeClass);
        String jsonStr = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        assertNotNull(jsonStr);
    }
}