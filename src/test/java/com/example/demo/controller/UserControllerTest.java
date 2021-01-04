package com.example.demo.controller;


import com.example.demo.dto.User;
import com.example.demo.entities.UserEntity;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.ResourceUtils;

import java.io.File;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {
    private static final String PATH = "/sign-up";
    private MockMvc mockMvc;
    private String requestJson;
    private User user;
    @InjectMocks
    private UserController userController;
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserService userService;


    @Before
    public void setUp() throws Exception {
        File file = ResourceUtils.getFile("classpath:model/User.json");
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        ObjectMapper objectMapper = new ObjectMapper();
        User userMapper = objectMapper.readValue(file, User.class);
        requestJson = objectMapper.writeValueAsString(userMapper);
        user = new User();
    }

    @Test
    public void saveUser_expectSuccess() throws Exception {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(user.getUserName());
        userEntity.setPassword(user.getPassword());

        when(userRepository.save(Mockito.any(UserEntity.class))).thenReturn(userEntity);

        userRepository.save(userEntity);

        mockMvc.perform(post(PATH)
                .contentType(APPLICATION_JSON)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }
}