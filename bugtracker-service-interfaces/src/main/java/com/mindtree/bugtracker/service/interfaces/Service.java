package com.mindtree.bugtracker.service.interfaces;

import java.util.List;

import com.mindtree.bugtracker.dto.BugDto;
import com.mindtree.bugtracker.dto.LoginDto;
import com.mindtree.bugtracker.model.Bug;
import com.mindtree.bugtracker.model.Employee;
import com.mindtree.bugtracker.model.Status;

public interface Service {

	Bug addBug(Bug bug);

	LoginDto validateLogin(LoginDto loginDto);

	List<Bug> getBugs(Employee employee);

	List<BugDto> getAllBugs(Status status);
}