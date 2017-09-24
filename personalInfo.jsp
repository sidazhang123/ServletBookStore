<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<script type="text/javascript" src="../js/jquery-1.11.3.js"></script>
<script>
	function check(form) {
		var p1 = form.pw1;
		var p2 = form.pw2;
		var p0 = form.pw0;
		if ((p0.value == null || p0.value == "") && (p1.value == null || p1.value == "") &&
			(p2.value == "" || p2.value == null)) {
			return true;
		}

		if (p1.value != p2.value) {
			if (p1.value == null || p1.value == ""||p2.value == null || p2.value == "") {
				document.getElementById("r2").innerHTML="<font color='red'>This field cannot be empty.</font>";

				return false;
			}
			document.getElementById("r1").innerHTML="<font color='red'>Two inputs are not identical.</font>";
			return false;
		}
		if (p1.value == null || p1.value == ""||p2.value == null || p2.value == "") {
				document.getElementById("r2").innerHTML="<font color='red'>This field cannot be empty.</font>";

				return false;
			}


		if (p0.value == null || p0.value == "") {
			document.getElementById("r0").innerHTML="<font color='red'>This field cannot be empty.</font>";
			return false;
		}
		return true;
	}



	function display(id) {
		var target = document.getElementById(id);
		if (target.style.display == "none") {
			target.style.display = "";
		} else {
			target.style.display = "none";
		}
	}
</script>
</head>
<body>

	<h3>PersonalInfo</h3>
	<c:if test="${empty customer}">
		<h2>no customer obj.</h2>
	</c:if>
	<a href="${pageContext.request.contextPath}">Home Page</a>
	<c:if test="${!empty customer}">
		<form name="form"
			action="${pageContext.request.contextPath}/servlet/ClientServlet?op=changeCustomerInfo"
			method="POST" onsubmit="return check(this);">
			<input type="button" onclick="display('menu')"
				value="Change My Password" />
			<table id="menu" border="1" width="438"
				style="margin-bottom: 10px;display:none;">
				<tr>
					<td>Original password:</td>
					<td><input name="originalpw" id="pw0" type="password"><span id="r0"></span></td>
				</tr>
				<tr>
					<td>New password:</td>
					<td><input name="newpw" id="pw1" type="password"><span id="r1"></span></td>
				</tr>
				<tr>
					<td>Please enter again:</td>
					<td><input id="pw2" type="password"><span id="r2"></span></td>
				</tr>

			</table>
			<table border="1" width="438">
				<tr>
					<td>First Name:</td>
					<td><input name="firstname" value=${customer.firstname }></td>
				</tr>
				<tr>
					<td>Last Name:</td>
					<td><input name="lastname" value=${customer.lastname }></td>
				</tr>
				<tr>
					<td>Nick Name:</td>
					<td><input name="nickname" value=${customer.nickname }></td>
				</tr>
				<tr>
					<td>Year of Birth:</td>
					<td><input name="yearofbirth" pattern="\d{4}"
						title="Please input a valid Year." value=${customer.yearofbirth }></td>
				</tr>
				<tr>
					<td>Phone No.:</td>
					<td><input name="phone" pattern="\d*" title="Digits Only!"
						value=${customer.phone }></td>
				</tr>
				<tr>
					<td>Address:</td>
					<td><input name="address" value=${customer.address }></td>
				</tr>
				<tr>
					<td>Email:</td>
					<td><input name="email" type="email" value=${customer.email }></td>
				</tr>
				<tr>
					<td>Credit Card No.:</td>
					<td><input name="creditcardno" pattern="\d*"
						title="Digits Only!" value=${customer.creditcardno }></td>
				</tr>

			</table>
			<input type="submit" value="Update my Information">
			</table>
		</form>
	</c:if>



</body>
</html>
