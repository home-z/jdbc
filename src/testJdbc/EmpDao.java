package testJdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import jdbc1.DBUtil3;

public class EmpDao {
	
	public JdbcTest finaEmp(int id){
		Connection conn = null;
		try {
			conn = DBUtil3.getConnection();
			//?占位符
			// 查询项只能写星号，否则报sql异常，有待挖掘
			String sql = "select * from jdbctest where empno=?";
			//使用PreparedStatement要先构造带有占位符的sql
			//conn.prepareStatement("select id from table where name=?")则使用conn.setString(1,"zzf")
			//
			PreparedStatement ps = conn.prepareStatement(sql);
			/**
			 * 对占位符进行初始化,setLong,setString不同的参数使用不同的set等
			 * PreparedStatement该接口是动态执行sql的效率高，同时可防止sql注入攻击
			 */
			ps.setInt(1, id);//其实就是在对占位符赋值，参数1代表一个sql中的第一个占位符，
			//如此类推，该方法的第二个参数即使该占位符的值
			ResultSet rs = ps.executeQuery();//执行sql
			if (rs.next()) {
				JdbcTest jt = new JdbcTest();
				jt.setEmpno(rs.getInt("empno"));
				jt.setEname(rs.getString("ename"));
				jt.setSal(rs.getInt("sal"));
				jt.setDepton(rs.getString("depton"));
				return jt;
			}
		} catch (SQLException e) {
			System.out.println("根据id查询员工失败");
			e.printStackTrace();
			throw new RuntimeException("根据id查询员工失败");
		} finally {
			if (conn != null) {
				DBUtil3.close(conn);
			}
		}
		return null;
	}
	
	public void save(JdbcTest js) {
		//TODO 写个保存员工demo
		Connection connection  = null;
		try {
			connection = DBUtil3.getConnection();
			connection.setAutoCommit(false);
			String sql = "insert into jdbctest values(?,?,?,?)";
			//PreparedStatement继承自statement
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, js.getEmpno());
			ps.setString(2, js.getEname());
			ps.setInt(3, js.getSal());
			ps.setString(4, js.getDepton());
			/**
			 * executeUpdate 的返回值是一个整数，指示受影响的行数（即更新计数）。
			 * 对于 CREATE TABLE 或 DROP TABLE 等不操作
			 * 行的语句，executeUpdate的返回值总为零
			 */
			int i = ps.executeUpdate();//TODO 空指针
			if (i > 0) {
				connection.commit();
				System.out.println("插入成功");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("数据保存异常");
			throw new RuntimeException("保存数据失败");
		} finally {
			if (connection != null) {
				DBUtil3.close(connection);
			}
		}
	}
	
	public void testUpdate(JdbcTest jt) {
		Connection conn = null;
		try {
			conn = DBUtil3.getConnection();
			PreparedStatement ps = conn.prepareStatement("update jdbctest set ename=? where" 
					+ "empno=?");
			ps.setString(1, jt.getEname());
			ps.setInt(2, jt.getEmpno());
			int i = ps.executeUpdate();
			if (i > 0) {
				System.out.println("更新数据成功");
			}
		} catch (SQLException e) {
			System.out.println("更新数据失败");
			e.printStackTrace();
			throw new RuntimeException("更新数据失败");
		} finally {
			if (conn != null) {
				DBUtil3.close(conn);
			}
		}
		
	}
	
	
	
	/**
	 * 以下方法通过jdbc模拟对数据库的操作
	 */
	@Test
	public void insert(){
		//模拟插入的数据
		Integer empno = 5;
		String ename = "欧阳3";
		Integer sal = 6060;
		String depton = "普罗米修斯";
		Date date = new Date(System.currentTimeMillis());
		
		Connection con = null;
		try {
			con = DBUtil3.getConnection();
			PreparedStatement ps = con.prepareStatement("insert into jdbctest values(?,?,?,?,?");
			ps.setInt(1, empno);
			ps.setString(2, ename);
			ps.setInt(3, sal);
			ps.setString(4, depton);
			ps.setDate(5, date);
			int i = ps.executeUpdate();
			if (i > 0) {
				System.out.println("插入成功");
			}
		} catch (SQLException e) {
			System.out.println("插入数据失败");
			e.printStackTrace();
			throw new RuntimeException("插入数据失败");
		} finally {
			if (con != null) {
				DBUtil3.close(con);
			}
		}
		
		
	}
	
}
