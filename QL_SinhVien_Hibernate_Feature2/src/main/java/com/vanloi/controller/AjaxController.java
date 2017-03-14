package com.vanloi.controller;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.vanloi.model.ResultSearch;
import com.vanloi.model.Student;
import com.vanloi.model.StudentSearch;
import com.vanloi.services.StudentService;

@RestController
public class AjaxController {
	public static int NUMBER_RECORD_IN_PAGE = 10;
	private Logger logger = Logger.getLogger(AjaxController.class);

	@Autowired
	private StudentService studentService;

	// ------------------SEARCH-----------------------
	// ----------------AJAX---------------------
	@ResponseBody
	@RequestMapping(value = "manager/search")
	public ResultSearch getSearchResultViaAjax(@RequestBody StudentSearch student) {

		int page = student.getPageIndex();
		int pageIndex = (page - 1) * NUMBER_RECORD_IN_PAGE;
		List<StudentSearch> listStudents = studentService.findStudent(student, pageIndex, NUMBER_RECORD_IN_PAGE);
		int pageLength = studentService.countStudentFinding(student);
		pageLength = ((int) Math.ceil(pageLength / (double) NUMBER_RECORD_IN_PAGE));
		ResultSearch result = new ResultSearch();
		result.setListStudent(listStudents);
		result.setPageIndex(page);
		result.setPageLength(pageLength);
		result.setPageNumber(NUMBER_RECORD_IN_PAGE);

		return result;
	}

	// request in ajax DELETE
	@ResponseBody
	@RequestMapping(value = "manager/delete")
	public ResultSearch deleteStudent(@RequestBody StudentSearch student) {
		studentService.deleteStudent(student.getStudentId());
		int page = student.getPageIndex();
		int pageIndex = (page - 1) * NUMBER_RECORD_IN_PAGE;
		List<StudentSearch> listStudents = studentService.findStudent(student, pageIndex, NUMBER_RECORD_IN_PAGE);
		int pageLength = studentService.countStudentFinding(student);
		pageLength = ((int) Math.ceil(pageLength / (double) NUMBER_RECORD_IN_PAGE));
		ResultSearch result = new ResultSearch();
		result.setListStudent(listStudents);
		result.setPageIndex(page);
		result.setPageLength(pageLength);
		result.setPageNumber(NUMBER_RECORD_IN_PAGE);
		logger.info("DELETE STUDENT");
		return result;
	}
	@RequestMapping(method = RequestMethod.POST, value = "manager/update")
	public ModelAndView updateStudent(@RequestBody StudentSearch student) {
		ModelAndView model = new ModelAndView("register-student");
		model.addObject("student", new Student());
		return model;
	}

    /* this is the conroller's part of the magic; I'm just using a simple GET but you
     * could just as easily do a POST here, obviously
     */
    @RequestMapping( method= RequestMethod.GET, value="/subView" )
    public ModelAndView getSubView( Model model ) {
        model.addAttribute( "user", "Joe Dirt" );
        model.addAttribute( "time", new Date() );
        return new ModelAndView( "subView" );
    }
}
