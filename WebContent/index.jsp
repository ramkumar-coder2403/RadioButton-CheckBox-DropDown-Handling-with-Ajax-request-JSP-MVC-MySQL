<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Upload Form</title>
<!-- adding online script libraries  -->
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.13.0/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/ui/1.13.0/jquery-ui.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.js"></script>

<script type="text/javascript">
	//Reset Form here
	function resetForm() {

		//document.getElementById("u_form").reset();
	}
</script>
<style type="text/css">
select {
	width: 20%;
	padding: 12px 20px;
	margin: 8px 0;
	display: inline-block;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
}

input[type=text], input[type=tel], input[type=email] {
	width: 20%;
	padding: 12px 20px;
	margin: 8px 0;
	display: inline-block;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
}

input[type=submit] {
	width: 20%;
	background-color: #4CAF50;
	color: white;
	padding: 14px 20px;
	margin: 8px 0;
	border: none;
	border-radius: 4px;
	cursor: pointer;
	align-content: center;
}

input[type=submit]:hover {
	background-color: #45a049;
}

div {
	border-radius: 5px;
	background-color: #f2f2f2;
	padding: 20px;
	padding-left: 15%;
}

textarea {
	width: 50%;
	height: 150px;
	padding: 12px 20px;
	margin: 8px 0;
	display: inline-block;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
	padding: 12px 20px;
}

input[type=date] {
	width: 20%;
	padding: 12px 20px;
	margin: 8px 0;
	display: inline-block;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
}
</style>

