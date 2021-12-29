<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Details</title>
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
	border: 1;
	border-collapse: collapse;
	padding: 10px;
}

table.center {
	margin-left: auto;
	margin-right: auto;
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
	<header> VERIFY DETAILS </header>
	<br>
	<br>
	<table class="center">
		<tr>
			<th>User ID</th>
			<td>${user.id}</td>
		</tr>
		<tr>
			<th>Name</th>
			<td>${user.name}</td>
		</tr>
		<tr>
			<th>Gender</th>
			<td>${user.gender}</td>
		</tr>
		<tr>
			<th>Language</th>
			<td>${user.english}${user.telugu}${user.tamil}</td>
		</tr>
		<tr>
			<th>Aadhar</th>
			<td>${user.aadhar}</td>
		</tr>
		<tr>
			<th>Mobile</th>
			<td>${user.mobile}</td>
		</tr>
		<tr>
			<th>Email</th>
			<td>${user.email}</td>
		</tr>
		<tr>
			<th>Address</th>
			<td>${user.district}${user.mandal}${user.village}</td>
		</tr>
		<tr>
			<th>Date of Birth</th>
			<td>${user.dob}</td>
		</tr>
		<tr>
			<td><form id="delete_form" method="get"
					action="<%=request.getContextPath()%>/AjaxServlet">
					<input type="hidden" name="operation" value='delete'> <input
						type="hidden" name="u_id" value='${user.id}'>
					<button type="button" class="btn btn-info btn-lg"
						onclick="confirmDelete()">Delete</button>
				</form></td>

			<td><form id="edit_form" method="get"
					action="<%=request.getContextPath()%>/AjaxServlet">
					<input type="hidden" name="operation" value='edit'> <input
						type="hidden" name="u_id" value='${user.id}'>
					<button type="button" class="btn btn-info btn-lg"
						onclick="confirmEdit()">Edit</button>
				</form></td>
		</tr>
	</table>

	<div class="footer">@nic.gov.in</div>
	<script type="text/javascript">
		/* DELETE CONFIRM DIALOG  */
		function confirmDelete() {
			if (confirm("are you sure you want to delete this file?") == true) {
				$('#delete_form').submit();
				return true;
			} else
				return false;
		}
		/* EDIT CONFIRM DIALOG */
		function confirmEdit() {
			if (confirm("are you sure you want to edit this file?") == true) {
				$('#edit_form').submit();
				return true;
			} else
				return false;
		}
	</script>

</body>
</html>