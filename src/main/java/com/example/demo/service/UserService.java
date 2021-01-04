package com.example.demo.service;

import com.example.demo.dto.User;
import com.example.demo.entities.UserEntity;
import com.example.demo.exeption.UserAlreadyInUse;
import com.example.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {
    private final EmployeeService employeeService;
    private final UserRepository userRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public String saveUser(User user) {

        UserEntity userEntity = new UserEntity();
        userEntity.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userEntity.setUserName(user.getUserName());
        UserEntity byUserName = userRepository.findByUserName(user.getUserName());
        if (byUserName != null) {
            throw new UserAlreadyInUse(userEntity.getUserName());
        }
        UserEntity userEntity1 = userRepository.save(userEntity);
        employeeService.getEmployeeToUser(userEntity, user);
        return userEntity1.getUserName();
    }


}