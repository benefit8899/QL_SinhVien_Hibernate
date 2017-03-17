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
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
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
<link
	href="${pageContext.request.contextPath}/resource/datepicker/css/datepicker.css"
	rel="stylesheet" />
<script
	src="${pageContext.request.contextPath}/resource/js/jquery-1.8.3.js">
	
</script>

<script
	src="${pageContext.request.contextPath}/resource/bootstrap/js/bootstrap.js">
	
</script>

<script
	src="${pageContext.request.contextPath}/resource/datepicker/js/bootstrap-datepicker.js">
	
</script>
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
	<spring:htmlEscape defaultHtmlEscape="true" />
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
						id="srch-term" type="text" value="${student.getStudentName()}">
					<div class="input-group-btn">
						<button class="btn btn-default form-control" type="button"
							onclick="searchClick();">
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
	<c:choose>
		<c:when test="${fn:length(status) != 0}">
			<script>
				alert("${status} failed !!!");
			</script>
		</c:when>

	</c:choose>
	<div class="container">
		<div class="row">
			<div class="col-lg-8 searchDialog col-lg-offset-2">
				<form id="myForm" method="post" class="bs-example form-horizontal"
					commandName="student" onsubmit="return validateForm()">
					<fieldset>
						<div class="form-group">
							<label for="studentNameInput" class="col-lg-2 control-label">Full
								name</label>
							<div class="col-lg-4">
								<input type="text" class="form-control" id="studentNameInput"
									placeholder="User Name" maxlength="100"
									value="${student.getStudentName()}" />
							</div>
							<label for="studentCodeInput" class="col-lg-2 control-label">Code</label>
							<div class="col-lg-4">
								<input type="text" class="form-control" maxlength="50"
									id="studentCodeInput" placeholder="MASV"
									value="${student.getStudentCode()}" />
							</div>
						</div>
						<div class="form-group"></div>
						<div class="form-group">
							<label for="dateOfBirthInput" class="col-lg-2 control-label">Date
								Of Birth</label>
							<div class="date form_date col-lg-4">
								<fmt:formatDate pattern="dd/MM/yyyy" var="dateformat"
									value="${student.getDateOfBirth()}" />
								<input type="text" class="form-control inputDate"
									id="dateOfBirthInput" placeholder="from date"
									value="${dateformat}" />
							</div>
							<label for="dateOfBirthUpInput" class="col-lg-2 control-label">to</label>
							<div class="date form_date col-lg-4">
								<fmt:formatDate pattern="dd/MM/yyyy" var="dateformat2"
									value="${student.getDateOfBirthUp()}" />
								<input type="text" class="form-control inputDate"
									id="dateOfBirthUpInput" placeholder="to date"
									value="${dateformat2}" />
							</div>
						</div>

						<div class="form-group">
							<label for="addressInput" class="col-lg-2 control-label">Address</label>
							<div class="col-lg-5">
								<input type="text" class="form-control" maxlength="200"
									id="addressInput" placeholder="address"
									value="${student.getAddress()}" />
							</div>
							<label for="averageScoreInput" class="col-lg-2 control-label">Average
								Score</label>
							<div class="col-lg-3">
								<input type="number" class="form-control" id="averageScoreInput"
									placeholder="Score" value="${student.getAverageScore()}" />
							</div>
						</div>
						<div class="form-group"></div>
						<div class="col-lg-6 col-lg-offset-9">
							<button class="btn btn-default" onclick="hideSearchDialog();"
								type="button">Cancel</button>
							<button class="btn btn-primary" type="button"
								onclick="searchClick();">Search</button>
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
					<div class="divTableCell">${pageIndexSearch}</div>
					<div class="divTableCell">ID</div>
					<div class="divTableCell">Fullname</div>
					<div class="divTableCell">Code</div>
					<div class="divTableCell">Birth</div>
					<div class="divTableCell">Average Score</div>
					<div class="divTableCell">Address</div>
					<div class="divTableCell smallcol2">Edit</div>
					<div class="divTableCell smallcol2">Delete</div>
				</div>
				<c:set var="index" value="${pageNumber * (pageIndex - 1) + 1}"
					scope="page" />
				<c:if test="${fn:length(studentList) == 0}">
					<br>
					<h4 class="center">No student found</h4>
				</c:if>
				<c:forEach items="${studentList}" var="student">
					<div class="divTableRow divTableRowData">
						<div class="divTableCell center smallcol">
							<c:out value="${index}" escapeXml="true"></c:out>
						</div>
						<div class="divTableCell center smallcol2">
							<c:out value="${student.getStudentId()}" escapeXml="true"></c:out>
						</div>
						<div class="divTableCell largecol">
							<c:out value="${student.getStudentName()}" escapeXml="true"></c:out>
						</div>
						<div class="divTableCell center smallcol2">
							<c:out value="${student.getStudentCode()}" escapeXml="true"></c:out>
						</div>
						<div class="divTableCell center col10">
							<fmt:formatDate pattern="dd/MM/yyyy"
								value="${student.getStudentInfo().getDateOfBirth()}" />
						</div>
						<div class="divTableCell center ">
							<c:out value="${student.getStudentInfo().getAverageScore()}"
								escapeXml="true"></c:out>
						</div>
						<div class="divTableCell autocol">
							<c:out value="${student.getStudentInfo().getAddress()}"
								escapeXml="true"></c:out>
						</div>
						<div class="divTableCell center smallcol2">
							<a
								href="${pageContext.request.contextPath}/manager/edit/${student.getStudentId()}${searchLink}"><span
								class="editbutton"></span></a>
						</div>
						<div class="divTableCell center smallcol2">
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
		<c:set var="pageNumberLength"
			value="${fn:length(pageParameter.lstPage)}" scope="page" />
		<ul class="pagination">
			<c:choose>
				<c:when test="${pageParameter.pageIndex == 1}">
					<li class="active"><a href="${linkpage}1">&laquo;</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="${linkpage}1">&laquo;</a></li>
				</c:otherwise>
			</c:choose>

			<li><c:forEach var="i" begin="${pageParameter.lstPage[0]}"
					end="${pageParameter.lstPage[pageNumberLength - 1]}">
					<c:choose>
						<c:when test="${i == pageParameter.pageIndex}">
							<li class="active"><a href="${linkpage}${i}">${i}</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="${linkpage}${i}">${i}</a></li>
						</c:otherwise>
					</c:choose>
					<c:set var="i" value="${i + 1}" />
				</c:forEach> <c:choose>
					<c:when
						test="${pageParameter.pageIndex == pageParameter.pageLength}">
						<li class="active"><a
							href="${linkpage}${pageParameter.pageLength}">&raquo;</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="${linkpage}${pageParameter.pageLength}">&raquo;</a></li>
					</c:otherwise>
				</c:choose>
		</ul>
	</center>
	<br>
	<input id="pageIndexSearch" type="hidden" value="${pageIndexSearch}"></input>
	<input id="advanceSearch" type="hidden" value="${student.isAdvanceSearch()}"></input>
	<c:if test="${not empty pageIndexSearch}">
		<script>
			$(document).ready(function() {
				if($("#advanceSearch").val() === "true"){
					showSearchDialog();
					searchViaAjax($("#pageIndexSearch").val(),"","");
				}
				console.log($("#pageIndexSearch").val());
			});
		</script>
	</c:if>
	</body>
	<script>
		$('#dateOfBirthInput').datepicker();
		$('#dateOfBirthUpInput').datepicker();
		$("#dateOfBirthInput").change(function() {
			checkDateSearch();
		});
		$("#dateOfBirthUpInput").change(function() {
			checkDateSearch();
		});
		$("#dateOfBirthInput").on('change keydown paste input', function() {
			var key = event.keyCode || event.charCode;
			if (key != 8 && key != 46)
				return false;
			else {
				$("#dateOfBirthInput").val("");
			}
		});
		$("#dateOfBirthUpInput").on('change keydown paste input', function() {
			var key = event.keyCode || event.charCode;
			if (key != 8 && key != 46)
				return false;
			else {
				$("#dateOfBirthUpInput").val("");
			}
		});
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
		function searchClick(studentId, requestUrl) {
			searchViaAjax(1, studentId, requestUrl);
		}
		function getback() {
			$.ajax({
				type : "GET",
				url : "subView",
				success : function(response) {
					$("html").html(response);
				}
			});
		}
		function searchViaAjax(pageIndex, studentId, requestUrl) {
			var student = {}
			student["studentName"] = $("#srch-term").val();
			student["studentId"] = studentId;
			var x = $(".searchDialog")[0];
			var advanceSearch = false;
			if ($(x).css('display') !== 'none') {
				advanceSearch = true;
				var x = $("#dateOfBirthInput").val();
				var y = $("#dateOfBirthUpInput").val();
				var dateFrom;
				var dateTo;
				if (x !== "" && y !== "") {
					xs = getDateFromFormat($("#dateOfBirthInput").val()
							+ " 00:00:00", "dd/MM/yyyy HH:mm:ss");
					ys = getDateFromFormat($("#dateOfBirthUpInput").val()
							+ " 00:00:00", "dd/MM/yyyy HH:mm:ss");
					dateFrom = new Date(xs);
					dateTo = new Date(ys);
					if (dateFrom > dateTo) {
						alert("Date not invalid!!!");
						$("#dateOfBirthUpInput").val(x);
						return;
					}
				} else if (x !== "") {
					dateFrom = getDateFromFormat($("#dateOfBirthInput").val()
							+ " 00:00:00", "dd/MM/yyyy HH:mm:ss");
				} else if (y !== "") {
					dateTo = getDateFromFormat($("#dateOfBirthUpInput").val()
							+ " 00:00:00", "dd/MM/yyyy HH:mm:ss");
				}
				//set parameter ajax
				student["studentName"] = $("#studentNameInput").val();
				student["averageScore"] = $("#averageScoreInput").val();
				student["address"] = $("#addressInput").val();
				student["studentCode"] = $("#studentCodeInput").val();
				student["dateOfBirth"] = dateFrom;
				student["dateOfBirthUp"] = dateTo;
			}
			student["pageIndex"] = pageIndex;

			if (typeof requestUrl === "undefined" || requestUrl === "") {
				requestUrl = "${pageContext.request.contextPath}/manager/search"
			} else {
				requestUrl = "${pageContext.request.contextPath}/" + requestUrl;
				student["advanceSearch"] =  advanceSearch;
			}
			$
					.ajax({
						type : "POST",
						contentType : "application/json",
						url : requestUrl,
						data : JSON.stringify(student),
						// 						dataType : 'json',
						timeout : 100000,
						success : function(data) {
							console.log("SUCCESS: ", data);
							if (requestUrl === "${pageContext.request.contextPath}/manager/update") {
								document.open();
								document.write(data);
								document.write("<script>document.getElementById(\"myForm\").action = \"${pageContext.request.contextPath}/manager/updatefromajax\";<\/script>");
								document.close();
							} else {
								display(data);
							}
						},
						error : function(e) {
							console.log("ERROR: ", e);
						},
						done : function(e) {
							console.log("DONE");
						}
					});
		}
		function display(data) {
			var listStudent = data['listStudent'];
			var pageIndex = data['pageIndex'];
			var pageLength = data['pageLength'];
			var pageNumber = data['pageNumber'];
			var pageParameter = data['pageParameter'];
			loadDataSearch(listStudent, pageIndex, pageLength, pageNumber,
					pageParameter);
		}
		function checkDateSearch() {
			var x = $("#dateOfBirthInput").val();
			var y = $("#dateOfBirthUpInput").val();
			if (x !== "" && y !== "") {
				var dateFrom = new Date(x);
				var dateTo = new Date(y);
				if (dateFrom > dateTo) {
					alert("Date not invalid!!!");
					$("#dateOfBirthUpInput").html(x);
				}
			}
		}
		function parse(s) {
			return (s < 10) ? '0' + s : s;
		}
		function loadDataSearch(listStudent, pageIndex, pageLength, pageNumber,
				pageParameter) {
			$('.divTableBody').html('');
			$('.pagination').html('');
			var stringtag = "";
			stringtag += "<form name=\"myform\" method=\"post\">";
			stringtag += "<div class=\"divTableRow center titletext\">";
			stringtag += "<div class=\"divTableCell\">STT</div>";
			stringtag += "<div class=\"divTableCell\">ID</div>";
			stringtag += "	<div class=\"divTableCell\">Fullname</div>";
			stringtag += "	<div class=\"divTableCell\">Code</div>";
			stringtag += "	<div class=\"divTableCell\">Birth</div>";
			stringtag += "	<div class=\"divTableCell\">Average Score</div>";
			stringtag += "	<div class=\"divTableCell\">Address</div>";
			stringtag += "	<div class=\"divTableCell\">Edit</div>";
			stringtag += "	<div class=\"divTableCell\">Delete</div>";
			stringtag += "</div>";

			var stt = (pageIndex - 1) * pageNumber + 1;

			for (var index_lstStu = 0; index_lstStu < listStudent.length; index_lstStu++) {
				stringtag += "<div class=\"divTableRow divTableRowData\">";
				stringtag += "<div class=\"divTableCell center smallcol\">"
						+ stt++ + "</div>";
				stringtag += "<div class=\"divTableCell center smallcol2\">"
						+ listStudent[index_lstStu]['studentId'] + "</div>";
				stringtag += "<div class=\"divTableCell largecol\">"
						+ (listStudent[index_lstStu]['studentName'].replace(
								"<", "&lt;").replace(">", "&gt;")) + "</div>";
				stringtag += "<div class=\"divTableCell center\">"
						+ listStudent[index_lstStu]['studentCode'].replace("<",
								"&lt;").replace(">", "&gt;") + "</div>";
				// format date output				
				var date = new Date(listStudent[index_lstStu]['dateOfBirth']);
				date = [ parse(date.getDate()), parse(date.getMonth() + 1),
						date.getFullYear() ].join('/');
				stringtag += "<div class=\"divTableCell center\">" + date
						+ "</div>";
				stringtag += "<div class=\"divTableCell center \">"
						+ listStudent[index_lstStu]['averageScore'] + "</div>";
				stringtag += "<div class=\"divTableCell autocol\">"
						+ listStudent[index_lstStu]['address'].replace("<",
								"&lt;").replace(">", "&gt;") + "</div>";

				stringtag += "<div class=\"divTableCell center\">";
				stringtag += "<a ";
				stringtag += "href=\"javascript:searchViaAjax("
						+ pageParameter.pageIndex
						+ ","
						+ listStudent[index_lstStu]['studentId']
						+ ", 'manager/update')\"><span ";
				stringtag += "class=\"editbutton\"></span></a>";
				stringtag += "</div>";
				stringtag += "<div class=\"divTableCell center\">";
				stringtag += "<a ";
				stringtag += "	href=\"javascript:searchClick("
						+ listStudent[index_lstStu]['studentId']
						+ ", 'manager/delete');\"><span ";
				stringtag += "class=\"deletebutton\"";
				stringtag += "onclick=\"return confirm('Are you sure to delete this?')\"></span></a>";
				stringtag += "</div>";

				stringtag += "</div>";
			}

			stringtag += "</form>";
			if (listStudent.length === 0) {
				stringtag = "<h4 class = \"center\">No student found</h4>";
				$('.divTableBody').html(stringtag);
			} else {
				$('.divTableBody').html(stringtag);

				stringtag = "";
				if (pageParameter.pageIndex === 1)
					stringtag += "<li class=\"active\"><a href=\"javascript:searchViaAjax(1);\">&laquo;</a></li>";
				else
					stringtag += "<li><a href=\"javascript:searchViaAjax(1);\">&laquo;</a></li>";
				for (var indexpage = pageParameter.lstPage[0]; indexpage <= pageParameter.lstPage[pageParameter.lstPage.length - 1]; indexpage++) {
					if (indexpage === pageParameter.pageIndex)
						stringtag += "<li class=\"active\"><a href=\"javascript:searchViaAjax("
								+ indexpage + ");\">" + indexpage + "</a></li>";
					else
						stringtag += "<li ><a href=\"javascript:searchViaAjax("
								+ indexpage + ");\">" + indexpage + "</a></li>";
				}
				if (pageParameter.pageIndex === pageParameter.pageLength)
					stringtag += "<li class=\"active\"><a href=\"javascript:searchViaAjax("
							+ pageParameter.pageLength
							+ ");\">&raquo;</a></li>";
				else
					stringtag += "<li><a href=\"javascript:searchViaAjax("
							+ pageParameter.pageLength
							+ ");\">&raquo;</a></li>";
				$('.pagination').html(stringtag);
			}
		}
	</script>
	</ html>