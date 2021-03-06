package com.hqyj.connectdatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.hqyj.util.DBPool;
import com.hqyj.util.DBUtil;

/*
 * java语言	访问数据库
 * 驱动：jar包
 * 
 * mysql服务器	jdbc驱动
 * 
 * classpath：类的查找路径
 * 
 * Referenced Libraries:涉及和引用到的库
 */
public class ConnectDatabase2 {
	public static void main(String[] args) {
		
		
		//获得连接
		//连接到数据库，需要提供一些信息：账号、密码、路径 ip号
		//DriverManager.getConnection(路径url, 账号user, 密码password)
		Connection con;
		try {
			Connection con1;
			Connection con2;
			Connection con3;
			con1 = DBUtil.getConnection();
			con2 = DBUtil.getConnection();
			//con2.close();
			con3 = DBUtil.getConnection();
			System.out.println(con1==con2);
			System.out.println(con2==con3);
			System.out.println(con1==con3);
			
			/*Connection con1;
			Connection con2;
			Connection con3;
			con1 = DBPool.getConnection();
			con2 = DBPool.getConnection();
			//con2.close();
			con3 = DBPool.getConnection();
			System.out.println(con1);
			System.out.println(con2);
			System.out.println(con3);*/
			
			con = DBUtil.getConnection();//DriverManager.getConnection("jdbc:mysql://localhost:3306/lib?user=root&password=123456");
			//&amp;useSSL=false&amp;serverTimezone=Hongkong&amp;useUnicode=true
			
			//获取数据
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
			
			//插入数据（添加数据）
			/*int num = stmt.executeUpdate("Insert into student(name,age) values('小王','99')");
			System.out.println("影响的行数：" + num);
			*/
			
			//删除数据
			/*int num1 = stmt.executeUpdate("Delete from student where id=16");
			System.out.println("影响的行数：" + num1);
			*/
			
			//修改数据
			/*int num2 = stmt.executeUpdate("Update student set age= 33 where id>8");
			System.out.println("影响的行数：" + num2);
			*/
			
			//简便写法
			/*String name = "老王";
			int age = 89;
			
			String sql = "Insert into student(name,age) values('"+ name +"','"+ age +"')";
			int num = stmt.executeUpdate(sql);
			System.out.println("影响的行数：" + num);
			*/
			
			String sql = "Delete from student where id=9";
			int num = stmt.executeUpdate(sql);
			System.out.println("影响的行数：" + num);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		

	}

}
