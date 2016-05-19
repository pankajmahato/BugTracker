package com.mindtree.bugtracker.webapp;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mindtree.bugtracker.dto.LoginDto;
import com.mindtree.bugtracker.model.Bug;
import com.mindtree.bugtracker.model.Role;
import com.mindtree.bugtracker.service.impl.ServiceImpl;
import com.mindtree.bugtracker.service.interfaces.Service;

@Controller
public class FrontController {

	Service service = new ServiceImpl();

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView defaultLandingPage() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}

	@RequestMapping(value = "/submitBug.do", method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("home");
		return modelAndView;
	}

	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public ModelAndView login(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		LoginDto loginDto = new LoginDto();
		loginDto.setName(username);
		loginDto.setPassword(password);
		loginDto = service.validateLogin(loginDto);
		String target = "login";
		List<Bug> bugList = null;

		if (loginDto == null) {
		} else if (loginDto.getRole().equals(Role.USER)) {
			target = "submitBug";
			modelAndView.addObject("userBugs", loginDto.getUser());
		} else if (loginDto.getRole().equals(Role.SUPPORT)) {
			target = "reviewBug";
			modelAndView.addObject("supportBugs", loginDto.getSupport());
		} else if (loginDto.getRole().equals(Role.ADMIN)) {
			target = "assignBug";
		}
		modelAndView.setViewName(target);
		return modelAndView;
	}
}
