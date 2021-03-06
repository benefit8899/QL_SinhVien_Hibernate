package com.vanloi.model;
// Generated Mar 2, 2017 2:31:24 PM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;

import java.util.Collection;
import java.util.LinkedHashSet;

import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;


/**
 * Student generated by hbm2java
 */
@Entity
@Table(name = "student")
public class Student{

	private Integer studentId;
	@NotEmpty
	private String studentName;
	@NotEmpty
	private String studentCode;
	@Valid
	private StudentInfo studentInfo;
	private Collection<StudentInfo> studentInfos = new LinkedHashSet<StudentInfo>();

	public Student() {
	}

	public Student(String studentName, String studentCode, Collection<StudentInfo> studentInfos) {
		this.studentName = studentName;
		this.studentCode = studentCode;
		this.studentInfos = studentInfos;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "student_id", unique = true, nullable = false)
	public Integer getStudentId() {
		return this.studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	@Column(name = "student_name", length = 100)
	public String getStudentName() {
		return this.studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	@Column(name = "student_code", length = 50)
	public String getStudentCode() {
		return this.studentCode;
	}

	public void setStudentCode(String studentCode) {
		this.studentCode = studentCode;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "student", cascade = CascadeType.ALL)
	public Collection<StudentInfo> getStudentInfos() {
		return this.studentInfos;
	}
	public void setStudentInfos(Collection<StudentInfo> studentInfos) {
		this.studentInfos = studentInfos;
	}
	@Transient
	public StudentInfo getStudentInfo() {
		if(studentInfo == null)
			return studentInfos.iterator().hasNext()? studentInfos.iterator().next():null;
		else
			return studentInfo;
	}

	public void setStudentInfo(StudentInfo studentInfo) {
		this.studentInfo = studentInfo;
		if(studentInfo != null){
			studentInfo.setStudent(this);
			studentInfos.add(studentInfo);
		}
	}
}
