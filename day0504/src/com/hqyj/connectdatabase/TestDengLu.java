package com.hqyj.connectdatabase;

import java.sql.*;
import java.util.Scanner;

import com.hqyj.util.DBUtil;

public class TestDengLu {

	public static void main(String[] args) throws SQLException {
		
		System.out.println("请输入账户名：");
		Scanner input = new Scanner(System.in);
		//String name = input.next();
		String name = input.nextLine();
		
		System.out.println("请输入密码：");
		//String password = input.next();
		String password = input.nextLine();
		
		//连接数据库，查询有无这个user
		Connection con = DBUtil.getConnection();
		Statement stmt = con.createStatement();
		
		String sql = "select 9 from user where name = '"+ name +"' and password = '" + password + "'";
		System.out.println(sql);
		
		ResultSet rs = stmt.executeQuery(sql);
		if(rs.next()){
			System.out.println("登录成功");
		}else{
			System.out.println("登录失败");
		}

		
	}

}
