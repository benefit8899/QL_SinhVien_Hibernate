package com.vanloi.model;

import java.util.ArrayList;
import java.util.List;

public class ResultSearch {
	private List<StudentSearch> listStudent = new ArrayList<StudentSearch>();
	public List<StudentSearch> getListStudent() {
		return listStudent;
	}
	public void setListStudent(List<StudentSearch> listStudent) {
		this.listStudent = listStudent;
	}
	public int getPageLength() {
		return pageLength;
	}
	public void setPageLength(int pageLength) {
		this.pageLength = pageLength;
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	private int pageLength = 0;
	private int pageIndex = 0;
}
