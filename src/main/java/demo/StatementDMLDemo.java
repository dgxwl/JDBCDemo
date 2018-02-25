package demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class StatementDMLDemo {
	public static void main(String[] args) {
		try (
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo1", "root", "1234");
				Statement sta = conn.createStatement();
		){
			Class.forName("com.mysql.jdbc.Driver");
			
			sta.execute("create table demo_2 ("
					+ "id int,"
					+ "name varchar(20)"
					+ ")");
			
			sta.executeUpdate("insert into demo_2 values (1,'aaa')");
			sta.executeUpdate("insert into demo_2 values (2,'bbb')");
			
			ResultSet rs = sta.executeQuery("select * from demo_2");
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				System.out.println(id + ":" + name);
			}
			
//			sta.execute("drop table demo_2");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
