package org.jisonami.controller.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jisonami.entity.User;
import org.jisonami.service.impl.UserService;

@WebServlet(urlPatterns="/servlet/register.do")
public class RegisterServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 获取用户注册信息
		User user = new User();
		user.setName(req.getParameter("username"));
		user.setPassword(req.getParameter("password"));
		// 验证数据库里是否已注册
		UserService userService = new UserService();
		try {
			if(userService.exits(user)){
				// 提示该用户已注册
				req.setAttribute("error", "用户名已存在！");
				req.getRequestDispatcher("/Resources/jsp/account/servlet/register.jsp").forward(req, resp);
			} else {
				// 将用户信息存到数据库
				try {
					userService.save(user);
				} catch (Exception e) {
					e.printStackTrace();
					// 提示注册失败
				}
				// 注册成功，跳转到登陆页面
				resp.sendRedirect("../Resources/jsp/account/servlet/login.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
