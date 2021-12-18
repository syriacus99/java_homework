package tool;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConnectionUtil2 {
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

	public static Map<String,Object> doSql(String sql, Object... objects) throws SQLException {
		// prepare to connect
		Map<String,Object> map = new HashMap<>();
		Connection conn = ConnectionUtil.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql);
		String upperSql = sql.toUpperCase();
		String[] colName = new String[] {"idcard","examcard","type","姓名","籍贯","分数"};
		int index = 1;
		for(Object args : objects) {
			ps.setObject(index, args);
			index++;
		}
		//selecet query
		if (upperSql.startsWith("SELECT")) {
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int num = metaData.getColumnCount();
			while (rs.next()) {
				for (int i = 1; i < num; i++) {
					//System.out.print(colName[i-1]+":"+rs.getObject(i)+" ");
					map.put(metaData.getColumnName(i), rs.getObject(i));
				}
				System.out.println("\n");
			}
		}
		else {
			int count = ps.executeUpdate();
		}
		ps.close();
		conn.close();
		return map;
	}
}
