package com.example.demo.entities;

import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Table(name = "EMPLOYEE")
@Entity
@NoArgsConstructor
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_EMPLOYEE")
    @SequenceGenerator(name = "S_EMPLOYEE", sequenceName = "S_EMPLOYEE", allocationSize = 1)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "JOB")
    private String job;

    @ManyToOne
    @JoinColumn(name = "DEPARTMENT_ID")
    private DepartmentEntity departmentEntity;

    @ManyToOne
    @JoinColumn(name = "TEAM_ENTITY_ID")
    private TeamEntity teamEntity;

    public EmployeeEntity(String name) {
        this.name = name;
    }
}
