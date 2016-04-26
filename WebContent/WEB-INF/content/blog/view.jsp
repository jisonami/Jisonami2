<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/Resources/jsp/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>预览文章</title>
	<link href="${_ctxPath }/Resources/css/blog/publish.css" type="text/css" rel="stylesheet" />
</head>
<body>
<jsp:include page="blogheader_templet.jsp"></jsp:include>
	
	<div class="content">
		<jsp:include page="blogmenu_templet.jsp"></jsp:include>
		
		<div id="blogcontent">
			<a href="${_ctxPath }/blog/blogForward.do">返回博客列表</a><br/><br/>
			<input name="blogId" type="hidden" value="${blog.id }"/>
			博客标题：${blog.title }
			<br/><br/>
			<fmt:formatDate value="${blog.publishTime }" pattern="yyyy-MM-dd" />
			<br/><br/>
			博客类型：${blogTypes }
			<br/><br/>
			${blog.content }
		</div>
	</div>
	
<jsp:include page="/Resources/jsp/common/copyright_templet.jsp"></jsp:include>
</body>
</html>