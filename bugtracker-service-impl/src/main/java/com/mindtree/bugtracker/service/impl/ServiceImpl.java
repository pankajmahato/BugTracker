package com.mindtree.bugtracker.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.mindtree.bugtracker.dto.BugDto;
import com.mindtree.bugtracker.dto.EmployeeDto;
import com.mindtree.bugtracker.model.Bug;
import com.mindtree.bugtracker.model.Employee;
import com.mindtree.bugtracker.model.Role;
import com.mindtree.bugtracker.model.Status;
import com.mindtree.bugtracker.persistence.impl.PersistenceManagerImpl;
import com.mindtree.bugtracker.persistence.interfaces.PersistenceManager;
import com.mindtree.bugtracker.service.interfaces.Service;

public class ServiceImpl implements Service {

	PersistenceManager persistenceManager = new PersistenceManagerImpl();

	@Override
	public BugDto addBug(BugDto bugDto) {

		Bug bug = new Bug();
		Employee support = new Employee();
		if (bugDto.getSupport() != null) {
			support.setId(bugDto.getSupport().getId());
		} else {
			support = null;
		}

		Employee user = new Employee();
		if (bugDto.getUser() != null) {
			user.setId(bugDto.getUser().getId());
		} else {
			user = null;
		}

		bug.setDateSubmitted(bugDto.getDateSubmitted());
		bug.setDescription(bugDto.getDescription());
		bug.setStatus(bugDto.getStatus());
		bug.setSupport(support);
		bug.setTitle(bugDto.getTitle());
		bug.setUser(user);

		bug = persistenceManager.addBug(bug);

		bugDto.setId(bug.getId());
		return bugDto;
	}

	@Override
	public EmployeeDto validateLogin(EmployeeDto employeeDto) {

		Employee employee = new Employee();
		employee.setName(employeeDto.getName());
		employee.setPassword(employeeDto.getPassword());

		employee = persistenceManager.validateEmployee(employee);
		if (employee != null) {
			employeeDto.setId(employee.getId());
			employeeDto.setRole(employee.getRole());
			employeeDto.setUser(employee.getUser());
			employeeDto.setSupport(employee.getSupport());
		} else {
			employeeDto = null;
		}
		return employeeDto;
	}

	@Override
	public EmployeeDto getEmployee(int id) {

		EmployeeDto employeeDto = new EmployeeDto();
		Employee employee = new Employee();

		employee = persistenceManager.getEmployee(id);
		if (employee != null) {
			employeeDto.setId(employee.getId());
			employeeDto.setName(employee.getName());
			employeeDto.setRole(employee.getRole());
			employeeDto.setUser(employee.getUser());
			employeeDto.setSupport(employee.getSupport());
		} else {
			employeeDto = null;
		}
		return employeeDto;
	}

	@Override
	public List<Bug> getBugs(Employee employee) {
		return null;
	}

	@Override
	public List<BugDto> getAllBugs(Status status) {

		List<BugDto> bugListDto = new ArrayList<>();
		List<Bug> bugList = null;

		BugDto bugDto = null;

		bugList = persistenceManager.getAllBugs(status);

		for (Bug b : bugList) {
			bugDto = new BugDto();
			bugDto.setDateSubmitted(b.getDateSubmitted());
			bugDto.setDescription(b.getDescription());
			bugDto.setId(b.getId());
			bugDto.setStatus(b.getStatus());

			EmployeeDto employeeDto = new EmployeeDto();
			Employee employee = b.getSupport();
			if (employee != null) {
				employeeDto.setId(employee.getId());
				employeeDto.setName(employee.getName());
				employeeDto.setPassword(employee.getPassword());
				employeeDto.setRole(employee.getRole());
			} else {
				employeeDto = null;
			}
			bugDto.setSupport(employeeDto);
			bugDto.setTitle(b.getTitle());

			employeeDto = new EmployeeDto();
			employee = b.getUser();
			if (employee != null) {
				employeeDto.setId(employee.getId());
				employeeDto.setName(employee.getName());
				employeeDto.setPassword(employee.getPassword());
				employeeDto.setRole(employee.getRole());
			} else {
				employeeDto = null;
			}

			bugDto.setUser(employeeDto);

			bugListDto.add(bugDto);

		}

		return bugListDto;
	}

	@Override
	public BugDto getBug(int id) {

		BugDto bugDto = new BugDto();
		Bug bug = new Bug();

		bug = persistenceManager.getBug(id);
		if (bug != null) {
			bugDto.setDateSubmitted(bug.getDateSubmitted());
			bugDto.setDescription(bug.getDescription());
			bugDto.setId(bug.getId());
			bugDto.setStatus(bug.getStatus());

			EmployeeDto employeeDto = new EmployeeDto();
			Employee employee = bug.getSupport();
			if (employee != null) {
				employeeDto.setId(employee.getId());
				employeeDto.setName(employee.getName());
				employeeDto.setPassword(employee.getPassword());
				employeeDto.setRole(employee.getRole());
			} else {
				employeeDto = null;
			}
			bugDto.setSupport(employeeDto);
			bugDto.setTitle(bug.getTitle());

			employeeDto = new EmployeeDto();
			employee = bug.getUser();
			if (employee != null) {
				employeeDto.setId(employee.getId());
				employeeDto.setName(employee.getName());
				employeeDto.setPassword(employee.getPassword());
				employeeDto.setRole(employee.getRole());
			} else {
				employeeDto = null;
			}

			bugDto.setUser(employeeDto);
		} else {
			bugDto = null;
		}
		return bugDto;
	}

	@Override
	public List<EmployeeDto> getAllEmployee(Role role) {

		List<EmployeeDto> employeeDtoList = new ArrayList<>();
		EmployeeDto employeeDto = null;
		// Employee employee = new Employee();
		List<Employee> employeeList = null;

		employeeList = persistenceManager.getAllEmployee(role);
		for (Employee employee : employeeList) {
			if (employee != null) {
				employeeDto = new EmployeeDto();
				employeeDto.setId(employee.getId());
				employeeDto.setName(employee.getName());
				employeeDto.setRole(employee.getRole());
				employeeDto.setUser(employee.getUser());
				employeeDto.setSupport(employee.getSupport());
			} else {
				employeeDto = null;
			}
			employeeDtoList.add(employeeDto);
		}
		return employeeDtoList;
	}
}
