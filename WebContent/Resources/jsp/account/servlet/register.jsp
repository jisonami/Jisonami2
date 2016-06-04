<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/Resources/jsp/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>新用户注册</title>
<link href="${_ctxPath }/Resources/css/account/register.css" type="text/css" rel="stylesheet"  />
<script type="text/javascript" src="${_ctxPath }/Resources/js/account/verify.js"></script>
</head>
<body>
	<jsp:include page="/Resources/jsp/common/menu_templet.jsp"></jsp:include>

	<div id="registerForm">
		<span style="color:red">
		<c:if test="${error!=null}">
			${error }
		</c:if>
		</span>
		<form action="${_ctxPath }/servlet/register.do" method="post">
			<div id="user">
				用户名：<input name="username" type="text" /><br /> 
			</div>
			<div id="pass">
				密&nbsp;码：<input name="password" type="password" /><br /> 
			</div>
			<div id="verifyPass">
				确认密码：<input name="verifyPassword" type="password" />
			<br />
			</div>
			<div id="control">
				<input name="register" type="submit" value="注册" onclick="return checkRegister(this.form)"/>
				<a href="login.jsp">返回登陆</a>
			</div>
		</form>
	</div>
	
	<jsp:include page="/Resources/jsp/common/copyright_templet.jsp"></jsp:include>
</body>
</html>