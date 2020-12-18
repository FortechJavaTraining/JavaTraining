package com.example.demo;

import com.example.demo.entities.EmployeeEntity;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.entities.DepartmentEntity;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableJpaRepositories(basePackages = "com.example.demo")
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public CommandLineRunner insertDepartment(DepartmentRepository departmentRepository) {
//        return (args) -> {
//            departmentRepository.save(new DepartmentEntity("Economic"));
//            departmentRepository.save(new DepartmentEntity("Production"));
//            departmentRepository.save(new DepartmentEntity("Marketing"));
//            departmentRepository.deleteAll();
//        };
//    }

//    @Bean
//    public CommandLineRunner insertEmployee(EmployeeRepository employeeRepository) {
//        return (args) -> {
//            employeeRepository.save(new EmployeeEntity("Edi"));
//            employeeRepository.save(new EmployeeEntity("Atti"));
//            employeeRepository.save(new EmployeeEntity("Bogdi"));
//        };
//    }
}