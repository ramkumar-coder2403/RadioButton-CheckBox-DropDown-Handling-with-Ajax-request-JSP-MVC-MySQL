<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>All details</title>
<!-- ADDING ONLINE SCRIPTS FOR BOOTSTRAP  -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<!-- END ADDING ONLINE SCRIPTS  -->
<style type="text/css">
/* Style the header */
header {
	background-color: #666;
	padding: 30px;
	text-align: center;
	font-size: 35px;
	color: white;
}
/* Style the table */
table, th, td {
	border-collapse: collapse;
	padding: 10px;
}

/* Style the footer */
.footer {
	position: fixed;
	padding-top: 10px;
	padding-bottom: 10px;
	left: 0;
	bottom: 0;
	width: 100%;
	background-color: #777;
	color: white;
	text-align: center;
}
</style>
</head>
<body>
	<header> USER DETAILS </header>
	<br>
	<br>&nbsp;&nbsp;
	<table border="1">
		<tr>
			<th>User ID</th>
			<th>Name</th>
			<th>Gender</th>
			<th>Language</th>
			<th>Aadhar</th>
			<th>Mobile</th>
			<th>email</th>
			<th>Address</th>
			<th>Date of Birth</th>
		</tr>
		<c:forEach var="user" items="${userList}">
			<tr>
				<td>${user.id}</td>
				<td>${user.name}</td>
				<td>${user.gender}</td>
				<td>${user.language}</td>
				<td>${user.aadhar}</td>
				<td>${user.mobile}</td>
				<td>${user.email}</td>
				<td>${user.address}</td>
				<td>${user.dob}</td>
			</tr>
		</c:forEach>
	</table>&nbsp;&nbsp;&nbsp;&nbsp;
	<%--For displaying Previous link except for the 1st page --%>
	<c:if test="${currentPage != 1}">
		<td><a href="AjaxServlet?operation=view&page=${currentPage - 1}">Previous</a></td>
	</c:if>

	<%--For displaying Page numbers. 
    The when condition does not display a link for the current page--%>
	<table border="1">
		<tr>
			<c:forEach begin="1" end="${noOfPages}" var="i">
				<c:choose>
					<c:when test="${currentPage eq i}">
						<td>${i}</td>
					</c:when>
					<c:otherwise>
						<td><a href="AjaxServlet?operation=view&page=${i}">${i}</a></td>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</tr>
	</table>

	<%--For displaying Next link --%>
	<c:if test="${currentPage lt noOfPages}">
		<td><a href="AjaxServlet?operation=view&page=${currentPage + 1}">Next</a></td>
	</c:if>

	<div class="footer">@nic.gov.in</div>
</body>
</html>