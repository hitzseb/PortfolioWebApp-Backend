package com.hitzseb.portfolio.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.hitzseb.portfolio.model.Experience;
import com.hitzseb.portfolio.model.Person;
import com.hitzseb.portfolio.model.Project;
import com.hitzseb.portfolio.model.Skill;
import com.hitzseb.portfolio.model.Study;
import com.hitzseb.portfolio.util.Views;
import com.hitzseb.portfolio.service.ProfileService;
import com.hitzseb.portfolio.model.User;

import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@CrossOrigin("http://localhost:4200/")
public class ProfileController {
	private final ProfileService profileService;

	// PERSON
	
	@JsonView(Views.Public.class)
	@RequestMapping("/view-persons")
	public List<Person> findPersons() {
		return profileService.findPersons();
	}

	@GetMapping("/view-person/{id}")
	@ResponseBody
	public Person findPersonById(@PathVariable Long id) {
		return profileService.findPerson(id);
	}
	
	@DeleteMapping("/admin/delete-person/{id}")
	public void deletePersonById(@PathVariable Long id) {
		if (getCurrentUserId() != id) {
			profileService.deletePerson(id);
		}
	}

	@GetMapping("/get-person")
	@ResponseBody
	public Person findPerson() {
		return profileService.findPerson(getCurrentUserId());
	}
	
	@PutMapping("/edit-banner")
	public void editBanner(@RequestParam String banner) {
		Person person = getCurrentPerson();
		person.setBanner(banner);
		profileService.savePerson(getCurrentPerson());
	}
	
	@PutMapping("/edit-avatar")
	public void editAvatar(@RequestParam String avatar) {
		Person person = getCurrentPerson();
		person.setAvatar(avatar);
		profileService.savePerson(getCurrentPerson());
	}
	
	@PutMapping("/edit-fullname")
	public void editFullName(@RequestParam String fullName) {
		Person person = getCurrentPerson();
		person.setFullName(fullName);
		profileService.savePerson(person);
	}
	@PutMapping("/edit-title")
	public void editTitle(@RequestParam String title) {
		Person person = getCurrentPerson();
		person.setTitle(title);
		profileService.savePerson(person);
	}
	
	@PutMapping("/edit-domicile")
	public void editCity(@RequestParam String domicile) {
		Person person = getCurrentPerson();
		person.setDomicile(domicile);
		profileService.savePerson(person);
	}
	
	@PutMapping("/edit-contact")
	public void editContact(@RequestParam String contact) {
		Person person = getCurrentPerson();
		person.setContact(contact);
		profileService.savePerson(person);
	}
	
	@PutMapping("/edit-about")
	public void editAbout(@RequestParam String about) {
		Person person = getCurrentPerson();
		person.setAbout(about);
		profileService.savePerson(person);
	}

	// EXPERIENCE

	@GetMapping("/get-experience/{id}")
	public Experience findExperienceById(@PathVariable Long id) {
		if (profileService.findExperience(id).getPerson() == getCurrentPerson()) {
			return profileService.findExperience(id);
		} else {
			return null;
		}
	}

	@PostMapping("/new-experience")
	public void createExperience(@RequestBody Experience experience) {
		experience.setPerson(getCurrentPerson());
		profileService.saveExperience(experience);
	}

	@DeleteMapping("/delete-experience/{id}")
	public void deleteExperience(@PathVariable Long id) {
		Experience experience = profileService.findExperience(id);
		if (experience.getPerson() == getCurrentPerson()) {
			profileService.deleteExperience(id);
		}
	}

	@PutMapping("/edit-experience/{id}")
	public void editExperience(@PathVariable Long id, @RequestBody Experience newExperience) {
		Experience oldExperience = profileService.findExperience(id);
		if (oldExperience.getPerson() == getCurrentPerson()) {
			oldExperience.setCompany(newExperience.getCompany());
			oldExperience.setLogo(newExperience.getLogo());
			oldExperience.setPosition(newExperience.getPosition());
			oldExperience.setStartDate(newExperience.getStartDate());
			oldExperience.setEndDate(newExperience.getEndDate());
		}
		profileService.saveExperience(oldExperience);
	}

