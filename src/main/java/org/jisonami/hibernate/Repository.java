package org.jisonami.hibernate;

import java.util.List;
import java.util.Map;

public interface Repository {

	public String add(Object o);

	public boolean edit(Object o);

	public boolean delete(Object o);
	
	public <T> boolean delete(Class<T> clazz, String id);

	public <T> T queryOne(Class<T> clazz, String id);

	public <T> List<T> query(String hql);

	public <T> List<T> query(String hql, Map<String, Object> queryParam);

}
