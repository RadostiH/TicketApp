package utils;

import java.sql.*;

public class DatabaseConnector {
	private final String database = "jdbc:postgresql://localhost:3306/cats";
	private final String username = "postgres";
	private final String password = "1234";
	
	public Connection sqlConnection() {
		Connection con = null;
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(database, username, password);
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return con;
	}
}


