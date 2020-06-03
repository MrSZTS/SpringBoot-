package com.hqyj.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBPool {
	private static String url = "jdbc:mysql://localhost:3306/lib";
	private static String user = "root";
	private static String password = "123456";
	private static String driver = "com.mysql.jdbc.Driver";
	
	private static Connection[] cons;//��������
	
	private static int count = 0;//������������ǰ�ֵ��ڼ��������ṩ����
	
	//��̬�飺һ����ǰ�౻ʹ�ã���ô��̬��ͻᱻִ��һ��ss
		static{
			try {
				Class.forName(driver);//��������	forName	��ʲô����ȥѰ��
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			cons = new Connection[10];
			for (int i = 0; i < cons.length; i++) {
				try {
					cons[i] = DriverManager.getConnection(url,user,password);//���ö�Σ�ÿ�δ������������Ӷ���һ��
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
	private DBPool(){}
	
	//�������
	public static Connection getConnection() throws SQLException{
		//��cons[] ������һ������
		count++;
		//Ϊ��ֹ�������int�ķ�Χ
		if(count >= 10) count=0;
		return cons[count % 10];//1 2 3 ......9
			
			
		}
	

}
