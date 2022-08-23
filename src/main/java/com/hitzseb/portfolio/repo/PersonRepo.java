package com.hitzseb.portfolio.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hitzseb.portfolio.model.Person;

@Repository
public interface PersonRepo extends JpaRepository<Person, Long> {
}