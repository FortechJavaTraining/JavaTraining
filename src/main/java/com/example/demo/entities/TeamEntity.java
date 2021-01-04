package com.example.demo.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Table(name = "TEAM")
@Entity
@NoArgsConstructor
public class TeamEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_TEAM")
    @SequenceGenerator(name = "S_TEAM", sequenceName = "S_TEAM", allocationSize = 1)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "EXTERNAL_ID")
    private String externalId;

    @OneToOne
    @JoinColumn(name = "TEAM_LEAD")
    private EmployeeEntity teamLead;

    @OneToMany(mappedBy = "teamEntity", fetch = FetchType.LAZY)
    private List<EmployeeEntity> employeeEntityList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "PROJECT_ID")
    private ProjectEntity projectEntity;

    public TeamEntity(String name) {
        this.name = name;
    }
}