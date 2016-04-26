package org.jisonami.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.jisonami.entity.Blog;
import org.jisonami.sql.DBUtils;
import org.jisonami.util.JDBCUtils;
import org.jisonami.util.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class BlogService {
	public boolean save(Blog blog) throws SQLException{
		blog.setId(StringUtils.generateUUID());
		Connection conn = DBUtils.getConnection();
		String sql = "insert into t_blog(id, title, content, author, blogType, publishTime) values(?, ?, ?, ?, ?, ?)";
		PreparedStatement preStmt = conn.prepareStatement(sql);
		preStmt.setString(1, blog.getId());
		preStmt.setString(2, blog.getTitle());
		preStmt.setString(3, blog.getContent());
		preStmt.setString(4, blog.getAuthor());
		preStmt.setString(5, blog.getBlogType());
		preStmt.setTimestamp(6, new Timestamp(blog.getPublishTime().getTime()));
		int rowChange = preStmt.executeUpdate();
		preStmt.close();
		conn.close();
		if(rowChange != 0){
			return true;
		}
		return false;
	}
	public boolean delete(String id) throws SQLException{
		Connection conn = DBUtils.getConnection();
		String sql = "delete from t_blog t where t.id = ?";
		PreparedStatement preStmt = conn.prepareStatement(sql);
		preStmt.setString(1, id);
		int rowChanges = preStmt.executeUpdate();
		preStmt.close();
		conn.close();
		if(rowChanges != 0){
			return true;
		} else {
			return false;
		}
	}
	public boolean edit(Blog blog) throws SQLException{
		Connection conn = DBUtils.getConnection();
		String sql = "update t_blog t set t.title = ? , t.content = ? , t.blogtype = ? , t.edittime = ? where t.id = ?";
		PreparedStatement preStmt = conn.prepareStatement(sql);
		preStmt.setString(1, blog.getTitle());
		preStmt.setString(2, blog.getContent());
		preStmt.setString(3, blog.getBlogType());
		preStmt.setTimestamp(4, new Timestamp(blog.getEditTime().getTime()));
		preStmt.setString(5, blog.getId());
		int rowChanges = preStmt.executeUpdate();
		preStmt.close();
		conn.close();
		if(rowChanges != 0){
			return true;
		} else {
			return false;
		}
	}
	public List<Blog> query() throws SQLException, IOException{
		Connection conn = DBUtils.getConnection();
		String sql = "select * from t_blog";
		PreparedStatement preStmt = conn.prepareStatement(sql);
		ResultSet rs = preStmt.executeQuery();
		
		List<Blog> blogs = new ArrayList<Blog>();
		while(rs.next()){
			Blog blog = new Blog();
			blog.setId(rs.getString("id"));
			blog.setTitle(rs.getString("title"));
			blog.setContent(JDBCUtils.clobToString(rs.getClob("content")));
			blog.setAuthor(rs.getString("author"));
			blog.setBlogType(rs.getString("blogtype"));
			blog.setPublishTime(rs.getTimestamp("publishTime"));
			blogs.add(blog);
		}
		
		rs.close();
		preStmt.close();
		conn.close();
		return blogs;
	}
	public Blog queryById(String id) throws SQLException, IOException{
		Connection conn = DBUtils.getConnection();
		String sql = "select * from t_blog t where t.id = ?";
		PreparedStatement preStmt = conn.prepareStatement(sql);
		preStmt.setString(1, id);
		ResultSet rs = preStmt.executeQuery();
		
		// 根据id查应该是只有一行数据的
		Blog blog = new Blog();
		if(rs.next()){
			blog.setId(rs.getString("id"));
			blog.setTitle(rs.getString("title"));
			blog.setBlogType(rs.getString("blogtype"));
			blog.setAuthor(rs.getString("author"));
			blog.setContent(JDBCUtils.clobToString(rs.getClob("content")));
			blog.setPublishTime(rs.getTimestamp("publishTime"));
		}
		
		rs.close();
		preStmt.close();
		conn.close();
		return blog;
	}
	public List<Blog> queryByAuthor(String author) throws SQLException, IOException{
		Connection conn = DBUtils.getConnection();
		String sql = "select * from t_blog t where t.author = ?";
		PreparedStatement preStmt = conn.prepareStatement(sql);
		preStmt.setString(1, author);
		ResultSet rs = preStmt.executeQuery();
		
		List<Blog> blogs = new ArrayList<Blog>();
		while(rs.next()){
			Blog blog = new Blog();
			blog.setId(rs.getString("id"));
			blog.setTitle(rs.getString("title"));
			blog.setContent(JDBCUtils.clobToString(rs.getClob("content")));
			blog.setAuthor(rs.getString("author"));
			blog.setBlogType(rs.getString("blogtype"));
			blog.setPublishTime(rs.getTimestamp("publishTime"));
			blogs.add(blog);
		}
		
		rs.close();
		preStmt.close();
		conn.close();
		return blogs;
	}
	public List<Blog> queryByBlogType(String blogTypeId) throws SQLException, IOException{
		Connection conn = DBUtils.getConnection();
		String sql = "select * from t_blog t where t.blogtype like ?";
		PreparedStatement preStmt = conn.prepareStatement(sql);
		preStmt.setString(1, "%"+blogTypeId+"%");
		ResultSet rs = preStmt.executeQuery();
		
		List<Blog> blogs = new ArrayList<Blog>();
		while(rs.next()){
			Blog blog = new Blog();
			blog.setId(rs.getString("id"));
			blog.setTitle(rs.getString("title"));
			blog.setContent(JDBCUtils.clobToString(rs.getClob("content")));
			blog.setAuthor(rs.getString("author"));
			blog.setBlogType(rs.getString("blogtype"));
			blog.setPublishTime(rs.getTimestamp("publishTime"));
			blogs.add(blog);
		}
		
		rs.close();
		preStmt.close();
		conn.close();
		return blogs;
	}
	public int blogCountByBlogType(String blogTypeId) throws SQLException{
		Connection conn = DBUtils.getConnection();
		String sql = "select * from t_blog t where t.blogtype like ?";
		PreparedStatement preStmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		preStmt.setString(1, "%"+blogTypeId+"%");
		ResultSet rs = preStmt.executeQuery();
		rs.last();
		int rowCount = rs.getRow();
		rs.close();
		preStmt.close();
		conn.close();
		return rowCount;
	}
	
	
	public static void main(String[] args) throws SQLException, IOException {
		new BlogService().queryByAuthor("jison");
	}
}
