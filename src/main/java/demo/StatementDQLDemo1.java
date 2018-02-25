package demo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import util.DBUtils;

public class StatementDQLDemo1 {
	public static void main(String[] args) throws Exception {
		Connection conn = DBUtils.getConnection();
		Statement sta = conn.createStatement();
		
		String sql = "select empno,ename from emp";
		ResultSet rs = sta.executeQuery(sql);
		while (rs.next()) {
			int id = rs.getInt("empno");
			String name = rs.getString("ename");
			System.out.println(id+"号员工姓名:" + name);
		}
		
		//调用封装的关闭连接对象的方法
		DBUtils.closeConnection(conn);
	}
}
