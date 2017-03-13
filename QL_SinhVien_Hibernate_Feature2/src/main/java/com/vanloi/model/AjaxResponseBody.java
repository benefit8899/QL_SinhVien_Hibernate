package com.vanloi.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

import jsonviews.Views;

public class AjaxResponseBody {
	@JsonView(Views.Public.class)
	String msg;
	@JsonView(Views.Public.class)
	String code;
	@JsonView(Views.Public.class)
	List<Student> result;
	@JsonView(Views.Public.class)
	String userName;
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<Student> getResult() {
		return result;
	}

	public void setResult(List<Student> result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "AjaxResponseResult [userName=" + userName+",  msg=" + msg + ", code=" + code + ", result=" + result + "]";
	}
}