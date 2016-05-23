package com.mindtree.bugtracker.service.interfaces;

import java.util.List;

import com.mindtree.bugtracker.dto.BugDto;
import com.mindtree.bugtracker.dto.BugListDto;
import com.mindtree.bugtracker.dto.EmployeeDto;
import com.mindtree.bugtracker.model.Bug;
import com.mindtree.bugtracker.model.Employee;
import com.mindtree.bugtracker.model.Role;
import com.mindtree.bugtracker.model.Status;

public interface Service {

	BugDto addBug(BugDto bugDto);

	EmployeeDto validateLogin(EmployeeDto loginDto);

	List<Bug> getBugs(Employee employee);

	List<BugDto> getAllBugs(Status status, EmployeeDto employeeDto);

	EmployeeDto getEmployee(int id);

	BugDto getBug(int id);

	List<EmployeeDto> getAllEmployee(Role role);

	boolean assignBugs(BugListDto bugListDto);

	boolean reviewBugs(BugListDto bugListDto);
}