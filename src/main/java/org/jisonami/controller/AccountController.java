package org.jisonami.controller;

import org.jisonami.entity.User;
import org.jisonami.service.UserService;
import org.jisonami.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@SessionAttributes("username")
public class AccountController {

	@Autowired
	private UserService userService;

	@RequestMapping("/login.do")
	public String login(UserVO userVO, ModelMap model) {
		User user = new User();
		BeanUtils.copyProperties(userVO, user);
		user.setName(userVO.getUsername());
		try {
			if (userService.validate(user)) {
				// 若匹配，跳转到登陆成功页面
				model.put("username", user.getName());
				return "index";
			} else {
				model.put("error", "用户名或密码错误！");
				return "login";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "login";
	}

	@RequestMapping("/logout.do")
	public String logout(SessionStatus sessionStatus) {
		sessionStatus.setComplete();
		return "login";
	}

	@RequestMapping("register.do")
	public String register(UserVO userVO, ModelMap model) {
		User user = new User();
		BeanUtils.copyProperties(userVO, user);
		user.setName(userVO.getUsername());
		if(userService.exits(user)){
			// 提示该用户已注册
			model.put("error", "用户名已存在！");
			return "register";
		} else {
			// 将用户信息存到数据库
			userService.save(user);
			return "login";
		}
	}
}
