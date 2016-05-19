package com.mindtree.bugtracker.service.impl;

import com.mindtree.bugtracker.model.Bug;
import com.mindtree.bugtracker.persistence.impl.PersistenceManager;

public class Service {

	public Bug addBug(Bug bug) {

		PersistenceManager persistenceManager = new PersistenceManager();

		return persistenceManager.addBug(bug);
	}

}
