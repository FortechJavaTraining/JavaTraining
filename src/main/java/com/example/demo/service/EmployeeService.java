package com.example.demo.service;

import com.example.demo.entities.DepartmentEntity;
import com.example.demo.exeption.DepartmentNotFoundException;
import com.example.demo.exeption.EmployeeNotFoundException;
import com.example.demo.dto.Employee;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.entities.EmployeeEntity;
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
        if(employeeEntity.getId() != null) {
            employee.setId(employeeEntity.getId());
        }
        employee.setJob(employeeEntity.getJob());
        employee.setDepartmentId(employeeEntity.getDepartmentEntity().getId());
        employee.setDepartmentName(employeeEntity.getDepartmentEntity().getName());
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
    }

    private EmployeeEntity getEmployeeEntity(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    private DepartmentEntity getDepartmentEntity(Long id) {
        return departmentRepository.findById(id).orElseThrow(() -> new DepartmentNotFoundException(id));
    }
}