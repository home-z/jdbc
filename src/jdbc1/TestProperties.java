package jdbc1;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
/**
 * 演示：Properties工具类读取属性文件
 */
public class TestProperties {
	public static void main(String[] args) throws IOException {
		Properties pro = new Properties();
		//获取指向类路径中属性文件的输入流
		InputStream is = TestProperties.class.getClassLoader().getResourceAsStream("db.properties");
		//加载属性文件数据到内存中
		pro.load(is);
		//根据key得到value
		String url = pro.getProperty("jdbc.url");
		System.out.println(url);
	}
}
