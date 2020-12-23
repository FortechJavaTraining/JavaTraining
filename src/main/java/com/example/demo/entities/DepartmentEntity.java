package com.example.demo.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "DEPARTMENT")
@Getter
@Setter
@NoArgsConstructor
public class DepartmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_DEPARTMENT")
    @SequenceGenerator(name = "S_DEPARTMENT", sequenceName = "S_DEPARTMENT", allocationSize = 1)
    private long id;

    @Column(name = "NAME")
    private String name;

    @OneToMany(mappedBy = "departmentEntity", fetch = FetchType.LAZY)
    private List<EmployeeEntity> employeeEntityList = new ArrayList<>();

    public DepartmentEntity(String name) {
        this.name = name;
    }
}