package com.example.demo.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.UUID;

@Getter
@Setter
@Table(name = "Project")
@Entity
public class ProjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_PROJECT")
    @SequenceGenerator(name = "S_PROJECT", sequenceName = "S_PROJECT", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @Column(name = "EXTERNAL_ID", updatable = false, unique = true, nullable = false)
    private String externalID;

    @Size(min = 1, max = 100)
    @Column(name = "NAME", unique = true)
    private String name;

    public ProjectEntity() {
        this.externalID = UUID.randomUUID().toString();
    }

}