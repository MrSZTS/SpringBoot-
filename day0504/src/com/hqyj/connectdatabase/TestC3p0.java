package com.hqyj.connectdatabase;

/*
 * c3p0:开源的JDBC连接池
 */

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hqyj.model.Student;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class TestC3p0 {

	public static void main(String[] args) throws PropertyVetoException, SQLException {
		
		ComboPooledDataSource pool = new ComboPooledDataSource();
		
		pool.setDriverClass("com.mysql.jdbc.Driver");//PropertyVetoException //会调用Class.forName()产生异常ClassNotFoundException
		pool.setJdbcUrl("jdbc:mysql://localhost:3306/lib");
		pool.setUser("root");
		pool.setPassword("123456");
		
		Connection con = pool.getConnection();
		
		String sql = "insert into student(name,age) values('小米','14')";
		String sql1 = "select * from student";
		
		Statement stmt = con.createStatement();
		//stmt.executeUpdate(sql);
		ResultSet rs = stmt.executeQuery(sql1);
		
		System.out.println("id\t姓名\t年龄");
		System.out.println("--------------------");
		
		List<Student> stus = new ArrayList<>();//list是接口，要把对象实例化出来，所以只能new具体的类
		
		while(rs.next()){
			int id = rs.getInt("id");
			String name = rs.getString("name");
			int age = rs.getInt("age");
			
			Student stu = new Student(id,name,age);
			stus.add(stu);
			
			//System.out.println(id + "\t" + name + "\t" + age);
		}
		
		//遍历
		for (Student one:stus) {
			System.out.println(one.getId() + "\t" + one.getName() + "\t" + one.getAge());
			
			
		}

	}

}
