<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>MIS Report</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- ADDING ONLINE SCRIPTS FOR BOOTSTRAP  -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
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
}

td {
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
	<header> USER IDENTIFICATION </header>
	<table class="center">
		<tr>
			<th><h2>Table of content</h2></th>
		</tr>
		<tr>
			<td><a href="register.jsp"><button type="button"
						class="btn btn-info btn-lg" style="width: 200px;">Register</button></a></td>
		</tr>
		<tr>
			<td><button type="button" class="btn btn-info btn-lg"
					data-toggle="modal" data-target="#myModal" style="width: 200px;">Edit
					and Delete</button></td>
		</tr>
		<tr>
			<td><a href="AjaxServlet?operation=view"><button
						type="button" class="btn btn-info btn-lg" style="width: 200px;">View
						records</button></a></td>
		</tr>
		<!-- <tr>
			<td><a href="exit.jsp">Exit</a></td>
		</tr> -->
	</table>
	<!-- Modal -->
	<!-- FORM START HERE -->
	<form id="form" onsubmit="return submitFunc()" method="get"
		action="<%=request.getContextPath()%>/AjaxServlet">
		<div class="modal fade" id="myModal" role="dialog">
			<div class="modal-dialog">
				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">ENTER USER ID</h4>
					</div>
					<div class="modal-body">
						<input type="hidden" id="operation" name="operation" value="opt"
							required="required"> <label for="userID">User ID<font
							color="red">*</font></label><br> <input type="number" id="u_id"
							name="u_id" maxlength="7"><br>
						<ul class="list-group" id="result"></ul>
						<!-- <br> <label for="Name">Name</label><br> <input
							type="text" id="u_name" name="u_name" maxlength="50" readonly> -->
					</div>
					<div class="modal-footer">
						<input type="submit" class="btn btn-default" data-dismiss="modal"
							value="close"> <input type="submit"
							class="btn btn-default" id="submit" value="Submit">
					</div>


				</div>
			</div>
		</div>
	</form>
	<!-- FORM END -->
	<div class="footer">@nic.gov.in</div>
	<script type="text/javascript">
		
		//AJAX READY STATE FUNCTION HERE
		$(document)
				.ready(
						function() {

							//SET CACHE FALSE
							$.ajaxSetup({
								cache : false
							});
							let obj = null;
							//GET JSON DATA
							$
									.ajax({
										url : "AjaxServlet",
										method : "GET",
										data : {
											operation : 'user_id'
										},
										success : function(data, textStatus,
												jqXHR) {
											console.log(data);
											obj = $.parseJSON(data);

										},
										error : function(jqXHR, textStatus,
												errorThrown) {
											cosole.log("Error");
										},
										cache : false
									});

							$('#u_id')
									.keyup(
											function() {
												$('#result').html('');
												$('#state').val('');
												var searchField = $('#u_id')
														.val();
												var expression = new RegExp(
														searchField, "i");

												$
														.each(
																obj,
																function(key,
																		value) {
																	if (value.id
																			.search(expression) != -1
																			|| value.name
																					.search(expression) != -1) {
																		$(
																				'#result')
																				.append(
																						'<li class="list-group-item link-class"> '
																								+ value.id
																								+ ' | <span class="text-muted">'
																								+ value.name
																								+ '</span></li>');
																	}
																});

											});

							$('#result').on('click', 'li', function() {
								var click_text = $(this).text().split('|');
								$('#u_id').val($.trim(click_text[0]));
								$("#result").html('');
							});

						});
		//SUBMIT FORM FOR EDIT AND DELETE
		function submitFunc() {
			//ID VALIDATION
			var regexPattern = new RegExp(/^[0-9]+$/);
			var u_id = $('#u_id').val();
			if (u_id != '') {
				$('#myModal').modal('toggle');
				//console.log($('#myModal').modal('toggle').length = 0);
				return true;
			} else {
				alert("Enter USER ID");
				return false;
			}

			return false;
		}
	</script>
</body>
</html>
<!-- $('#myModal').modal('toggle'); -->
<!-- ^-- hide model  -->