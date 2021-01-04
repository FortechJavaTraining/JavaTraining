package com.example.demo.repository;

import com.example.demo.entities.ProjectEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository extends CrudRepository<ProjectEntity, Long> {
    Optional<ProjectEntity> findByExternalID(String externalID);
}
