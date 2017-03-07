package com.vanloi.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vanloi.model.Student;

@Repository
public class StudentDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@SuppressWarnings("unchecked")
	public List<Student> getAllStudents() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Student> studentList = session.createQuery("from Student").list();
		return studentList;
	}

	@SuppressWarnings("unchecked")
	public List<Student> getAllStudentsLimit(int start, int length) {
		Session session = this.sessionFactory.getCurrentSession();
		List<Student> studentList = session.createQuery("from Student").setFirstResult(start).setMaxResults(length)
				.list();
		return studentList;
	}

	public Long countAllStudent() {
		Session session = this.sessionFactory.getCurrentSession();
		return (Long) session.createQuery("select count (*) from Student").uniqueResult();
	}

	public Long countAllStudent(String[] keys, String[] values) {
//		String query = keys[0] + "=:" + keys[0];
//
//		for (int i = 1; i < keys.length; i++) {
//			query += " and " + keys[i] + "=" + values[i];
//		}
		Session session = this.sessionFactory.getCurrentSession();
		return (Long) session.createQuery("select count (*) from Student where ").uniqueResult();
	}

	public Student getStudent(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Student student = (Student) session.get(Student.class, new Integer(id));
		return student;
	}

	public Student addStudent(Student student) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(student);
		return student;
	}

	public void updateStudent(Student student) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(student);
	}

	public void deleteStudent(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Student p = (Student) session.load(Student.class, new Integer(id));
		if (null != p) {
			session.delete(p);
		}
	}
}
