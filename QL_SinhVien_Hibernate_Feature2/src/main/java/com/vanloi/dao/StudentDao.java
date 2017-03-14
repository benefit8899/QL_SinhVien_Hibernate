package com.vanloi.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mysql.jdbc.StringUtils;
import com.vanloi.model.Student;
import com.vanloi.model.StudentInfo;
import com.vanloi.model.StudentSearch;

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
		StudentInfo stf = student.getStudentInfos().iterator().next();
		student.getStudentInfos().clear();
		// int a = student.getStudentInfos().size();
		stf.setAddress(student.getStudentInfo().getAddress());
		stf.setAverageScore(student.getStudentInfo().getAverageScore());
		stf.setDateOfBirth(student.getStudentInfo().getDateOfBirth());
		student.getStudentInfos().add(stf);

		// int b = student.getStudentInfos().size();
		session.update(student);
	}

	public void deleteStudent(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Student p = (Student) session.load(Student.class, new Integer(id));
		if (null != p) {
			session.delete(p);
		}
	}

	@SuppressWarnings("unchecked")
	public List<StudentSearch> findStudent(StudentSearch student, int start, int length) {
		Session session = this.sessionFactory.getCurrentSession();
//		String query = "from Student s, StudentInfo sf where s = sf.student";

		String query = "from Student s, StudentInfo sf where sf.student = s";
		if (!StringUtils.isNullOrEmpty(student.getStudentName()))
			query += " and student_name like :studentName";
		if (!StringUtils.isNullOrEmpty(student.getStudentCode()))
			query += " and student_code = :studentCode ";
			if (!StringUtils.isNullOrEmpty(student.getAddress()))
				query += " and address like :studentAddress";
			if (student.getAverageScore() != -1)
				query += " and average_score >= :averageScore ";
			if (student.getDateOfBirth() != null && student.getDateOfBirthUp() !=  null)
				query += " and date_of_birth between :dateOfBirth and :dateOfBirthUp ";
			else if (student.getDateOfBirth() != null)
				query += " and date_of_birth >= :dateOfBirth ";
			else if (student.getDateOfBirthUp() != null)
				query += " and date_of_birth <= :dateOfBirthUp ";
	
		Query queryS = session.createQuery(query);

		if (!StringUtils.isNullOrEmpty(student.getStudentName())) {
			queryS.setParameter("studentName","%" + student.getStudentName() + "%");
		}
		if (!StringUtils.isNullOrEmpty(student.getStudentCode()))
			queryS.setParameter("studentCode", student.getStudentCode());
			if (!StringUtils.isNullOrEmpty(student.getAddress()))
				queryS.setParameter("studentAddress","%"+ student.getAddress()+ "%") ;
			if (student.getAverageScore() != -1)
				queryS.setParameter("averageScore", student.getAverageScore());
			if (student.getDateOfBirth() != null && student.getDateOfBirthUp() != null) {
				queryS.setParameter("dateOfBirth", student.getAverageScore());
				queryS.setParameter("dateOfBirthUp", student.getDateOfBirthUp());
			} else if (student.getDateOfBirth() != null)
				queryS.setParameter("dateOfBirth", student.getDateOfBirth());
			else if (student.getDateOfBirthUp() != null)
				queryS.setParameter("dateOfBirthUp", student.getDateOfBirthUp());
		
		queryS.setMaxResults(length);
		queryS.setFirstResult(start);
		List<Student> studentList = queryS.list();
		List<StudentSearch> studentListResult = new ArrayList<StudentSearch>();
		for(Object c : studentList){
			Object[] cs = (Object[])c;
			Student s = (Student)cs[0];
			StudentInfo sf = (StudentInfo)cs[1];
			
			StudentSearch st = new StudentSearch();
			st.setStudentId(s.getStudentId());
			st.setStudentName(s.getStudentName());
			st.setStudentCode(s.getStudentCode());
			st.setAddress(sf.getAddress());
			st.setDateOfBirth(sf.getDateOfBirth());
			st.setAverageScore(sf.getAverageScore());
			studentListResult.add(st);
		}
		return studentListResult;
	}
	public int countStudentFinding(StudentSearch student) {
		Session session = this.sessionFactory.getCurrentSession();
//		String query = "from Student s, StudentInfo sf where s = sf.student";

		String query = "select count (*) from Student s, StudentInfo sf where sf.student = s";
		if (!StringUtils.isNullOrEmpty(student.getStudentName()))
			query += " and student_name like :studentName";
		if (!StringUtils.isNullOrEmpty(student.getStudentCode()))
			query += " and student_code = :studentCode ";
			if (!StringUtils.isNullOrEmpty(student.getAddress()))
				query += " and address like :studentAddress";
			if (student.getAverageScore() != -1)
				query += " and average_score >= :averageScore ";
			if (student.getDateOfBirth() != null && student.getDateOfBirthUp() !=  null)
				query += " and date_of_birth between :dateOfBirth and :dateOfBirthUp ";
			else if (student.getDateOfBirth() != null)
				query += " and date_of_birth >= :dateOfBirth ";
			else if (student.getDateOfBirthUp() != null)
				query += " and date_of_birth <= :dateOfBirthUp ";
	
		Query queryS = session.createQuery(query);

		if (!StringUtils.isNullOrEmpty(student.getStudentName())) {
			queryS.setParameter("studentName","%" + student.getStudentName() + "%");
		}
		if (!StringUtils.isNullOrEmpty(student.getStudentCode()))
			queryS.setParameter("studentCode", student.getStudentCode());
			if (!StringUtils.isNullOrEmpty(student.getAddress()))
				queryS.setParameter("studentAddress","%" +student.getAddress() + "%");
			if (student.getAverageScore() != -1)
				queryS.setParameter("averageScore", student.getAverageScore());
			if (student.getDateOfBirth() != null && student.getDateOfBirthUp() != null) {
				queryS.setParameter("dateOfBirth", student.getAverageScore());
				queryS.setParameter("dateOfBirthUp", student.getDateOfBirthUp());
			} else if (student.getDateOfBirth() != null)
				queryS.setParameter("dateOfBirth", student.getDateOfBirth());
			else if (student.getDateOfBirthUp() != null)
				queryS.setParameter("dateOfBirthUp", student.getDateOfBirthUp());
			
		Long lengthStudentList = (Long)queryS.uniqueResult();
		return lengthStudentList.intValue();
	}
}
