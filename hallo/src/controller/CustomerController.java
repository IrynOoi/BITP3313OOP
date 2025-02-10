//CustomerController.java
package controller;

import Database.DatabaseConnection;
import model.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class CustomerController 
{

	public CustomerController() {}
	
	public boolean customerLogin(Customer customer) throws ClassNotFoundException, SQLException 
	{
		Connection conn = DatabaseConnection.doConnection();
		
		String sql = "SELECT * FROM customer WHERE username = ? and password = ?";
		
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setInt(1, customer.getCustomerID());
		preparedStatement.setString(2, customer.getPassword());
		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		if (resultSet.next())
		{ 
			customer.setCustomerID(resultSet.getInt(1));
			customer.setPassword(resultSet.getString(2));
			customer.setCustomer_name(resultSet.getString(3));
			customer.setCustomer_phone(resultSet.getString(4));
			customer.setCustomer_email(resultSet.getString(5));
			conn.close();
			return true;
		}
		else
		{
			conn.close();
			return false;
		}
	}
	
	public String doLogin(Customer customer) throws ClassNotFoundException, SQLException
	{
		String ROLE ="-1";
		
		//sql statement
		String sql = "select CustomerID,password from customer where CustomerID = ? and password = ? and status = 'Active'";
		
		//1. Create connection
		Connection conn = DatabaseConnection.doConnection();
		
		//2. prepare the statement
		PreparedStatement preparedStatement = conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
		preparedStatement.setInt(1, customer.getCustomerID());
		preparedStatement.setString(2, customer.getPassword());
		
		//3. Execute the query
		ResultSet resultSet = preparedStatement.executeQuery();
		if(resultSet.next())
		{
			ROLE = resultSet.getString(1);
		}
		
		//4. close connection (MUST)
		conn.close();
		return ROLE;
	}



public Customer showProfile(Customer customer)throws ClassNotFoundException, SQLException
{
	
	//sql statement
	String sql = "select CustomerID,customer_name, username,customer_phone, customer_email,Address from customer where CustomerID = ? ";
	
	//1. Create connection
	Connection conn = DatabaseConnection.doConnection();
	//2. prepare the statement
	PreparedStatement preparedStatement = conn.prepareStatement(sql);
	preparedStatement.setInt(1, customer.getCustomerID());

	//3. Execute the query
	ResultSet resultSet = preparedStatement.executeQuery();
	if(resultSet.next())
	{
		   Customer customer1 = new Customer();
		   customer1.setCustomerID(resultSet.getInt(1));
		   customer1.setCustomer_name(resultSet.getString(2));
		   customer1.setCustomer_username(resultSet.getString(3));
		   customer1.setCustomer_phone(resultSet.getString(4));
		   customer1.setCustomer_email(resultSet.getString(5));
		   customer1.setCustomer_address(resultSet.getString(6));
		  
	
		  
		   return customer1;
	}
	
	//4. close the connection (MUST)
	conn.close();
	
	return customer;
	
}
}