	// STUDY

	@GetMapping("/get-study/{id}")
	public Study findStudyById(@PathVariable Long id) {
		if (profileService.findStudy(id).getPerson() == getCurrentPerson()) {
			return profileService.findStudy(id);
		} else {
			return null;
		}
	}

	@PostMapping("/new-study")
	public void createStudy(@RequestBody Study study) {
		Person person = getCurrentPerson();
		study.setPerson(person);
		profileService.saveStudy(study);
	}

	@DeleteMapping("/delete-study/{id}")
	public void deleteStudy(@PathVariable Long id) {
		Study study = profileService.findStudy(id);
		if (study.getPerson() == getCurrentPerson()) {
			profileService.deleteStudy(id);
		}
	}

	@PutMapping("/edit-study/{id}")
	public void editStudy(@PathVariable Long id, @RequestBody Study newStudy) {
		Study oldStudy = profileService.findStudy(id);
		if (oldStudy.getPerson() == getCurrentPerson()) {
			oldStudy.setInstitution(newStudy.getInstitution());
			oldStudy.setLogo(newStudy.getLogo());
			oldStudy.setName(newStudy.getName());
			oldStudy.setStartDate(newStudy.getStartDate());
			oldStudy.setEndDate(newStudy.getEndDate());
		}
		profileService.saveStudy(oldStudy);
	}

	// SKILL

	@GetMapping("/get-skill/{id}")
	public Skill findSkill(@PathVariable Long id) {
		if (profileService.findSkill(id).getPerson() == getCurrentPerson()) {
			return profileService.findSkill(id);
		} else {
			return null;
		}
	}

	@PostMapping("/new-skill")
	public void createSkill(@RequestBody Skill skill) {
		Person person = getCurrentPerson();
		skill.setPerson(person);
		profileService.saveSkill(skill);
	}

	@DeleteMapping("/delete-skill/{id}")
	public void deleteSkill(@PathVariable Long id) {
		Skill skill = profileService.findSkill(id);
		if (skill.getPerson() == getCurrentPerson()) {
			profileService.deleteSkill(id);
		}
	}

	@PutMapping("/edit-skill/{id}")
	public void editSkill(@PathVariable Long id, @RequestBody Skill newSkill) {
		Skill oldSkill = profileService.findSkill(id);
		if (oldSkill.getPerson() == getCurrentPerson()) {
			oldSkill.setName(newSkill.getName());
			oldSkill.setProgress(newSkill.getProgress());
		}
		profileService.saveSkill(oldSkill);
	}

	// PROJECT

	@GetMapping("/get-project/{id}")
	public Project findProjectById(@PathVariable Long id) {
		if (profileService.findProject(id).getPerson() == getCurrentPerson()) {
			return profileService.findProject(id);
		} else {
			return null;
		}
	}

	@PostMapping("/new-project")
	public void createProject(@RequestBody Project project) {
		Person person = getCurrentPerson();
		project.setPerson(person);
		profileService.saveProject(project);
	}

	@DeleteMapping("/delete-project/{id}")
	public void deleteProject(@PathVariable Long id) {
		Project project = profileService.findProject(id);
		if (project.getPerson() == getCurrentPerson()) {
			profileService.deleteProject(id);
		}
	}

	@PutMapping("/edit-project/{id}")
	public void editProject(@PathVariable Long id, @RequestBody Project newProject) {
		Project oldProject = profileService.findProject(id);
		if (oldProject.getPerson() == getCurrentPerson()) {
			oldProject.setName(newProject.getName());
			oldProject.setUrl(newProject.getUrl());
			oldProject.setDescription(newProject.getDescription());
		}
		profileService.saveProject(oldProject);
	}
	
	public Person getCurrentPerson() {
		Person person = profileService.findPerson(getCurrentUserId());
		return person;
	}
	
	public Long getCurrentUserId() {
		Long id = getCurrentUser().getId();
		return id;
	}
	
	public User getCurrentUser() {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return user;
	}

}
