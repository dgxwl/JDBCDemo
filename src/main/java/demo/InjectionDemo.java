package demo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import util.DBUtils;
/**
 * Statement不能防止sql注入
 * @author Administrator
 *
 */
public class InjectionDemo {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入用户名:");
		String name = scanner.nextLine();
		System.out.println("请输入密码:");
		String password = scanner.nextLine();
		boolean flag = login(name, password);
		if (flag) {
			System.out.println("欢迎," + name);
		} else {
			System.out.println("登录失败");
		}
		
		scanner.close();
	}
	
	public static boolean login(String name, String password) {
		Connection conn = null;
		try {
			conn = DBUtils.getConnection();
			String sql = "select count(*) from t9 where name=\'" + name + "\' and password=\'" + password + "\'";
			System.out.println(sql);
			Statement sta = conn.createStatement();
			ResultSet rs = sta.executeQuery(sql);
			while (rs.next()) {
				int n = rs.getInt(1);
				return n >= 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeConnection(conn);
		}
		
		return false;
	}
}
