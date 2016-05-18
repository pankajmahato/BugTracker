package com.mindtree.bugtracker.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "User")
@SequenceGenerator(name = "user_seq", sequenceName = "USER_SEQ")
public class User {

	@Id
	@Column(name = "userId")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "user_seq")
	private int userId;

	@Column(name = "name")
	private String name;

	@Column(name = "password")
	private String password;

	@Column(name = "role")
	@Enumerated(EnumType.STRING)
	private Role role;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private Set<Bug> bugs;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "support")
	private Set<Bug> support;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Set<Bug> getBugs() {
		return bugs;
	}

	public void setBugs(Set<Bug> bugs) {
		this.bugs = bugs;
	}

	public Set<Bug> getSupport() {
		return support;
	}

	public void setSupport(Set<Bug> support) {
		this.support = support;
	}

}
