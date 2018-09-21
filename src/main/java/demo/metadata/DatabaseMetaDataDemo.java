package demo.metadata;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

import util.DBUtils;

/**
 * DatabaseMetaData: 获取数据库的元数据
 * @author Administrator
 *
 */
public class DatabaseMetaDataDemo {
	public static void main(String[] args) {
		Connection conn = null;
		try {
			conn = DBUtils.getConnection();
			//获取数据库元数据
			DatabaseMetaData metaData = conn.getMetaData();
			//获得必要参数
			System.out.println(metaData.getDriverName());
			System.out.println(metaData.getURL());
			System.out.println(metaData.getUserName());
			//获得数据库产品信息
			System.out.println(metaData.getDatabaseProductName());
			System.out.println(metaData.getDatabaseProductVersion());
			
			//获取表; 参数tableNamePattern: 指定表名, 传"%"则获取所有表
			String schema = conn.getMetaData().getUserName().toUpperCase();  //Oracle和DB2需要这个参数
			ResultSet resultSet = metaData.getTables(null, "%", "%", new String[] {"TABLE"});
			while (resultSet.next()) {
				//获取表名
				String tableName = resultSet.getString("TABLE_NAME");
				System.out.println(tableName);
				//获得该表字段的元数据
				ResultSet rs = metaData.getColumns(null, schema, tableName, "%");
				while (rs.next()) {
					//字段名
					String columnName = rs.getString("COLUMN_NAME");
					//字段类型
					String type = rs.getString("TYPE_NAME");
					//字段长度
					int dataSize = rs.getInt("COLUMN_SIZE");
					//字段(浮点类型)小数点后位数
					int digits = rs.getInt("DECIMAL_DIGITS");
					//字段非空约束
					int nullable = rs.getInt("NULLABLE");
					//字段注释
					String remarks = rs.getString("REMARKS");
					
					System.out.println("字段名: " + columnName + ", 字段类型: " + type + ", 长度: " + dataSize
							+ ", 小数点后位数: " + digits + ", 非空(0非空, 1可空): " + nullable + ", 字段注释(没有则空串): " + remarks);
				}
			}
			
			//指定表获取主键元数据结果集(一个表可能有多个主键): getPrimaryKeys(catalog, schema, table指定表名)
			ResultSet pkSet = metaData.getPrimaryKeys(null, null, "t_user");
			while (pkSet.next()) {
				//主键名
				String pkName = pkSet.getString("COLUMN_NAME");
				//主键的序列位置
				String keySeq = pkSet.getString("KEY_SEQ");
				System.out.println("主键名: " + pkName + ", 主键的位置: " + keySeq);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeConnection(conn);
		}
	}
}
