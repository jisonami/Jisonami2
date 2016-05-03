package org.jisonami.controller.account;

import java.util.ArrayList;
import java.util.List;

import org.jisonami.controller.blog.BlogBeanCopyFactory;
import org.jisonami.entity.Blog;
import org.jisonami.entity.User;
import org.jisonami.service.BlogService;
import org.jisonami.service.UserService;
import org.jisonami.util.CollectionUtils;
import org.jisonami.vo.BlogVO;
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
	@Autowired
	private BlogService blogService;
	@Autowired
	private BlogBeanCopyFactory blogBeanCopyFactory;

	@RequestMapping("/login.do")
	public String login(UserVO userVO, ModelMap model) {
		User user = new User();
		BeanUtils.copyProperties(userVO, user);
		user.setName(userVO.getUsername());
		try {
			if (userService.validate(user)) {
				// 若匹配，跳转到blog主页
				// 查询该用户下的所有博客
				List<Blog> blogs = blogService.queryByAuthor(user.getName());
				List<BlogVO> blogVOs = new ArrayList<BlogVO>();
				CollectionUtils.copyList(blogs, blogVOs, BlogVO.class, blogBeanCopyFactory.newBlogBeanCopy());
				model.put("username", user.getName());
				model.put("blogs", blogVOs);
				return "blog/blog";
			} else {
				// 若不匹配，提示用户名或密码错误
				model.put("error", "用户名或密码错误！");
				return "../../login";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "../../login";
	}

	@RequestMapping("/logout.do")
	public String logout(SessionStatus sessionStatus) {
		sessionStatus.setComplete();
		return "../../login";
	}

	@RequestMapping("register.do")
	public String register(UserVO userVO, ModelMap model) {
		User user = new User();
		BeanUtils.copyProperties(userVO, user);
		user.setName(userVO.getUsername());
		if(userService.exits(user)){
			// 提示该用户已注册
			model.put("error", "用户名已存在！");
			return "../../register";
		} else {
			// 将用户信息存到数据库
			userService.save(user);
			return "../../login";
		}
	}
}
