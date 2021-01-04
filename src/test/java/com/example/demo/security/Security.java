package com.example.demo.security;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class Security{
    @Autowired
    private MockMvc mvc;

    @Test
    public void nonexistentUserCannotGetToken() throws Exception {
        String username = "nonexistentuser";
        String password = "password";

        String body = "{\"username\":\"" + username + "\", \"password\":\""
                + password + "\"}";

        mvc.perform(MockMvcRequestBuilders.post("/v2/token")
                .content(body))
                .andExpect(status().isForbidden())
                .andReturn();
    }

}
