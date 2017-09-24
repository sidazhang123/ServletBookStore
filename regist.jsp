<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/header.jsp"%>
    <h3>New User</h3>
    <form action="${pageContext.request.contextPath}/servlet/ClientServlet?op=customerRegist" method="post">
    	<table border="1" width="438">
    		<tr>
    			<td>Username：</td>
    			<td>
    				<input name="username" required/>
    			</td>
    		</tr>
    		<tr>
    			<td>Password：</td>
    			<td>
    				<input type="password" name="password" required/>
    			</td>
    		</tr>
    		<tr>
    			<td>First Name:</td>
    			<td>
    				<input name="firstname"/>
    			</td>
    		</tr>
    		<tr>
    			<td>Last Name:</td>
    			<td>
    				<input name="lastname"/>
    			</td>
    		</tr>
    		<tr>
    			<td>Nick Name:</td>
    			<td>
    				<input name="nickname" required/>
    			</td>
    		</tr>
    		<tr>
    			<td>Year of Birth:</td>
    			<td>
    				<input name="yearofbirth" pattern="\d{4}" title="Please input a valid Year." name="yearofbirth"/>
    			</td>
    		</tr>
    		<tr>
    			<td>Phone number：</td>
    			<td>
    				<input name="phone" pattern="\d*" title="Digits Only!" name="phone"/>
    			</td>
    		</tr>
    		<tr>
    			<td>Address：</td>
    			<td><input name="address"/></td>
    		</tr>
    		<tr>
    			<td>Email：</td>
    			<td>
    				<input type="email" name="email" required/>
    			</td>
    		</tr>
    		<tr>
    			<td>Credit Card No.:</td>
    			<td>
    				<input name="creditcardno" pattern="\d*" title="Digits Only!" required/>
    			</td>
    		</tr>
    		<tr>
    			<td colspan="2">
    				<input type="submit" value="Regist"/>
    			</td>
    		</tr>
    	</table>
    </form>
  </body>
</html>
