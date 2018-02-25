package demo;

import java.sql.Connection;
import java.sql.PreparedStatement;

import util.DBUtils;

public class PreparedStatementDMLDemo {
	public static void main(String[] args) {
		Connection conn = null;
		try {
			conn = DBUtils.getConnection();
			//? 是占位符,把可变数据的位置占住
			//"where id=?"
			String sql = "insert into t9 values (?,?,?)";
			//将sql语句发送到数据库,并创建执行计划
			//ps代表执行计划
			PreparedStatement ps = conn.prepareStatement(sql);
			//替换执行计划中的参数:
			//按照顺序和数量发送给执行计划
			ps.setInt(1, 4);
			ps.setString(2, "ddd");
			ps.setString(3, "444");
			//运行执行计划
			ps.executeUpdate();
			
			ps.setInt(1, 5);
			ps.setString(2, "eee");
			ps.setString(3, "555");
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeConnection(conn);
		}
	}
}
