package com.example.demo.service;

import com.example.demo.dto.Department;
import com.example.demo.dto.PageRequestDto;
import com.example.demo.entities.DepartmentEntity;
import com.example.demo.entities.EmployeeEntity;
import com.example.demo.entities.UserEntity;
import com.example.demo.exeption.EmployeeExistInDepartment;
import com.example.demo.repository.DepartmentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

    @Test(expected = EmployeeExistInDepartment.class)
    public void givenAnEntity_deleteDepartment_ExpectException() {

        DepartmentEntity departmentEntity = new DepartmentEntity();
        departmentEntity.setId(3L);
        departmentEntity.setName("HR");

        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);

        EmployeeEntity employeeEntity = new EmployeeEntity();
        List<EmployeeEntity> employeeEntityList = new ArrayList<>();
        employeeEntity.setId(1L);
        employeeEntity.setName("Alex");
        employeeEntity.setUserEntity(userEntity);
        employeeEntity.setDepartmentEntity(departmentEntity);
        employeeEntityList.add(employeeEntity);
        departmentEntity.setEmployeeEntityList(employeeEntityList);

        when(departmentRepository.findById(1L)).thenReturn(Optional.of(departmentEntity));
        service.deleteDepartment(1L);
    }

    @Test
    public void givenAnEntity_getAllDepartmentsPage_shouldReturnValidDto() {
        Department department = new Department();
        department.setName("HR");
        department.setId(1L);

        List<Department> departments = new ArrayList<>();
        departments.add(department);

        DepartmentEntity departmentEntity = new DepartmentEntity();
        departmentEntity.setName("HR");
        departmentEntity.setId(2L);

        Page<Department> departmentPage = new PageImpl<>(departments);
        PageRequestDto pageRequestDto = new PageRequestDto();
        pageRequestDto.setPageNumber(1);
        pageRequestDto.setPageSize(1);

        List<DepartmentEntity> departmentEntities = new ArrayList<>();
        departmentEntities.add(departmentEntity);

        Page<DepartmentEntity> departmentEntityPage = new PageImpl<>(departmentEntities);

        when(departmentRepository.findAllByNameContains(department.getName(), PageRequest.of(pageRequestDto.getPageNumber(), pageRequestDto.getPageSize())))
                .thenReturn(departmentEntityPage);

        Page<Department> allDepartmentsPage = service.getAllDepartmentsPage(pageRequestDto, department.getName());

        assertEquals(departmentPage.getTotalPages(), allDepartmentsPage.getTotalPages());
    }
}