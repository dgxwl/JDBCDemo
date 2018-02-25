package practice;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Arrays;

import util.DBUtils;

/**
 * 课堂练习1
 * 使用sta对象,批量执行DDL语句 5个
 * log6 (id int,msg varchar(20))
 * sta.addBatch(sql)
 * sta.executeBatch()
 * @author JAVA
 *
 */
public class Test1 {
	public static void main(String[] args) {
		Connection conn = null;
		try {
			conn = DBUtils.getConnection();
			String ddl1 = "create table log6(id int,msg varchar(20))";
			String ddl2 = "create table log7(id int,msg varchar(20))";
			String ddl3 = "create table log8(id int,msg varchar(20))";
			String ddl4 = "create table log9(id int,msg varchar(20))";
			String ddl5 = "create table log10(id int,msg varchar(20))";
			Statement sta = conn.createStatement();
			sta.addBatch(ddl1);
			sta.addBatch(ddl2);
			sta.addBatch(ddl3);
			sta.addBatch(ddl4);
			sta.addBatch(ddl5);
			int[] arr = sta.executeBatch();
			System.out.println(Arrays.toString(arr));
			sta.clearBatch();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeConnection(conn);
		}
	}
}
