package com.example.demo.service;

import com.example.demo.dto.Employee;
import com.example.demo.dto.TeamLeadDto;
import com.example.demo.entities.DepartmentEntity;
import com.example.demo.entities.EmployeeEntity;
import com.example.demo.exeption.EmployeeNotFoundException;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.EmployeeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.Silent.class)
public class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService service;
    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private DepartmentRepository departmentRepository;

    @Test
    public void givenAnEntity_getEmployeeById_shouldReturnValidDto() {
        Employee employee = new Employee();
        employee.setId(3L);

        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setId(3L);

        DepartmentEntity departmentEntity = new DepartmentEntity();
        departmentEntity.setId(1L);
        employeeEntity.setDepartmentEntity(departmentEntity);

        when(employeeRepository.findById(3L)).thenReturn(Optional.of(employeeEntity));

        Employee employeeByTd = service.getEmployeeById(3L);

        assertEquals(employee.getId(), employeeByTd.getId());
    }


    @Test
    public void givenAnEntity_saveEmployee_shouldReturnValidDto() {
        Employee employee = new Employee();
        employee.setName("Norbeee");
        employee.setId(2L);
        employee.setDepartmentId(2L);

        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setName("Norbeee");
        employeeEntity.setId(2L);

        DepartmentEntity departmentEntity = new DepartmentEntity();
        departmentEntity.setId(1L);
        employeeEntity.setDepartmentEntity(departmentEntity);

        when(departmentRepository.findById(2L)).thenReturn(Optional.of(new DepartmentEntity("")));
        when(employeeRepository.save(Mockito.any(EmployeeEntity.class))).thenReturn(employeeEntity);

        Employee employee2 = service.saveEmployee(employee);

        assertEquals(employee.getName(), employee2.getName());
    }

    @Test
    public void givenAnEntity_updateEmployee_shouldReturnValidDto() {
        Employee employee = new Employee();
        employee.setName("Norbeee");
        employee.setId(2L);
        employee.setDepartmentId(1L);

        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setName("Norbeee");
        employeeEntity.setId(2L);

        DepartmentEntity departmentEntity = new DepartmentEntity();
        departmentEntity.setId(1L);
        employeeEntity.setDepartmentEntity(departmentEntity);

        when(employeeRepository.findById(2L)).thenReturn(Optional.of(employeeEntity));
        when(departmentRepository.findById(1L)).thenReturn(Optional.of(new DepartmentEntity("")));
        when(employeeRepository.save(Mockito.any(EmployeeEntity.class))).thenReturn(employeeEntity);

        Employee employee2 = service.updateEmployee(employee, 2L);

        assertEquals(employee.getName(), employee2.getName());
    }

    @Test
    public void givenAnEntity_getAllEmployees_shouldReturnValidDto() {
        Employee employee = new Employee();
        employee.setName("Norbeee");
        employee.setId(2L);
        employee.setDepartmentId(1L);
        Employee employee1 = new Employee();
        employee.setName("Norbeee");
        employee.setId(2L);
        employee.setDepartmentId(1L);
        List<Employee> employees = new ArrayList<>();
        employees.add(employee);
        employees.add(employee1);

        DepartmentEntity departmentEntity = new DepartmentEntity();
        departmentEntity.setId(1L);

        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setName("Norbeee");
        employeeEntity.setId(2L);
        EmployeeEntity employeeEntity1 = new EmployeeEntity();
        employeeEntity.setName("Norbeee");
        employeeEntity.setId(2L);
        List<EmployeeEntity> employeeEntities = new ArrayList<>();
        employeeEntities.add(employeeEntity);
        employeeEntities.add(employeeEntity1);
        employeeEntity.setDepartmentEntity(departmentEntity);
        employeeEntity1.setDepartmentEntity(departmentEntity);

        when(employeeRepository.findAll()).thenReturn(employeeEntities);

        List<Employee> employees1 = service.getAllEmployees();

        assertEquals(employees.get(0).getName(), employees1.get(0).getName());
    }

    @Test
    public void givenAnEntity_deleteEmployee_shouldReturnValidDto() {
        Employee employee = new Employee();
        employee.setId(3L);

        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setId(3L);

        DepartmentEntity departmentEntity = new DepartmentEntity();
        departmentEntity.setId(1L);
        employeeEntity.setDepartmentEntity(departmentEntity);

        when(employeeRepository.findById(3L)).thenReturn(Optional.of(employeeEntity));

        Employee employeeByTd = service.deleteEmployee(3L);

        assertEquals(employee.getId(), employeeByTd.getId());

        verify(employeeRepository).deleteById(3L);
    }

    @Test
    public void givenTeamLeadId_updateTeamLead_expectValidDtoSuccess() {
        TeamLeadDto teamLeadDto = new TeamLeadDto();
        EmployeeEntity employeeEntity = new EmployeeEntity();
        teamLeadDto.setTeamLeadId(1);
        teamLeadDto.setEmployees(Arrays.asList(1L, 2L));
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employeeEntity));
        when(employeeRepository.findById(2L)).thenReturn(Optional.of(employeeEntity));

        service.updateEmployeeTeamLead(teamLeadDto);

        verify(employeeRepository, times(2)).save(employeeEntity);

    }

    @Test(expected = EmployeeNotFoundException.class)
    public void givenTeamLeadId_updateTeamLead_expectException() {
        TeamLeadDto teamLeadDto = new TeamLeadDto();
        teamLeadDto.setTeamLeadId(1);
        teamLeadDto.setEmployees(Arrays.asList(1L, 2L));
        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

        service.updateEmployeeTeamLead(teamLeadDto);
    }


    @Test
    public void givenEmployeeId_deleteTeamLead_expectValidDtoSuccess() {
        List<EmployeeEntity> employeeEntities = new ArrayList<>();
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setId(1L);
        employeeEntity.setJob("Gradinar");
        EmployeeEntity employeeEntity1 = new EmployeeEntity();
        employeeEntity1.setId(2L);
        employeeEntity1.setJob("Gradinar");
        employeeEntities.add(employeeEntity);
        employeeEntities.add(employeeEntity1);


        List<Long> employeeId = new ArrayList<>();
        employeeId.add(1L);
        employeeId.add(2L);

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employeeEntity));
        when(employeeRepository.findById(2L)).thenReturn(Optional.of(employeeEntity1));

        service.deleteTeamLeadId(employeeId);

        verify(employeeRepository, times(1)).save(employeeEntity);

    }


    @Test(expected = EmployeeNotFoundException.class)
    public void givenTeamLeadId_deleteTeamLead_expectException() {
        List<Long> employeeId = new ArrayList<>();
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setId(1L);
        employeeEntity.setJob("Gradinar");

        employeeId.add(1L);

        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

        service.deleteTeamLeadId(employeeId);


    }
}