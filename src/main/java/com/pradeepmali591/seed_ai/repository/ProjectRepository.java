package com.pradeepmali591.seed_ai.repository;

import com.pradeepmali591.seed_ai.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

}
