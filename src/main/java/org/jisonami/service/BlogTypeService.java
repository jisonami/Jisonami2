package org.jisonami.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.jisonami.entity.BlogType;
import org.jisonami.sql.DBUtils;
import org.jisonami.util.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class BlogTypeService {
	public boolean save(BlogType blogType) throws SQLException{
		Connection conn = DBUtils.getConnection();
		blogType.setId(StringUtils.generateUUID());
		String sql = "insert into t_blogtype(id, blogAuthor, name) values(?, ?, ?)";
		PreparedStatement preStmt = conn.prepareStatement(sql);
		preStmt.setString(1, blogType.getId());
		preStmt.setString(2, blogType.getBlogAuthor());
		preStmt.setString(3, blogType.getName());
		int rowChange = preStmt.executeUpdate();
		preStmt.close();
		conn.close();
		if(rowChange!=0){
			return true;
		}
		return false;
	}
	public boolean delete(String blogTypeId) throws SQLException{
		Connection conn = DBUtils.getConnection();
		String sql = "delete from t_blogtype t where t.id = ?";
		PreparedStatement preStmt = conn.prepareStatement(sql);
		preStmt.setString(1, blogTypeId);
		int rowChange = preStmt.executeUpdate();
		preStmt.close();
		conn.close();
		if(rowChange!=0){
			return true;
		}
		return false;
	}
	public boolean edit(BlogType blogType) throws SQLException{
		Connection conn = DBUtils.getConnection();
		String sql = "update t_blogtype t set t.name = ? where t.id = ?";
		PreparedStatement preStmt = conn.prepareStatement(sql);
		preStmt.setString(1, blogType.getName());
		preStmt.setString(2, blogType.getId());
		int rowChange = preStmt.executeUpdate();
		preStmt.close();
		conn.close();
		if(rowChange!=0){
			return true;
		}
		return false;
	}
	public List<BlogType> queryByAuthor(String blogAuthor) throws SQLException{
		Connection conn = DBUtils.getConnection();
		String sql = "select * from t_blogtype t where t.blogAuthor = ?";
		PreparedStatement preStmt = conn.prepareStatement(sql);
		preStmt.setString(1, blogAuthor);
		ResultSet rs = preStmt.executeQuery();
		List<BlogType> blogTypes = new ArrayList<BlogType>();
		while(rs.next()){
			BlogType blogType = new BlogType();
			blogType.setId(rs.getString("id"));
			blogType.setBlogAuthor(blogAuthor);
			blogType.setName(rs.getString("name"));
			blogTypes.add(blogType);
		}
		rs.close();
		preStmt.close();
		conn.close();
		return blogTypes;
	}
	public BlogType queryById(String blogTypeId) throws SQLException{
		Connection conn = DBUtils.getConnection();
		String sql = "select * from t_blogtype t where t.id = ?";
		PreparedStatement preStmt = conn.prepareStatement(sql);
		preStmt.setString(1, blogTypeId);
		ResultSet rs = preStmt.executeQuery();
		BlogType blogType = null;
		if(rs.next()){
			blogType = new BlogType();
			blogType.setId(rs.getString("id"));
			blogType.setBlogAuthor(rs.getString("blogauthor"));
			blogType.setName(rs.getString("name"));
		}
		rs.close();
		preStmt.close();
		conn.close();
		return blogType;
	}
}
