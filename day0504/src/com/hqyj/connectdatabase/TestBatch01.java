package com.hqyj.connectdatabase;
/*
 * 批处理
 * prepareStatement
 * 
 * 缺点：没法及时改变语句结构
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
				pstmt.setString(1, "小妹");
				pstmt.setInt(2, 90);
				
				//批处理
				//pstmt.executeUpdate();
				pstmt.addBatch();
				//void addBatch() throws SQLException将一组参数添加到此 PreparedStatement 对象的批处理命令中。
			}	
		
		pstmt.executeBatch();
		//boolean execute() throws SQLException在此 PreparedStatement 对象中执行 SQL 语句，该语句可以是任何种类的 SQL 语句。

	}

}
