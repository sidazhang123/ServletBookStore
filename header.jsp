<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>Welcome</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/util.js"></script>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
  </head>
  
  <body>
  
  <center>
  	<br/><br/>
    <h1>Online Book Store</h1>
    <br/>
   
   <form action="${pageContext.request.contextPath}/servlet/ClientServlet?op=listBookByCategory" method = "post">
   		Title:<input type = "text" name = "name">
   		Description:<input type = "text" name = "des">
   		Type: <select name="categoryId">
   						<option selected></option>
    					<c:forEach items="${cs}" var="c">
    						<option value="${c.id}">${c.name}</option>
    					</c:forEach>
    				</select>
   		<input type = "submit" value = "search">
   		<a href="${pageContext.request.contextPath}/servlet/ClientServlet?op=advancedsearch">Advance Search </a>
   </form>
     
    <a href="${pageContext.request.contextPath}">Home page</a> 
    <c:if test="${sessionScope.customer==null }" >
    	 <a href="${pageContext.request.contextPath}/regist.jsp">Register</a>
    	<a href="${pageContext.request.contextPath}/login.jsp">Login</a>
    </c:if>
      <c:if test="${sessionScope.customer!=null }" >
    	 <a href="${pageContext.request.contextPath}/personalInfo.jsp">${sessionScope.customer.nickname }</a>
    	 <a href="${pageContext.request.contextPath}/servlet/ClientServlet?op=logout">Logout</a>
    	 <a href="${pageContext.request.contextPath}/servlet/ClientServlet?op=sale">sale Management  </a>
    	 <a href="${pageContext.request.contextPath}/servlet/ManageServlet?op=addBookUI">Add book</a>	
    </c:if>
    
   
    <a href="${pageContext.request.contextPath}/servlet/ClientServlet?op=showOrders">My order</a>
    <a href="${pageContext.request.contextPath}/servlet/ClientServlet?op=showCart">Shopping cart</a>
    <br/>
 
    