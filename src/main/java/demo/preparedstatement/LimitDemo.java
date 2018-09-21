package demo.preparedstatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.DBUtils;

/**
 * 操作分页
 * @author JAVA
 *
 */
public class LimitDemo {
	public static void main(String[] args) {
		Connection conn = null;
		try {
			conn = DBUtils.getConnection();
			String sql = "select id from keywords limit ?,?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, 0);
			ps.setInt(2, 5);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getInt(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeConnection(conn);
		}
	}
}
