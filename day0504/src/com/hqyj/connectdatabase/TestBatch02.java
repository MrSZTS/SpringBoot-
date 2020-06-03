package com.hqyj.connectdatabase;
/*
 * Statement
 * 
 * Statement比prepareStatement相比：
 * 可以在中途改执行的sql语句
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
			stmt.addBatch("insert into student(name,age) values('笑笑',45)");
			//stmt.executeUpdate("insert into student(name,age) values('j笑笑',45)");
			
			if(i>5){
				//stmt.addBatch("update.....");
			}
		}
		
		stmt.executeBatch();
		
	}

}
