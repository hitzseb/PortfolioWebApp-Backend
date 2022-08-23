package com.hitzseb.portfolio.service;

import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.hitzseb.portfolio.model.Experience;
import com.hitzseb.portfolio.model.Person;
import com.hitzseb.portfolio.model.Project;
import com.hitzseb.portfolio.model.Skill;
import com.hitzseb.portfolio.model.Study;
import com.hitzseb.portfolio.repo.ExperienceRepo;
import com.hitzseb.portfolio.repo.PersonRepo;
import com.hitzseb.portfolio.repo.ProjectRepo;
import com.hitzseb.portfolio.repo.SkillRepo;
import com.hitzseb.portfolio.repo.StudyRepo;

@Service
@AllArgsConstructor
public class ProfileService {
	private final PersonRepo personRepo;
	private final ExperienceRepo experienceRepo;
	private final SkillRepo skillRepo;
	private final StudyRepo studyRepo;
	private final ProjectRepo projectRepo;

	// PERSON
	
	public List<Person> findPersons() {
		return personRepo.findAll();
	}

	public Person findPerson(Long id) {
		return personRepo.findById(id).orElse(null);
	}

	public void savePerson(Person person) {
		personRepo.save(person);
	}
	
	public void deletePerson(Long id) {
		personRepo.deleteById(id);
	}

	// EXPERIENCE

	public Experience findExperience(Long id) {
		return experienceRepo.findById(id).orElse(null);
	}

	public void saveExperience(Experience experience) {
		experienceRepo.save(experience);
	}

	public void deleteExperience(Long id) {
		experienceRepo.deleteById(id);
	}
	
	// SKILL
	
	public Skill findSkill(Long id) {
		return skillRepo.findById(id).orElse(null);
	}
	
	public void saveSkill(Skill skill) {
		skillRepo.save(skill);
	}
	
	public void deleteSkill(Long id) {
		skillRepo.deleteById(id);
	}

	// STUDY

	public Study findStudy(Long id) {
		return studyRepo.findById(id).orElse(null);
	}

	public void saveStudy(Study study) {
		studyRepo.save(study);
	}

	public void deleteStudy(Long id) {
		studyRepo.deleteById(id);
	}

	// PROJECT

	public Project findProject(Long id) {
		return projectRepo.findById(id).orElse(null);
	}

	public void saveProject(Project project) {
		projectRepo.save(project);
	}

	public void deleteProject(Long id) {
		projectRepo.deleteById(id);
	}

}
