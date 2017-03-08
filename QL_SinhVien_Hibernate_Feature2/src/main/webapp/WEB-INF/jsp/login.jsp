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
<link
	href="${pageContext.request.contextPath}/resource/assets/css/bootstrap-united.css"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/resource/assets/css/style.css"
	rel="stylesheet" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<body>

	<div class="col-lg-6 col-lg-offset-3">
		<br />
		<div class="well">
			<h2 class="center">Login</h2>
			<div class="container">
				<div class="row">
					<div class="col-lg-6">
						<form:form id="myForm" method="post" commandName="userLogin"
							action="login">
							<fieldset>
								<div class="form-group">
									<label for="userNameInput" class="col-lg-3 control-label">User
										Name</label>
									<form:input type="text" class="form-control" path="userName"
										id="userNameInput" placeholder="User Name" />
									<form:errors path="userName" cssClass="error" />
									<div class="col-lg-9"></div>
								</div>
								<div class="form-group">
									<label for="passwordInput" class="col-lg-3 control-label">Password</label>
									<form:input type="password" class="form-control"
										path="password" id="passwordInput" placeholder="password" />
									<form:errors path="password" cssClass="error" />
									<div class="col-lg-9"></div>
								</div>
								<%
									String message = request.getParameter("message");
										if (message != null)
											out.println("<h2 class=\"center\">" + message + "</h2>");
										else
											out.println("<h2 class=\"center\">" + "" + "</h2>");
								%>
								<div class="col-lg-9 col-lg-offset-3">
									<button class="btn btn-default"
										onclick="window.location.href='/'">Cancel</button>
									<button class="btn btn-primary">Login</button>
								</div>
							</fieldset>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>