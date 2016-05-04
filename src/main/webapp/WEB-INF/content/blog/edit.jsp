<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/Resources/jsp/common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>修改文章</title>
	<link href="${_ctxPath }/Resources/css/blog/edit.css" type="text/css" rel="stylesheet" />
	<script type="text/javascript" src="${_ctxPath }/Resources/js/blog/edit.js"></script>
</head>
<body>
	<jsp:include page="blogheader_templet.jsp"></jsp:include>
	
	<div class="content">
		<jsp:include page="blogmenu_templet.jsp"></jsp:include>
		
		<div id="blogcontent">
			<a href="${_ctxPath }/blog/blogForward.do">返回博客列表</a><br/><br/>
			<form action="edit.do" method="post">
				<input name="blogId" type="hidden" value="${blog.id }"/>
				标题：<input id="blogTitle" name="title" type="text" value="${blog.title }" /><br/><br/>
				博客分类：<input id="blogTypes" name="blogTypes" type="text" value="${blogTypes }" /><br/>
				<input id="blogTypeIds" name="blogTypeIds" type="hidden" value="${blogTypeIds }" />
				<c:forEach var="blogType" items="${blogTypeList }">
					<input name='blogType' blogTypeId='${blogType.id }' blogTypeName='${blogType.name }' type='checkbox' 
						<c:if test='${fn:contains(blogTypes, blogType.name) }'>
							checked='checked'
						</c:if>
					/>${blogType.name }&nbsp;
				</c:forEach>
				<br/><br/>
				正文：<textarea name="content" rows="20" cols="90">
					${blog.content }
				</textarea><br/>
				<input type="submit" value="发布"/>
			</form>
		</div>
	</div>
	
<jsp:include page="/Resources/jsp/common/copyright_templet.jsp"></jsp:include>
</body>
</html>