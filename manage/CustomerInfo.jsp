<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<script type="text/javascript" src="../js/jquery-1.11.3.js"></script>

</head>
<body>

	<h3>CustomerlInfo</h3>
	<c:if test="${empty cus}">
		<h2>no customer obj.</h2>
	</c:if>
	<a href="${pageContext.request.contextPath}/servlet/ManageServlet?op=listCustomers">Customer list</a>
	<c:if test="${!empty cus}">
		<form name="form"
			action="${pageContext.request.contextPath}/servlet/ManageServlet?op=changeCustomerInfo&code=${cus.code}"
			method="POST" >


			<table border="1" width="438">
				<tr>
					<td>Username:</td>
					<td><input name="username" value=${cus.username }></td>
				</tr>
				<tr>
					<td>Password: </td>
					<td><input name="password" value=${cus.password }></td>
				</tr>
				<tr>
					<td>First Name:</td>
					<td><input name="firstname" value=${cus.firstname }></td>
				</tr>
				<tr>
					<td>Last Name:</td>
					<td><input name="lastname" value=${cus.lastname }></td>
				</tr>
				<tr>
					<td>Nick Name:</td>
					<td><input name="nickname" value=${cus.nickname }></td>
				</tr>
				<tr>
					<td>Year of Birth:</td>
					<td><input name="yearofbirth" pattern="\d{4}"
						title="Please input a valid Year." value=${cus.yearofbirth }></td>
				</tr>
				<tr>
					<td>Phone No.:</td>
					<td><input name="phone" pattern="\d*" title="Digits Only!"
						value=${cus.phone }></td>
				</tr>
				<tr>
					<td>Address:</td>
					<td><input name="address" value=${cus.address }></td>
				</tr>
				<tr>
					<td>Email:</td>
					<td><input name="email" type="email" value=${cus.email }></td>
				</tr>
				<tr>
					<td>Credit Card No.:</td>
					<td><input name="creditcardno" pattern="\d*"
						title="Digits Only!" value=${cus.creditcardno }></td>
				</tr>

			</table>
			<input type="submit" value="Update Information">
			
		</form>
	</c:if>



</body>
</html>
