package practice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.DBUtils;

/**
 * 课堂练习3:转账业务
 * create table account (
 * 	id int,
 * 	name varchar(20),
 * 	money double(7,2)
 * );
 * insert into account values (1,'aaa',10000);
 * insert into account values (2,'bbb',100);
 * @author JAVA
 *
 */
public class Test3 {
	public static void main(String[] args) {
		Connection conn = null;
		try {
			conn = DBUtils.getConnection();
			conn.setAutoCommit(false);
			String sql = "update account set money=money+? where id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			//减钱
			ps.setDouble(1, -10000);
			ps.setInt(2, 1);
			int n = ps.executeUpdate();
			if (n != 1) {
				throw new Exception("扣错了");
			}
			//加钱
			ps.setDouble(1, 10000);
			ps.setInt(2, 2);
			n = ps.executeUpdate();
			if (n != 1) {
				throw new Exception("加错了");
			}
			ps.close();
			//检查账户余额是否充足
			String dql = "select money from account where id=?";
			ps = conn.prepareStatement(dql);
			ps.setInt(1, 1);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				double money = rs.getDouble(1);
				if (money < 0) {
					throw new Exception("余额不足");
				}
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
