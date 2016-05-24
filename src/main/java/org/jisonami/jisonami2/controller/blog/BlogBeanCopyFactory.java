package org.jisonami.jisonami2.controller.blog;

import org.jisonami.jisonami2.entity.Blog;
import org.jisonami.jisonami2.entity.BlogType;
import org.jisonami.jisonami2.service.BlogTypeService;
import org.jisonami.jisonami2.util.BeanCopy;
import org.jisonami.jisonami2.vo.BlogVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BlogBeanCopyFactory{

	@Autowired
	private BlogTypeService blogTypeService;
	
	public BeanCopy<Blog, BlogVO> newBlogBeanCopy(){
		return (f, t) -> {
				BeanUtils.copyProperties(f, t);
				t.setBlogTypeIds(f.getBlogType());
				convertBlogTypeIdsToNames(t);
		};
	}
	
	private void convertBlogTypeIdsToNames(BlogVO blogVO){
		String blogTypeIds = blogVO.getBlogTypeIds();
		if(blogTypeIds==null || "".equals(blogTypeIds)){
			return ;
		}
		String[] blogTypeIdArray = blogTypeIds.split(",");
		String blogTypeNames = "";
		for (String blogTypeId : blogTypeIdArray) {
			BlogType blogType = blogTypeService.queryById(blogTypeId);
			if("".equals(blogTypeNames)){
				blogTypeNames = blogTypeNames + blogType.getName();
			} else {
				blogTypeNames = blogTypeNames + "," + blogType.getName();
			}
		}
		blogVO.setBlogTypeNames(blogTypeNames);
	}
}
