<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>正在跳转。。。</title>
</head>
<body>
<!-- 未登录，跳转到登陆页面 -->
<%-- <%response.sendRedirect("login.jsp"); %> --%>
<!-- 已登录，跳转到blog首页 -->
<%-- <%request.getRequestDispatcher("/WEB-INF/content/blog/blog.jsp").forward(request, response); %> --%>
<%-- <%response.sendRedirect("login.jsp"); %> --%>
<%request.getRequestDispatcher("/blog/blogIndexForward.do").forward(request, response); %>
</body>
</html>