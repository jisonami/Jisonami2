<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/Resources/jsp/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>博客首页</title>
<link href="${_ctxPath }/Resources/css/blog/blogIndex.css"
	type="text/css" rel="stylesheet" />
<script src="${_ctxPath }/Resources/js/blog/blogIndex.js"></script>
</head>
<body>
	<jsp:include page="/Resources/jsp/common/menu_templet.jsp"></jsp:include>
	<div class="content">
		<div id="blogmenu">全站分类</div>
		<div id="blogcontent">
			<c:forEach var="blog" items="${blogs }">
				<span id="blogtitlle"> <a
					href='${_ctxPath }/blog/ViewForward.do?blogId=" + ${blog.id } + "'>
						${blog.title } </a>
				</span>
				<br />
				${blog.content }
				<br />
				<span id="author"> ${blog.author } &nbsp;&nbsp;&nbsp;&nbsp; </span>
				<span id="publishtime"> 
					<fmt:formatDate value="${blog.publishTime }" pattern="yyyy-MM-dd HH:mm:ss" />
				</span>
				<br />
				<br />
			</c:forEach>
		</div>
	</div>
	<jsp:include page="/Resources/jsp/common/copyright_templet.jsp"></jsp:include>
</body>
</html>