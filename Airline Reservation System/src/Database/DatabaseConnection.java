//To connect to database
package Database;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnection
{
	static Connection conn = null;
	public static Connection doConnection() throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver");
		
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/airline_reservation_system", "root", "");
		return conn;
	}
	
	public static void main(String [] args)
	{
		try {
			System.out.println(DatabaseConnection.doConnection());
		}catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
	}
}