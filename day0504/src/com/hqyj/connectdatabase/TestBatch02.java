package com.hqyj.connectdatabase;
/*
 * Statement
 * 
 * Statement��prepareStatement��ȣ�
 * ��������;��ִ�е�sql���
 */
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.hqyj.util.DBUtil;

public class TestBatch02 {

	public static void main(String[] args) throws SQLException {
		Connection con = DBUtil.getConnection();
		
		Statement stmt = con.createStatement();
		
		for(int i = 0;i <= 10;i++){
			stmt.addBatch("insert into student(name,age) values('ЦЦ',45)");
			//stmt.executeUpdate("insert into student(name,age) values('jЦЦ',45)");
			
			if(i>5){
				//stmt.addBatch("update.....");
			}
		}
		
		stmt.executeBatch();
		
	}

}
