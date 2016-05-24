package com.mindtree.bugtracker.webapp.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mindtree.bugtracker.dto.BugDto;
import com.mindtree.bugtracker.dto.BugListDto;
import com.mindtree.bugtracker.dto.EmployeeDto;
import com.mindtree.bugtracker.model.Role;
import com.mindtree.bugtracker.model.Status;
import com.mindtree.bugtracker.service.impl.ServiceImpl;
import com.mindtree.bugtracker.service.interfaces.Service;
import com.mindtree.bugtracker.webapp.dto.AuthenticationDetailsDto;

@Controller
public class FrontController {

	Service service = new ServiceImpl();

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}

	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView forbidden() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("403");
		return modelAndView;
	}

	@RequestMapping(value = "/login1", method = RequestMethod.POST)
	public ModelAndView login(@RequestParam(value = "err", required = false) String err,
			@RequestParam(value = "logout", required = false) String logout) {
		ModelAndView modelAndView = new ModelAndView();
		if (err != null) {
			modelAndView.addObject("err", "Invalid username and password!");
		}
		if (logout != null) {
			modelAndView.addObject("msg", "You've been logged out successfully.");
		}
		modelAndView.setViewName("login");
		return modelAndView;
	}

	@RequestMapping(value = "/submitBug.do", method = RequestMethod.POST)
	public ModelAndView submitBug(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();

		EmployeeDto employeeDto = new EmployeeDto();
		int id = Integer.parseInt(request.getParameter("userId"));

		employeeDto.setId(id);

		BugDto bugDto = new BugDto();
		bugDto.setDateSubmitted(new Date());
		bugDto.setDescription(request.getParameter("description"));
		bugDto.setStatus(Status.OPEN);
		bugDto.setTitle(request.getParameter("title"));
		bugDto.setUser(employeeDto);

		service.addBug(bugDto);

		modelAndView.setViewName("home");
		return modelAndView;
	}

	@RequestMapping(value = "/home.do", method = RequestMethod.POST)
	public ModelAndView home(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		EmployeeDto loginDto = new EmployeeDto();
		loginDto.setName(username);
		loginDto.setPassword(password);
		System.out.println((SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
		loginDto = ((AuthenticationDetailsDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getEmployeeDto();
		String target = "login";
		// List<Bug> bugList = null;
		BugListDto bugListDto = new BugListDto();
		List<EmployeeDto> employeeDtoList = null;

		if (loginDto == null) {
			target = "login";
			modelAndView.addObject("message", "Invalid Login credentials");
		} else if (loginDto.getRole().equals(Role.USER)) {
			target = "submitBug";
			bugListDto.setBugListDto(service.getAllBugs(Status.IN_PROGRESS, loginDto));
			modelAndView.addObject("userBugs", bugListDto);
			modelAndView.addObject("userId", loginDto.getId());
			/*
			 * modelAndView.addObject("userBugs", loginDto.getUser());
			 * modelAndView.addObject("userId", loginDto.getId());
			 */
		} else if (loginDto.getRole().equals(Role.SUPPORT)) {
			target = "reviewBug";
			bugListDto.setBugListDto(service.getAllBugs(Status.IN_PROGRESS, loginDto));
			modelAndView.addObject("bugListDto", bugListDto);
			/*
			 * modelAndView.addObject("supportBugs", loginDto.getSupport());
			 * modelAndView.addObject("supportId", loginDto.getId());
			 */
		} else if (loginDto.getRole().equals(Role.ADMIN)) {
			target = "assignBug";
			bugListDto.setBugListDto(service.getAllBugs(Status.OPEN, loginDto));
			employeeDtoList = service.getAllEmployee(Role.SUPPORT);
			modelAndView.addObject("bugListDto", new BugListDto());
			modelAndView.addObject("adminBugs", bugListDto);
			modelAndView.addObject("employeeList", employeeDtoList);
		} else {
			target = "login";
			modelAndView.addObject("message", "Invalid Login credentials");
		}
		modelAndView.setViewName(target);
		return modelAndView;
	}

	@RequestMapping(value = "/assignBug.do", method = RequestMethod.POST)
	public ModelAndView assignBug(@ModelAttribute("bugListDto") BugListDto bugListDto) {
		ModelAndView modelAndView = new ModelAndView();

		service.assignBugs(bugListDto);

		modelAndView.setViewName("home");
		return modelAndView;
	}

	@RequestMapping(value = "/reviewBug.do", method = RequestMethod.POST)
	public ModelAndView reviewBug(@ModelAttribute("bugListDto") BugListDto bugListDto) {
		ModelAndView modelAndView = new ModelAndView();

		service.reviewBugs(bugListDto);

		modelAndView.setViewName("home");
		return modelAndView;
	}
}
