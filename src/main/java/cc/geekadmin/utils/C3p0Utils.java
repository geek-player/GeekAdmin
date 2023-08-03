package cc.geekadmin.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class C3p0Utils {
	private static ComboPooledDataSource dataSource = null;

	private static ThreadLocal<Connection> connectionThreadLocal = new ThreadLocal<>();

	static {
		dataSource = new ComboPooledDataSource();
	}

	public static DataSource getDataSource() {
		return dataSource;
	}

	public static Connection getConnection() {
		Connection connection = connectionThreadLocal.get();
		try {
			if (connection == null||connection.isClosed()) {

				connection = dataSource.getConnection();
					connectionThreadLocal.set(connection);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	public static Connection getTranConnection() throws SQLException {
		// 得到ThreadLocal中的connection
		Connection connection = connectionThreadLocal.get();
		if (connection == null) {
			connection = getConnection();
			connectionThreadLocal.set(connection);
		}
		return connection;
	}

	public static void beginTran() throws SQLException {
		getTranConnection().setAutoCommit(false);
	}

	public static void commit() throws SQLException {
		Connection conn = getTranConnection();
		if (conn != null) {
			conn.commit();
			conn.close();
			connectionThreadLocal.remove();
		}
	}

	public static void rollback() throws SQLException {
		Connection conn = getTranConnection();
		if (conn != null) {
			conn.rollback();
			conn.close();
			connectionThreadLocal.remove();
		}
	}

	public static void close(ResultSet resultSet, Statement statement, Connection connection) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
