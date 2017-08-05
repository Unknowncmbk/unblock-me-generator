package io.isles.puzzlecratesgenerator.database;

/**
 * Immutable object containing the credentials for a database.
 */
public class DatabaseCredentials {

	private final String host;
	private final String name;
	private final String username;
	private final String password;

	/**
	 * Constructs a new DatabaseCredentials object.
	 * 
	 * @param host - The host of the database (e.g,
	 *            jdbc:mysql://mysql.mywebsite.com:3902)
	 * @param name - Name of the database (e.g, coredatabase)
	 * @param username - User-name used for logging into the database (e.g,
	 *            root)
	 * @param password - Password used for logging into the datasbe (e.g,
	 *            jeo97324ljax)
	 */
	public DatabaseCredentials(String host, String name, String username, String password) {
		this.host = host;
		this.name = name;
		this.username = username;
		this.password = password;
	}

	/**
	 * Get the database's host.
	 * 
	 * @return Database host.
	 */
	public final String getHost() {
		return host;
	}

	/**
	 * Get the database's name.
	 * 
	 * @return Name of the database.
	 */
	public final String getName() {
		return name;
	}

	/**
	 * Get the user-name used in authentication.
	 * 
	 * @return User-name used in authentication.
	 */
	public final String getUsername() {
		return username;
	}

	/**
	 * Get the password used in authentication.
	 * 
	 * @return Password used in authentication.
	 */
	public final String getPassword() {
		return password;
	}

}
