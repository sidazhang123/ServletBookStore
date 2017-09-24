<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>Advanced Search</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/util.js"></script>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
  </head>
  
  <body>
	  <center>
	  	<br><br/>
	    <h1>Advanced Search</h1>
	    <br/>
	    <a href="${pageContext.request.contextPath}">Home Page</a> 
	    <br/>
	    <br/>
		    <form action="${pageContext.request.contextPath}/servlet/ClientServlet?op=listBookByCategory" method="post">
		    	<table>
					<tr>
			    		<td>Title:</td>
			    		<td><input type = "text" name = "name"></td>
			        </Tr>
			        <tr>
			   			<td>Description:</td>
			   			<td><input type = "text" name = "des"></td>
			   		</tr>
			   		<tr>
			   			<td>Author:</td>
			   			<td><input type = "text" name = "author"></td>
			   		</tr>
			   		<tr>
			   			<td>Type:</td>
				   		<td><select name="categoryId">
				   						<option selected></option>
				    					<c:forEach items="${cs}" var="c">
				    						<option value="${c.id}">${c.name}</option>
				    					</c:forEach>
				    		 </select>
				    	</td>
				    </tr>
				    <tr>
			   			<td>year:</td>
			   			<td><input type = "text" name = "year"></td>
			   		</tr>
			   		<tr>
			   			<td>language:</td>
			   			<td><input type = "text" name = "Venue"></td>
			   		</tr>
			   		<tr>
			   			<td></td>
			   			<td><input  type = "submit" value = "search"></td>
			   		</tr>
		    	</table>
		    </form>
	  </center>
  </body>
</html>
