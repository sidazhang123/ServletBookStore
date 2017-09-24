<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Online Payment</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">


  </head>
  
  <body>
  <center>
    <form action="${pageContext.request.contextPath}/servlet/PayServlet" method="post">
		<table width="60%">
			<tr>
				<td bgcolor="#F7FEFF" colspan="4">
				Order Number：<INPUT TYPE="text" NAME="ordernum" value="${param.ordernum}"  readonly="readonly"> 
				Payment Amount：<INPUT TYPE="text"  size="6" value="${param.price}"  readonly="readonly">Dollars</td>
			</tr>
			<tr>
				<td><br />
				</td>
			</tr>

			<tr>
				<td>
			</tr>
			<tr>
				<td><br />
				</td>
			</tr>
			<tr>
				<td><INPUT TYPE="submit" value="Confirm Payment">
				</td>
			</tr>
		</table>
	</form>
    
    </center>
    
  </body>
</html>
