<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
#mynetwork {
	width: 600px;
	height: 600px;
	border: 1px solid lightgray;
}
</style>

<script type="text/javascript" src="vis.js"></script>
<link href="vis.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">
	function draw() {

		var myData = ${sessionScope.data};

	

		// create some nodes
		var nodes = myData[0];

		// create some edges
		var edges = myData[1];

		// create a network
		var container = document.getElementById('mynetwork');
		var data = {
			nodes : nodes,
			edges : edges
		};
		var options = {};

		var network = new vis.Network(container, data, options);
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>



	<input type="button" value="Display" onclick="draw()">

	<div id="mynetwork"></div>

	<input type="hidden" id="data" value="${data }" />

	<!-- 	<input type="button" value="test" onclick="myTest()"> -->


</body>
</html>