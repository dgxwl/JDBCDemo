package demo.statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class FirstStatementDemo {
	public static void main(String[] args) {
		try {
			//1.注册驱动
			Class.forName("com.mysql.jdbc.Driver");
			//2.获得Connection对象
			//url jdbc:mysql://数据库IP地址:端口号/库名
			//user 数据库用户名
			//password 数据库密码
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo1", "root", "1234");
			//验证是否连接成功
//			System.out.println(conn);
			//证明接口实现类来自驱动
//			System.out.println(conn.getClass());
			//3.获取statement对象
			Statement sta = conn.createStatement();
//			System.out.println(sta);
			
//			sta.execute(sql);  //可处理任何SQL语句,常用于执行DDL和DCL
//			sta.executeUpdate(sql);  //执行DML语句,insert,update,delete
//			sta.executeQuery(sql);  //执行DQL,返回值resultSet
			
			//建表DDL
			String ddl = "create table demo_1("
					+ "id int,"
					+ "name varchar(20));";
//			boolean flag = sta.execute(ddl);
//			System.out.println(flag);  //false代表无返回结果集,true为有返回结果集
			
			//插入操作DML
			String dml = "insert into demo_1 values (1,'hanmeimei')";
//			sta.executeUpdate(dml);
			
			//查询DQL
			String dql = "select 'hello' as a from dual";
//			boolean flag = sta.execute(dql);
//			System.out.println(flag);
			ResultSet rs = sta.executeQuery(dql);
			while (rs.next()) {
				String str = rs.getString("a");
				System.out.println(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
