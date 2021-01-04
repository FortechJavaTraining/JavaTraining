package com.example.demo.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Table(name = "USERS")
@Entity
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_USER")
    @SequenceGenerator(name = "S_USER", sequenceName = "S_USER", allocationSize = 1)
    private Long id;

    @Column(name = "USER_NAME", unique = true)
    private String userName;

    @Column(name = "PASSWORD")
    private String password;

    @OneToOne(mappedBy = "userEntity",
            fetch = FetchType.LAZY
    )
    private EmployeeEntity employeeEntity;



}
