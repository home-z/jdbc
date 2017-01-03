package jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 传统jdbc开发，无连接池管理。每一次
 * 访问数据库时的获得和关闭连接，损耗性能
 * @author Administrator
 *
 */
public class Jdbc1 {
	public static void main(String[] args) {
		Connection conn = null;
		try {
			//1加载数据库驱动类
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("加载驱动类成功...");
			//2获得数据库连接
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8", 
					"root", "root");
			System.out.println("获得连接...");
			//3，Statemnt sql语句的发送并执行对象
			Statement state = conn.createStatement();
			String sql =  "insert into JdbcTest values(2,'xyh',6000,'PHP')";
			/**
			 * int executeUpdate(String sql);
			 *发送insert,update,delete语句
			 *返回值int表示影响数据库表的行数
			 *boolean execute(String sql):返回值为boolean类型的真或者假，用于执行给定的SQL语句。
      		 *4：ResultSet executeQuery(String sql):用于执行指定sql语句，返回值为一个ResultSet对象。
      		 *5：int executeUpdate(String sql):执行给定的insert，update，delete的sql语句，返回
      		 *一个int类型的值，1为成功0为失败
			 */
			int i = state.executeUpdate(sql);
			if (i > 0) {
				System.out.println("保存成功");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			//1、记录日志
			//2、上报：通知调用者 throw new RuntimeException(e);
			System.out.println("加载数据库驱动类异常");
			throw new RuntimeException("加载数据库驱动类异常");
		} catch (SQLException e) {
			System.out.println("数据库连接错误");
			throw new RuntimeException("数据库连接错误");
		} finally {
			if (conn != null) {
				try {
					conn.close();
					System.out.println("关闭数据库连接");
				} catch (SQLException e) {
					System.out.println("关闭连接异常");
					throw new RuntimeException("关闭连接异常");
				}
			}
		}
		System.out.println("操作成功");
	}
}
