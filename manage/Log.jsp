<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/manage/header.jsp"%>
<h3>show Logs</h3>
    <c:if test="${empty page.records}">
    	<h2>NO logs.</h2>
    </c:if>
    <c:if test="${!empty page.records}">
    	<table border="1" width="900">
    		<tr>
    			<th>seq</th>
    			<th>Operation</th>
    			<th>Book Name</th>
    			<th>User Name</th>
    			<th>Time</th>
   
    		</tr>
    		<c:forEach items="${page.records}" var="b" varStatus="vs">
    			<tr class="${vs.index%2==0?'even':'odd'}">
    				<td>${b.logid}</td>
	    			<td>${b.mark}</td>
	    			<td>${b.bookname}</td>
	    			<td>${b.username}</td>
	    			<td>${b.timestamp}</td>

	    		</tr>
    		</c:forEach>
    	</table>
    	<%@ include file="/commons/page.jsp"%>
    	<a href="${pageContext.request.contextPath}/servlet/ManageServlet?op=cleanLog">clean All Log</a>
    </c:if>
  </body>
</html>
