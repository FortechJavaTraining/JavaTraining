package com.example.demo.service;

import com.example.demo.dto.Department;
import com.example.demo.dto.Department;
import com.example.demo.entities.DepartmentEntity;
import com.example.demo.entities.DepartmentEntity;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.DepartmentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.Silent.class)
public class DepartmentServiceTest {

    @InjectMocks
    private DepartmentService service;
    @Mock
    private DepartmentRepository departmentRepository;

    @Test
    public void givenAnEntity_getDepartmentById_shouldReturnValidDto() {
        Department department = new Department();
        department.setId(3L);

        DepartmentEntity departmentEntity = new DepartmentEntity();
        departmentEntity.setId(3L);

        when(departmentRepository.findById(3L)).thenReturn(Optional.of(departmentEntity));

        Department departmentByTd = service.getDepartmentById(3L);

        assertEquals(department.getId(), departmentByTd.getId());
    }

    @Test
    public void givenAnEntity_saveDepartment_shouldReturnValidDto() {
        Department department = new Department();
        department.setName("Norbeee");
        department.setId(2L);

        DepartmentEntity departmentEntity = new DepartmentEntity();
        departmentEntity.setName("Norbeee");
        departmentEntity.setId(2L);

        when(departmentRepository.findById(2L)).thenReturn(Optional.of(new DepartmentEntity("")));
        when(departmentRepository.save(Mockito.any(DepartmentEntity.class))).thenReturn(departmentEntity);

        Department department2 = service.saveDepartment(department);

        assertEquals(department.getName(), department2.getName());
    }

    @Test
    public void givenAnEntity_updateDepartment_shouldReturnValidDto() {
        Department department = new Department();
        department.setName("Norbeee");
        department.setId(2L);

        DepartmentEntity departmentEntity = new DepartmentEntity();
        departmentEntity.setName("Norbeee");
        departmentEntity.setId(2L);

        when(departmentRepository.findById(2L)).thenReturn(Optional.of(departmentEntity));
        when(departmentRepository.save(Mockito.any(DepartmentEntity.class))).thenReturn(departmentEntity);

        Department department2 = service.updateDepartment(department, 2L);

        assertEquals(department.getName(), department2.getName());
    }

    @Test
    public void givenAnEntity_getAllDepartments_shouldReturnValidDto() {
        Department department = new Department();
        department.setName("Norbeee");
        department.setId(2L);
        Department department1 = new Department();
        department.setName("Norbeee");
        department.setId(2L);
        List<Department> departments = new ArrayList<>();
        departments.add(department);
        departments.add(department1);

        DepartmentEntity departmentEntity = new DepartmentEntity();
        departmentEntity.setName("Norbeee");
        departmentEntity.setId(2L);
        DepartmentEntity departmentEntity1 = new DepartmentEntity();
        departmentEntity.setName("Norbeee");
        departmentEntity.setId(2L);
        List<DepartmentEntity> departmentEntities = new ArrayList<>();
        departmentEntities.add(departmentEntity);
        departmentEntities.add(departmentEntity1);

        when(departmentRepository.findAll()).thenReturn(departmentEntities);

        List<Department> departments1 = service.getAllDepartments();

        assertEquals(departments.get(0).getName(), departments1.get(0).getName());
    }

    @Test
    public void givenAnEntity_deleteDepartment_shouldReturnValidDto() {
        Department department = new Department();
        department.setId(3L);

        DepartmentEntity departmentEntity = new DepartmentEntity();
        departmentEntity.setId(3L);

        when(departmentRepository.findById(3L)).thenReturn(Optional.of(departmentEntity));

        Department departmentByTd = service.deleteDepartment(3L);

        assertEquals(department.getId(), departmentByTd.getId());

        verify(departmentRepository).deleteById(3L);
    }
}