<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/header.jsp"%>
    <br/>
	<h3>Book Details:</h3>
    <%-- <c:forEach items="${cs}" var="c">
    	<a href="${pageContext.request.contextPath}/servlet/ClientServlet?op=listBookByCategory&categoryId=${c.id}">${c.name}</a>&nbsp;&nbsp;
    </c:forEach> --%>
    <table>
		    		<tr>
		    			<img src="${pageContext.request.contextPath}/../images/${book.path}/${book.filename}"/>
		    		</tr>
		    		<tr>
	    				<td>Seller：</td>
	    				<td>${book.seller}</td>
    				</tr>
    				<tr>
	    				<td>Title：</td>
	    				<td>${book.name}</td>
    				</tr>
    				<tr>
    					<td>Type:</td>
    					<td>${book.category.name}</td>
    				</tr>
    				<tr>
    					<td>Author：</td>
    					<td>${book.author}</td>
    				</tr>
    				<tr>
    					<td>Price：</td>
    					<td>${book.price}</td>
    				</tr>
    				<tr>
    					<td>Description:</td>
    					<td>${book.des}</td>
    				</tr>
    				<tr>
    					<td>Publish time：</td>
    					<td>${book.year}</td>
    				</tr>
    				<tr>
    					<td>Venue:</td>
    					<td>${book.venue}</td>
    				</tr>
    				<c:if test="${option=='welcome'}">
    				<tr>
    					<td></td>
    					<td>
    						<a href="${pageContext.request.contextPath}/servlet/ClientServlet?op=buyBook&bookId=${book.id}">Add to cart</a>
    					</td>
    				</tr>
    				</c:if>
    				<c:if test="${option=='listBookByCategory'}">
    				<tr>
    					<td></td>
    					<td><a href="${pageContext.request.contextPath}/servlet/ClientServlet?op=buyBook&bookId=${b.id}">Add to cart</a>
    					</td>
    				</tr>
    				</c:if>
	
    </table>	
    
    </center>
  </body>
</html>
