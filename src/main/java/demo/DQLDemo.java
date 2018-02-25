package demo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import util.DBUtils;

public class DQLDemo {
	public static void main(String[] args) throws Exception {
		Connection conn = DBUtils.getConnection();
//		System.out.println(conn);

		//查询t9表中的数据
		String sql = "select * from t9";
		Statement sta = conn.createStatement();
		ResultSet rs = sta.executeQuery(sql);
		while (rs.next()) {
			int id = rs.getInt(1);
			String name = rs.getString(2);
			String password = rs.getString(3);
			System.out.println(id + ", " + name + ", " + password);
		}
		
		DBUtils.closeConnection(conn);
	}
}
