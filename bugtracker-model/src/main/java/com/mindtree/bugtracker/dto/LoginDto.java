package com.mindtree.bugtracker.dto;

import java.util.Set;

import com.mindtree.bugtracker.model.Bug;
import com.mindtree.bugtracker.model.Role;

public class LoginDto {

	private int id;
	private String name;
	private String password;
	private Role role;
	private Set<Bug> user;
	private Set<Bug> support;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Set<Bug> getUser() {
		return user;
	}

	public void setUser(Set<Bug> user) {
		this.user = user;
	}

	public Set<Bug> getSupport() {
		return support;
	}

	public void setSupport(Set<Bug> support) {
		this.support = support;
	}

}
