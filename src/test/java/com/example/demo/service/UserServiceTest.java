package com.example.demo.service;

import com.example.demo.dto.User;
import com.example.demo.entities.UserEntity;
import com.example.demo.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class UserServiceTest {

    @InjectMocks
    private UserService service;
    @Mock
    private UserRepository userRepository;
    @Mock
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    public void givenAnEntity_saveUser_shouldReturnValidDto(){
        User user = new User();
        user.setUserName("test");

        UserEntity userEntity = new UserEntity();
        userEntity.setUserName("test");

        when(userRepository.save(Mockito.any(UserEntity.class))).thenReturn(userEntity);

        String user1 = service.saveUser(user);

        assertEquals(user.getUserName(), user1);
    }
}