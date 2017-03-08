package com.vanloi.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.vanloi.model.Student;
import com.vanloi.model.StudentInfo;
import com.vanloi.model.User;
import com.vanloi.services.StudentService;
import com.vanloi.services.UserService;

@Controller
@SessionAttributes({ "userLogin", "page", "pageLength" })
public class StudentController {
	public static int NUMBER_RECORD_IN_PAGE = 10;

	@Autowired
	private UserService userService;
	@Autowired
	private StudentService studentService;

	// LOGIN
	// get request page login page
	@RequestMapping(value = { "login" }, method = RequestMethod.GET)
	public String getLoginPage(Model model, HttpSession session) {
		if (session.getAttribute("userLogin") != null)
			return "magager";
		User user = new User();
		model.addAttribute("userLogin", user);
		return "login";
	}

	// process login page
	@RequestMapping(value = { "login" }, method = RequestMethod.POST)
	public String processLogin(@Valid User userLogin, BindingResult result, Model model, HttpSession session) {
		if (result.hasErrors()) {
			return "login";
		}
		User user = userService.getUserByUserName(userLogin.getUserName());
		if (user != null && userLogin.getPassword().equals(user.getPassword())) {
			model.addAttribute("userLogin", user);
			model.addAttribute("page", "1");
			
			return loadPage(model, session);
		} else

		{
			model.addAttribute("message", "username and password incorect!!");
			model.addAttribute("user", new User());
			return "redirect:login.html";
		}
	}

	@RequestMapping(value = { "manager" }, method = RequestMethod.POST)
	public String processLogin2(@Valid User userLogin, BindingResult result, Model model, HttpSession session) {
		if (result.hasErrors())
			return "login";
		User user = userService.getUserByUserName(userLogin.getUserName());
		if (user != null && userLogin.getPassword().equals(user.getPassword())) {
			model.addAttribute("userLogin", user);
			model.addAttribute("page", "1");
			session.setAttribute("userLogin", user);
			session.setAttribute("page", "1");
			return loadPage(model, session);
		} else {
			return "redirect:login.html";
		}
	}

	@RequestMapping(value = { "manager/{pageNumber}" }, method = RequestMethod.GET)
	public String processLoadPage(@PathVariable("pageNumber") String pageNumber, Model model, HttpSession session) {
		session.setAttribute("page", pageNumber);
		model.addAttribute("page", pageNumber);
		return loadPage(model, session);
	}

	@RequestMapping(value = { "/liststudent/" }, method = RequestMethod.GET)
	public String loadPage(Model model, HttpSession session) {
		String userName = ((User) session.getAttribute("userLogin")).getUserName();
		String page = (String) session.getAttribute("page");
		int pageNumber = Integer.valueOf(page);
		int startIndex = (pageNumber - 1) * NUMBER_RECORD_IN_PAGE;
		int pageLength = (int) Math.ceil(studentService.countAllStudent() / (double) NUMBER_RECORD_IN_PAGE);

		List<Student> studentList = studentService.getAllStudentsLimit(startIndex, NUMBER_RECORD_IN_PAGE);
		if (studentList.size() == 0 && pageNumber >= 1) {
			startIndex = --pageNumber * NUMBER_RECORD_IN_PAGE;
			studentList = studentService.getAllStudentsLimit(startIndex, NUMBER_RECORD_IN_PAGE);
		}

		model.addAttribute("studentList", studentList);
		model.addAttribute("pageNumber", pageNumber);
		model.addAttribute("pageLength", pageLength);
		model.addAttribute("userName", userName);
		return "manager";
	}

	// recieve request edit of a student from showlist page or editlist page
	@RequestMapping(value = { "/manager/edit/{student_id}/{page}" }, method = { RequestMethod.GET })
	public String editStudent(@ModelAttribute("student_id") String student_id, @ModelAttribute("page") String page,
			Model model) {
		Student student = studentService.getStudent(Integer.valueOf(student_id));
		model.addAttribute("student", student);
		return "register-student";
	}

	// CRUD
	// recive request mapping url
	@RequestMapping(value = { "register-student" }, method = RequestMethod.GET)
	public String requestMappingRegisterStudent(Model model) {
		model.addAttribute(new Student());
		return "register-student";
	}

	// process add student
	@RequestMapping(value = { "register-student" }, method = RequestMethod.POST)
	public String requestAddNewRegisterStudent(@Valid Student student, Model model, HttpSession session) {
		studentService.addStudent(student);
		String pageLength = session.getAttribute("pageLength").toString();
		session.setAttribute("page", pageLength);
		model.addAttribute("page", pageLength);
		return loadPage(model, session);
	}

	@RequestMapping(value = { "/manager/edit/{student_id}" }, method = { RequestMethod.GET })
	public String deleteStudent(@ModelAttribute("student_id") String student_id, @ModelAttribute("page") String page,
			Model model, HttpSession session) {
		studentService.deleteStudent(Integer.valueOf(student_id));
		return loadPage(model, session);
	}

	@RequestMapping(value = { "/manager/edit/{student_id}/{page}" }, method = { RequestMethod.POST })
	public String editStudent(@ModelAttribute("student") Student student, Model model,
			@ModelAttribute("student_id") String student_id, @ModelAttribute("page") String page, HttpSession session) {
		student.setStudentId(Integer.valueOf(student_id));
		studentService.updateStudent(student);

		model.addAttribute("search", "");
		return loadPage(model, session);
	}

	@RequestMapping(value = { "subject-manager" }, method = RequestMethod.GET)
	public String requestMappingSubject(Model model) {
		return "subject-manager";
	}

	@RequestMapping(value = { "register-manager" }, method = RequestMethod.GET)
	public String requestMappingRegisterSubject(Model model) {
		return "register-subject";
	}

}