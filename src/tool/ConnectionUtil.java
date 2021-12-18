package tool;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
	public static Connection getConnection() {
		Properties prop = new Properties();// 文件转为流

		InputStream is = ConnectionUtil.class.getClassLoader().getResourceAsStream("db.properties");

		try {
			prop.load(is);// 读入文件properties
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url = prop.getProperty("url");
		String user = prop.getProperty("user");
		String pass = prop.getProperty("pass");
		String driver = prop.getProperty("driver");

		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(url, user, pass);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return connection;
	}

	public static void doSql(String sql, Object... objects) throws SQLException {
		// prepare to connect
		Connection conn = ConnectionUtil.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql);
		String upperSql = sql.toUpperCase();
		int index = 0;
		for(Object args : objects) {
			ps.setObject(index, args);
			index++;
		}
		//selecet query
		if (upperSql.startsWith("SELECT")) {
			ResultSet rs = ps.getResultSet();
			ResultSetMetaData metaData = rs.getMetaData();
			int num = metaData.getColumnCount();
			ps.executeQuery();
			while (rs.next()) {
				for (int i = 1; i < num; i++) {
					System.out.println(rs.getObject(i));
				}
			}
		}
		else {
			int count = ps.executeUpdate();
		}
		ps.close();
		conn.close();
	}
}
