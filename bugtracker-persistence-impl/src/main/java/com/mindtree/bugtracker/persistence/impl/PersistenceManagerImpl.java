package com.mindtree.bugtracker.persistence.impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.mindtree.bugtracker.model.Bug;

public class PersistenceManager {

	public Bug addBug(Bug bug) {

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPersistence");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		entityManager.getTransaction().begin();
		entityManager.persist(bug);
		entityManager.getTransaction().commit();
		entityManager.close();
		return bug;
	}
}
