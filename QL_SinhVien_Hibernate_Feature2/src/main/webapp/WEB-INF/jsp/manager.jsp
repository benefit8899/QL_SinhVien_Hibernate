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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
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
	<c:choose>
		<c:when test="${fn:length(search) != 0}">
			<c:set var="linkpage"
				value="${pageContext.request.contextPath}/students/search/${search}/" />
		</c:when>
		<c:otherwise>
			<c:set var="linkpage"
				value="${pageContext.request.contextPath}/manager/" />
		</c:otherwise>
	</c:choose>
	<fmt:requestEncoding value="UTF-8" />
	<div class="navbar navbar-default">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
		</div>

		<div class="navbar-collapse collapse navbar-responsive-collapse">

			<ul class="nav navbar-nav navbar-right">

				<li><a href="${pageContext.request.contextPath}/">Home</a></li>
				<li><a
					href="${pageContext.request.contextPath}/register-subject.html">Register
						Subject</a></li>
				<li><a
					href="${pageContext.request.contextPath}/subject-manager.html">Subject</a></li>
				<li role="presentation" class="dropdown"><a
					class="dropdown-toggle" data-toggle="dropdown" href="#"
					role="button" aria-haspopup="true" aria-expanded="false">
						${userName } <span class="caret"></span>
				</a>
					<ul class="dropdown-menu">
						<li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
					</ul></li>
			</ul>
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

		<form
			action="${pageContext.request.contextPath}/register-student.html">
			<button class="btn btn-primary">New Student</button>
		</form>
		<div class="divTableBody">

			<form name="myform" method="post">
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
				<c:set var="index" value="${pageNumber}" scope="page" />
				<c:forEach items="${studentList}" var="student">
					<div class="divTableRow">
						<div class="divTableCell center smallcol">${index}</div>
						<div class="divTableCell center smallcol2">${student.getStudentId()}</div>
						<div class="divTableCell largecol">${student.getStudentName()}</div>
						<div class="divTableCell center">${student.getStudentCode()}</div>
						<div class="divTableCell center">
							<fmt:formatDate pattern="MM/dd/yyyy"
								value="${student.getStudentInfo().getDateOfBirth()}" />
						</div>
						<div class="divTableCell center ">${student.getStudentInfo().getAverageScore()}</div>
						<div class="divTableCell autocol">${student.getStudentInfo().getAddress()}</div>
						<div class="divTableCell center">
							<a
								href="${pageContext.request.contextPath}/manager/edit/${student.getStudentId()}${searchLink}"><span
								class="editbutton"></span></a>
						</div>
						<div class="divTableCell center">
							<a
								href="${pageContext.request.contextPath}/manager/delete/${student.getStudentId()}/${searchLink}"><span
								class="deletebutton"></span></a>
						</div>
					</div>
					<c:set var="index" value="${index + 1}" scope="page" />
				</c:forEach>

			</form>
		</div>
	</div>
	<br>

	<center>
		<ul class="pagination">
			<li><a href="${linkpage}/1">&laquo;</a></li>
			<li><c:forEach var="i" begin="1" end="${pageLength}">
					<c:choose>
						<c:when test="${i == pageNumber}">
							<li class="active"><a href="${linkpage}${i}">${i}</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="${linkpage}${i}">${i}</a></li>
						</c:otherwise>
					</c:choose>
					<c:set var="i" value="${i + 1}" />
				</c:forEach>
			<li><a href="${linkpage}${pageLength}">&raquo;</a></li>
		</ul>
	</center>
</body>
</html>