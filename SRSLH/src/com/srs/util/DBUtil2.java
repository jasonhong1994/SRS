package com.srs.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil2 {
	private static Properties pros = new Properties();
	private static String driver;
	private static String url;
	private static String mysqlurl;
	private static String pwd;
	
	static { 
		try {
			pros.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		driver = pros.getProperty("driver");
		url = pros.getProperty("url");
		mysqlurl = pros.getProperty("mysqlurl");
		pwd = pros.getProperty("pwd");
	}
	public static Connection open(){
		String DB = pros.getProperty("usingDB");
		if (DB.equals("sqlite")) {
			return open1();
		} else if (DB.equals("mysql")) {
			return open2();
		} else {
			return null;
		}
		
	}
	private static Connection open1() {
		try {
			Class.forName(driver);
			try {
				return DriverManager.getConnection(url);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static Connection open2() {
		try {
			Class.forName(driver);
			try {
				return DriverManager.getConnection(url,mysqlurl,pwd);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void close(Connection Conn) {
		if (Conn != null) {
			try {
				Conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
