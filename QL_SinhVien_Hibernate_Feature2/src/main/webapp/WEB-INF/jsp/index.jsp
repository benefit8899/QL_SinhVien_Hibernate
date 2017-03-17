<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<link
	href="${pageContext.request.contextPath}/resource/assets/css/bootstrap-united.css"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/resource/bootstrap/css/bootstrap-responsive.css"
	rel="stylesheet" />
<style>
body {
	height: 100%;
	margin: 0;
	background-size: 1440px 800px;
	background-repeat: no-repeat;
	display: compact;
}
</style>
</head>
<body>
	<div class="navbar navbar-default">

		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-responsive-collapse">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
		</div>

		<div class="navbar-collapse collapse navbar-responsive-collapse">
			<c:choose>
				<c:when test="${not empty userName}">
					<ul class="nav navbar-nav navbar-right">

						<li><a href="${pageContext.request.contextPath}/manager">Manager</a></li>
<!-- 						<li><a -->
<%-- 							href="${pageContext.request.contextPath}/register-subject.html">Register --%>
<!-- 								Subject</a></li> -->
<!-- 						<li><a -->
<%-- 							href="${pageContext.request.contextPath}/subject-manager.html">Subject</a></li> --%>
						<li role="presentation" class="dropdown"><a
					class="dropdown-toggle" data-toggle="dropdown" href="#"
					role="button" aria-haspopup="true" aria-expanded="false">
						${userName } <span class="caret"></span>
						</a>
							<ul class="dropdown-menu">
								<li><a href="#">Logout</a></li>
							</ul></li>
					</ul>
				</c:when>
				<c:otherwise>
					<ul class="nav navbar-nav navbar-right">
						<li><a href="login.html">Login</a></li>
					</ul>
				</c:otherwise>
			</c:choose>


		</div>
		<!-- /.nav-collapse -->
	</div>
	<div class="container">
		<div class="jumbotron">
			<div>
				<h3>HOme page</h3>
			</div>
		</div>

		<div></div>
	</div>
	<script
		src="${pageContext.request.contextPath}/resource/js/jquery-1.8.3.js">
		
	</script>
	<script
		src="${pageContext.request.contextPath}/resource/bootstrap/js/bootstrap.js">
		
	</script>

</body>
</html>

