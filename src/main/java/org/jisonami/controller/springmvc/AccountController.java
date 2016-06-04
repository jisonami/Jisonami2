package org.jisonami.controller.springmvc;

import org.jisonami.entity.User;
import org.jisonami.service.impl.UserService;
import org.jisonami.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping("/springmvc")
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
				return "Resources/jsp/account/springmvc/success";
			} else {
				model.put("error", "用户名或密码错误！");
				return "Resources/jsp/account/springmvc/login";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Resources/jsp/account/springmvc/login";
	}

	@RequestMapping("/logout.do")
	public String logout(ModelMap model, SessionStatus sessionStatus) {
		sessionStatus.setComplete();
		model.clear();
		return "index";
	}

	@RequestMapping("register.do")
	public String register(UserVO userVO, ModelMap model) {
		User user = new User();
		BeanUtils.copyProperties(userVO, user);
		user.setName(userVO.getUsername());
		try {
			if(userService.exits(user)){
				// 提示该用户已注册
				model.put("error", "用户名已存在！");
				return "Resources/jsp/account/springmvc/register";
			} else {
				// 将用户信息存到数据库
				userService.save(user);
				return "Resources/jsp/account/springmvc/login";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Resources/jsp/account/springmvc/register";
	}
}
