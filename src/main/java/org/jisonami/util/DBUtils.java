package org.jisonami.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtils {
	static Properties properties = new Properties();
	static {
		try {
			properties.load(DBUtils.class.getClassLoader().getResourceAsStream("DBConfig.properties"));
			Class.forName(getDriver());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(getUrl(), 
				getUserName(),
				getPassword());
	}
	
	public static String getDriver(){
		return properties.getProperty("jdbc.driver");
	}
	
	public static String getUrl(){
		return properties.getProperty("jdbc.url");
	}
	
	public static String getUserName(){
		return properties.getProperty("jdbc.user");
	}
	
	public static String getPassword(){
		return properties.getProperty("jdbc.pass");
	}
}
