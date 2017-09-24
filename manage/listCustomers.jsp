<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/manage/header.jsp"%>

<center>
	<h3>listCustomers</h3>
	<c:if test="${empty page.records}">
		<h2>no customers</h2>
	</c:if>
	<c:if test="${!empty page.records}">
		<table border="1" width="438">
			<tr>
				<th>Username</th>
				<th>Is_banned</th>
				<th>Operation</th>
			</tr>
			<c:forEach items="${page.records}" var="c" varStatus="vs">
				<tr class="${vs.index%2==0?'even':'odd'}">
					<td><a href="${pageContext.request.contextPath}/servlet/ManageServlet?op=showCustomerInfo&code=${c.code}">${c.username}</a></td>
					<td>${c.isbanned}</td>
					<td><c:if test="${!c.isbanned=='true'}"> 
[<a
								href="${pageContext.request.contextPath}/servlet/ManageServlet?op=banCustomer&isbanned=true&code=${c.code}">ban</a>]
</c:if> <c:if test="${c.isbanned=='true'}"> 
[<a
								href="${pageContext.request.contextPath}/servlet/ManageServlet?op=banCustomer&isbanned=false&code=${c.code}">reactive</a>]
</c:if></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</center>
<script type="text/javascript" src="../js/jquery-1.11.3.js"></script>
<script type="text/javascript">

	function del(obj) {
		var Vname = $(obj).parent().prev().prev().text();
		$.post("${pageContext.request.contextPath}/servlet/ManageServlet", {
			op : 'delCategory',
			name : Vname
		}, function(data) {});
	}
</script>


</body>
</html>
