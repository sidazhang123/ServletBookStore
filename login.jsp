<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/header.jsp"%>
    <h3>Login Page</h3>
    <form action="${pageContext.request.contextPath}/servlet/ClientServlet?op=login" method="post">
    	<table border="1" width="600">
    		<tr>
    			<td>Username：</td>
    			<td>
    				<input name="username"/>
    			</td>
    		</tr>
    		<tr>
    			<td>Password：</td>
    			<td>
    				<input type="password" name="password"/>
    			</td>
    		</tr>
    		<tr>
    			<td colspan="2">
    				<input type="submit" value="Login"/>
    			</td>
    		</tr>
    	</table>
    </form>
  </body>
</html>
