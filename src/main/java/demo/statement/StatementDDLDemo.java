package demo.statement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import util.DBUtils;

/**
 * 创建一张表t9,id,name,password
 * 插入1条数据: 1,aaa,111
 * 查询表
 * @author JAVA
 *
 */
public class StatementDDLDemo {
	public static void main(String[] args) {
		Connection conn = null;
		try {
			conn = DBUtils.getConnection();
			System.out.println(conn);
			Statement sta = conn.createStatement();
			
			String sql = "create table t9 ("
					+ "id int,"
					+ "name varchar(20),"
					+ "password varchar(20))";
//			boolean flag = sta.execute(sql);
//			System.out.println(flag);
			
			sql = "insert into t9 values (1, 'aaa', '111')";
//			int num = sta.executeUpdate(sql);
//			System.out.println(num);
			
			sql = "select * from t9";
			ResultSet rs = sta.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String password = rs.getString(3);
				System.out.println(id + ", " + name + ", " + password);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeConnection(conn);
		}
	}
}
