package practice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import util.DBUtils;

/**
 * 课堂练习4
 * 1.完成转账事务逻辑
 * 2.封装成方法pay(int from, int to, double money)
 * @author JAVA
 *
 */
public class Test4 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("谁转账?");
		int from = scanner.nextInt();
		System.out.println("转账给谁?");
		int to = scanner.nextInt();
		System.out.println("转多少?");
		double money = scanner.nextDouble();
		pay(from, to, money);
		scanner.close();
	}
	
	public static void pay(int from, int to, double money) {
		Connection conn = null;
		try {
			conn = DBUtils.getConnection();
			conn.setAutoCommit(false);
			String sql = "update account set money=money+? where id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			//减钱
			ps.setDouble(1, -money);
			ps.setInt(2, from);
			int n = ps.executeUpdate();
			if (n != 1) {
				throw new Exception("加错了");
			}
			//加钱
			ps.setDouble(1, money);
			ps.setInt(2, to);
			n = ps.executeUpdate();
			if (n != 1) {
				throw new Exception("减错了");
			}
			ps.close();
			//检查余额
			sql = "select money from account where id=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, from);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				double e = rs.getDouble(1);
				if (e < 0) {
					throw new Exception("余额不足");
				}
			}
			conn.commit();
			System.out.println("操作成功!");
		} catch (Exception e) {
			e.printStackTrace();
			DBUtils.rollback(conn);
		} finally {
			DBUtils.closeConnection(conn);
		}
	}
}
