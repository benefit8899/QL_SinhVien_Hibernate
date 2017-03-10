package com.vanloi.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.mysql.jdbc.StringUtils;
import com.vanloi.model.Student;
import com.vanloi.model.StudentInfo;
import com.vanloi.model.User;
import com.vanloi.services.StudentService;
import com.vanloi.services.UserService;

@Controller
@SessionAttributes({ "userLogin", "page", "pageLength", "userName", "student" })
public class StudentController {
	public static int NUMBER_RECORD_IN_PAGE = 10;

	private Logger logger = Logger.getLogger(StudentController.class);
	
	@Autowired
	private UserService userService;
	@Autowired
	private StudentService studentService;

	// ------------------ HOME-------------------
	@RequestMapping(value = { "/", "index" })
	public String requestMappingHome(Model model, HttpSession session) {
		String userName = (String) session.getAttribute("userName");
		userName = userName != null ? userName : ""; // Attribute not be null
		model.addAttribute("userName", userName);
		return "index";
	}

	// ----------------- LOGIN ------------------
	// get request page login page
	@RequestMapping(value = { "login" }, method = RequestMethod.GET)
	public String getLoginPage(Model model, HttpSession session) {
		User user = new User();
		model.addAttribute("userLogin", user);
		return "login";
	}

	// process login page
	@RequestMapping(value = { "manager" }, method = { RequestMethod.POST })
	public String processLogin2(@ModelAttribute("userLogin") @Valid User userLogin, BindingResult result, Model model,
			HttpSession session) {
		if (result.hasErrors())
			return "login";

		User user = userService.getUserByUserName(userLogin.getUserName());
		if (user != null && userLogin.getPassword().equals(user.getPassword())) {
			model.addAttribute("userName", user.getUserName());
			model.addAttribute("page", "1");
			session.setAttribute("userName", user.getUserName());
			session.setAttribute("page", "1");
			
			logger.info("This is an info log entry");
	        logger.error("This is an error log entry");
			
			return loadPage(model, session);
		} else {
			model.addAttribute("message", "user name and password incorect!!");
			return "redirect:login.html";
		}
	}

	// Process log out
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(Model model, HttpSession session) {
		session.removeAttribute("userName");
		model.addAttribute("userName", "");
		return "index";
	}

	// ----------------- MAIN PAGE -------------------
	@RequestMapping(value = { "manager/{pageNumber}" }, method = RequestMethod.GET)
	public String processLoadPage(@PathVariable("pageNumber") String pageNumber, Model model, HttpSession session) {

		String userName = (String) session.getAttribute("userName");
		// check for client type url
		if (StringUtils.isNullOrEmpty(userName))
			return "index";

		session.setAttribute("page", pageNumber);
		model.addAttribute("page", pageNumber);
		return loadPage(model, session);
	}

	@RequestMapping(value = { "manager" }, method = RequestMethod.GET)
	public String loadPage(Model model, HttpSession session) {

		String userName = (String) session.getAttribute("userName");

		// check for client type url
		if (StringUtils.isNullOrEmpty(userName))
			return "index";

		String page = (String) session.getAttribute("page");
		int pageNumber = Integer.valueOf(page);
		int startIndex = (pageNumber - 1) * NUMBER_RECORD_IN_PAGE;
		int pageLength = (int) Math.ceil(studentService.countAllStudent() / (double) NUMBER_RECORD_IN_PAGE);

		List<Student> studentList = studentService.getAllStudentsLimit(startIndex, NUMBER_RECORD_IN_PAGE);
		if (studentList.size() == 0 && pageNumber >= 1) {
			pageNumber--;
			startIndex = (pageNumber - 1) * NUMBER_RECORD_IN_PAGE;
			studentList = studentService.getAllStudentsLimit(startIndex, NUMBER_RECORD_IN_PAGE);
		}

		model.addAttribute("studentList", studentList);
		model.addAttribute("pageNumber", pageNumber);
		model.addAttribute("pageLength", pageLength);
		model.addAttribute("userName", userName);
		return "manager";
	}

	// ------------ CRUD -----------------
	// INSERT
	// recive request mapping url
	@RequestMapping(value = { "register-student" }, method = RequestMethod.GET)
	public String requestMappingRegisterStudent(Model model, HttpSession session) {

		String userName = (String) session.getAttribute("userName");

		// check for client type url
		if (StringUtils.isNullOrEmpty(userName))
			return "index";

		Student student = new Student();
		student.setStudentInfo(new StudentInfo());
		student.getStudentInfo().setDateOfBirth(new Date());
		model.addAttribute(student);
		return "register-student";
	}

	// process add student
	@RequestMapping(value = { "register-student" }, method = RequestMethod.POST)
	public String requestAddNewRegisterStudent(@Valid Student student, BindingResult result, Model model,
			HttpSession session) {
		if (result.hasErrors())
			return "register-student";
		studentService.addStudent(student);
		String pageLength = String
				.valueOf(((int) Math.ceil(studentService.countAllStudent() / (double) NUMBER_RECORD_IN_PAGE)));
		session.setAttribute("page", pageLength);
		model.addAttribute("page", pageLength);
		return loadPage(model, session);
	}

	// UPDATE
	// recieve request edit of a student from showlist page or editlist page
	@RequestMapping(value = { "/manager/edit/{student_id}" }, method = { RequestMethod.GET })
	public String editStudent(@ModelAttribute("student_id") String student_id, @ModelAttribute("page") String page,
			Model model, HttpSession session) {

		String userName = (String) session.getAttribute("userName");

		// check for client type url
		if (StringUtils.isNullOrEmpty(userName))
			return "index";

		Student student = studentService.getStudent(Integer.valueOf(student_id));
		student.setStudentInfo(student.getStudentInfos().iterator().next());
		model.addAttribute("student", student);
		return "register-student";
	}

	@RequestMapping(value = { "/manager/edit/{student_id}" }, method = { RequestMethod.POST })
	public String editStudent(@ModelAttribute("student") @Valid Student student, BindingResult result, Model model,
			@ModelAttribute("student_id") String student_id, @ModelAttribute("page") String page, HttpSession session) {
		if (result.hasErrors())
			return "register-student";

		student.setStudentId(Integer.valueOf(student_id));

		Student studentOld = (Student) session.getAttribute("student");
		session.removeAttribute("student");
		student.getStudentInfo().setInfoId(studentOld.getStudentInfo().getInfoId());
		studentService.updateStudent(student);
		return loadPage(model, session);
	}

	// DELETE
	@RequestMapping(value = { "/manager/delete/{student_id}" }, method = { RequestMethod.GET })
	public String deleteStudent(@ModelAttribute("student_id") String student_id, @ModelAttribute("page") String page,
			Model model, HttpSession session) {

		String userName = (String) session.getAttribute("userName");

		// check for client type url
		if (StringUtils.isNullOrEmpty(userName))
			return "index";

		studentService.deleteStudent(Integer.valueOf(student_id));
		return loadPage(model, session);
	}

	@RequestMapping(value = { "subject-manager" }, method = RequestMethod.GET)
	public String requestMappingSubject(Model model, HttpSession session) {

		String userName = (String) session.getAttribute("userName");

		// check for client type url
		if (StringUtils.isNullOrEmpty(userName))
			return "index";

		return "subject-manager";
	}

	@RequestMapping(value = "register-subject", method = RequestMethod.GET)
	public String requestMappingRegisterSubject(Model model, HttpSession session) {

		String userName = (String) session.getAttribute("userName");

		// check for client type url
		if (StringUtils.isNullOrEmpty(userName))
			return "index";

		return "register-subject";
	}

}