package com.example.demo.repository;

import com.example.demo.entities.ProjectEntity;
import com.example.demo.entities.TeamEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepository extends CrudRepository<TeamEntity,Long> {
    List<TeamEntity> findByProjectEntity(Optional<ProjectEntity> byExternalID);
}
