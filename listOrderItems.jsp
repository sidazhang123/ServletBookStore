<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/header.jsp"%>
<h1>Order details</h1>
<c:if test="${empty itemList}">
    	itemList is empty.
    </c:if>
<c:if test="${!empty itemList}">
	<table border="1" width="638">
		<tr>
			<th>Book Name</th>
			<th>Author</th>
			<th>Price</th>
			<th>Number of Items</th>

		</tr>
		<c:forEach items="${itemList}" var="orderitem" varStatus="vs">
			<tr class="${vs.index%2==0?'even':'odd'}">
				<td><a href="${pageContext.request.contextPath}/servlet/ClientServlet?op=detail&bookId=${orderitem.book.id}">${orderitem.book.name}</a></td>
				<td>${orderitem.book.author}</td>
				<td>${orderitem.book.price}</td>
				<td>${orderitem.number}</td>
			</tr>
		</c:forEach>
	</table>
</c:if>
</body>
</html>
