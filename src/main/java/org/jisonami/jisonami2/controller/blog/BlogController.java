package org.jisonami.jisonami2.controller.blog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jisonami.jisonami2.entity.Blog;
import org.jisonami.jisonami2.entity.BlogType;
import org.jisonami.jisonami2.service.BlogService;
import org.jisonami.jisonami2.service.BlogTypeService;
import org.jisonami.jisonami2.util.BeanCopy;
import org.jisonami.jisonami2.util.CollectionUtils;
import org.jisonami.jisonami2.vo.BlogTypeVO;
import org.jisonami.jisonami2.vo.BlogVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/blog")
@SessionAttributes("username")
public class BlogController {

	@Autowired
	private BlogService blogService;
	@Autowired
	private BlogTypeService blogTypeService;
	@Autowired
	private BlogBeanCopyFactory blogBeanCopyFactory;
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String blogIndex(ModelMap model){
		try {
			List<Blog> blogs = blogService.query();
			List<BlogVO> blogVOs = new ArrayList<BlogVO>();
			CollectionUtils.copyList(blogs, blogVOs, BlogVO.class);
			model.put("blogs", blogVOs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/jisonami2/blog/blogIndex";
	}
	
	@RequestMapping(value="/publish", method=RequestMethod.POST)
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
			
			// 全部博客数量
			model.put("blogCount", blogs.size());
			// 博客类型查询
			Map<BlogType, Integer> blogTypeInfos = queryBlogTypeInfo(username);
			model.put("blogTypeInfos", blogTypeInfos);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 提示发布成功，3秒后跳转到blog页面
		return "/blog/blog";
	}
	
	@RequestMapping(value="/edit/{blogId}", method=RequestMethod.POST)
	public String edit(BlogVO blogVO, @PathVariable("blogId") String blogId, @ModelAttribute("username") String username, ModelMap model){
		Blog blog = blogService.queryById(blogId);
		BeanUtils.copyProperties(blogVO, blog, new String[]{"id", "author","publishTime"});
//		blog.setId(blogId);
//		blog.setAuthor(username);
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
				
				// 全部博客数量
				model.put("blogCount", blogs.size());
				// 博客类型查询
				Map<BlogType, Integer> blogTypeInfos = queryBlogTypeInfo(username);
				model.put("blogTypeInfos", blogTypeInfos);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 返回博客列表
		}else{
			// 编辑不成功，返回错误提示
			System.out.println("编辑不成功");
		}
		return "/blog/blog";
	}
	
	@RequestMapping(value="/delete/{blogId}", method=RequestMethod.GET)
	public String delete(@PathVariable("blogId") String blogId, @ModelAttribute("username") String username, ModelMap model){
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
				
				// 全部博客数量
				model.put("blogCount", blogs.size());
				// 博客类型查询
				Map<BlogType, Integer> blogTypeInfos = queryBlogTypeInfo(username);
				model.put("blogTypeInfos", blogTypeInfos);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			// 编辑不成功，返回错误提示
			
		}
		return "/blog/blog";
	}
	
	@RequestMapping(value="/all", method=RequestMethod.GET)
	public String blogAllByAuthor(@ModelAttribute("username") String username, ModelMap model){
		// 查询该用户下的所有博客
		List<Blog> blogs = null;
		try {
			blogs = blogService.queryByAuthor(username);
			
			// 全部博客数量
			model.put("blogCount", blogs.size());
			// 博客类型查询
			Map<BlogType, Integer> blogTypeInfos = queryBlogTypeInfo(username);
			model.put("blogTypeInfos", blogTypeInfos);
			
			List<BlogVO> blogVOs = new ArrayList<BlogVO>();
			CollectionUtils.copyList(blogs, blogVOs, BlogVO.class, blogBeanCopyFactory.newBlogBeanCopy());
			model.put("blogs", blogVOs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/blog/blog";
	}
	
	@RequestMapping(value="/{blogTypeId}", method=RequestMethod.GET)
	public String blogForward(@PathVariable("blogTypeId") String blogTypeId, @ModelAttribute("username") String username, ModelMap model){
		// 查询该用户下的所有博客
		List<Blog> blogs = null;
		try {
			if(blogTypeId!=null && !"".equals(blogTypeId)){
				blogs = blogService.queryByBlogType(blogTypeId);
			} else {
				blogs = blogService.queryByAuthor(username);
			}
			
			// 全部博客数量
			model.put("blogCount", blogService.queryByAuthor(username).size());
			// 博客类型查询
			Map<BlogType, Integer> blogTypeInfos = queryBlogTypeInfo(username);
			model.put("blogTypeInfos", blogTypeInfos);
			
			List<BlogVO> blogVOs = new ArrayList<BlogVO>();
			CollectionUtils.copyList(blogs, blogVOs, BlogVO.class, blogBeanCopyFactory.newBlogBeanCopy());
			model.put("blogs", blogVOs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/blog/blog";
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
	
	@RequestMapping(value="/view/{blogId}", method=RequestMethod.GET)
	public String viewForward(@PathVariable("blogId") String blogId, @ModelAttribute("username") String username, ModelMap model){
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
		
		// 全部博客数量
		model.put("blogCount", blogService.queryByAuthor(username).size());
		// 博客类型查询
		Map<BlogType, Integer> blogTypeInfos = queryBlogTypeInfo(username);
		model.put("blogTypeInfos", blogTypeInfos);

		return "/blog/view";
	}
	
	@RequestMapping(value="/editForward/{blogId}")
	public String editForward(@PathVariable("blogId") String blogId, @ModelAttribute("username") String username, ModelMap model){
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
		List<BlogTypeVO> blogTypeVOList = new ArrayList<BlogTypeVO>();
		try {
			CollectionUtils.copyList(blogTypeList, blogTypeVOList, BlogTypeVO.class, new BeanCopy<BlogType, BlogTypeVO>() {
				@Override
				public void copyProperties(BlogType f, BlogTypeVO t) {
					BeanUtils.copyProperties(f, t);
					if(blogTypeIds.contains(t.getId())){
						t.setChecked(true);
					}
				}
			});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.put("blogTypeList", blogTypeVOList);
		
		// 全部博客数量
		model.put("blogCount", blogService.queryByAuthor(username).size());
		// 博客类型查询
		Map<BlogType, Integer> blogTypeInfos = queryBlogTypeInfo(username);
		model.put("blogTypeInfos", blogTypeInfos);
		
		return "/blog/edit";
	}
	
	@RequestMapping(value="/publishForward")
	public String publishForward(@ModelAttribute("username") String username, ModelMap model){
		List<BlogType> blogTypeList = null;
		blogTypeList = blogTypeService.queryByAuthor(username);
		model.put("blogTypeList", blogTypeList);
		
		// 全部博客数量
		model.put("blogCount", blogService.queryByAuthor(username).size());
		// 博客类型查询
		Map<BlogType, Integer> blogTypeInfos = queryBlogTypeInfo(username);
		model.put("blogTypeInfos", blogTypeInfos);
		
		return "/blog/publish";
	}
	
}
