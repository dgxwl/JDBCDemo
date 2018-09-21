package demo.metadata;

import java.sql.Connection;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;

import util.DBUtils;

/**
 * 获取执行语句时的参数元数据
 * @author Administrator
 *
 */
public class ParameterMetaDataDemo {
	public static void main(String[] args) {
		Connection conn = null;
		try {
			conn = DBUtils.getConnection();
			String sql = "insert into t_user values (null, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			//获取参数元数据
			ParameterMetaData metaData = ps.getParameterMetaData();
			//获得参数个数
			System.out.println(metaData.getParameterCount());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeConnection(conn);
		}
	}
}
