package com.mindtree.bugtracker.service.impl;

import java.util.List;

import com.mindtree.bugtracker.dto.BugDto;
import com.mindtree.bugtracker.dto.LoginDto;
import com.mindtree.bugtracker.model.Bug;
import com.mindtree.bugtracker.model.Employee;
import com.mindtree.bugtracker.model.Status;
import com.mindtree.bugtracker.persistence.impl.PersistenceManagerImpl;
import com.mindtree.bugtracker.persistence.interfaces.PersistenceManager;
import com.mindtree.bugtracker.service.interfaces.Service;

public class ServiceImpl implements Service {

	PersistenceManager persistenceManager = new PersistenceManagerImpl();

	@Override
	public Bug addBug(Bug bug) {

		return persistenceManager.addBug(bug);
	}

	@Override
	public LoginDto validateLogin(LoginDto loginDto) {

		Employee employee = new Employee();
		employee.setName(loginDto.getName());
		employee.setPassword(loginDto.getPassword());

		employee = persistenceManager.validateEmployee(employee);
		if (employee != null) {
			loginDto.setId(employee.getId());
			loginDto.setRole(employee.getRole());
			loginDto.setUser(employee.getUser());
			loginDto.setSupport(employee.getSupport());
		} else {
			loginDto = null;
		}
		return loginDto;
	}

	@Override
	public List<Bug> getBugs(Employee employee) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BugDto> getAllBugs(Status status) {
		return null;
	}

}
