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
		<p class="title">交易列表</p>
		<table align="center" cellpadding="8" border="2" >
			<tr>
				<td style="text-align:center;"><font size="1">序號</font></td>
				<td style="text-align:center;"><font size="1">帳戶</font></td>				
				<td style="text-align:center;"><font size="1">類型</font></td>
				<td style="text-align:center;"><font size="1">金額</font></td>
				<td style="text-align:center;"><font size="1">餘額</font></td>
				<td style="text-align:center;"><font size="1">時間</font></td>
			</tr>
			<c:forEach items="${TransactionModelList}" var="TransactionModelList">
				<tr>
					<td><font size="1">${TransactionModelList.transactionId}</font></td>
					<td><font size="1">${TransactionModelList.username}</font></td>
					<td><font size="1">${TransactionModelList.type}</font></td>
					<td><font size="1">${TransactionModelList.amount}</font></td>
					<td><font size="1">${TransactionModelList.balance}</font></td>
					<td><font size="1">${TransactionModelList.timeStamp}</font></td>
				</tr>
			</c:forEach>
		</table>
		
		<p><font style="font-size:12px" color="red">${requestScope.message}</font></p>
		
		<p>
		<form action="${pageContext.request.contextPath}/account" method="GET" >
			<input style="width:50px;" type="submit" value="帳戶列表" class="submitBtnCSS" />
		</form>
		<sec:authorize access="hasAnyRole('director')">
			<form action="${pageContext.request.contextPath}/security" method="GET" >
				<input style="width:50px;" type="submit" value="權限列表" class="submitBtnCSS" />
			</form>
		</sec:authorize>
		<form action="${pageContext.request.contextPath}/logout" method="POST" >
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			<input style="width:50px;" type="submit" value="登出" class="submitBtnCSS" />
		</form>
	</div>
</body>
</html>