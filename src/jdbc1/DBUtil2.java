package jdbc1;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class DBUtil2 {
	private static String driverclass;
	private static String url;
	private static String user;
	private static String password;
	
	static {
		Properties prope = new Properties();
		try {
			//加载数据属性文件
			//报空指针异常原因 db.properties文件没有放在项目的src目录下，程序找不到
			prope.load(DBUtil2.class.getClassLoader().getResourceAsStream("db.properties"));
			//properties格式的文件以键值对的形式在文件中取值 key:value  将它们看作map
			driverclass = prope.getProperty("jdbc.driver");
			url = prope.getProperty("jdbc.url");
			user = prope.getProperty("jdbc.user");
			password = prope.getProperty("jdbc.password");
			Class.forName(driverclass);
			System.out.println("加载数据库驱动成功...");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("加载数据库驱动类异常");
		} catch (IOException e) {
			throw new RuntimeException("读取数据库配置属性文件异常");
		}
	}

	public static Connection getConnection(){
		Connection conn;
		try {
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("成功获得连接...");
		} catch (SQLException e) {
			throw new RuntimeException("获取连接异常");
		}
		return conn;
	}
	
	public static void close(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new RuntimeException("关闭连接错误");
			}
		}
	}
	
	public static void main(String[] args) {
		Connection connection  = getConnection();
		System.out.println(connection.getClass().getName());
		close(connection);
	}
}
