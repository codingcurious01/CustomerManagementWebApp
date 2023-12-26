package in.bmart.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyDbConnection 
{
	private static Connection cn = null;
	
	private static final String dbUrl = "jdbc:mysql://localhost:3306/j2ee";
	private static final String dbUsername = "root";
	private static final String dbPassword = "root";
	
	public static Connection myConnection() 
	{
		// Establish the DB connection and Load Driver class
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			cn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return cn;
	}
}
