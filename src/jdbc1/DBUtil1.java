package jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBUtil1 {
	//数据库连接信息
	private static String driverClass = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost:3306/chinaap?useUnicode=true&characterEncoding=UTF8";
	private static String user = "root";
	private static String password = "root";
	
	//加载驱动
	static {
		try {
			Class.forName(driverClass);
			System.out.println("数据库驱动加载成功....");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("驱动类加载异常",e);
		}
	}
	
	//创建连接
	public static Connection getConnection(){
		Connection conn;
		try {
			conn = DriverManager.getConnection(url, user, password);
			System.err.println("获得连接...");
		} catch (SQLException e) {
			throw new RuntimeException("获取连接异常");
		}
		return conn;		
	}
	
	//关闭连接
	public static void close(Connection conn){
		if (conn != null) {
			try {
				conn.close();
				System.out.println("关闭连接成功");
			} catch (SQLException e) {
				throw new RuntimeException("关闭连接失败");
			}
		}
	}
	
	//测试
	public static void main(String[] args) {
		System.out.println("连接:"+getConnection());
	}
}
