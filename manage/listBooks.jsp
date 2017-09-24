<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/manage/header.jsp"%>
<h3>Books List</h3>
    <c:if test="${empty page.records}">
    	<h2>No books added，<a href="${pageContext.request.contextPath}/servlet/ManageServlet?op=addBookUI">Add</a></h2>
    </c:if>
    <c:if test="${!empty page.records}">
    	<table border="1" width="900">
    		<tr>
    			<th>Image</th>
    			<th>Title</th>
    			<th>Author</th>
    			<th>Price</th>
    			<th>Description</th>
    			<th>Type</th>
    			<th>Operation</th>
    		</tr>
    		<c:forEach items="${page.records}" var="b" varStatus="vs">
    			<tr class="${vs.index%2==0?'even':'odd'}">
	    			<td>
	    				<img src="${pageContext.request.contextPath}/../images/${b.path}/${b.filename}"/>
	    			</td>
	    			<td>${b.name}</td>
	    			<td>${b.author}</td>
	    			<td>${b.price}</td>
	    			<td>${b.des}</td>
	    			<td>${b.category.name}</td>
	    			<td>
	    				[<a href="javascript:alert('略')">Modify</a>]
	    				[<a href="javascript:alert('略')">Delete</a>]
	    			</td>
	    		</tr>
    		</c:forEach>
    	</table>
    	<%@ include file="/commons/page.jsp"%>
    </c:if>
  </body>
</html>