</head>
<body>
	<h2 style="margin-left: 15%;">General Form</h2>
	<%-- <form id="u_form" name="upload_form"
			action="<%=request.getContextPath()%>/UploadServlet" method="POST"> --%>

	<div id="formm">
		<h3 align="right">
			<a href="view.jsp">View Details</a>
		</h3>
		<label for="Name">Name(Up-to 50chars)<font color="red">*</font></label><br>
		<input type="text" id="u_name" name="u_name" maxlength="50"><br>
		<label for="Gender">Choose Gender<font color="red">*</font>
		</label><br> <input type="radio" id="male" name="gender" value="Male">Male
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="radio" id="female"
			name="gender" value="Female">Female<br> <br> <label
			for="Language">Select Language<font color="red">*</font></label><br>
		<input type="checkbox" id="english" name="language" value="English">English
		&nbsp;&nbsp; <input type="checkbox" id="telugu" name="language"
			value="Telugu">Telugu &nbsp;&nbsp; <input type="checkbox"
			id="tamil" name="language" value="Tamil">Tamil<br> <br>
		<label for="Aadhar">Aadhar(Mandatory for Male)</label><br> <input
			type="tel" id="aadhar" name="aathar" placeholder="123456789012"
			pattern="[0-9]{12}" maxlength="12"><br> <label
			for="Mobile">Mobile Number<font color="red">*</font></label><br>
		<input type="tel" id="mobile" name="mobile" placeholder="6379535469"
			pattern="[6-9]{1}[0-9]{9}" maxlength="12"><br> <label
			for="Email">Email<font color="red">*</font></label><br> <input
			type="email" id="email" name="email"><br> <label
			for="District">Select District<font color="red">*</font></label><br>
		<select id="district" name="district">
			<option value="">Select district</option>
		</select><br> <label for="Mandal">Select Mandal<font color="red">*</font></label><br>
		<select id="mandal" name="mandal">
		</select><br> <label for="Village">Select Village<font color="red">*</font></label><br>
		<select id="village" name="village">
		</select><br> <label for="DOB">Date of Birth<font color="red">*</font></label><br>

		<input type="date" id="dob" name="dob" min="1960-01-01"
			max="2021-12-31"><br> <input type="submit" id="submit"
			value="Submit Form"> <input type="hidden" id="result"
			readonly value="">
	</div>
	<script type="text/javascript">
		$('#submit').click(
				function() {
					//e.preventDefault(); // prevent form from reloading page
					if (validateForm()) {
						let u_name = $('#u_name').val();
						let gender = "";
						let ele = document.getElementsByName('gender');
						for (i = 0; i < ele.length; i++) {
							if (ele[i].checked)
								gender = ele[i].value;
						}
						let english = null;
						let telugu = null;
						let tamil = null;
						let l1 = document.getElementById('english').checked;
						let l2 = document.getElementById('telugu').checked;
						let l3 = document.getElementById('tamil').checked;

						if (l1)
							english = document.getElementById('english').value;
						if (l2)
							telugu = document.getElementById('telugu').value;
						if (l3)
							tamil = document.getElementById('tamil').value;
						let aadhar = $('#aadhar').val();
						let mobile = $('#mobile').val();
						let email = $('#email').val();
						let district = $('#district').val();
						let mandal = $('#mandal').val();
						let village = $('#village').val();
						let dob = $('#dob').val();

						console.log(dob + " : " + gender + " : " + tamil);
						console.log(aadhar + " : " + mobile + " : " + email
								+ " : " + district);
						let data = {
							operation : 'insert',
							name : u_name,
							gender : gender,
							english : english,
							telugu : telugu,
							tamil : tamil,
							aadhar : aadhar,
							mobile : mobile,
							email : email,
							district : district,
							mandal : mandal,
							village : village,
							dob : dob
						};
						var resultData = null;
						$.ajax({
							url : "AjaxServlet",
							method : "POST",
							data : data,
							success : function(data, textStatus, jqXHR) {
								console.log(data);

								resultData = parseInt(data);

								console.log("values : " + resultData);
								if (resultData == 2) {
									alert("Already registered!");

								}
								if (resultData == 1) {
									alert("Registered Successfully!");
								}

								document.getElementById('u_name').value = '';
							},
							error : function(jqXHR, textStatus, errorThrown) {
								console.log("err : " + jqXHR);
							},
							cache : false
						});

						/* console.log("Here...");
						 $('#u_form').each(function() {
							this.reset();
						});  */

					}
				});
	</script>
	<script type="text/javascript">
		//Form Validation Here.....
		//Name Validation
		function validateForm() {
			let name = document.getElementById('u_name').value;
			let result = /^[a-zA-Z ]+$/.test(name);
			if (!result) {
				alert("Enter Valid name!");
				return false;
			}
			if (name.length < 3) {
				alert("Please Enter maximum 3 letters");
				return false;
			}
			//RadioButton Validate / Gender
			if ((document.getElementById('male').checked == false)
					&& (document.getElementById('female').checked == false)) {
				alert("Please Pick Gender");
				return false;
			}
			//CheckBox Validation / Language
			if ((document.getElementById("english").checked == false)
					&& (document.getElementById("telugu").checked == false)
					&& (document.getElementById("tamil").checked == false)) {
				alert("Atleast choose one language");
				return false;
			}
			//Aadhar validation 
			var aadharRegx = /^[0-9]{12}$/;
			var aadhar = document.getElementById("aadhar").value;
			if (document.getElementById('male').checked == true) {

				if (!aadhar.match(aadharRegx)) {
					alert("Aadhar mandatory for Male....Please provide valid Aathar");
					return false;
				}
			}
			if (aadhar != '' && !aadhar.match(aadharRegx)) {
				alert("Please provide valid Aathar");
				return false;
			}
			//Mobile Number Validation
			var regexPattern = new RegExp(/^[0-9]+$/);
			var mobile = document.getElementById('mobile').value;
			if (mobile.value == '' || !regexPattern.test(mobile)
					|| mobile.length != 10) {
				alert("Enter valid Mobile No");
				return false;
			}
			//Email Validation
			var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
			var mail = document.getElementById('email').value;
			if (mail == '' || !mail.match(mailformat)) {
				alert("Enter valid Email ID");
				return false;
			}
			//Address Validation
			if (document.getElementById('district').value == ''
					|| document.getElementById('mandal').value == ''
					|| document.getElementById('village').value == '') {
				alert("Please select all address field!");
				return false;
			}

			//DOB validation
			var dob = document.getElementById('dob').value;
			console.log(dob);
			if (dob === '' || dob === null) {
				alert("Please Pick DOB!");
				return false;
			}

			return true;
		}
	</script>
	<script type="text/javascript">
		//Ajax for getting dynamic dropdown.....
		$(document)
				.ready(
						function() {
							$
									.ajax({
										url : "DropDownServlet",
										method : "GET",
										data : {
											operation : 'district'
										},
										success : function(data, textStatus,
												jqXHR) {
											console.log(data);
											let obj = $.parseJSON(data);
											$.each(obj, function(key, value) {
												$('#district').append(
														'<option value="' + value.id + '">'
																+ value.name
																+ '</option>')
											});
										},
										error : function(jqXHR, textStatus,
												errorThrown) {
											$('#district')
													.append(
															'<option>District Unavailable</option>');
										},
										cache : false
									});

							$('#district')
									.change(
											function() {
												$('#mandal').find('option')
														.remove();
												$('#mandal')
														.append(
																'<option value="">Select Mandal</option>');
												$('#village').find('option')
														.remove();
												$('#village')
														.append(
																'<option value="">Select village</option>');

												let tid = $('#district').val();
												let data = {
													operation : "mandal",
													id : tid
												};

												$
														.ajax({
															url : "DropDownServlet",
															method : "GET",
															data : data,
															success : function(
																	data,
																	textStatus,
																	jqXHR) {
																console
																		.log(data);
																let obj = $
																		.parseJSON(data);
																$
																		.each(
																				obj,
																				function(
																						key,
																						value) {
																					$(
																							'#mandal')
																							.append(
																									'<option value="' + value.id + '">'
																											+ value.name
																											+ '</option>')
																				});
															},
															error : function(
																	jqXHR,
																	textStatus,
																	errorThrown) {
																$('#mandal')
																		.append(
																				'<option value="">Mandal Unavailable</option>');
															},
															cache : false
														});
											});

							$('#mandal')
									.change(
											function() {
												$('#village').find('option')
														.remove();
												$('#village')
														.append(
																'<option value="">Select Village</option>');

												let sid = $('#mandal').val();
												let data = {
													operation : "village",
													id : sid
												};

												$
														.ajax({
															url : "DropDownServlet",
															method : "GET",
															data : data,
															success : function(
																	data,
																	textStatus,
																	jqXHR) {
																console
																		.log(data);
																let obj = $
																		.parseJSON(data);
																$
																		.each(
																				obj,
																				function(
																						key,
																						value) {
																					$(
																							'#village')
																							.append(
																									'<option value="' + value.id + '">'
																											+ value.name
																											+ '</option>')
																				});

															},
															error : function(
																	jqXHR,
																	textStatus,
																	errorThrown) {
																$('#village')
																		.append(
																				'<option value="">Village Unavailable</option>');
															},
															cache : false
														});
											});

						});
	</script>
</body>
</html>