<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/Resources/jsp/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登陆页面</title>
<link href="${_ctxPath }/Resources/css/index.css" type="text/css" rel="stylesheet">
</head>
<body>
	<jsp:include page="/Resources/jsp/common/menu_templet.jsp"></jsp:include>

	<div id="content">
		<c:choose>
			<c:when test="${username != null }">
				<span class="tips">登录成功！</span>
			</c:when>
			<c:otherwise>
				<span class="tips">请先登录！</span>
			</c:otherwise>
		</c:choose>
	</div>
	
	<jsp:include page="/Resources/jsp/common/copyright_templet.jsp"></jsp:include>
</body>
</html>