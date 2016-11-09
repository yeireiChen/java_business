<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<link href="css/style.css" rel="stylesheet" type="text/css">
	<title>00_hw06</title>
</head>
<body>
	<div class="body">
		<p class="title">交易明細</p>
		<div style="width:130px;margin:0 auto;">
    		<p align="left" style="margin:7 auto;" class="content">帳戶：${StatementModel.username}</p>
    		<p align="left" style="margin:7 auto;" class="content">餘額：${StatementModel.balance} 元</p>
  		</div>
		<table align="center" cellpadding="8" border="2" >
			<tr>
				<td style="text-align:center;"><font size="1">序號</font></td>
				<td style="text-align:center;"><font size="1">作業</font></td>
				<td style="text-align:center;"><font size="1">金額</font></td>
				<td style="text-align:center;"><font size="1">餘額</font></td>
				<td style="text-align:center;"><font size="1">時間</font></td>
			</tr>
			<c:forEach items="${StatementModel.statementList}" var="StatementModel">
				<tr>
					<td style="text-align:center;"><font size="1">${StatementModel.transactionId}</font></td>
					<td style="text-align:center;"><font size="1">${StatementModel.type}</font></td>
					<td style="text-align:center;"><font size="1">${StatementModel.amount}</font></td>
					<td style="text-align:center;"><font size="1">${StatementModel.balance}</font></td>
					<td style="text-align:center;"><font size="1">${StatementModel.timeStamp}</font></td>
				</tr>
			</c:forEach>
		</table>
		<p><font style="font-size:12px" color="red">${requestScope.message}</font></p>
		<p>
		<a href="${pageContext.request.contextPath}/operation"><font size="1">回上頁</font></a>
	</div>
</body>
</html>