<%@ page contentType="text/html; charset=UTF-8" %>
<%@	page session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">
<title>chooser</title>
</head>
<body>
	<h1 align="center">股票網路交易平台</h1>
	<div  class="body">
		<form action="${pageContext.request.contextPath}/operation" method="GET" >
			<input type="submit" value="銀行" class="submitBtnCSS" />
		</form>
		<form action="${pageContext.request.contextPath}/operation" method="GET" >
			<input type="submit" value="股票" class="submitBtnCSS" />
		</form>
	
	</div>
</body>
</html>