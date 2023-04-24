package review.utilities;

import java.sql.Connection;
import java.sql.DriverManager;

public class RV_MySQLConnectUtilities {
	private static Connection getMySQLConnection(String hostName, String userName, String databaseName, String password) {
		Connection conn = null;
		try {
			String connectionUrl = "jdbc:mysql://" + hostName + ":3306/" + databaseName;
			conn = DriverManager.getConnection(connectionUrl, userName, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static Connection getMySQLConnection() {
		String hostName = "localhost";
		String userName = "root";
		String databaseName = "nhung";
		String password = "";
		return getMySQLConnection(hostName, userName, databaseName, password);
	}
}
