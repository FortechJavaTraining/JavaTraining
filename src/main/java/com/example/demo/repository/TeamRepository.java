package com.example.demo.repository;

import com.example.demo.entities.TeamEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends PagingAndSortingRepository<TeamEntity, Long> {
    Page<TeamEntity> findAllByNameContainsOrExternalIdContains(String name, String externalId, Pageable pageable);
}
