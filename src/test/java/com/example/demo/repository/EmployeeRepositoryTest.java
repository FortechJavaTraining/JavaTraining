package com.example.demo.repository;

import com.example.demo.entities.DepartmentEntity;
import com.example.demo.entities.EmployeeEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class EmployeeRepositoryTest {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    @Before
    public void saveDepartmentAndEmployee(){
        DepartmentEntity departmentEntity = new DepartmentEntity();
        departmentEntity.setName("Money");

        DepartmentEntity departmentEntitySaved = departmentRepository.save(departmentEntity);

        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setName("Norbeboy");
        employeeEntity.setJob("Dev");
        employeeEntity.setDepartmentEntity(departmentEntitySaved);

        employeeRepository.save(employeeEntity);
    }

    private List<EmployeeEntity> getEmployeeEntities(){
        return StreamSupport.stream(employeeRepository.findAll().spliterator(),false)
                .collect(Collectors.toList());
    }

    private EmployeeEntity getEmployeeEntity(){
        return employeeRepository.findById(1L).get();
    }

    @Test
    public void findAllEmployees() {
        List<EmployeeEntity> employeeEntities = getEmployeeEntities();

        assertEquals(employeeEntities.size(), 1);
    }

    @Test
    public void findEmployeeById() {
        EmployeeEntity employeeEntity = getEmployeeEntity();

        assertEquals(employeeEntity.getName(), "Norbeboy");
    }

    @Test
    public void updateEmployeeById() {
            EmployeeEntity employeeEntity = getEmployeeEntity();
            employeeEntity.setName("Norbeboy111");
            employeeRepository.save(employeeEntity);

            List<EmployeeEntity> employeeEntities = getEmployeeEntities();

            assertEquals(employeeEntities.get(0).getName(), "Norbeboy111");
    }

    @Test
    public void deleteEmployeeById() {
        employeeRepository.deleteById(1L);

        List<EmployeeEntity> employeeEntities = getEmployeeEntities();

        assertEquals(employeeEntities.size(), 0);
    }
}