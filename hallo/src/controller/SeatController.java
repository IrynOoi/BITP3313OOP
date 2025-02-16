// SeatController.java
package controller;

import Database.DatabaseConnection;
import model.Seat;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class SeatController {
    public SeatController() {}

    // Fetch seat details from the database
    public boolean isSeatAvailable(String seatNo) throws ClassNotFoundException, SQLException {
        // Establish connection
        Connection conn = DatabaseConnection.doConnection();

        
        // SQL query to check the seat status
        String sql = "SELECT Seat_Status FROM seats WHERE Seat_No = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, seatNo); // Set the seat number in the query

        // Execute the query
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            String seatStatus = resultSet.getString("Seat_Status");
            
            // Check if the seat is available
            conn.close();
            return "Available".equalsIgnoreCase(seatStatus); // Returns true if status is 'Available'
        }

        conn.close();
        return false; // Return false if the seat is not found
    }

    // Optional: Reserve a seat
    public boolean reserveSeat(String seatNo) throws ClassNotFoundException, SQLException {
        // Establish connection
        Connection conn = DatabaseConnection.doConnection();

        // SQL query to update the seat status
        String sql = "UPDATE seats SET Seat_Status = 'Occupied' WHERE Seat_No = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, seatNo);

        // Execute the update
        int rowsUpdated = preparedStatement.executeUpdate();
        conn.close();

        // Return true if a row was updated, meaning the seat was successfully reserved
        return rowsUpdated > 0;
    }
}
