package controller;

import Database.DatabaseConnection;
import model.Booking;
import model.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;


import java.sql.ResultSet;

public class CustomerController {

	
    public CustomerController() {}

    public Customer customerLogin(Customer customer) throws ClassNotFoundException, SQLException {
        Connection conn = DatabaseConnection.doConnection();
        
        // SQL query to get all attributes for the specified CustomerID
        String sql = "SELECT * FROM customer WHERE username = ? and password = ?";
        
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, customer.getCustomer_username());  
        preparedStatement.setString(2, customer.getPassword());
        
        ResultSet resultSet = preparedStatement.executeQuery();
        
        if (resultSet.next()) {
            // Populate the Customer object with retrieved data
            customer = new Customer();
            customer.setCustomerID(resultSet.getInt("Customer_ID"));
            customer.setPassword(resultSet.getString("Password"));
            customer.setCustomer_name(resultSet.getString("customer_name"));
            customer.setCustomer_phone(resultSet.getString("customer_phone"));
            customer.setCustomer_email(resultSet.getString("customer_email"));
            customer.setCustomer_address(resultSet.getString("Address"));
            customer.setGender(resultSet.getString("Cus_Gender"));
            customer.setStatus(resultSet.getString("status"));
            customer.setCustomer_username(resultSet.getString("username"));
            
            conn.close();
            return customer;
            
        }else {
        conn.close();
        return null;  // Return the customer object or null if not found
        }
    }



    // Retrieve and show the customer profile
    public Customer showProfile(Customer customer) throws ClassNotFoundException, SQLException {
        Connection conn = DatabaseConnection.doConnection();
        
        String sql = "SELECT * FROM customer WHERE Customer_ID = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, customer.getCustomerID());

        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            customer.setPassword(resultSet.getString("Password"));
            customer.setCustomer_name(resultSet.getString("customer_name"));
            customer.setCustomer_phone(resultSet.getString("customer_phone"));
            customer.setCustomer_email(resultSet.getString("customer_email"));
            customer.setCustomer_address(resultSet.getString("Address"));
            customer.setGender(resultSet.getString("Cus_Gender"));
            customer.setStatus(resultSet.getString("status"));
            customer.setCustomer_username(resultSet.getString("username"));
            
        }
        conn.close();
        return customer;
    }
    
    public boolean UpdateRegistration(Customer customer) throws ClassNotFoundException, SQLException {
        String getDatabaseId_sql = "SELECT MAX(Customer_ID) FROM customer";
        String register_sql = "INSERT INTO customer (Customer_ID, Cus_Gender, Password, customer_name, Address, customer_email, customer_phone, status, username) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        PreparedStatement pstmt = null;
        Statement stmt = null;
        Connection conn = null;
        
        try {
            conn = DatabaseConnection.doConnection();
            
            stmt = conn.createStatement(); // Initialize the statement here
            int newCustomerId = 1;
            ResultSet result = stmt.executeQuery(getDatabaseId_sql);
            if (result.next()) {
                newCustomerId = result.getInt(1) + 1;
            }

            pstmt = conn.prepareStatement(register_sql);
            pstmt.setInt(1, newCustomerId);
            pstmt.setString(2, customer.getGender());
            pstmt.setString(3, customer.getPassword());
            pstmt.setString(4, customer.getCustomer_name());
            pstmt.setString(5, customer.getCustomer_address());
            pstmt.setString(6, customer.getCustomer_email());
            pstmt.setString(7, customer.getCustomer_phone());
            pstmt.setString(8, customer.getStatus());
            pstmt.setString(9, customer.getCustomer_username());

            // Print the SQL statement for debugging
            //System.out.println("Executing SQL: " + register_sql);
            
            int rowsInserted = pstmt.executeUpdate();
            
            // Check how many rows were inserted for debugging
            //System.out.println("Rows inserted: " + rowsInserted);
            
            return rowsInserted > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public boolean updateCustomerProfile(Customer customer) throws ClassNotFoundException, SQLException {
        String update_sql = "UPDATE customer SET Cus_Gender = ?, Password = ?, customer_name = ?, Address = ?, customer_email = ?, customer_phone = ?, username = ? WHERE Customer_ID = ?";
        
        Connection conn = DatabaseConnection.doConnection();
        PreparedStatement pstmt = conn.prepareStatement(update_sql);

        try {
            conn = DatabaseConnection.doConnection();
            pstmt = conn.prepareStatement(update_sql);
            pstmt.setString(1, customer.getGender());
            pstmt.setString(2, customer.getPassword());
            pstmt.setString(3, customer.getCustomer_name());
            pstmt.setString(4, customer.getCustomer_address());
            pstmt.setString(5, customer.getCustomer_email());
            pstmt.setString(6, customer.getCustomer_phone());
            pstmt.setString(7, customer.getCustomer_username());
            pstmt.setInt(8, customer.getCustomerID());

            int rowsUpdated = pstmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
   
}
