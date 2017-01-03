package testJdbc;

import org.junit.Test;

public class TestClassEmp {
	@Test
	public void findById(){
		//TODO 问题可能在dao的sql写错了
		EmpDao dao = new EmpDao();
		JdbcTest jt = dao.finaEmp(2);
		if (jt != null) {
			System.out.println(jt.getEname()+"|"+jt.getDepton()+"|"+jt.getSal());
			
		}
	}
	
	@Test
	public void testInsert() {
		//TODO 空指针
		JdbcTest jdbcTest = new JdbcTest();
		EmpDao ed = new EmpDao();
		jdbcTest.setEmpno(8);
		jdbcTest.setEname("老鹰乐队");
		jdbcTest.setSal(6066);
		jdbcTest.setDepton("房客");
		ed.save(jdbcTest);
		System.out.println("保存完成");
	}
	
	@Test
	public void updateTest() {
		JdbcTest jt = new JdbcTest();
		EmpDao dao = new EmpDao();
		jt.setEmpno(2);
		jt.setEname("zzf");
		dao.testUpdate(jt);
	}
}
