<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Display data</title>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.13.0/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/ui/1.13.0/jquery-ui.js"></script>
<style type="text/css">
table {
	font-family: arial, sans-serif;
	border-collapse: collapse;
	width: 100%;
}

div {
	border-radius: 5px;
	background-color: #f2f2f2;
	padding: 20px;
}

td, th {
	border: 1px solid #dddddd;
	text-align: left;
	padding: 8px;
}
</style>
</head>
<body>
	<h2 align="center">Details</h2>
	<div>
		<table id="content">
			<tr>
				<th>Name</th>
				<th>Gender</th>
				<th>Language</th>
				<th>Aadhar</th>
				<th>Mobile</th>
				<th>email</th>
				<th>Address</th>
				<th>Date of Birth</th>
				<th>Edit</th>
				<th>Delete</th>
			</tr>

		</table>
	</div>
	<script type="text/javascript">
	console.log("length : "+history.length);
		$(document)
				.ready(
						function() {
							$
									.ajax({

										url : "OptServlet",
										method : "GET",
										data : {
											operation : 'get_details'
										},

										success : function(data, textStatus,
												jqXHR) {
											console.log(data);
											let obj = $.parseJSON(data);

											$
													.each(
															obj,
															function(key, value) {

																$('#content')
																		.append(
																				'<tr> <td>'
																						+ value.name
																						+ '</td> <td>'
																						+ value.gender
																						+ '</td> <td>'
																						+ value.language
																						+ '</td> <td>'
																						+ value.aadhar
																						+ '</td> <td>'
																						+ value.mobile
																						+ '</td><td>'
																						+ value.email
																						+ '</td> <td>'
																						+ value.address
																						+ '</td><td>'
																						+ value.dob
																						+ '</td> <td><a href=OptServlet?operation=edit&id='
																						+ value.id
																						+ '>Edit</a></td><td><a href=OptServlet?operation=delete&id='
																						+ value.id
																						+ '>Delete</a></td>')
															});
											/* $('select').formSelect(); */
										},
										error : function(jqXHR, textStatus,
												errorThrown) {
											$('#content').append(
													'<td>null</td>');
										},
										cache : false
									});
						});
	</script>
</body>
</html>