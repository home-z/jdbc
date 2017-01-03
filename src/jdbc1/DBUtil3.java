package jdbc1;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;

public class DBUtil3 {
	private static BasicDataSource ds;
	static {
		Properties pro = new Properties();
		try {				//class.getClassLoader()获得当前类的加载路径.getResourceAsStream加载指定属性文件
			pro.load(DBUtil3.class.getClassLoader().getResourceAsStream("db.properties"));
			String drivate = pro.getProperty("jdbc.driver");
			String url = pro.getProperty("jdbc.url");
			String user = pro.getProperty("jdbc.user");
			String pwd = pro.getProperty("jdbc.password");
			String maxActive = pro.getProperty("dbcp.maxActive");
			String strinitSize = pro.getProperty("dbcp.initSize");
			//实例化，并初始化连接池
			ds = new BasicDataSource();
			ds.setDriverClassName(drivate);;
			ds.setUrl(url);
			ds.setUsername(user);
			ds.setPassword(pwd);
			ds.setMaxActive(Integer.parseInt(maxActive));
			ds.setInitialSize(Integer.parseInt(strinitSize));
		} catch (IOException e) {
			throw new RuntimeException("加载属性文件错误");
		}
		
	}
	public static Connection getConnection() throws SQLException {
		return ds.getConnection();
	}
	
	public static void close(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				//显示错误信息，e 并抛给调用者
				throw new RuntimeException("归还连接错误",e);
			}
		}
	}
	
	public static void main(String[] args) throws SQLException {
		Connection conn = getConnection();
		System.out.println(conn.getClass().getName());
		close(conn);
		System.out.println("完成");
	}
}
