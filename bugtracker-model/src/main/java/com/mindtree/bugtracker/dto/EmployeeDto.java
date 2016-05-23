package com.mindtree.bugtracker.dto;

import java.util.List;

import com.mindtree.bugtracker.model.Bug;
import com.mindtree.bugtracker.model.Role;

public class EmployeeDto {

	private int id;
	private String name;
	private String password;
	private Role role;
	private List<Bug> user;
	private List<Bug> support;

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

	public List<Bug> getUser() {
		return user;
	}

	public void setUser(List<Bug> user) {
		this.user = user;
	}

	public List<Bug> getSupport() {
		return support;
	}

	public void setSupport(List<Bug> support) {
		this.support = support;
	}

}
