<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/Resources/jsp/common/taglibs.jsp"%>

<link href="${_ctxPath }/Resources/css/common/menu_templet.css"
	type="text/css" rel="stylesheet" />
<div id="headermenu" class="headermenu">
	<span id="logo" class="logo"> <img alt="Jisonami"
		src="${_ctxPath }/Resources/images/logo/Jisonami-Logo.png" />
	</span>
	<div id="menu">
		<div id="sitemenu">
			
		</div>
		<div id="usermenu">
			<c:choose>
				<c:when test="${username != null }">
				欢迎${username },
					<a href='${_ctxPath }/logout.do'>退出</a>
					<br />
				</c:when>
				<c:otherwise>
				您还未登录！
				<a href='${_ctxPath }/login.jsp'>登录</a>
					<a href='${_ctxPath }/register.jsp'>注册</a>
					<br />
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</div>
<br />
