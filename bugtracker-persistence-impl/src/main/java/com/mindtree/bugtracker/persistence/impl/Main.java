package com.mindtree.bugtracker.persistence.impl;

import java.util.Date;

import com.mindtree.bugtracker.model.Bug;
import com.mindtree.bugtracker.model.Role;
import com.mindtree.bugtracker.model.Status;
import com.mindtree.bugtracker.model.Employee;
import com.mindtree.bugtracker.persistence.interfaces.PersistenceManager;

public class Main {

	public static void main(String[] args) {
		Bug bug = new Bug();
		Employee user = new Employee();
		user.setName("name");
		user.setPassword("password");
		user.setRole(Role.USER);

		Employee support = new Employee();
		support.setName("name");
		support.setPassword("password");
		support.setRole(Role.SUPPORT);

		bug.setDateSubmitted(new Date());
		bug.setDescription("description");
		bug.setStatus(Status.OPEN);
		bug.setSupport(support);
		bug.setTitle("title");
		bug.setUser(user);

		PersistenceManager manager = new PersistenceManagerImpl();
		manager.addBug(bug);
		System.out.println("success");
	}

}
