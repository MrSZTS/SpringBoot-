package com.hqyj.connectdatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * java����	�������ݿ�
 * ������jar��
 * 
 * mysql������	jdbc����
 * 
 * classpath����Ĳ���·��
 * 
 * Referenced Libraries:�漰�����õ��Ŀ�
 */
public class ConnectDatabase {
	public static void main(String[] args) {
		//�������� 
		try {
			Class.forName("com.mysql.jdbc.Driver");//forName	��ʲô����ȥѰ��
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//�������
		//���ӵ����ݿ⣬��Ҫ�ṩһЩ��Ϣ���˺š����롢·�� ip��
		//DriverManager.getConnection(·��url, �˺�user, ����password)
		Connection con;
		try {
			con= DriverManager.getConnection("jdbc:mysql://localhost:3306/lib?user=root&password=123456");
			//&amp;useSSL=false&amp;serverTimezone=Hongkong&amp;useUnicode=true
			
			//��ȡ����
			Statement stmt = con.createStatement();
			/*ResultSet rs = stmt.executeQuery("SELECT * FROM student");
			while(rs.next()){
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				System.out.print("id = " + id);
				System.out.print("\tname = " + name);
				System.out.print("\tage = " + age);
				System.out.println();
			}*/
			
			//�������ݣ�������ݣ�
			/*int num = stmt.executeUpdate("Insert into student(name,age) values('С��','99')");
			System.out.println("Ӱ���������" + num);
			*/
			
			//ɾ������
			/*int num1 = stmt.executeUpdate("Delete from student where id=16");
			System.out.println("Ӱ���������" + num1);
			*/
			
			//�޸�����
			/*int num2 = stmt.executeUpdate("Update student set age= 33 where id>8");
			System.out.println("Ӱ���������" + num2);
			*/
			
			//���д��
			String name = "����";
			int age = 89;
			
			String sql = "Insert into student(name,age) values('"+ name +"','"+ age +"')";
			int num = stmt.executeUpdate(sql);
			System.out.println("Ӱ���������" + num);
			
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		

	}

}
