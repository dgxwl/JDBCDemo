package practice;

import java.sql.Connection;
import java.sql.PreparedStatement;

import util.DBUtils;

/**
 * 课堂练习2
 * 使用PrepareStatement对象,批量更新20条数据
 * - 控制避免内存溢出
 * @author JAVA
 *
 */
public class Test2 {
	public static void main(String[] args) {
		String dml = "insert into log1 values(?,?)";
		Connection conn = null;
		try {
			conn = DBUtils.getConnection();
			PreparedStatement ps = conn.prepareStatement(dml);
			//批量存储参数,并解决内存溢出的问题
			for (int i = 1; i <= 30; i++) {
				ps.setInt(1, i);
				ps.setString(2, "test"+i);
				ps.addBatch();
				//存到一定数量就批量执行一次
				if (i % 8 == 0) {
					ps.executeBatch();
					ps.clearBatch();
				}
			}
			//最后一遍剩余6条,执行剩余未执行的部分
			ps.executeBatch();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeConnection(conn);
		}
	}
}
