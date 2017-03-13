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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
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
			<form class="navbar-form" role="search">
				<div class="input-group add-on searchBox">
					<input class="form-control" placeholder="Search" name="srch-term"
						id="srch-term" type="text">
					<div class="input-group-btn">
						<button class="btn btn-default form-control" type="button" onclick="searchClick();">
							<i class="glyphicon glyphicon-search"></i>
						</button>
						<button class="btn btn-default form-control" type="button"
							onclick="showSearchDialog();">
							<i class="glyphicon glyphicon-cog"></i>
						</button>
					</div>
				</div>
			</form>
		</div>
	</div>

	<%-- 	<% --%>
	<!-- // 		String message = request.getParameter("message"); -->
	<!-- // 		if (message != null) -->
	<!-- // 			out.write("<h2 class=\"center\">Save success, student id recently insert is " + message + "</h2>"); -->
	<%-- 	%> --%>

	<div class="container">
		<div class="row">
			<div class="col-lg-8 searchDialog col-lg-offset-2">
				<form id="myForm" method="post" class="bs-example form-horizontal"
					commandName="student" onsubmit="return validateForm()">
					<fieldset>
						<div class="form-group">
							<label for="userNameInput" class="col-lg-2 control-label">Họ
								tên</label>
							<div class="col-lg-4">
								<input type="text" class="form-control" id="userNameInput"
									placeholder="User Name" maxlength="100"
									value="${student.getStudentName()}" />
							</div>
							<label for="passwordInput" class="col-lg-2 control-label">Mã
								sv</label>
							<div class="col-lg-4">
								<input type="text" class="form-control" maxlength="50"
									id="passwordInput" placeholder="MASV" />
							</div>
						</div>
						<div class="form-group"></div>
						<div class="form-group">
							<label for="dateOfBirthInput" class="col-lg-2 control-label">Ngày
								sinh</label>
							<div class="date form_date col-lg-4">
								<fmt:formatDate pattern="MM/dd/yyyy" var="dateformat"
									value="${student.getStudentInfo().getDateOfBirth()}" />
								<input type="text" class="form-control inputDate"
									id="dateOfBirthInput" placeholder="Date of Birth"
									value="${dateformat}" disabled="disabled" onfocus="this.blur()" />
							</div>
							<label for="dateOfBirthInput" class="col-lg-2 control-label">to</label>
							<div class="date form_date col-lg-4">
								<fmt:formatDate pattern="MM/dd/yyyy" var="dateformat"
									value="${student.getStudentInfo().getDateOfBirth()}" />
								<input type="text" class="form-control inputDate"
									id="dateOfBirthInput" placeholder="Date of Birth"
									value="${dateformat}" disabled="disabled" onfocus="this.blur()" />
							</div>
						</div>

						<div class="form-group">
							<label for="firstNameInput" class="col-lg-2 control-label">Địa
								chỉ</label>
							<div class="col-lg-5">
								<input type="text" class="form-control" maxlength="200"
									id="firstNameInput" placeholder="Địa chỉ" />
							</div>
							<label for="lastNameInput" class="col-lg-2 control-label">Điểm</label>
							<div class="col-lg-3">
								<input type="number" class="form-control" id="averageScore"
									placeholder="Điểm" />
							</div>
						</div>
						<div class="form-group"></div>
						<div class="col-lg-9 col-lg-offset-9">
							<button class="btn btn-default" onclick="hideSearchDialog();"
								type="button">Cancel</button>
							<button class="btn btn-primary" type="button"  onclick="searchClick();">Search</button>
						</div>
					</fieldset>
				</form>
			</div>
		</div>
	</div>
	<div id="feedback"></div>
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
					<div class="divTableRow divTableRowData">
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
								class="deletebutton"
								onclick="return confirm('Are you sure to delete this?')"></span></a>
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
	<br>

	<script type="text/javascript">
		var elems = document.getElementsByClassName('confirmation');
		var confirmIt = function(e) {
			if (!confirm('Are you sure?'))
				e.preventDefault();
		};
		for (var i = 0, l = elems.length; i < l; i++) {
			elems[i].addEventListener('click', confirmIt, false);
		}
		function hideSearchDialog() {
			$(".searchDialog").hide();
			$(".searchBox").show();
		}
		function showSearchDialog() {
			$(".searchDialog").show();
			$(".searchBox").hide();
		}
		function searchClick() {
			searchViaAjax();
		}
		function searchViaAjax() {
			var student = {}
			student["studentName"] = $("#srch-term").val();
			var studentName = $("#userNameInput").val();
			var averageScore = $("#averageScore").val(); 
			student["studentName"] = studentName;
			student["averageScore"] = averageScore;
			student["pageIndex"] = 1;
			$.ajax({
				type : "POST",
				contentType : "application/json",
				url : "${pageContext.request.contextPath}/manager/search",
				data : JSON.stringify(student),
				dataType : 'json',
				timeout : 100000,
				success : function(data) {
					console.log("SUCCESS: ", data);
					display(data);
				},
				error : function(e) {
					console.log("ERROR: ", e);
					display(e);
				},
				done : function(e) {
					console.log("DONE");
				}
			});
		}
		function display(data) {
			var json = "<h4>Ajax Response</h4><pre>"
					+ JSON.stringify(data, null, 4) + "</pre>";
			$('#feedback').html(json);
			
			$('.divTableRowData').html('');
		}
	</script>
</body>
</html>