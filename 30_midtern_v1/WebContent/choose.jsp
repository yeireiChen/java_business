<%@ page contentType="text/html; charset=UTF-8" %>
<%@page session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">
<title>chooser</title>
</head>
<body>
	<h1>股票網路交易平台</h1>
	
	<form action="${pageContext.request.contextPath}/register" method="POST" >
		<input type="text" name="username" id="registerName" hidden="true"/>
		<input type="text" name="password" id="registerPassword" hidden="true"/>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		<input type="submit" value="註冊" class="submitBtnCSS" onClick="registerAccount()"/>
	</form>
</body>
</html>