package log;
/**
 *�ڵ�¼����ʵ�����ݿ������
 * 
 * @author
 * 
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectMySql {
	Connection con = null;
	public void connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // ����JDBC_MySQL����
		} catch (Exception e) {
			System.out.println("Error0");
		}
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/log?useSSL=false&serverTimezone=UTC", "root", "liao20040805"); // ���Ӵ���
		} catch (SQLException e) {
			System.out.println("Database connection failed");
		}
	}
}
