package demo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import util.DBUtils;

public class Demo4 {
	public static void main(String[] args) {
		Thread t1 = new DemoThread(5000);
		Thread t2 = new DemoThread(6000);
		Thread t3 = new DemoThread(10);
		t1.start();
		t2.start();
		t3.start();
	}
}

/**
 * 线程类
 * @author JAVA
 *
 */
class DemoThread extends Thread {
	//睡眠时间
	int wait;
	public DemoThread(int wait) {
		this.wait = wait;
	}
	
	@Override
	public void run() {
		Connection conn = DBUtils.getConnection();
		System.out.println("获取连接对象:" + conn);
		String sql = "select * from t9";
		try {
			Statement sta = conn.createStatement();
			ResultSet rs = sta.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String password = rs.getString(3);
				System.out.println(id);
			}
			Thread.sleep(wait);
			System.out.println(getName() + "已结束");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeConnection(conn);
		}
		
	}
}
