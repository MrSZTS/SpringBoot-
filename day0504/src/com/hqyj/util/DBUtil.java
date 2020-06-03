package com.hqyj.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil{
	
	//ΪʲôҪ��static��
	private static String url = "jdbc:mysql://localhost:3306/lib";
	private static String user = "root";
	private static String password = "123456";
	private static String driver = "com.mysql.jdbc.Driver";
	
	private static Connection con;//��̬�ķ���ֻ�ܷ��ʾ�̬�ı���
	
	//��̬�飺һ����ǰ�౻ʹ�ã���ô��̬��ͻᱻִ��һ��ss
	static{
		//�������� 
		try {
			Class.forName(driver);//forName	��ʲô����ȥѰ��
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private DBUtil(){}
	
	//�������
	public static Connection getConnection() throws SQLException{
		//Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lib?user=root&password=123456");
		//����Ѿ��������Ӿ�ֱ�ӣ�ʹ�ã��ṩ���ݣ�����Ҫ�ٵ�DriverManager
		if(con == null)	con = DriverManager.getConnection(url,user,password);//if����ֻ��һ�������Բ��������
		
		//��һ���Ѿ����ر���
		//con.isClosed()	ѯ���Ƿ�ر���
		//con.close()		�رգ������رգ�
		else if(con.isClosed()){//�ж��Ƿ��Ѿ����ر��ˣ���Ϊcon.close()�Ϳ��Թر�
			con = DriverManager.getConnection(url,user,password);
		}		
		return con;
		
	}
}


