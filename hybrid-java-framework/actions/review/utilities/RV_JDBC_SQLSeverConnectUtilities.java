package review.utilities;

import java.sql.Connection;
import java.sql.DriverManager;

public class RV_JDBC_SQLSeverConnectUtilities {
	private static Connection getJDBCSQLServerConnection(String hostName, String instanceName, String databaseName,
			String username, String pass) {
		Connection conn = null;
		try {
			String connectionUrl = "jdbc:sqlserver://" + hostName + ":1433" + ";instance=" + instanceName
					+ ";databaseName=" + databaseName;
			conn = DriverManager.getConnection(connectionUrl, username, pass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static Connection getJDBCSQLServerConnection() {
		String hostName = "localhost";
		String instanceName = "NHUNG";
		String databaseName = "master";
		String username = "sa";
		String pass = "1";
		return getJDBCSQLServerConnection(hostName, instanceName, databaseName, username, pass);
	}
}
