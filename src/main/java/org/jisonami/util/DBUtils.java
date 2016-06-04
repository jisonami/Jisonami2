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
			Class.forName(properties.getProperty("jdbc.driver"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(properties.getProperty("jdbc.url"), 
				properties.getProperty("jdbc.user"),
				properties.getProperty("jdbc.pass"));
	}
	
}
