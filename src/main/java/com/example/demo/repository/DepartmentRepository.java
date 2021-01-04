package com.example.demo.repository;

import com.example.demo.entities.DepartmentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends PagingAndSortingRepository<DepartmentEntity, Long> {
    Page<DepartmentEntity> findAllByNameContains(String name, Pageable pageable);
}
