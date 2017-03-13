package com.vanloi.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.vanloi.model.ResultSearch;
import com.vanloi.model.StudentSearch;
import com.vanloi.services.StudentService;

@RestController
public class AjaxController {
	public static int NUMBER_RECORD_IN_PAGE = 10;

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
		return result;
	}
}
