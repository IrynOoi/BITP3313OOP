package controller;

import Database.DatabaseConnection;
import model.Seat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SeatController {
    public SeatController() {}

    // Check if a seat is occupied
    public boolean isSeatOccupied(String seatNo, int flightId) throws SQLException, ClassNotFoundException {
        int seatId = getSeatId(seatNo, flightId);  // Using seatNo and flightId to get Seat_ID
        if (seatId == -1) {
            return false;  // Seat ID not found
        }

        // Query to check if the seat is occupied
        String query = "SELECT COUNT(*) FROM booking b JOIN seats s ON b.seat_id = s.seat_id "
                     + "WHERE b.flight_id = ? AND b.seat_id = ? ";
        
        try (Connection conn = DatabaseConnection.doConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, flightId);
            stmt.setInt(2, seatId); 
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return rs.getInt(1) > 0; // If count > 0, the seat is occupied
        }
    }

    // Get Seat ID from Seat Number and Flight ID
    public int getSeatId(String seatNo, int flightId) throws ClassNotFoundException {
        int seatId = -1;
        try (Connection conn = DatabaseConnection.doConnection()) {
            String query = "SELECT Seat_ID FROM seats WHERE Seat_No = ? AND Flight_ID = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, seatNo);
            stmt.setInt(2, flightId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                seatId = rs.getInt("Seat_ID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seatId;
    }
    
    
    public Seat seatDetails(Seat seat) throws SQLException, ClassNotFoundException{
    	String Seat_No = null;
    	String Seat_Class = null;
    	String Seat_Status = null;
    	double Price = 0;
    	
    	String sql = "SELECT Seat_No, Seat_Class, Price FROM seats WHERE Seat_ID = ?";
    	Connection conn = DatabaseConnection.doConnection();
        if (conn != null) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, seat.getSeat_ID());
            
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Seat_No = rs.getString("Seat_No");
                Seat_Class = rs.getString("Seat_Class");
                Price = rs.getDouble("Price");
            } 
            
            seat.setSeat_No(Seat_No);
            seat.setSeat_Class(Seat_Class);
            seat.setPrice(Price);
            
            conn.close();
    		return seat;
          
        }
        else {
        	conn.close();
        	return null;
        }
    }
}