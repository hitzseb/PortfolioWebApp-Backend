package com.hitzseb.portfolio.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hitzseb.portfolio.model.Study;

@Repository
public interface StudyRepo extends JpaRepository<Study, Long> {
}