package org.jisonami.controller.blog;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jisonami.entity.BlogType;
import org.jisonami.service.BlogService;
import org.jisonami.service.BlogTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/blog/blogtype/")
@SessionAttributes("username")
public class BlogTypeController {

	@Autowired
	BlogService blogService;
	@Autowired
	BlogTypeService blogTypeService;
	
	@RequestMapping("blogTypeManager.do")
	public String blogTypeManager(@ModelAttribute("username") String username, @RequestParam("blogType") String blogTypeName, ModelMap model){
		BlogType blogType = new BlogType();
		blogType.setBlogAuthor(username);
		blogType.setName(blogTypeName);
		boolean result = false;
		try {
			result = blogTypeService.save(blogType);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(result){
			Map<BlogType,Integer> blogTypeInfo = queryBlogTypeInfo(username);
			model.put("blogTypeInfo", blogTypeInfo);
		}else {
			// 保存博客类型出错
		}
		return "/blog/blogTypeManager";
	}
	
	@RequestMapping(value="blogTypeDelete.do")
	public String blogTypeDelete(String blogTypeId, @ModelAttribute("username") String username, ModelMap model){
		boolean result = false;
		try {
			result = blogTypeService.delete(blogTypeId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(result){
			Map<BlogType,Integer> blogTypeInfo = queryBlogTypeInfo(username);
			model.put("blogTypeInfo", blogTypeInfo);
		} else {
			
		}
		return "/blog/blogTypeManager";
	}
	
	@RequestMapping("blogTypeManagerForward.do")
	public String blogTypeManagerForward(@ModelAttribute("username") String username, ModelMap model){
		Map<BlogType,Integer> blogTypeInfo = queryBlogTypeInfo(username);
		model.put("blogTypeInfo", blogTypeInfo);
		return "/blog/blogTypeManager";
	}

	private Map<BlogType, Integer> queryBlogTypeInfo(String author){
		Map<BlogType,Integer> blogTypeInfo = new HashMap<BlogType,Integer>();
		List<BlogType> blogTypes = null;
		try {
			blogTypes = blogTypeService.queryByAuthor(author);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (BlogType bt : blogTypes) {
			try {
				int blogCount = blogService.blogCountByBlogType(bt.getId());
				blogTypeInfo.put(bt, blogCount);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return blogTypeInfo;
	}
	
}
