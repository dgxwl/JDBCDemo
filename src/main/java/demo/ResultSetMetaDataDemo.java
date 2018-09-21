package demo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import util.DBUtils;

/**
 * 执行语句时获得结果集的元数据
 * @author Administrator
 *
 */
public class ResultSetMetaDataDemo {
	public static void main(String[] args) {
		Connection conn = null;
		try {
			conn = DBUtils.getConnection();
			String dql = "select * from emp";
			Statement sta = conn.createStatement();
			ResultSet rs = sta.executeQuery(dql);
			//获取元数据
			ResultSetMetaData meta = rs.getMetaData();
			//获取列的数量
			int n = meta.getColumnCount();
			System.out.println("列数:" + n);
			//获取列的名称
			String name1 = meta.getColumnName(1);
			String name2 = meta.getColumnName(2);
			System.out.println("列名1:" + name1 + ", " + "列名2:" + name2);
			for (int i = 0; i < meta.getColumnCount(); i++) {
				System.out.println("第" + (i+1) + "列名称:" + meta.getColumnName(i+1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeConnection(conn);
		}
	}
}
