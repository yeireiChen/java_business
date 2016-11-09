<%@ page contentType="text/html; charset=UTF-8" %>
<%@page session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html>
<head>
	<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">
	<title>00_hw06</title>
</head>
<body>
	<div class="body">
		<p class="title">帳戶交易</p>
		<sec:authorize access="hasAnyRole('customer')">
			<div style="width:130px;margin:0 auto;">
					<p align="left" style="margin:7 auto;" class="content">帳戶：${AccountModel.username}</p>
					<p align="left" style="margin:7 auto;" class="content">餘額：${AccountModel.balance}</p>
					<p align="left" style="margin:7 auto;" class="content">金額：<input type="number" name="amount" id="amount" size="6"  min="1" style="height:15px;width:70px"/></p>
			</div>
		</sec:authorize>
		<p><font style="font-size:12px" color="red">${requestScope.message}</font></p>
		<sec:authorize access="hasAnyRole('customer')">
			<form action="${pageContext.request.contextPath}/deposit" method="POST" >
				<input type="number" name="amount" id="depositAmount" hidden="true"/>
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				<input type="submit" value="存款" class="submitBtnCSS" onClick="addBalance()"/>
			</form>
			<form action="${pageContext.request.contextPath}/withdraw" method="POST" >
				<input type="number" name="amount" id="withdrawAmount" hidden="true"/>
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				<input type="submit" value="提款" class="submitBtnCSS" onClick="subtractBalance()"/>
			</form>
			<form action="${pageContext.request.contextPath}/statement" method="POST" >
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				<input type="submit" value="明細" class="submitBtnCSS" />
			</form>
			<form action="${pageContext.request.contextPath}/close" method="POST" >
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				<input type="submit" value="結清" class="submitBtnCSS" />
			</form>
		</sec:authorize>
		<sec:authorize access="hasAnyRole('manager', 'director')">
			<form action="${pageContext.request.contextPath}/account" method="GET" >
				<input style="width:50px;" type="submit" value="帳戶列表" class="submitBtnCSS" />
			</form>
			<form action="${pageContext.request.contextPath}/transaction" method="GET" >
				<input style="width:50px;" type="submit" value="交易列表" class="submitBtnCSS" />
			</form>
		</sec:authorize>
		<sec:authorize access="hasAnyRole('director')">
			<form action="${pageContext.request.contextPath}/security" method="GET" >
				<input style="width:50px;" type="submit" value="權限管理" class="submitBtnCSS" />
			</form>
		</sec:authorize>
		<form action="${pageContext.request.contextPath}/logout" method="POST" >
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			<input style="width:50px;" type="submit" value="登出" class="submitBtnCSS" />
		</form>
	</div>
</body>
</html>
<script type="text/javascript">
function addBalance(){
	document.getElementById("depositAmount").value = document.getElementById("amount").value;
}

function subtractBalance(){
	document.getElementById("withdrawAmount").value = document.getElementById("amount").value;
}
</script>
