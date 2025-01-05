package Database;
import java.sql.*;

public class DatabaseConnection 
{
	String driver;
	String dbName;
	String connectionURL;
	String username;
	String password;
	
	public DatabaseConnection()
	{
		driver = "com.mysql.cj.jdbc.Driver";
		connectionURL = "jdbc:mysql://localhost:3306/";
		dbName = "airlinebookingsystem"; //your database name
		username = "root";
		password = "";
	}
	
	public Connection getConnection() throws Exception
	{
		Class.forName(driver);
		Connection connection = DriverManager.getConnection(connectionURL+dbName, username, password);
		return connection;
	}
	
	public static void main(String [] args)
	{
		DatabaseConnection db = new DatabaseConnection(); //(database java filename) db = new (database java filename) 
		
		try {
			//Create a connection to the database
			Connection conn = db.getConnection();
			System.out.println("Database successfully connected!\n");
			
			//Close the connection
			conn.close();
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}	
	}

}
