package demo;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Arrays;

import util.DBUtils;
/**
 * 批量执行DDL语句
 * @author JAVA
 *
 */
public class DDLBatchDemo {
	public static void main(String[] args) {
		//准备一批DDL语句
		String ddl1 = "create table log1(id int,msg varchar(20))";
		String ddl2 = "create table log2(id int,msg varchar(20))";
		String ddl3 = "create table log3(id int,msg varchar(20))";
		String ddl4 = "create table log4(id int,msg varchar(20))";
		String ddl5 = "create table log5(id int,msg varchar(20))";
		Connection conn = null;
		try {
			conn = DBUtils.getConnection();
			Statement sta = conn.createStatement();
			//把一批DDL语句添加到缓存中
			sta.addBatch(ddl1);
			sta.addBatch(ddl2);
			sta.addBatch(ddl3);
			sta.addBatch(ddl4);
			sta.addBatch(ddl5);
			/*
			 * 执行一批DDL语句
			 * 1.>=0 代表成功
			 * 2.-2 代表成功
			 * oracle对executeBatch()并不完全支持,返回-2
			 * 3.-3 代表失败
			 */
			int[] arr = sta.executeBatch();
			System.out.println(Arrays.toString(arr));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeConnection(conn);
		}
	}
}
