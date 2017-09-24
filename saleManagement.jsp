<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/header.jsp"%>
    <br/>
    Books List：
    <%-- <c:forEach items="${cs}" var="c">
    	<a href="${pageContext.request.contextPath}/servlet/ClientServlet?op=listBookByCategory&categoryId=${c.id}">${c.name}</a>&nbsp;&nbsp;
    </c:forEach> --%>
    <hr/>
    <c:if test="${ page ==null}">
    	<h3>No book selling</h3>
    </c:if>
    <c:if test="${ page !=null}">
    <table>
    	<tr>
    		<c:forEach items="${page.records}" var="b">
    			<td>
    				<img src="${pageContext.request.contextPath}/../images/${b.path}/${b.filename}"/><br/>
    				Title：<a href="${pageContext.request.contextPath}/servlet/ClientServlet?op=detail&bookId=${b.id}">${b.name}<br/></a>
    				Author：${b.author}<br/>
    				Price：${b.price}<br/>
    				<c:if test="${b.status =='0' }" >	
    					<a href="${pageContext.request.contextPath}/servlet/ClientServlet?op=relist&bookId=${b.id}">Relist</a>
    				</c:if>
    				<c:if test="${b.status =='1' }" >	
    					<a href="${pageContext.request.contextPath}/servlet/ClientServlet?op=pause&bookId=${b.id}">Pause</a>
    				</c:if>
    			</td>
    		</c:forEach>
    	</tr>	
    </table>
    </c:if>	
    <%@ include file="/commons/page.jsp"%>
    
    </center>
  </body>
</html>
