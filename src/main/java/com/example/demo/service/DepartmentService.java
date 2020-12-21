package com.example.demo.service;

import com.example.demo.dto.Department;
import com.example.demo.dto.Employee;
import com.example.demo.entities.DepartmentEntity;
import com.example.demo.entities.EmployeeEntity;
import com.example.demo.exeption.DepartmentNotFoundException;
import com.example.demo.exeption.EmployeeExistInDepartment;
import com.example.demo.repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    public Department saveDepartment(Department department) {
        DepartmentEntity departmentEntity = new DepartmentEntity();
        setDepartmentEntity(department, departmentEntity);
        DepartmentEntity departmentEntity1 = departmentRepository.save(departmentEntity);
        return convertDepartmentEntityToDepartment(departmentEntity1);
    }

    public List<Department> getAllDepartments() {
        return StreamSupport.stream(departmentRepository.findAll().spliterator(), false)
                .map(this::convertDepartmentEntityToDepartment).collect(Collectors.toList());
    }

    public Department getDepartmentById(Long id) {
        DepartmentEntity departmentEntity = getDepartmentEntity(id);
        return convertDepartmentEntityToDepartment(departmentEntity);
    }

    public Department updateDepartment(Department department, Long id) {
        DepartmentEntity departmentEntity = setDepartmentEntityDetails(department, id);
        departmentRepository.save(departmentEntity);
        return department;
    }

    public Department deleteDepartment(Long id) {
        DepartmentEntity departmentEntity = getDepartmentEntity(id);
        Department department = convertDepartmentEntityToDepartment(departmentEntity);
        if (departmentEntity.getEmployeeEntityList().isEmpty()) {
            departmentRepository.deleteById(id);
        } else {
            throw new EmployeeExistInDepartment(id);
        }
        return department;
    }

    private void setDepartmentEntity(Department department, DepartmentEntity departmentEntity) {
        departmentEntity.setName(department.getName());
    }

    private Department convertDepartmentEntityToDepartment(DepartmentEntity departmentEntity) {
        Department department = new Department();
        department.setId(departmentEntity.getId());
        department.setName(departmentEntity.getName());
        department.setEmployeeList(convertDepartmentEntityToEmployee(departmentEntity));
        return department;
    }

    private List<Employee> convertDepartmentEntityToEmployee(DepartmentEntity departmentEntity) {
        List<Employee> employees = new ArrayList<>();
        for (EmployeeEntity employeeEntity : departmentEntity.getEmployeeEntityList()) {
            Employee employee = convertEntityToEmployee(employeeEntity);
            employees.add(employee);
        }
        return employees;
    }

    private DepartmentEntity setDepartmentEntityDetails(Department department, Long id) {
        DepartmentEntity departmentEntity = getDepartmentEntity(id);
        setDepartmentEntity(department, departmentEntity);
        return departmentEntity;
    }

    public Employee convertEntityToEmployee(EmployeeEntity employeeEntity) {
        Employee employee = new Employee();
        employee.setId(employeeEntity.getId());
        employee.setName(employeeEntity.getName());
        employee.setJob(employeeEntity.getJob());
        employee.setDepartmentId(employeeEntity.getDepartmentEntity().getId());
        employee.setDepartmentName(employeeEntity.getDepartmentEntity().getName());
        return employee;
    }

    private DepartmentEntity getDepartmentEntity(Long id) {
        return departmentRepository.findById(id).orElseThrow(() -> new DepartmentNotFoundException(id));
    }
}
