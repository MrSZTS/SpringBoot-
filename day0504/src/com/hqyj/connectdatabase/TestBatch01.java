package com.hqyj.connectdatabase;
/*
 * ������
 * prepareStatement
 * 
 * ȱ�㣺û����ʱ�ı����ṹ
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.hqyj.util.DBUtil;

public class TestBatch01 {

	public static void main(String[] args) throws SQLException {
		Connection con = DBUtil.getConnection();
		
		PreparedStatement pstmt = con.prepareStatement("insert into student(name,age) values(?,?)");
		
			for(int i = 0;i <= 10;i++){
				pstmt.setString(1, "С��");
				pstmt.setInt(2, 90);
				
				//������
				//pstmt.executeUpdate();
				pstmt.addBatch();
				//void addBatch() throws SQLException��һ�������ӵ��� PreparedStatement ����������������С�
			}	
		
		pstmt.executeBatch();
		//boolean execute() throws SQLException�ڴ� PreparedStatement ������ִ�� SQL ��䣬�����������κ������ SQL ��䡣

	}

}
