<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit</title>
<link
	href="${pageContext.request.contextPath}/resource/bootstrap/css/bootstrap.css"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/resource/datepicker/css/datepicker.css"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/resource/assets/css/bootstrap-united.css"
	rel="stylesheet" />

<style>
.green {
	font-weight: bold;
	color: green;
}

.message {
	margin-bottom: 10px;
}

.error {
	color: #ff0000;
	font-size: 0.9em;
	font-weight: bold;
}

.errorblock {
	color: #000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
</style>
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

			<ul class="nav navbar-nav navbar-right">

				<li><a href="${pageContext.request.contextPath}/">Home</a></li>
				<li><a
					href="${pageContext.request.contextPath}/register-subject.html">Register
						Subject</a></li>
				<li><a
					href="${pageContext.request.contextPath}/subject-manager.html">Subject</a></li>
				<li><a class="dropdown-toggle" type="button"
					data-toggle="dropdown">${userName}</span>
				</a>
					<ul class="dropdown-menu">
						<li><a href="#">Logout</a></li>
					</ul></li>
			</ul>
		</div>
		<!-- /.nav-collapse -->
	</div>

	<script
		src="${pageContext.request.contextPath}/resource/js/jquery-1.8.3.js">
		
	</script>

	<script
		src="${pageContext.request.contextPath}/resource/bootstrap/js/bootstrap.js">
		
	</script>

	<script
		src="${pageContext.request.contextPath}/resource/datepicker/js/bootstrap-datepicker.js">
		
	</script>
	<div class="col-lg-6 col-lg-offset-3">
		<div class="well">
			<div class="container">
				<div class="row">
					<div class="col-lg-6">
						<form:form id="myForm" method="post"
							class="bs-example form-horizontal" commandName="student"
							onsubmit="return validateForm()">
							<fieldset>
								<div class="form-group">
									<label for="userNameInput" class="col-lg-3 control-label">Họ tên</label>
									<div class="col-lg-9">
										<form:input type="text" class="form-control"
											path="studentName" id="userNameInput" placeholder="User Name"
											value="${student.getStudentName()}" />
										<form:errors path="studentName" cssClass="error" />
									</div>
								</div>
								<div class="form-group">
									<label for="passwordInput" class="col-lg-3 control-label">Mã
										sv</label>
									<div class="col-lg-9">
										<form:input type="text" class="form-control"
											path="studentCode" id="passwordInput" placeholder="MASV" />
										<form:errors path="studentCode" cssClass="error" />
									</div>
								</div>
								<div class="form-group">
									<label for="firstNameInput" class="col-lg-3 control-label">Địa chỉ</label>
									<div class="col-lg-9">
										<form:input type="text" class="form-control" path="studentInfo.address"
											id="firstNameInput" placeholder="Địa chỉ" />
										<form:errors path="studentInfo.address" cssClass="error" />
									</div>
								</div>
								<div class="form-group">
									<label for="dateOfBirthInput" class="col-lg-3 control-label">Ngày sinh</label>
									<div class="date form_date col-lg-9">
										<fmt:formatDate pattern="MM/dd/yyyy" var="dateformat"
											value="${student.getStudentInfo().getDateOfBirth()}" />
										<form:input type="text" class="form-control inputDate"
											path="studentInfo.dateOfBirth" id="dateOfBirthInput"
											placeholder="Date of Birth" value="${dateformat}"
											disabled="disabled" onfocus="this.blur()" />
										<form:errors path="studentInfo.dateOfBirth" cssClass="error" />
									</div>
								</div>
								<div class="form-group">
									<label for="lastNameInput" class="col-lg-3 control-label">Điểm</label>
									<div class="col-lg-9">
										<form:input type="text" class="form-control"
											path="studentInfo.averageScore" id="lastNameInput" placeholder="Điểm" />
										<form:errors path="studentInfo.averageScore" cssClass="error" />
									</div>
								</div>
								<div class="col-lg-9 col-lg-offset-3">
									<button type="reset" class="btn btn-default">Cancel</button>

									<button class="btn btn-primary">Submit</button>
								</div>
							</fieldset>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script>
		$(function() {
			$('#dateOfBirthInput').datepicker();
		});
	</script>


</body>
</html>