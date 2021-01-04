package com.example.demo.service;

import com.example.demo.dto.User;
import com.example.demo.entities.EmployeeEntity;
import com.example.demo.entities.UserEntity;
import com.example.demo.exeption.UserAlreadyInUse;
import com.example.demo.repository.EmployeeRepository;
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

    @Mock
    EmployeeService employeeService;
    @Mock
    UserRepository userRepository;
    @Mock
    EmployeeRepository employeeRepository;
    @InjectMocks
    UserService userService;
    @InjectMocks
    private UserService service;
    @Mock
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    public void givenAnUser_saveUser_Valid() {
        User user = new User();
        user.setUserName("Alex");
        user.setPassword("pass");
        user.setDepartmentId(1L);

        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(user.getUserName());
        userEntity.setPassword(user.getPassword());

        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setUserEntity(userEntity);
        employeeEntity.setName(user.getEmployeeName());
        employeeEntity.setJob(user.getEmployeeJob());

        userEntity.setEmployeeEntity(employeeEntity);

        when(employeeRepository.save(Mockito.any(EmployeeEntity.class))).thenReturn(new EmployeeEntity());
        when(userRepository.save(Mockito.any(UserEntity.class))).thenReturn(userEntity);

        employeeService.getEmployeeToUser(userEntity, user);

        String user1 = userService.saveUser(user);

        assertEquals(user1, user.getUserName());
        assertEquals(userEntity.getPassword(), user.getPassword());
        assertEquals(userEntity.getEmployeeEntity(), employeeEntity);
    }

    @Test
    public void givenAnEntity_saveUser_shouldReturnValidDto() {
        User user = new User();
        user.setUserName("test");

        UserEntity userEntity = new UserEntity();
        userEntity.setUserName("test");

        when(userRepository.save(Mockito.any(UserEntity.class))).thenReturn(userEntity);

        String user1 = service.saveUser(user);

        assertEquals(user.getUserName(), user1);
    }

    @Test(expected = UserAlreadyInUse.class)
    public void givenAnUserWithSameUniqueName_saveUser_ExpectException() {
        User user = new User();
        user.setUserName("test");

        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(user.getUserName());

        when(userRepository.findByUserName(userEntity.getUserName()))
                .thenReturn(userEntity)
                .thenThrow(new UserAlreadyInUse(userEntity.getUserName()));

        String user1 = service.saveUser(user);

        assertEquals(userEntity.getUserName(), user1);
    }
}