package org.jisonami.jisonami2.controller.account;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jisonami.jisonami2.controller.blog.BlogBeanCopyFactory;
import org.jisonami.jisonami2.entity.Blog;
import org.jisonami.jisonami2.entity.BlogType;
import org.jisonami.jisonami2.entity.User;
import org.jisonami.jisonami2.service.BlogService;
import org.jisonami.jisonami2.service.BlogTypeService;
import org.jisonami.jisonami2.service.UserService;
import org.jisonami.jisonami2.util.CollectionUtils;
import org.jisonami.jisonami2.vo.BlogVO;
import org.jisonami.jisonami2.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@SessionAttributes("username")
@RequestMapping("/account")
public class AccountController {

	@Autowired
	private UserService userService;
	@Autowired
	private BlogService blogService;
	@Autowired
	private BlogBeanCopyFactory blogBeanCopyFactory;
	@Autowired
	private BlogTypeService blogTypeService;

	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String add(UserVO userVO, ModelMap model){
		User user = new User();
		BeanUtils.copyProperties(userVO, user);
		user.setName(userVO.getUsername());
		if(userService.exits(user)){
			// 提示该用户已注册
			model.put("error", "用户名已存在！");
			return "account/register";
		} else {
			// 将用户信息存到数据库
			userService.save(user);
			return "account/login";
		}
	}

	@RequestMapping(value="/login/validate", method=RequestMethod.POST)
	public String validate(UserVO userVO, ModelMap model){
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
				
				// 全部博客数量
				model.put("blogCount", blogVOs.size());
				// 博客类型查询
				Map<BlogType, Integer> blogTypeInfos = queryBlogTypeInfo(user.getName());
				model.put("blogTypeInfos", blogTypeInfos);
				
				return "blog/blog";
			} else {
				// 若不匹配，提示用户名或密码错误
				model.put("error", "用户名或密码错误！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "account/login";
	}
	
	private Map<BlogType, Integer> queryBlogTypeInfo(String author){
		Map<BlogType,Integer> blogTypeInfo = new HashMap<BlogType,Integer>();
		List<BlogType> blogTypes = null;
		blogTypes = blogTypeService.queryByAuthor(author);
		for (BlogType bt : blogTypes) {
			int blogCount = blogService.blogCountByBlogType(bt.getId());
			blogTypeInfo.put(bt, blogCount);
		}
		return blogTypeInfo;
	}
	
	@RequestMapping("/login")
	public String login() {
		return "account/login";
	}
	
	@RequestMapping("/register")
	public String register() {
		return "account/register";
	}

	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(SessionStatus sessionStatus) {
		sessionStatus.setComplete();
		return "account/login";
	}

}
