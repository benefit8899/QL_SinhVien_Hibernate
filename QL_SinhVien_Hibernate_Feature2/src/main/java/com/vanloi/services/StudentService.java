package com.vanloi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.vanloi.dao.StudentDao;
import com.vanloi.model.Student;
import com.vanloi.model.StudentSearch;

@Service("studentService")
public class StudentService {

	private PlatformTransactionManager transactionManager;

	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

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

	public boolean addStudent(Student student) {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setName("insertStudent");
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = transactionManager.getTransaction(def);
		try {
			studentDao.addStudent(student);
//			int a = 3 / 0;
		} catch (RuntimeException ex) {
			transactionManager.rollback(status);
			return false;
		}
		transactionManager.commit(status);
		return true;
	}
	public boolean updateStudent(Student student) {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setName("insertStudent");
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = transactionManager.getTransaction(def);
		try {
			studentDao.updateStudent(student);
		} catch (RuntimeException ex) {
			transactionManager.rollback(status);
			return false;
		}
		transactionManager.commit(status);
		return true;
	}

	public boolean deleteStudent(int id) {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setName("insertStudent");
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = transactionManager.getTransaction(def);
		try {
			studentDao.deleteStudent(id);
		} catch (RuntimeException ex) {
			transactionManager.rollback(status);
			return false;
		}
		transactionManager.commit(status);
		return true;
	}

	@Transactional
	public int countAllStudent() {
		return studentDao.countAllStudent().intValue();
	}

	@Transactional
	public List<StudentSearch> findStudent(StudentSearch student, int start, int length) {
		return studentDao.findStudent(student, start, length);
	}

	@Transactional
	public int countStudentFinding(StudentSearch student) {
		return studentDao.countStudentFinding(student);
	}
}
