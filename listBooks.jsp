<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/header.jsp"%>
    <br/>
  <c:if test="${page==null}">
  		<h3>No matched books found!</h3>
  </c:if>
  <c:if test="${page!=null}">
    Books List：
    <a href="${pageContext.request.contextPath}/servlet/ClientServlet?op=vis">Graph</a>
    <%-- <c:forEach items="${cs}" var="c">
    	<a href="${pageContext.request.contextPath}/servlet/ClientServlet?op=listBookByCategory&categoryId=${c.id}">${c.name}</a>&nbsp;&nbsp;
    </c:forEach> --%>
    <hr/>
    <table>
    	
    		<c:forEach items="${page.records}" var="b">
    		<tr>
    			<td>
    				<img src="${pageContext.request.contextPath}/../images/${b.path}/${b.filename}"/><br/>
    			</td>
    			<td>
    				Title：<a href="${pageContext.request.contextPath}/servlet/ClientServlet?op=detail&bookId=${b.id}">${b.name}<br/></a>
    				Author：${b.author}<br/>
    				Price：${b.price}<br/>
    				<c:if test="${customer.code != b.code}">
    					<a href="${pageContext.request.contextPath}/servlet/ClientServlet?op=buyBook&bookId=${b.id}">Add to cart</a>
    				</c:if>
    			</td>
    		</tr>
    		</c:forEach>	
    </table>	
    <%@ include file="/commons/page.jsp"%>
   </c:if>
    </center>
  </body>
</html>
