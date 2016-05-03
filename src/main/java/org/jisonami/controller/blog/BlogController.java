package org.jisonami.controller.blog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.jisonami.entity.Blog;
import org.jisonami.entity.BlogType;
import org.jisonami.service.BlogService;
import org.jisonami.service.BlogTypeService;
import org.jisonami.util.CollectionUtils;
import org.jisonami.vo.BlogVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/blog/")
@SessionAttributes("username")
public class BlogController {

	@Autowired
	private BlogService blogService;
	@Autowired
	private BlogTypeService blogTypeService;
	@Autowired
	private BlogBeanCopyFactory blogBeanCopyFactory;
	
	@RequestMapping("blogIndexForward.do")
	public String blogIndex(ModelMap model){
		try {
			List<Blog> blogs = blogService.query();
			List<BlogVO> blogVOs = new ArrayList<BlogVO>();
			CollectionUtils.copyList(blogs, blogVOs, BlogVO.class);
			model.put("blogs", blogVOs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/blog/blogIndex";
	}
	
	@RequestMapping("publish.do")
	public String publish(BlogVO blogVO, @ModelAttribute("username") String username, ModelMap model){
		try {
			Blog blog = new Blog();
			BeanUtils.copyProperties(blogVO, blog);
			blog.setBlogType(blogVO.getBlogTypeIds());
			blog.setAuthor(username);
			blog.setPublishTime(new Date());
			blogService.save(blog);
			
			// 查询该用户下的所有博客
			List<Blog> blogs = blogService.queryByAuthor(username);
			List<BlogVO> blogVOs = new ArrayList<BlogVO>();
			CollectionUtils.copyList(blogs, blogVOs, BlogVO.class, blogBeanCopyFactory.newBlogBeanCopy());
			model.put("blogs", blogVOs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 提示发布成功，3秒后跳转到blog页面
		return "/blog/blog";
	}
	
	@RequestMapping("edit.do")
	public String edit(BlogVO blogVO, String blogId, @ModelAttribute("username") String username, ModelMap model){
		Blog blog = new Blog();
		BeanUtils.copyProperties(blogVO, blog);
		blog.setId(blogId);
		blog.setAuthor(username);
		blog.setBlogType(blogVO.getBlogTypeIds());
		blog.setEditTime(new Date());
		boolean result = false;
		result = blogService.edit(blog);
		if(result){
			// 查询该用户下的所有博客
			List<Blog> blogs;
			try {
				blogs = blogService.queryByAuthor(username);
				List<BlogVO> blogVOs = new ArrayList<BlogVO>();
				CollectionUtils.copyList(blogs, blogVOs, BlogVO.class, blogBeanCopyFactory.newBlogBeanCopy());
				model.put("blogs", blogVOs);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 返回博客列表
		}else{
			// 编辑不成功，返回错误提示
			
		}
		return "/blog/blog";
	}
	
	@RequestMapping("delete.do")
	public String delete(String blogId, @ModelAttribute("username") String username, ModelMap model){
		boolean result = false;
		result = blogService.delete(blogId);
		if(result){
			// 查询该用户下的所有博客
			List<Blog> blogs;
			try {
				blogs = blogService.queryByAuthor(username);
				List<BlogVO> blogVOs = new ArrayList<BlogVO>();
				CollectionUtils.copyList(blogs, blogVOs, BlogVO.class, blogBeanCopyFactory.newBlogBeanCopy());
				model.put("blogs", blogVOs);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			// 编辑不成功，返回错误提示
			
		}
		return "/blog/blog";
	}
	
	@RequestMapping("blogForward.do")
	public String blogForward(String blogTypeId, @ModelAttribute("username") String username, ModelMap model){
		// 查询该用户下的所有博客
		List<Blog> blogs = null;
		try {
			if(blogTypeId!=null && !"".equals(blogTypeId)){
				blogs = blogService.queryByBlogType(blogTypeId);
			} else {
				blogs = blogService.queryByAuthor(username);
			}
			List<BlogVO> blogVOs = new ArrayList<BlogVO>();
			CollectionUtils.copyList(blogs, blogVOs, BlogVO.class, blogBeanCopyFactory.newBlogBeanCopy());
			model.put("blogs", blogVOs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/blog/blog";
	}
	
	@RequestMapping("ViewForward.do")
	public String viewForward(String blogId, ModelMap model){
		Blog blog = null;
		blog = blogService.queryById(blogId);
		if(blog!=null){
			model.put("blog", blog);
		}
		
		String blogTypeIds = blog.getBlogType();
		String blogTypes = "";
		List<String> blogTypeIdList = Arrays.asList(blogTypeIds.split(","));
		for(int i=0;i<blogTypeIdList.size();i++){
			String blogTypeId = blogTypeIdList.get(i);
			BlogType blogType = null;
			blogType = blogTypeService.queryById(blogTypeId);
			if(i < blogTypeIdList.size()-1){
				blogTypes = blogTypes + blogType.getName() + ",";
			} else {
				blogTypes = blogTypes + blogType.getName();
			}
		}
		if(blogTypeIds!=null && !"".equals(blogTypeIds)){
			model.put("blogTypeIds", blogTypeIds);
		}
		if(blogTypes!=null && !"".equals(blogTypes)){
			model.put("blogTypes", blogTypes);
		}
		return "/blog/view";
	}
	
	@RequestMapping("EditForward.do")
	public String editForward(String blogId, @ModelAttribute("username") String username, ModelMap model){
		Blog blog = null;
		blog = blogService.queryById(blogId);
		if(blog!=null){
			model.put("blog", blog);
		}
		
		String blogTypeIds = blog.getBlogType();
		String blogTypes = "";
		List<String> blogTypeIdList = Arrays.asList(blogTypeIds.split(","));
		for(int i=0;i<blogTypeIdList.size();i++){
			String blogTypeId = blogTypeIdList.get(i);
			BlogType blogType = null;
			blogType = blogTypeService.queryById(blogTypeId);
			if(i < blogTypeIdList.size()-1){
				blogTypes = blogTypes + blogType.getName() + ",";
			} else {
				blogTypes = blogTypes + blogType.getName();
			}
		}
		if(blogTypeIds!=null && !"".equals(blogTypeIds)){
			model.put("blogTypeIds", blogTypeIds);
		}
		if(blogTypes!=null && !"".equals(blogTypes)){
			model.put("blogTypes", blogTypes);
		}
		
		List<BlogType> blogTypeList = null;
		blogTypeList = blogTypeService.queryByAuthor(username);
		model.put("blogTypeList", blogTypeList);
		
		return "/blog/edit";
	}
	
	@RequestMapping("publishForward.do")
	public String publishForward(@ModelAttribute("username") String username, ModelMap model){
		List<BlogType> blogTypeList = null;
		blogTypeList = blogTypeService.queryByAuthor(username);
		model.put("blogTypeList", blogTypeList);
		return "/blog/publish";
	}
	
}
