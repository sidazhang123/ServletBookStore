<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/header.jsp"%>
<h1>Order Info</h1>
<c:if test="${empty orders}">
    	You haven't bought any items yet.
    </c:if>
<c:if test="${!empty orders}">
	<table border="1" width="638">
		<tr>
			<th>Order Number</th>
			<th>Total Amount</th>
			<th>Number of Items</th>
			<th>Status</th>
			<th>Operation</th>
		</tr>
		<c:forEach items="${orders}" var="o" varStatus="vs">
			<tr class="${vs.index%2==0?'even':'odd'}">
				<td><a href="${pageContext.request.contextPath}/servlet/ClientServlet?op=listOrderItems&ordernum=${o.ordernum}">${o.ordernum}</a></td>
				<td>${o.price}</td>
				<td>${o.number}</td>
				<td><c:choose>
						<c:when test="${o.status==0}">
	    						Unpaid
	    					</c:when>
						<c:when test="${o.status==1}">
	    						Paid
	    					</c:when>
						<c:otherwise>

						</c:otherwise>
					</c:choose></td>
				<td><c:choose>
						<c:when test="${o.status==0}">
							<a
								href="${pageContext.request.contextPath}/pay.jsp?ordernum=${o.ordernum}&price=${o.price}">Pay</a>
						</c:when>
						<c:when test="${o.status==1}">
	    						Track
	    					</c:when>
						<c:otherwise>

						</c:otherwise>
					</c:choose></td>
			</tr>
		</c:forEach>
	</table>
</c:if>
</body>
</html>
