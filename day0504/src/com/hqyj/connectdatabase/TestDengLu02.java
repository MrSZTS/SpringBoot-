package com.hqyj.connectdatabase;
/*
 * Ԥ����ʽ����������ݿ�
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.hqyj.util.DBUtil;

public class TestDengLu02 {

	public static void main(String[] args) throws SQLException {
		System.out.println("�������˻�����");
		Scanner input = new Scanner(System.in);
		String name = input.nextLine();
		
		System.out.println("���������룺");
		String password = input.nextLine();
		
		//�������ݿ⣬��ѯ�������user
		Connection con = DBUtil.getConnection();
		
		//Ԥ����
		PreparedStatement pstmt = con.prepareStatement("select * from user where name = ? and password = ? ");
		
		pstmt.setString(1, name);//1,2 ��ʾ���õڼ����ʺ�
		pstmt.setString(2, password);
		
		ResultSet rs = pstmt.executeQuery();
		
		if(rs.next()){
			System.out.println("��¼�ɹ�");
		}else{
			System.out.println("��¼ʧ��");
		}
		

	}

}
