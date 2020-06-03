package com.hqyj.connectdatabase;
/*
 * 预处理方式处理访问数据库
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.hqyj.util.DBUtil;

public class TestDengLu02 {

	public static void main(String[] args) throws SQLException {
		System.out.println("请输入账户名：");
		Scanner input = new Scanner(System.in);
		String name = input.nextLine();
		
		System.out.println("请输入密码：");
		String password = input.nextLine();
		
		//连接数据库，查询有无这个user
		Connection con = DBUtil.getConnection();
		
		//预处理
		PreparedStatement pstmt = con.prepareStatement("select * from user where name = ? and password = ? ");
		
		pstmt.setString(1, name);//1,2 表示设置第几个问号
		pstmt.setString(2, password);
		
		ResultSet rs = pstmt.executeQuery();
		
		if(rs.next()){
			System.out.println("登录成功");
		}else{
			System.out.println("登录失败");
		}
		

	}

}
