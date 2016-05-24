package com.mindtree.bugtracker.webapp.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.mindtree.bugtracker.dto.EmployeeDto;

public class AuthenticationDetailsDto extends User {

	private static final long serialVersionUID = -4356766453895065895L;

	private EmployeeDto employeeDto;

	public AuthenticationDetailsDto(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	}

	public AuthenticationDetailsDto(String username, String password,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

	public AuthenticationDetailsDto(String username, String password,
			Collection<? extends GrantedAuthority> authorities, EmployeeDto employeeDto) {
		super(username, password, authorities);
		this.employeeDto = employeeDto;
	}

	public EmployeeDto getEmployeeDto() {
		return employeeDto;
	}

	public void setEmployeeDto(EmployeeDto employeeDto) {
		this.employeeDto = employeeDto;
	}

}
