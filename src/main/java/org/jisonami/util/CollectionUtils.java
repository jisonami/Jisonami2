package org.jisonami.util;

import java.util.List;

import org.springframework.beans.BeanUtils;

public class CollectionUtils {

	public static <F,T> void copyList(List<F> sourceList, List<T> targetList, Class<T> targetClass) throws Exception{
		copyList(sourceList, targetList, targetClass, null);
	}
	
	public static <F,T> void copyList(List<F> sourceList, List<T> targetList, Class<T> targetClass, BeanCopy<F, T> beanCopy) throws Exception{
		for (F f : sourceList) {
			T t = targetClass.newInstance();
			if(beanCopy == null){
				BeanUtils.copyProperties(f, t);
			} else {
				beanCopy.copyProperties(f, t);
			}
			targetList.add(t);
		}
	}
}
