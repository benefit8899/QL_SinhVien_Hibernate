package com.vanloi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vanloi.dao.StudentDao;
import com.vanloi.model.Student;

@Service("studentService")
public class StudentService {

	@Autowired
	StudentDao studentDao;

	@Transactional
	public List<Student> getAllStudents() {
		return studentDao.getAllStudents();
	}

	@Transactional
	public List<Student> getAllStudentsLimit(int start, int length) {
		return studentDao.getAllStudentsLimit(start, length);
	}

	@Transactional
	public Student getStudent(int id) {
		return studentDao.getStudent(id);
	}

	@Transactional
	public void addStudent(Student student) {
		studentDao.addStudent(student);
	}

	@Transactional
	public void updateStudent(Student student) {
		studentDao.updateStudent(student);

	}

	@Transactional
	public void deleteStudent(int id) {
		studentDao.deleteStudent(id);
	}

	@Transactional
	public int countAllStudent() {
		return studentDao.countAllStudent().intValue();
	}

}
