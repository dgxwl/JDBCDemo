package demo;

import java.sql.Connection;
import java.sql.PreparedStatement;

import util.DBUtils;
/**
 * 事务
 * @author JAVA
 *
 */
public class TransactionDemo {
	public static void main(String[] args) {
		Connection conn = null;
		try {
			conn = DBUtils.getConnection();
			//取消自动提交事务,同时开始了一个事务
			conn.setAutoCommit(false);
			String sql = "insert into t9 values(?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, 21);
			ps.setString(2, "wuber");
			ps.setString(3, "123");
			int n = ps.executeUpdate();
			//提交事务
			conn.commit();
			System.out.println("成功执行:" + n);
		} catch (Exception e) {
			e.printStackTrace();
			DBUtils.rollback(conn);
		} finally {
			DBUtils.closeConnection(conn);
		}
	}
}
