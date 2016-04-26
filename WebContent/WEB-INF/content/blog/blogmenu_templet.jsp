<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="/Resources/jsp/common/taglibs.jsp" %>

<link href="${_ctxPath }/Resources/css/blog/blogmenu_templet.css" type="text/css" rel="stylesheet" />

<div id="blogmenu">
	<span class="blod-font">博客管理</span><br/>
	<a href="${_ctxPath }/blog/publishForward.do">发表文章</a><br/>
	<a href="${_ctxPath }/blog/blogtype/blogTypeManagerForward.do">分类管理</a><br/><br/>
	<span class="blod-font">文章管理</span><br/>
	
 	<!-- 读取数据库配置信息 -->
	<fmt:bundle basename="/org/jisonami/sql/DBConfig">
		<fmt:message key="driver" var="driver"></fmt:message>
		<fmt:message key="url" var="url"></fmt:message>
		<fmt:message key="user" var="user"></fmt:message>
		<fmt:message key="pass" var="pass"></fmt:message>
	</fmt:bundle>
	<!-- 设置数据源 -->
	<sql:setDataSource driver="${driver }" url="${url }" user="${user }" password="${pass }"/>
	<!-- 查询该作者所有博客类型 -->
	<sql:query var="blogTypes" >
		select * from t_blogtype t where t.blogAuthor = ?
		<sql:param value="${username }" />
	</sql:query>
	<!-- 查询该作者的所有博客数量 -->
	<sql:query var="allBlog">
		select * from t_blog t where t.author = ?
		<sql:param value="${username }" />
	</sql:query>
	<!-- 列出博客类型对应的博客数量 -->
	<a href='${_ctxPath }/blog/blogForward.do'>全部博客(${allBlog.rowCount })</a><br/>
	<c:forEach var="blogType" items="${blogTypes.rows }">
		<sql:query var="blogsByBlogType">
			select * from t_blog t where t.blogtype like ?
			<sql:param value="%${blogType.id }%" />
		</sql:query>
		<span id="blogTypeName">
			<a href='${_ctxPath }/blog/blogForward.do?blogTypeId=${blogType.id }'>
				${blogType.name }(${blogsByBlogType.rowCount })
			</a>
		</span><br/>
	</c:forEach>
	
	<a href="blog/publishForward.do">草稿箱</a><br/>
	<a href="blog/publishForward.do">回收站</a><br/><br/>
</div>
