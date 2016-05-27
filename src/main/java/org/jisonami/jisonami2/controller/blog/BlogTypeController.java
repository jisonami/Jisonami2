package org.jisonami.jisonami2.controller.blog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jisonami.jisonami2.entity.BlogType;
import org.jisonami.jisonami2.service.BlogService;
import org.jisonami.jisonami2.service.BlogTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/blog/blogtype")
@SessionAttributes("username")
public class BlogTypeController {

	@Autowired
	private BlogService blogService;
	@Autowired
	private BlogTypeService blogTypeService;
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String blogTypeManager(@ModelAttribute("username") String username, @RequestParam("blogType") String blogTypeName, ModelMap model){
		BlogType blogType = new BlogType();
		blogType.setBlogAuthor(username);
		blogType.setName(blogTypeName);
		boolean result = false;
		result = blogTypeService.save(blogType);
		if(result){
			// 全部博客数量
			model.put("blogCount", blogService.queryByAuthor(username).size());
			// 博客类型查询
			Map<BlogType, Integer> blogTypeInfos = queryBlogTypeInfo(username);
			model.put("blogTypeInfos", blogTypeInfos);
		}else {
			// 保存博客类型出错
		}
		return "/blog/blogTypeManager";
	}
	
	@RequestMapping(value="/blogTypeDelete/{blogTypeId}", method=RequestMethod.GET)
	public String blogTypeDelete(@PathVariable("blogTypeId") String blogTypeId, @ModelAttribute("username") String username, ModelMap model){
		boolean result = false;
		result = blogTypeService.delete(blogTypeId);
		if(result){
			// 全部博客数量
			model.put("blogCount", blogService.queryByAuthor(username).size());
			// 博客类型查询
			Map<BlogType, Integer> blogTypeInfos = queryBlogTypeInfo(username);
			model.put("blogTypeInfos", blogTypeInfos);
		} else {
			
		}
		return "/blog/blogTypeManager";
	}
	
	@RequestMapping(value="blogTypeManagerForward", method=RequestMethod.GET)
	public String blogTypeManagerForward(@ModelAttribute("username") String username, ModelMap model){
		// 全部博客数量
		model.put("blogCount", blogService.queryByAuthor(username).size());
		// 博客类型查询
		Map<BlogType, Integer> blogTypeInfos = queryBlogTypeInfo(username);
		model.put("blogTypeInfos", blogTypeInfos);
		return "/blog/blogTypeManager";
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
	
}
