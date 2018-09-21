package demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.DBUtils;

/**
 * 插入时返回自增列的值
 * @author Administrator
 *
 */
public class AutoIncrementColumnsDemo {
	public static void main(String[] args) {
		Connection conn = null;
		String sql = "insert into keywords values (null,?)";
		try {
			conn = DBUtils.getConnection();
			conn.setAutoCommit(false);
			//自动生成序列号的列名
			//一张表中可以存在多个自增长的列,所以这里使用数组保存
			String[] cols = {"id"};
			/*
			 * 获取ps对象:
			 * 第一个参数生成执行计划SQL
			 * 第二个参数执行时返回得到的值自增长列的列名
			 */
			PreparedStatement ps = conn.prepareStatement(sql, cols);
			ps.setString(1, "雾霾");
			//ps.executeXX()一执行,自增的id值就返回到ps对象中保存
			int n = ps.executeUpdate();
			if (n != 1) {
				throw new Exception("keywords插入错误");
			}
			//获取自增长的值
			ResultSet rs = ps.getGeneratedKeys();
			
			int id = -1;
			while (rs.next()) {
				//此处的rs.getXXX()只能是index,不能为列名
				id = rs.getInt(1);
				System.out.println(id);
			}
			ps.close();
			String sql2 = "insert into post values (null,?,?)";
			ps = conn.prepareStatement(sql2);
			ps.setString(1, "今天天气很冷,晚上有雾霾");
			ps.setInt(2, id);
			n = ps.executeUpdate();
			if (n != 1) {
				throw new Exception("post插入错误");
			}
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			DBUtils.rollback(conn);
		} finally {
			DBUtils.closeConnection(conn);
		}
	}
}
