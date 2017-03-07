<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Manager page</title>
<link
	href="${pageContext.request.contextPath}/resource/assets/css/bootstrap-united.css"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/resource/assets/css/style.css"
	rel="stylesheet" />
<script
	src="${pageContext.request.contextPath}/resource/js/jquery-1.8.3.js">
	
</script>
<script
	src="${pageContext.request.contextPath}/resource/bootstrap/js/bootstrap.js">
	
</script>
</head>
<body>
	<fmt:requestEncoding value="UTF-8" />
	<div class="navbar navbar-default">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
		</div>

		<div class="navbar-collapse collapse navbar-responsive-collapse">
			<ul class="nav navbar-nav ">
				<li class="active"><a
					href="${pageContext.request.contextPath}/login.html">Username</a></li>
				<li><a href="${pageContext.request.contextPath}/editlist.html">Add
						new</a></li>
			</ul>
<%-- 			<form action="${pageContext.request.contextPath}/students/search" --%>
<%-- 				method="get"> --%>
<%-- 				<c:choose> --%>
<%-- 					<c:when test="${fn:length(search) != 0}"> --%>
<%-- 						<c:set var="linkpage" --%>
<%-- 							value="${pageContext.request.contextPath}/students/search/${search}/" /> --%>
<%-- 						<c:set var="searchLink" value="${search}/" /> --%>
<%-- 						<form:input type="text" --%>
<%-- 							class="navbar-form form-control navbar-right" path="search" --%>
<%-- 							name="searchBox" value="${search}" id="searchBox" --%>
<%-- 							placeholder="find student" /> --%>
<%-- 					</c:when> --%>
<%-- 					<c:otherwise> --%>
<%-- 						<c:set var="linkpage" --%>
<%-- 							value="${pageContext.request.contextPath}/students/" /> --%>
<%-- 						<c:set var="searchLink" value="" /> --%>
<%-- 						<form:input type="text" --%>
<%-- 							class="navbar-form form-control navbar-right" path="search" --%>
<%-- 							name="searchBox" value="" id="searchBox" --%>
<%-- 							placeholder="find student" /> --%>
<%-- 					</c:otherwise> --%>
<%-- 				</c:choose> --%>
<%-- 				<c:set var="searchValue" value="${search}" /> --%>
<%-- 			</form> --%>
		</div>
	</div>

<%-- 	<% --%>
<!-- // 		String message = request.getParameter("message"); -->
<!-- // 		if (message != null) -->
<!-- // 			out.write("<h2 class=\"center\">Save success, student id recently insert is " + message + "</h2>"); -->
<%-- 	%> --%>

	<h1 class="center">STUDENT LIST</h1>
	<br>
	<div class="divTable">
		<form name="myform" method="post">
			<div class="divTableBody">
				<div class="divTableRow center titletext">
					<div class="divTableCell">STT</div>
					<div class="divTableCell">ID</div>
					<div class="divTableCell">Fullname</div>
					<div class="divTableCell">Code</div>
					<div class="divTableCell">Birth</div>
					<div class="divTableCell">Average Score</div>
					<div class="divTableCell">Address</div>
					<div class="divTableCell">Edit</div>
					<div class="divTableCell">Delete</div>
				</div>
				<c:set var="index" value="${pageNumber}"
					scope="page" />

				<c:forEach items="${studentList}" var="student">
					<div class="divTableRow">
						<div class="divTableCell center smallcol">${index}</div>
						<div class="divTableCell center smallcol2">${student.getStudentId()}</div>
						<div class="divTableCell">${student.getStudentName()}</div>
						<div class="divTableCell center">${student.getStudentCode()}</div>
						<div class="divTableCell center">
							<fmt:formatDate pattern="MM/dd/yyyy"
								value="${student.getStudentInfo().getDateOfBirth()}" />
						</div>
						<div class="divTableCell center">${student.getStudentInfo().getAverageScore()}</div>
						<div class="divTableCell">${student.getStudentInfo().getAddress()}</div>
						<div class="divTableCell center">
							<a
								href="${pageContext.request.contextPath}/students/edit/${student.getStudentId()}/${searchLink}${pageIndex + 1}"><span
								class="editbutton"></span></a>
						</div>
						<div class="divTableCell center">
							<a
								href="${pageContext.request.contextPath}/students/delete/${student.getStudentId()}/${searchLink}${pageIndex + 1}"><span
								class="deletebutton"></span></a>
						</div>
					</div>
					<c:set var="index" value="${index + 1}" scope="page" />
				</c:forEach>
			</div>
		</form>
	</div>
	<br>

	<center>
		<ul class="pagination">
			<li><a href="${linkpage}/1">&laquo;</a></li>
			<li><c:forEach var="i" begin="1" end="${pageNumber}">
					<c:choose>
						<c:when test="${i == pageIndex + 1}">
							<li class="active"><a href="${linkpage}${i}">${i}</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="${linkpage}${i}">${i}</a></li>
						</c:otherwise>
					</c:choose>
					<c:set var="i" value="${i + 1}" />
				</c:forEach>
			<li><a href="${linkpage}${pageNumber}">&raquo;</a></li>
		</ul>
	</center>
</body>
</html>