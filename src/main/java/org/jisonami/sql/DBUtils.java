package org.jisonami.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtils {
	static Properties properties = new Properties();
	static {
		try {
			properties.load(DBUtils.class.getClassLoader().getResourceAsStream("org/jisonami/sql/DBConfig.properties"));
			Class.forName(properties.getProperty("driver"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(properties.getProperty("url"), 
				properties.getProperty("user"),
				properties.getProperty("pass"));
	}
	
}
