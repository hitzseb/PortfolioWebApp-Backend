package com.hitzseb.portfolio.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hitzseb.portfolio.model.Skill;

@Repository
public interface SkillRepo extends JpaRepository<Skill, Long> {
}
