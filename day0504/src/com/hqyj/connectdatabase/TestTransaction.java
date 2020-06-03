package com.hqyj.connectdatabase;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.hqyj.util.DBUtil;

public class TestTransaction {

	public static void main(String[] args) throws SQLException {
		Connection con = DBUtil.getConnection();
		
		Statement stmt1 = con.createStatement();
		Statement stmt2 = con.createStatement();
		
		try{
			con.setAutoCommit(false);			
				stmt1.executeUpdate("update student set age = 222 where id = 3");
				int a = 5/0;			
				stmt1.executeUpdate("update student set age = 333 where id = 4");
			con.commit();
		}catch(ArithmeticException e){
			e.printStackTrace();
			con.rollback();
		}
		
		con.setAutoCommit(true);

	}

}
