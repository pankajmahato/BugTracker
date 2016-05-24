package com.mindtree.bugtracker.webapp.auth;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.mindtree.bugtracker.dto.EmployeeDto;
import com.mindtree.bugtracker.service.impl.ServiceImpl;
import com.mindtree.bugtracker.service.interfaces.Service;
import com.mindtree.bugtracker.webapp.dto.AuthenticationDetailsDto;

public class AuthProvider implements AuthenticationProvider {

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();

		Service service = new ServiceImpl();
		System.out.println("inside AuthProvider");

		EmployeeDto loginDto = new EmployeeDto();
		loginDto.setName(username);
		loginDto.setPassword(password);
		loginDto = service.validateLogin(loginDto);

		if (loginDto != null) {
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			// for (String role : details.getAuthorities()) {
			authorities.add(new SimpleGrantedAuthority(loginDto.getRole().toString()));
			// }

			UserDetails userDetails = new AuthenticationDetailsDto(loginDto.getName(), loginDto.getPassword(),
					authorities, loginDto);
			return new UsernamePasswordAuthenticationToken(userDetails, loginDto.getPassword(), authorities);
		} else {
			return null;
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
