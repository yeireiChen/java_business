<%@ page contentType="text/html; charset=UTF-8" %>
<%@page session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html>
<head>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.0/jquery.min.js"></script>
	<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">
	<title>00_hw06</title>
</head>
<body>
	<div class="body">
		<p class="title">權限管理</p>
		<table align="center" cellpadding="8" border="2" >
			<tr>
				<td style="text-align:center;"><font size="1">序號</font></td>
				<td style="text-align:center;"><font size="1">管理者</font></td>				
				<td style="text-align:center;"><font size="1">權限</font></td>
				<td style="text-align:center;"><font size="1">作業</font></td>
			</tr>
			<form action="${pageContext.request.contextPath}/security/create" method="POST">
				<input type="text" name="username"/>&nbsp
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				<input type="submit" value="新增" class="submitBtnCSS"/>
			</form>
			<p>
			
			<c:forEach items="${UserModelList}" var="UserModelList">
				<tr>
					<form name="securityForm" action="" method="POST">
						<td>
							<input type="number" name="userId" value="${UserModelList.userId}" hidden="true"/>
							<font size="1">${UserModelList.userId}</font>
						</td>
						<td>
							<input type="text" name="username" value="${UserModelList.username}" hidden="true"/>
							<font size="1">${UserModelList.username}</font>
						</td>
						<td>
							<label>
							    <input type="radio" id="manager" name="role" value="manager" <c:if test="${UserModelList.role == 'manager'}">checked</c:if>>
							    		<font size="1">經理</font>
							</label>
						  	<label>
						    	<input type="radio" id="director" name="role" value="director" <c:if test="${UserModelList.role == 'director'}">checked</c:if>>
						    			<font size="1">總監</font>
						  	</label>
						</td>
						<td>
						    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						    <input type="button" value="修改" onclick="submitForm('${pageContext.request.contextPath}/security/update',this.form)">
						    <input type="button" value="刪除" onclick="submitForm('${pageContext.request.contextPath}/security/delete',this.form)">
						</td>
					</form>
				</tr>
			</c:forEach>
		</table>
		<c:if test="${empty UserModelList}">
				<p><font style="font-size:12px" color="red">無其他管理者</font></p>
			</c:if>
		<p><font style="font-size:12px" color="red">${requestScope.message}</font></p>
		<p>
		<form action="${pageContext.request.contextPath}/account" method="GET" >
			<input style="width:50px;" type="submit" value="帳戶列表" class="submitBtnCSS" />
		</form>
		<sec:authorize access="hasAnyRole('director')">
			<form action="${pageContext.request.contextPath}/transaction" method="GET" >
				<input style="width:50px;" type="submit" value="交易列表" class="submitBtnCSS" />
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
	function submitForm (action, form) {
		$('form[name=securityForm]').attr('action', action);
		form.submit();
	}
</script>