package com.mindtree.bugtracker.dto;

import java.util.Date;

import com.mindtree.bugtracker.model.Employee;
import com.mindtree.bugtracker.model.Status;

public class BugDto {

	private int id;
	private String title;
	private String description;
	private Date dateSubmitted;
	private Employee user;
	private Employee support;
	private Status status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDateSubmitted() {
		return dateSubmitted;
	}

	public void setDateSubmitted(Date dateSubmitted) {
		this.dateSubmitted = dateSubmitted;
	}

	public Employee getUser() {
		return user;
	}

	public void setUser(Employee user) {
		this.user = user;
	}

	public Employee getSupport() {
		return support;
	}

	public void setSupport(Employee support) {
		this.support = support;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}
