package com.example.demo.service;

import com.example.demo.dto.Employee;
import com.example.demo.dto.TeamLeadDto;
import com.example.demo.entities.DepartmentEntity;
import com.example.demo.entities.EmployeeEntity;
import com.example.demo.entities.TeamEntity;
import com.example.demo.exeption.DepartmentNotFoundException;
import com.example.demo.exeption.EmployeeNotFoundException;
import com.example.demo.exeption.TeamNotFoundException;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.TeamRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final TeamRepository teamRepository;


    public Employee saveEmployee(Employee employee) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        setEmployeeEntity(employee, employeeEntity);
        EmployeeEntity employeeEntity1 = employeeRepository.save(employeeEntity);
        return convertEntityToEmployee(employeeEntity1);
    }

    public List<Employee> getAllEmployees() {
        return StreamSupport.stream(employeeRepository.findAll().spliterator(), false)
                .map(this::convertEntityToEmployee).collect(Collectors.toList());
    }

    public Employee getEmployeeById(Long id) {
        EmployeeEntity employeeEntity = getEmployeeEntity(id);
        return convertEntityToEmployee(employeeEntity);
    }

    public Employee updateEmployee(Employee employee, Long id) {
        EmployeeEntity employeeEntity = setEmployeeEntityDetails(employee, id);
        employeeRepository.save(employeeEntity);
        return convertEntityToEmployee(employeeEntity);
    }

    public Employee deleteEmployee(Long id) {
        EmployeeEntity employeeEntity = getEmployeeEntity(id);
        Employee employee = convertEntityToEmployee(employeeEntity);
        employeeRepository.deleteById(id);
        return employee;
    }

    private Employee convertEntityToEmployee(EmployeeEntity employeeEntity) {
        Employee employee = new Employee();
        employee.setName(employeeEntity.getName());
        if (employeeEntity.getId() != null) {
            employee.setId(employeeEntity.getId());
        }
        employee.setJob(employeeEntity.getJob());
        employee.setDepartmentId(employeeEntity.getDepartmentEntity().getId());
        employee.setDepartmentName(employeeEntity.getDepartmentEntity().getName());
        if (employeeEntity.getTeamEntity() != null)
            employee.setTeamName(employeeEntity.getTeamEntity().getName());
        return employee;
    }

    private EmployeeEntity setEmployeeEntityDetails(Employee employee, Long id) {
        EmployeeEntity employeeEntity = getEmployeeEntity(id);
        setEmployeeEntity(employee, employeeEntity);
        return employeeEntity;
    }

    private void setEmployeeEntity(Employee employee, EmployeeEntity employeeEntity) {
        employeeEntity.setName(employee.getName());
        employeeEntity.setJob(employee.getJob());
        employeeEntity.setDepartmentEntity(getDepartmentEntity(employee.getDepartmentId()));
        if (employee.getTeamId() != 0)
            employeeEntity.setTeamEntity(getTeamEntity(employee.getTeamId()));
    }

    private EmployeeEntity getEmployeeEntity(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    private DepartmentEntity getDepartmentEntity(Long id) {
        return departmentRepository.findById(id).orElseThrow(() -> new DepartmentNotFoundException(id));
    }

    private TeamEntity getTeamEntity(Long id) {
        return teamRepository.findById(id).orElseThrow(() -> new TeamNotFoundException(id));
    }

    public void updateEmployeeTeamLead(TeamLeadDto teamLeadDto) {
        EmployeeEntity employeeTeamLead = employeeRepository.findById(teamLeadDto.getTeamLeadId()).orElseThrow(() -> new EmployeeNotFoundException(teamLeadDto.getTeamLeadId()));
        for (Long employeesId : teamLeadDto.getEmployees()) {
            EmployeeEntity employeeEntity = employeeRepository.findById(employeesId).orElseThrow(() -> new EmployeeNotFoundException(employeesId));
            employeeEntity.setTeamLead(employeeTeamLead);
            employeeRepository.save(employeeEntity);
        }
    }

    public void deleteTeamLeadId(List<Long> employeeId) {
        for (int i = 0; i < employeeId.size(); i++) {
            int finalI = i;
            EmployeeEntity employeeEntity = employeeRepository.findById(employeeId.get(i)).orElseThrow(() -> new EmployeeNotFoundException(employeeId.get(finalI)));
            employeeEntity.setTeamLead(null);
            employeeRepository.save(employeeEntity);
        }
    }
}