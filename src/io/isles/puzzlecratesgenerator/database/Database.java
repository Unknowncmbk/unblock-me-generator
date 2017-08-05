package io.isles.puzzlecratesgenerator.database;

import java.sql.Connection;

/**
 * Representation of a basic SQL database.
 */
public interface Database {

	/**
	 * Gets the connection and by default opens the connection if it's
	 * unavailable.
	 * 
	 * @return Connection to the database.
	 */
	Connection getConnection();

	/**
	 * Gets the connection.
	 * 
	 * @param openIfUnavailable - Whether or not to open the connection to the
	 *            database if it's currently closed.
	 * @return Connection to the database.
	 */
	Connection getConnection(boolean openIfUnavailable);

	/**
	 * Get the database's credentials.
	 * 
	 * @return DatabaseCredentials attached to this database object.
	 */
	DatabaseCredentials getCredentials();

	/**
	 * Close the connection to the database.
	 */
	void close();

}