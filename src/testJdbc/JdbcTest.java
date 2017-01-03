package testJdbc;

public class JdbcTest {
	private Integer empno;
	private String ename;
	private Integer sal;
	private String depton;
	public Integer getEmpno() {
		return empno;
	}
	public void setEmpno(Integer empno) {
		this.empno = empno;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public Integer getSal() {
		return sal;
	}
	public void setSal(Integer sal) {
		this.sal = sal;
	}
	public String getDepton() {
		return depton;
	}
	public void setDepton(String depton) {
		this.depton = depton;
	}
	
	
	public JdbcTest() {
		
	}
	public JdbcTest(Integer empno, String ename, Integer sal, String depton) {
		super();
		this.empno = empno;
		this.ename = ename;
		this.sal = sal;
		this.depton = depton;
	}
	
}
