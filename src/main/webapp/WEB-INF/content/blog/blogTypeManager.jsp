<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/Resources/jsp/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>博客首页</title>
	<link href="${_ctxPath }/Resources/css/blog/blogTypeManager.css" type="text/css" rel="stylesheet" />
</head>
<body>
	<jsp:include page="blogheader_templet.jsp"></jsp:include>
	
	<div class="content">
		<jsp:include page="blogmenu_templet.jsp"></jsp:include>
		
		<div id="blogcontent">
			<h2>博客分类管理</h2><br/>
			<form action="${_ctxPath }/blog/blogtype/blogTypeManager.do" method="post">
				添加博客分类:<input name="blogType" type="text" />
				<input type="submit" value="提交" />
			</form><br/>
			<span id="blogTypeNameHeader" class="blod-font">名称</span>
			<span id="articleNumberHeader" class="blod-font">文章数量</span>
			<span id="blogTypeManagerHeader" class="blod-font">管理</span>
			<br/>
			<c:forEach var="blogTypeEntry" items="${blogTypeInfo }">
				<span id="blogTypeName">
					${blogTypeEntry.key.name }
				</span>
				<span id="articleNumber">
					${blogTypeEntry.value }
				</span>
				<span id="blogmanager">
					<a href='${_ctxPath }/blog/EditForward.do?blogTypeId=${blogTypeEntry.key.id }'>编辑</a>&nbsp;
					<a href='${_ctxPath }/blog/blogtype/blogTypeDelete.do?blogTypeId=${blogTypeEntry.key.id }'>删除</a>&nbsp;
				</span>
				<br/>
			</c:forEach>
		</div>
	</div>
	
	<jsp:include page="/Resources/jsp/common/copyright_templet.jsp"></jsp:include>
</body>
</html>