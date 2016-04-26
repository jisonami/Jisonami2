package org.jisonami.util;

/**
 * 定制CollectionUtils的copyList()时需要传入该接口的实现
 * @author jison
 *
 * @param <F>
 * @param <T>
 */
@FunctionalInterface
public interface BeanCopy<F, T> {

	void copyProperties(F f, T t);
}
