package me.sbahr.unblockmegenerator.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDatabase implements Database {

	private static final String DEFAULT_MYSQL_DRIVER = "com.mysql.jdbc.Driver";
	private final DatabaseCredentials credentials;
	private Connection connection = null;

	public BaseDatabase(DatabaseCredentials credentials) {
		this(credentials, DEFAULT_MYSQL_DRIVER);
	}

	public BaseDatabase(DatabaseCredentials credentials, String driver) {
		this.credentials = credentials;

		try {
			Class.forName(driver);
		}
		catch (ClassNotFoundException exc) {
			System.out.println("Fatal error, classloading of the jdbc driver failed!");
		}

		getConnection();
	}

	@Override
	public DatabaseCredentials getCredentials() {
		return credentials;
	}

	@Override
	public Connection getConnection() {
		return getConnection(true);
	}

	@Override
	public Connection getConnection(boolean openIfUnavailable) {
		String host = credentials.getHost();
		String database = credentials.getName();
		String username = credentials.getUsername();
		String password = credentials.getPassword();

		if (connection == null) {
			if (openIfUnavailable) {
				try {
					connection = DriverManager.getConnection(host + database, username, password);
					System.out.println("Connection to the database successful!");
				}
				catch (Exception exc) {
					System.out.println("Failed to create a connection to the database...");
					exc.printStackTrace();
				}
			}
		}
		else {
			try {
				/*
				 * Attempt a ping query, if the server closed it due to timeout,
				 * this will throw an exception
				 */
				connection.createStatement().execute("/* ping */ SELECT 1");
			}
			catch (Exception exc) {
				System.out.println("Connection ping failed! Attempting to reopen it...");

				try {
					connection = DriverManager.getConnection(host + database, username, password);
					System.out.println("Succesfully reopened the connection");
				}
				catch (SQLException sqlExc) {
					System.out.println("Failed to reopen the connection!");
				}
			}
		}

		return connection;

	}

	@Override
	public void close() {
		if (connection != null) {
			try {
				connection.close();
				connection = null;
			}
			catch (SQLException exc) {
				System.out.println("Error closing database connection");
			}
		}
		else {
			System.out.println("Connection already closed");
		}
	}

	/**
	 * Ensures the connection is closed in-case the user forgets to close it
	 * during cleanup.
	 */
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		close();
	}
}