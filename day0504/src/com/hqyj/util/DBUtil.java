package com.hqyj.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil{
	
	//为什么要用static？
	private static String url = "jdbc:mysql://localhost:3306/lib";
	private static String user = "root";
	private static String password = "123456";
	private static String driver = "com.mysql.jdbc.Driver";
	
	private static Connection con;//静态的方法只能访问静态的变量
	
	//静态块：一旦当前类被使用，那么静态块就会被执行一次ss
	static{
		//加载驱动 
		try {
			Class.forName(driver);//forName	用什么参数去寻找
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private DBUtil(){}
	
	//获得连接
	public static Connection getConnection() throws SQLException{
		//Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lib?user=root&password=123456");
		//如果已经有了连接就直接（使用）提供数据，而不要再调DriverManager
		if(con == null)	con = DriverManager.getConnection(url,user,password);//if语句后只有一句语句可以不打大括号
		
		//万一它已经被关闭了
		//con.isClosed()	询问是否关闭了
		//con.close()		关闭（主动关闭）
		else if(con.isClosed()){//判断是否已经被关闭了，因为con.close()就可以关闭
			con = DriverManager.getConnection(url,user,password);
		}		
		return con;
		
	}
}


