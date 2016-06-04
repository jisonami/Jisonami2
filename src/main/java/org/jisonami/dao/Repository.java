package org.jisonami.dao;

import java.sql.SQLException;
import java.util.List;

public interface Repository<T> {

	public boolean insert(T t) throws SQLException;

	public boolean delete(String id) throws SQLException;

	public boolean update(T t) throws SQLException;

	public T select(String id) throws SQLException;

	public List<T> select() throws SQLException;

}
