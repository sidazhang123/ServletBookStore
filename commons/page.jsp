<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<div>
	<c:if test ="${page.pageNum!=null && page.totalPageSize !=null }">
	Current page:${page.pageNum}/Total page:${page.totalPageSize}&nbsp;&nbsp;
    	<a href="${pageContext.request.contextPath}${page.url}&num=1">Home page</a>&nbsp;&nbsp;
    	<c:if test="${page.pageNum != '1'}">
    	<a href="${pageContext.request.contextPath}${page.url}&num=${page.prePageNum}">Previous page</a>&nbsp;&nbsp;
    	</c:if>          
    	<c:if test="${page.pageNum !=page.totalPageSize  }">
    	<a href="${pageContext.request.contextPath}${page.url}&num=${page.nextPageNum}">Next page</a>&nbsp;&nbsp;
    	</c:if>
    	<a href="${pageContext.request.contextPath}${page.url}&num=${page.totalPageSize}">Last page</a>&nbsp;&nbsp;
    	<select id="jump" onchange="jump(this)">
    		<c:forEach begin="1" end="${page.totalPageSize}" var="n">
    		   
    			<option value="${n}" ${page.pageNum==n?'selected="selected"':''}>${n}</option>
    		</c:forEach>
    	</select>
    	&nbsp;&nbsp;
    	<input type="text" size="3" id="num"/><input type="button" value="jump" onclick="jump1()"/>
    	<script type="text/javascript">
    		function jump(selectObj){
    			window.location.href="${pageContext.request.contextPath}${page.url}&num="+selectObj.value;
    		}
    		function jump1(){
    			//得到用户输入的页码
				var num = document.getElementById("num").value;
				//alert(num);
				//验证必须是个自然整数
				if(!/^[1-9][0-9]*$/.test(num)){
					alert("Please inout correct number");
					return;
				}
				//页码有没有超出范围
				if(num>${page.totalPageSize}){
					alert("Page number out of range");
					return;
				}
				window.location.href="${pageContext.request.contextPath}${page.url}&num="+num;
			}
    	</script>
    	</c:if>
    </div>
    