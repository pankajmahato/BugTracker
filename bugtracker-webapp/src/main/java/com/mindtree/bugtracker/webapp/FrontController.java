package com.mindtree.bugtracker.webapp;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mindtree.bugtracker.model.Bug;
import com.mindtree.bugtracker.model.Role;
import com.mindtree.bugtracker.model.Status;
import com.mindtree.bugtracker.model.User;
import com.mindtree.bugtracker.service.impl.Service;

@Controller
public class FrontController {

	@RequestMapping(value = "/home.do", method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("home");

		Service service = new Service();
		Bug bug = new Bug();
		User user = new User();
		user.setName("name");
		user.setPassword("password");
		user.setRole(Role.USER);

		User support = new User();
		support.setName("name");
		support.setPassword("password");
		support.setRole(Role.SUPPORT);

		bug.setDateSubmitted(new Date());
		bug.setDescription("description");
		bug.setStatus(Status.OPEN);
		bug.setSupport(support);
		bug.setTitle("title");
		bug.setUser(user);
		service.addBug(bug);
		System.out.println("bug added");
		return modelAndView;
	}
}
