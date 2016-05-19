package com.mindtree.bugtracker.persistence.interfaces;

import java.util.List;

import com.mindtree.bugtracker.model.Bug;
import com.mindtree.bugtracker.model.Employee;
import com.mindtree.bugtracker.model.Status;

public interface PersistenceManager {

	Bug addBug(Bug bug);

	List<Bug> getBugs(Employee employee);

	List<Bug> getAllBugs(Status status);

	Employee validateEmployee(Employee employee);

}