package org.jisonami.controller.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jisonami.entity.User;
import org.jisonami.service.impl.UserService;

@WebServlet(urlPatterns="/servlet/login.do")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 获取用户登陆信息
		User user = new User();
		user.setName(req.getParameter("username"));
		user.setPassword(req.getParameter("password"));
		// 验证用户信息是否与数据库匹配
		UserService userService = new UserService();
		try {
			if(userService.validate(user)){
				// 若匹配，跳转到主页
				req.getSession().setAttribute("username", user.getName());
				req.getRequestDispatcher("/Resources/jsp/account/servlet/success.jsp").forward(req, resp);
			} else {
				// 若不匹配，提示用户名或密码错误
				req.setAttribute("error", "用户名或密码错误！");
				req.getRequestDispatcher("/Resources/jsp/account/servlet/login.jsp").forward(req, resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
