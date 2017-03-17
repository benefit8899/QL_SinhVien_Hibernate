package com.vanloi.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.springframework.web.servlet.ModelAndView;

import com.mysql.jdbc.StringUtils;
import com.vanloi.model.PageParameter;
import com.vanloi.model.Student;
import com.vanloi.model.StudentInfo;
import com.vanloi.model.StudentSearch;
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
		String userName = (String) session.getAttribute("userName");
		if (!StringUtils.isNullOrEmpty(userName)) {
			model.addAttribute("userName", userName);
			return loadPage(model, session);
		}
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
		String password = convertToMD5(userLogin.getPassword());
		if (user != null && password.equals(user.getPassword())) {
			model.addAttribute("userName", user.getUserName());
			model.addAttribute("page", "1");
			session.setAttribute("userName", user.getUserName());
			session.setAttribute("page", "1");

			return loadPage(model, session);
		} else {
			model.addAttribute("message", "user name and password incorect!!");
			return "redirect:login.html";
		}
	}

	public String convertToMD5(String pass) {
		MessageDigest messageDigest;
		StringBuffer result = new StringBuffer();
		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(pass.getBytes());
			byte[] digest = messageDigest.digest();
			for (byte b : digest) {
				result.append(Integer.toHexString((int) (b & 0xff)));
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return result.toString();
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

		int pageLength = (int) Math.ceil(studentService.countAllStudent() / (double) NUMBER_RECORD_IN_PAGE);
		try {
			if (Integer.valueOf(pageNumber) <= 0)
				pageNumber = "1";
			else if (Integer.valueOf(pageNumber) > pageLength)
				pageNumber = String.valueOf(pageLength);
		} catch (Exception ex) {
			pageNumber = "1";
		}
		session.setAttribute("page", pageNumber);
		model.addAttribute("page", pageNumber);
		return loadPage(model, session);
	}

	@RequestMapping(value = { "manager", "manager/search" }, method = RequestMethod.GET)
	public String loadPage(Model model, HttpSession session) {

		String userName = (String) session.getAttribute("userName");
		List<Student> studentList = new ArrayList<Student>();
		// check for client type url
		if (StringUtils.isNullOrEmpty(userName))
			return "index";

		String page = (String) session.getAttribute("page");

		int pageNumber = Integer.valueOf(page);
		int startIndex = (pageNumber - 1) * NUMBER_RECORD_IN_PAGE;
		int pageLength = (int) Math.ceil(studentService.countAllStudent() / (double) NUMBER_RECORD_IN_PAGE);
		if (pageNumber > 0) {
			studentList = studentService.getAllStudentsLimit(startIndex, NUMBER_RECORD_IN_PAGE);
			if (studentList.size() == 0 && pageNumber >= 1) {
				pageNumber--;
				startIndex = (pageNumber - 1) * NUMBER_RECORD_IN_PAGE;
				studentList = studentService.getAllStudentsLimit(startIndex, NUMBER_RECORD_IN_PAGE);
			}
		}

		// set parameter for pageParameter for view
		PageParameter pageParameter = new PageParameter();
		pageParameter.setPageIndex(pageNumber);
		pageParameter.setPageLength(pageLength);
		pageParameter.setPageNumber(NUMBER_RECORD_IN_PAGE);

		model.addAttribute("studentList", studentList);
		model.addAttribute("pageIndex", pageNumber);
		model.addAttribute("pageLength", pageLength);
		model.addAttribute("pageNumber", NUMBER_RECORD_IN_PAGE);

		model.addAttribute("userName", userName);
		model.addAttribute("pageParameter", pageParameter);

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
		logger.info("INSERT STUDENT");

		return "register-student";
	}

	// process add student
	@RequestMapping(value = { "register-student" }, method = RequestMethod.POST)
	public String requestAddNewRegisterStudent(@Valid Student student, BindingResult result, Model model,
			HttpSession session) {
		if (result.hasErrors())
			return "register-student";

		boolean status = studentService.addStudent(student);
		String pageLength = String
				.valueOf(((int) Math.ceil(studentService.countAllStudent() / (double) NUMBER_RECORD_IN_PAGE)));
		if (!status)
			model.addAttribute("status", "insert");
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
		logger.info("UPDATE STUDENT");
		return "register-student";
	}
	@RequestMapping(value = {"manager/updatefromajax" }, method = { RequestMethod.POST })
	public String editStudent2(@ModelAttribute("student") @Valid Student student, BindingResult result, Model model, HttpSession session) {
		if (result.hasErrors())
			return "register-student";

		StudentSearch studentSearch = (StudentSearch)session.getAttribute("studentSearch");
		student.setStudentId(studentSearch.getStudentId());
		
		Student studentOld = (Student) session.getAttribute("student");
		session.removeAttribute("student");
		student.getStudentInfo().setInfoId(studentOld.getStudentInfo().getInfoId());
		boolean status = studentService.updateStudent(student);
		model.addAttribute("student", studentSearch);
		model.addAttribute("pageIndexSearch", studentSearch.getPageIndex());
		if (!status)
			model.addAttribute("status", "update");
		return loadPage(model, session);
	}

	
	
	@RequestMapping(value = { "/manager/edit/{student_id}"}, method = { RequestMethod.POST })
	public String editStudent(@ModelAttribute("student") @Valid Student student, BindingResult result, Model model,
			@ModelAttribute("student_id") String student_id, @ModelAttribute("page") String page, HttpSession session) {
		if (result.hasErrors())
			return "register-student";

		student.setStudentId(Integer.valueOf(student_id));

		Student studentOld = (Student) session.getAttribute("student");
		session.removeAttribute("student");
		student.getStudentInfo().setInfoId(studentOld.getStudentInfo().getInfoId());
		boolean status = studentService.updateStudent(student);
		if (!status)
			model.addAttribute("status", "update");
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

		boolean status = studentService.deleteStudent(Integer.valueOf(student_id));
		if (!status)
			model.addAttribute("status", "delete");
		logger.info("DELETE STUDENT");
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