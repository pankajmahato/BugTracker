package com.mindtree.bugtracker.persistence.interfaces;

import java.util.List;

import com.mindtree.bugtracker.model.Bug;
import com.mindtree.bugtracker.model.Employee;
import com.mindtree.bugtracker.model.Role;
import com.mindtree.bugtracker.model.Status;

public interface PersistenceManager {

	Bug addBug(Bug bug);

	List<Bug> getBugs(Employee employee);

	List<Bug> getAllBugs(Status status, Employee employee);

	Employee validateEmployee(Employee employee);

	Employee getEmployee(int id);

	List<Employee> getAllEmployee(Role role);

	Bug getBug(int id);

	boolean assignBugs(List<Bug> bugs);

	boolean reviewBugs(List<Bug> bugs);

}