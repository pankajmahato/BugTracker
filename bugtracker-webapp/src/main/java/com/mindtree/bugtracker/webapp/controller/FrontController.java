package com.mindtree.bugtracker.webapp.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
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

@Controller
public class FrontController {

	Service service = new ServiceImpl();

	/*
	 * @RequestMapping(value = "/login", method = RequestMethod.GET) public
	 * ModelAndView login() { ModelAndView modelAndView = new ModelAndView();
	 * modelAndView.setViewName("login"); return modelAndView; }
	 */

	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView forbidden() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("403");
		return modelAndView;
	}

	@RequestMapping(value = "/login")
	public ModelAndView login(@RequestParam(value = "error", required = false) String err,
			@RequestParam(value = "logout", required = false) String logout) {
		ModelAndView modelAndView = new ModelAndView();
		if (err != null) {
			modelAndView.addObject("error", "Invalid username and password!");
		}
		if (logout != null) {
			modelAndView.addObject("message", "You've been logged out successfully.");
		}
		modelAndView.setViewName("login");
		return modelAndView;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";
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

	@RequestMapping(value = "/home.do", method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();

		EmployeeDto loginDto = new EmployeeDto();
		loginDto = ((EmployeeDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		String target = "login";
		BugListDto bugListDto = new BugListDto();
		List<EmployeeDto> employeeDtoList = null;

		if (loginDto.getRole().equals(Role.ROLE_USER)) {
			target = "submitBug";
			bugListDto.setBugListDto(service.getAllBugs(Status.IN_PROGRESS, loginDto));
			modelAndView.addObject("userBugs", bugListDto);
			modelAndView.addObject("userId", loginDto.getId());
		} else if (loginDto.getRole().equals(Role.ROLE_SUPPORT)) {
			target = "reviewBug";
			bugListDto.setBugListDto(service.getAllBugs(Status.IN_PROGRESS, loginDto));
			modelAndView.addObject("bugListDto", bugListDto);
		} else if (loginDto.getRole().equals(Role.ROLE_ADMIN)) {
			target = "assignBug";
			bugListDto.setBugListDto(service.getAllBugs(Status.OPEN, loginDto));
			employeeDtoList = service.getAllEmployee(Role.ROLE_SUPPORT);
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
