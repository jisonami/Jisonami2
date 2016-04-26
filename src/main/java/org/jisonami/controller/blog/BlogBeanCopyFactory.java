package org.jisonami.controller.blog;

import java.sql.SQLException;

import org.jisonami.entity.Blog;
import org.jisonami.entity.BlogType;
import org.jisonami.service.BlogTypeService;
import org.jisonami.util.BeanCopy;
import org.jisonami.vo.BlogVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BlogBeanCopyFactory{

	@Autowired
	BlogTypeService blogTypeService;
	
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
			try {
				BlogType blogType = blogTypeService.queryById(blogTypeId);
				if("".equals(blogTypeNames)){
					blogTypeNames = blogTypeNames + blogType.getName();
				} else {
					blogTypeNames = blogTypeNames + "," + blogType.getName();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		blogVO.setBlogTypeNames(blogTypeNames);
	}
}
