package demo.preparedstatement.batch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Arrays;

import util.DBUtils;

/**
 * 批量执行DML语句
 * @author JAVA
 *
 */
public class DMLBatchDemo {
	public static void main(String[] args) {
		//准备DML语句
		String dml = "insert into log1 values(?,?)";
		Connection conn = null; 
		try {
			conn = DBUtils.getConnection();
			PreparedStatement ps = conn.prepareStatement(dml);
			//把一批参数添加到ps的缓存中
			ps.setInt(1, 1);
			ps.setString(2, "1111");
			ps.addBatch();
			ps.setInt(1, 2);
			ps.setString(2, "2222");
			ps.addBatch();
			ps.setInt(1, 3);
			ps.setString(2, "3333");
			ps.addBatch();
			//批量执行DML语句
			int[] arr = ps.executeBatch();
			System.out.println(Arrays.toString(arr));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeConnection(conn);
		}
	}
}
