<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/header.jsp"%>
    <h1>The items in cart</h1>
    <c:if test="${empty sessionScope.cart.items}">
    	No Book in cart
    </c:if>
    <c:if test="${!empty sessionScope.cart.items}">
    	<table border="1" width=800">
    		<tr>
    			<th>Title</th>
    			<th>Author</th>
    			<th>Price</th>
    			<th>Amount</th>
    			<th>Total</th>
    			<th>Operation</th>
    		</tr>
    		<c:forEach items="${sessionScope.cart.items}" var="me" varStatus="vs">
    			<tr class="${vs.index%2==0?'even':'odd'}">
	    			<td>${me.value.book.name}</td>
	    			<td>${me.value.book.author}</td>
	    			<td>${me.value.book.price}</td>
	    			<td><input type="text" size="3" id="number" name="number" value="${me.value.number}" onchange="changeNumber(this,'${me.value.number}','${me.key}')"/></td>
	    			<td>${me.value.price}</td>
	    			<td>
	    				<a href="javascript:delOneItem('${me.key}')">Remove</a>
	    			</td>
	    		</tr>
    		</c:forEach>
    		<tr>
    			<td align="right" colspan="6">
    				Total number：${sessionScope.cart.number}&nbsp;&nbsp;
    				Total price：${sessionScope.cart.price}&nbsp;&nbsp;
    				<a href="${pageContext.request.contextPath}/servlet/ClientServlet?op=genOrder">Check out</a>
    			</td>
    		</tr>
    	</table>
    </c:if>
    <script type="text/javascript">
    	function delOneItem(bookId){
    		var sure = window.confirm("Sure to delete？");
    		if(sure){
    			location.href="${pageContext.request.contextPath}/servlet/ClientServlet?op=delOneItem&bookId="+bookId;
    		}
    	}
    	function changeNumber(inputObj,oldNumber,bookId){
    		var value = inputObj.value;
    		//验证值必须是自然整数
    		if(!/^[1-9][0-9]*$/.test(value)){
//     			改为1
				//inputObj.value=1;
				alert("Please input valid amount");
				this.focus();
				return;
    		}
    		var sure = window.confirm("Sure to Change amount to "+value+"？");
    		if(sure){
    			location.href="${pageContext.request.contextPath}/servlet/ClientServlet?op=changeNum&bookId="+bookId+"&num="+value;
    		}else{
    			//改回原来的数量
    			inputObj.value = oldNumber;
    		}
    	}
    </script>
  </body>
</html>
