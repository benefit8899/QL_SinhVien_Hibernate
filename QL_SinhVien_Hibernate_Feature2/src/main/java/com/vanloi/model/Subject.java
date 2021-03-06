package com.vanloi.model;
// Generated Mar 2, 2017 3:07:30 PM by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Subject generated by hbm2java
 */
@Entity
@Table(name = "subject")
public class Subject{

	private Integer subjectId;
	private String subjectName;
	private Integer credits;
	private Set<Enrollment> enrollments = new HashSet<Enrollment>(0);

	public Subject() {
	}

	public Subject(String subjectName, Integer credits, Set<Enrollment> enrollments) {
		this.subjectName = subjectName;
		this.credits = credits;
		this.enrollments = enrollments;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "subject_id", unique = true, nullable = false)
	public Integer getSubjectId() {
		return this.subjectId;
	}

	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}

	@Column(name = "subject_name", length = 50)
	public String getSubjectName() {
		return this.subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	@Column(name = "credits")
	public Integer getCredits() {
		return this.credits;
	}

	public void setCredits(Integer credits) {
		this.credits = credits;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "subject")
	public Set<Enrollment> getEnrollments() {
		return this.enrollments;
	}

	public void setEnrollments(Set<Enrollment> enrollments) {
		this.enrollments = enrollments;
	}

}
