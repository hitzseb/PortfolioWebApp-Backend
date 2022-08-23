package com.hitzseb.portfolio.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hitzseb.portfolio.model.Experience;

@Repository
public interface ExperienceRepo extends JpaRepository<Experience, Long> {
}