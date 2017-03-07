package com.vanloi.controller;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.vanloi.model.Student;
import com.vanloi.model.User;
import com.vanloi.services.StudentService;
import com.vanloi.services.UserService;

@Controller
@SessionAttributes("userLogin")
public class StudentController {
	public static int NUMBER_RECORD_IN_PAGE = 10;
	
	@Autowired
	private UserService userService;
	@Autowired
	private StudentService studentService;
	
	// get request page login page
	@RequestMapping(value = {"login" }, method = RequestMethod.GET)
	public String getLoginPage(Model model, HttpSession session) {
		if (session.getAttribute("userLogin") != null)
			return "magager";
		User user = new User();
		model.addAttribute("user", user);
		return "login";
	}
	
	// process login page
	@RequestMapping(value = {"login" }, method = RequestMethod.POST)
	public String processLogin(User userLogin, BindingResult result, Model model) {
		if (result.hasErrors())
			return "login";
		User user = userService.getUserByUserName(userLogin.getUserName());
		if (user != null && userLogin.getPassword().equals(user.getPassword())) {
			return loadPage("0", model);
		} else {
			model.addAttribute("message", "username and password incorect!!");
			model.addAttribute("user", new User());
			return "login";
		}
	}
	
	@RequestMapping(value = { "/liststudent/" }, method = RequestMethod.GET)
	public String loadPage(String page, Model model) {
		int pageNumber = Integer.valueOf(page);
		int startIndex = (pageNumber - 1) * NUMBER_RECORD_IN_PAGE;
		int pageLegth = (int) studentService.countAllStudent();

		List<Student> studentList = studentService.getAllStudentsLimit(startIndex, NUMBER_RECORD_IN_PAGE);
		model.addAttribute("studentList", studentList);
		model.addAttribute("pageNumber", pageNumber);
		model.addAttribute("pageLegth", pageLegth);
		return "redirect:manager.html";
	}
}