package review.utilities;

import java.sql.Connection;
import java.sql.DriverManager;

public class RV_JTDS_SQLSeverConnectUtilities {
	private static Connection getSQLServerConnection(String hostName, String instanceName, String databseName,
			String userName, String pass) {
		Connection conn = null;
		try {
			String connectionUrl = "jdbc:jtds:sqlserver://" + hostName + ":1433/" + databseName + ";instance="
					+ instanceName;
			conn = DriverManager.getConnection(connectionUrl, userName, pass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static Connection getSQLServerConnection() {
		String hostName = "localhost";
		String instanceName = "NHUNG";
		String databseName = "master";
		String userName = "sa";
		String pass = "1";
		return getSQLServerConnection(hostName, instanceName, databseName, userName, pass);
	}
}
