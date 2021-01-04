package com.example.demo.repository;

import com.example.demo.entities.EmployeeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<EmployeeEntity, Long> {
    Page<EmployeeEntity> findAllByNameContainsOrJobContains(String name, String job, Pageable pageable);

}
