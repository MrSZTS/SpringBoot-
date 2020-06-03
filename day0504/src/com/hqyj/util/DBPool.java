package com.hqyj.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBPool {
	private static String url = "jdbc:mysql://localhost:3306/lib";
	private static String user = "root";
	private static String password = "123456";
	private static String driver = "com.mysql.jdbc.Driver";
	
	private static Connection[] cons;//对象数组
	
	private static int count = 0;//计数器，代表当前轮到第几个连接提供服务
	
	//静态块：一旦当前类被使用，那么静态块就会被执行一次ss
		static{
			try {
				Class.forName(driver);//加载驱动	forName	用什么参数去寻找
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			cons = new Connection[10];
			for (int i = 0; i < cons.length; i++) {
				try {
					cons[i] = DriverManager.getConnection(url,user,password);//调用多次，每次创建出来的连接都不一样
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
	private DBPool(){}
	
	//获得连接
	public static Connection getConnection() throws SQLException{
		//在cons[] 里面拿一个就行
		count++;
		//为防止自增溢出int的范围
		if(count >= 10) count=0;
		return cons[count % 10];//1 2 3 ......9
			
			
		}
	

}
