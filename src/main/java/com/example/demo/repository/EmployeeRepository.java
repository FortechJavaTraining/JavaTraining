package com.example.demo.repository;

import com.example.demo.entities.EmployeeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<EmployeeEntity, Long> {
    List<EmployeeEntity> findAllByTeamLead(EmployeeEntity teamLead);
    Long countAllByTeamLeadId(Long teamLeadId);
}
