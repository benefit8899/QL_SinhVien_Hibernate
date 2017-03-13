package com.vanloi.model;

import java.util.Date;

public class StudentSearch{
	private int pageIndex = 0;
	
	private int studentId;
	
	private String studentName;
	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentCode() {
		return studentCode;
	}

	public void setStudentCode(String studentCode) {
		this.studentCode = studentCode;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getAverageScore() {
		return averageScore;
	}

	public void setAverageScore(double averageScore) {
		this.averageScore = averageScore;
	}

	private String studentCode;
	private Date dateOfBirth;
	private String address;
	private double averageScore;
	private Date dateOfBirthUp;

	public Date getDateOfBirthUp() {
		return dateOfBirthUp;
	}

	public void setDateOfBirthUp(Date dateOfBirthUp) {
		this.dateOfBirthUp = dateOfBirthUp;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
}
