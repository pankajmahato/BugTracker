package com.mindtree.bugtracker.persistence.impl;

import java.util.Date;

import com.mindtree.bugtracker.model.Bug;
import com.mindtree.bugtracker.model.Role;
import com.mindtree.bugtracker.model.Status;
import com.mindtree.bugtracker.model.User;

public class Main {

	public static void main(String[] args) {
		Bug bug = new Bug();
		User user = new User();
		user.setName("name");
		user.setPassword("password");
		user.setRole(Role.USER);

		User support = new User();
		support.setName("name");
		support.setPassword("password");
		support.setRole(Role.SUPPORT);

		bug.setDateSubmitted(new Date());
		bug.setDescription("description");
		bug.setStatus(Status.OPEN);
		bug.setSupport(support);
		bug.setTitle("title");
		bug.setUser(user);

		PersistenceManager manager = new PersistenceManager();
		manager.addBug(bug);
		System.out.println("success");
	}

}
