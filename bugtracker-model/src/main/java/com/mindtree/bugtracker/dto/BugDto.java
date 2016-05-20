package com.mindtree.bugtracker.dto;

import java.util.Date;

import com.mindtree.bugtracker.model.Status;

public class BugDto {

	private int id;
	private String title;
	private String description;
	private Date dateSubmitted;
	private EmployeeDto user;
	private String userId;
	private EmployeeDto support;
	private String supportId;
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

	public EmployeeDto getUser() {
		return user;
	}

	public void setUser(EmployeeDto user) {
		this.user = user;
	}

	public EmployeeDto getSupport() {
		return support;
	}

	public void setSupport(EmployeeDto support) {
		this.support = support;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSupportId() {
		return supportId;
	}

	public void setSupportId(String supportId) {
		this.supportId = supportId;
	}

}
