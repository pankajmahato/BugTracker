package com.mindtree.bugtracker.persistence.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.mindtree.bugtracker.model.Bug;
import com.mindtree.bugtracker.model.Employee;
import com.mindtree.bugtracker.model.Role;
import com.mindtree.bugtracker.model.Status;
import com.mindtree.bugtracker.persistence.interfaces.PersistenceManager;

public class PersistenceManagerImpl implements PersistenceManager {

	private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPersistence");

	@Override
	public Bug addBug(Bug bug) {

		Employee employee = null;
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		int id = 0;

		if (entityManager != null) {
			try {

				if (bug.getSupport() != null) {
					id = bug.getSupport().getId();
					employee = entityManager.find(Employee.class, id);
					bug.setSupport(employee);
				} else if (bug.getUser() != null) {
					id = bug.getUser().getId();
					employee = entityManager.find(Employee.class, id);
					employee.setUser(null);
					employee.setSupport(null);
					bug.setUser(employee);
				}
				entityManager.getTransaction().begin();
				entityManager.persist(bug);
				entityManager.getTransaction().commit();
			} catch (PersistenceException persistenceException) {
				entityManager.getTransaction().rollback();
				persistenceException.printStackTrace();
			} finally {
				if (entityManager != null) {
					entityManager.close();
				}
			}
		}
		return bug;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Bug> getBugs(Employee employee) {

		List<Bug> bugList = null;
		String bugQuery;

		if (employee.getRole().equals(Role.USER)) {
			bugQuery = "SELECT b from Bug b WHERE b.user.id=:id";
		} else {
			bugQuery = "SELECT b from Bug b WHERE b.support.id=:id";
		}

		EntityManager entityManager = entityManagerFactory.createEntityManager();

		Query query = entityManager.createQuery(bugQuery);
		query.setParameter("id", employee.getId());
		if (entityManager != null) {
			try {
				bugList = (List<Bug>) query.getResultList();
			} catch (PersistenceException persistenceException) {
				persistenceException.printStackTrace();
			} finally {
				if (entityManager != null) {
					entityManager.close();
				}
			}
		}
		return bugList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Bug> getAllBugs(Status status, Employee employee) {
		List<Bug> bugList = null;
		String bugQuery;

		if (status != null) {
			bugQuery = "SELECT b from Bug b WHERE b.status=:status";
		} else {
			bugQuery = "SELECT b from Bug b";
		}
		if (employee.getRole().equals(Role.USER)) {
			bugQuery += " AND b.user.id=:id";
		} else if (employee.getRole().equals(Role.SUPPORT)) {
			bugQuery += " AND b.support.id=:id";
		}
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		Query query = entityManager.createQuery(bugQuery);
		if (status != null) {
			query.setParameter("status", status);
		}
		if (!employee.getRole().equals(Role.ADMIN)) {
			query.setParameter("id", employee.getId());
		}
		if (entityManager != null) {
			try {
				bugList = (List<Bug>) query.getResultList();
			} catch (PersistenceException persistenceException) {
				persistenceException.printStackTrace();
			} finally {
				if (entityManager != null) {
					entityManager.close();
				}
			}
		}
		return bugList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Employee validateEmployee(Employee employee) {

		List<Employee> employeeList = null;

		String loginQuery;

		loginQuery = "from Employee e WHERE e.name=:username";
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Query query = entityManager.createQuery(loginQuery);
		query.setParameter("username", employee.getName());
		if (entityManager != null) {
			try {
				employeeList = (List<Employee>) query.getResultList();
				if (employeeList != null && employeeList.size() == 1) {
					employee = employeeList.get(0);
					// employee.setPassword(null);
				} else {
					employee = null;
				}
			} catch (PersistenceException persistenceException) {
				persistenceException.printStackTrace();
			} finally {
				if (entityManager != null) {
					entityManager.close();
				}
			}
		}
		return employee;
	}

	@Override
	public Employee getEmployee(int id) {

		Employee employee = null;

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		if (entityManager != null) {
			try {
				employee = entityManager.find(Employee.class, id);
			} catch (PersistenceException persistenceException) {
				persistenceException.printStackTrace();
			} finally {
				if (entityManager != null) {
					entityManager.close();
				}
			}
		}
		return employee;
	}

	@Override
	public Bug getBug(int id) {

		Bug bug = null;

		EntityManager entityManager = entityManagerFactory.createEntityManager();
		if (entityManager != null) {
			try {
				bug = entityManager.find(Bug.class, id);
			} catch (PersistenceException persistenceException) {
				persistenceException.printStackTrace();
			} finally {
				if (entityManager != null) {
					entityManager.close();
				}
			}
		}
		return bug;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> getAllEmployee(Role role) {

		List<Employee> employeeList = null;

		String allEmployeeQuery;

		allEmployeeQuery = "from Employee e where e.role=:role";
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Query query = entityManager.createQuery(allEmployeeQuery);
		query.setParameter("role", role);

		if (entityManager != null) {
			try {
				employeeList = (List<Employee>) query.getResultList();
			} catch (PersistenceException persistenceException) {
				persistenceException.printStackTrace();
			} finally {
				if (entityManager != null) {
					entityManager.close();
				}
			}
		}
		return employeeList;
	}

	@Override
	public boolean assignBugs(List<Bug> bugs) {

		boolean success = true;

		Bug bug = null;
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		if (entityManager != null) {
			try {
				if (bugs != null) {
					entityManager.getTransaction().begin();
					for (Bug b : bugs) {
						bug = entityManager.find(Bug.class, b.getId());
						if (bug != null) {
							bug.setStatus(Status.IN_PROGRESS);

							Employee support = entityManager.find(Employee.class, b.getSupport().getId());
							support.setId(b.getSupport().getId());

							bug.setSupport(support);

							entityManager.persist(bug);
						}
					}
					entityManager.getTransaction().commit();
				}
			} catch (PersistenceException persistenceException) {
				persistenceException.printStackTrace();
				success = false;
			} finally {
				if (entityManager != null) {
					entityManager.close();
				}
			}
		}
		return success;
	}

	@Override
	public boolean reviewBugs(List<Bug> bugs) {

		boolean success = true;

		Bug bug = null;
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		if (entityManager != null) {
			try {
				if (bugs != null) {
					entityManager.getTransaction().begin();
					for (Bug b : bugs) {
						bug = entityManager.find(Bug.class, b.getId());
						if (bug != null) {
							bug.setStatus(Status.CLOSED);

							entityManager.persist(bug);
						}
					}
					entityManager.getTransaction().commit();
				}
			} catch (PersistenceException persistenceException) {
				persistenceException.printStackTrace();
				success = false;
			} finally {
				if (entityManager != null) {
					entityManager.close();
				}
			}
		}
		return success;
	}
}
