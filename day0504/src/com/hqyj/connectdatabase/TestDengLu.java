package com.hqyj.connectdatabase;

import java.sql.*;
import java.util.Scanner;

import com.hqyj.util.DBUtil;

public class TestDengLu {

	public static void main(String[] args) throws SQLException {
		
		System.out.println("�������˻�����");
		Scanner input = new Scanner(System.in);
		//String name = input.next();
		String name = input.nextLine();
		
		System.out.println("���������룺");
		//String password = input.next();
		String password = input.nextLine();
		
		//�������ݿ⣬��ѯ�������user
		Connection con = DBUtil.getConnection();
		Statement stmt = con.createStatement();
		
		String sql = "select 9 from user where name = '"+ name +"' and password = '" + password + "'";
		System.out.println(sql);
		
		ResultSet rs = stmt.executeQuery(sql);
		if(rs.next()){
			System.out.println("��¼�ɹ�");
		}else{
			System.out.println("��¼ʧ��");
		}

		
	}

}
