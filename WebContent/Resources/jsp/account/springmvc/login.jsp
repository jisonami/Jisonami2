<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/Resources/jsp/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登陆页面</title>
<link href="${_ctxPath }/Resources/css/account/login.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="${_ctxPath }/Resources/js/account/verify.js"></script>
</head>
<body>
	<jsp:include page="/Resources/jsp/common/menu_templet.jsp"></jsp:include>

	<div id="loginForm">
		<span style="color:red">
		<c:if test="${error!=null}">
			${error }
		</c:if>
		</span>
		<form action="${_ctxPath }/springmvc/login.do" method="post">
			<div id="user">
				用户名：<input name="username" type="text" /><br/>
			</div>
			<div id="pass">
				密&nbsp;码：<input name="password" type="password" /><br/>
			</div>
			<div id="control">
				<input type="submit" value="登陆" onclick="return checkLogin(this.form)"/>
				<a href="register.jsp">注册</a>
			</div>
		</form>
	</div>
	
	<jsp:include page="/Resources/jsp/common/copyright_templet.jsp"></jsp:include>
</body>
</html>