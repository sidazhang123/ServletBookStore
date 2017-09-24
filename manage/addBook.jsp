<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/header.jsp"%>
    <h3>Add Book</h3>
    <form enctype="multipart/form-data" action="${pageContext.request.contextPath}/servlet/ManageServlet?op=addBook" method="post">
    	<table border="1" width="438">
    		<tr>
    			<td>Title：</td>
    			<td>
    				<input name="name" id="name" />
    			</td>
    		</tr>
    		<tr>
    			<td>Author：</td>
    			<td>
    				<input name="author" id="author" />
    			</td>
    		</tr>
    		<tr>
    			<td>Year：</td>
    			<td>
    				<input name="year" id="year" />
    			</td>
    		</tr>
    		<tr>
    			<td>Venue：</td>
    			<td>
    				<input name="venue" id="language" />
    			</td>
    		</tr>
    		<tr>
    			<td>price：</td>
    			<td>
    				<input name="price" id="price" />元
    			</td>
    		</tr>
    		<tr>
    			<td>Image：</td>
    			<td>
    				<input type="file" name="image" />
    			</td>
    		</tr>
    		<tr>
    			<td>Description：</td>
    			<td>
    				<textarea rows="3" cols="38" name="des"></textarea>
    			</td>
    		</tr>
    		<tr>
    			<td>Type：</td>
    			<td>
    				<select name="categoryId">
    					<c:forEach items="${cs}" var="c">
    						<option value="${c.id}">${c.name}</option>
    					</c:forEach>
    				</select>
    			</td>
    		</tr>
    	</table>
    	<input type="submit" value="Add"/>
    </form>
  </body>
</html>
