package com.hitzseb.portfolio.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.hitzseb.portfolio.util.Views;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "persons")
@Data @NoArgsConstructor
public class Person {
	@JsonView(Views.Public.class)
	@Id
	private Long id;
	private String banner;
	@JsonView(Views.Public.class)
	private String avatar;
	@JsonView(Views.Public.class)
	private String fullName;
	private String title;
	private String contact;
	private String domicile;
	private String about;
	@JsonManagedReference
	@OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Experience> experiences;
	@JsonManagedReference
	@OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Study> studies;
	@JsonManagedReference
	@OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Skill> skills;
	@JsonManagedReference
	@OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Project> projects;
}