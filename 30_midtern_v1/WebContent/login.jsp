<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page session="true"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
	<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">
	
	<title>00_hw06</title>
</head>
<body>
	<div class="body">
		<p class="title">帳戶登入</p>
		<div align="center">
			<p style="display:inline" class="content">帳戶</p>　<input type="text" name="username" id="username" value="${UserModel.username}" size="7" style="height:15px"/>
		</div>
		<div>
			<p style="display:inline" class="content">密碼</p>　<input type="password" name="password" id="password"  value="${UserModel.password}" size="7" style="height:15px"/>
		</div>
		<p><font style="font-size:12px" color="red">${requestScope.message}</font></p>
		<form action="${pageContext.request.contextPath}/login" method="POST">
			<input type="text" name="username" id="loginName" hidden="true"/>
			<input type="text" name="password" id="loginPassword" hidden="true"/>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			<input type="submit" value="登入" class="submitBtnCSS" onClick="loginAccount()"/>
		</form>
		<c:if test="${UserModel.userId == 0 || empty UserModel.userId}">
			<form action="${pageContext.request.contextPath}/register" method="POST" >
				<input type="text" name="username" id="registerName" hidden="true"/>
				<input type="text" name="password" id="registerPassword" hidden="true"/>
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				<input type="submit" value="註冊" class="submitBtnCSS" onClick="registerAccount()"/>
			</form>
		</c:if>
	</div>
</body>

<script type="text/javascript">
function loginAccount(){
	document.getElementById("loginName").value = document.getElementById("username").value;
	document.getElementById("loginPassword").value = document.getElementById("password").value;
}

function registerAccount(){
	document.getElementById("registerName").value = document.getElementById("username").value;
	document.getElementById("registerPassword").value = document.getElementById("password").value;
}
</script>
</html>