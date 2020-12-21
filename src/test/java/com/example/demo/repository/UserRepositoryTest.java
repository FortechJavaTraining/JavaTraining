package com.example.demo.repository;

import com.example.demo.entities.UserEntity;
import com.example.demo.exeption.EmployeeNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Before
    public void saveUser() {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName("test");
        userRepository.save(userEntity);
    }

    private UserEntity getUserEntity() {
        return userRepository.findById(1L).orElseThrow(() -> new EmployeeNotFoundException(1L));
    }

    @Test
    public void findUserByName() {
        UserEntity userEntity = getUserEntity();

        assertEquals(userEntity.getUserName(), "test");
    }

}