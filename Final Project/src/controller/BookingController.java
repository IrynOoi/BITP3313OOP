package controller;

import Database.DatabaseConnection;
import model.Booking;
import model.Customer;
import model.Flight;
import model.Seat;
import model.Baggage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BookingController {
	
	

    public BookingController() {
    	
    }
    
    // Calculate total price: seat price + add-on price
    public static double calculateTotalPrice(Booking booking) throws ClassNotFoundException, SQLException {
        double seatPrice = 0, addOnPrice = 0;
        Connection conn = DatabaseConnection.doConnection();

        int bookingID = booking.getBookingId();
        Integer baggageID = booking.getBaggage().getBaggageID();

        try {
            // Fetch Seat Price
            String seatQuery = "SELECT Price FROM seats WHERE Seat_ID = ?";
            PreparedStatement seatStmt = conn.prepareStatement(seatQuery);
            seatStmt.setInt(1, booking.getSeat().getSeat_ID());
            ResultSet rs = seatStmt.executeQuery();
            if (rs.next()) {
                seatPrice = rs.getDouble("Price");
            }
            System.out.println("Seat Price Retrieved: RM " + seatPrice);

            // Fetch Add-On Baggage Price
            if (baggageID != null) {
                String baggageQuery = "SELECT addOnPrice FROM baggage WHERE baggageID = ?";
                PreparedStatement baggageStmt = conn.prepareStatement(baggageQuery);
                baggageStmt.setInt(1, baggageID);
                ResultSet rs2 = baggageStmt.executeQuery();
                if (rs2.next()) {
                    addOnPrice = rs2.getDouble("addOnPrice");
                }
            } else {
                System.out.println("Baggage ID is NULL, setting Add-On Price to 0");
            }

            System.out.println("Baggage Price Retrieved: RM " + addOnPrice);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        double totalPrice = seatPrice + addOnPrice;
        System.out.println("Final Total Price: RM " + totalPrice);
        
        return totalPrice;
    }

    
    public static boolean insertBooking(Booking booking) throws ClassNotFoundException, SQLException {
        String query = "INSERT INTO booking (BookingID, Customer_ID, Flight_ID, Seat_ID, Booking_Date, baggageID, paymentMethod, totalPrice) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        PreparedStatement pstmt = null;
        Connection conn = null;
        
        try {
            conn = DatabaseConnection.doConnection();
            pstmt = conn.prepareStatement(query);
            
            pstmt.setInt(1, booking.getBookingId());
            pstmt.setInt(2, booking.getCustomer().getCustomerID());
            pstmt.setInt(3, booking.getFlight().getFlightId());
            pstmt.setInt(4, booking.getSeat().getSeat_ID());
            pstmt.setString(5, booking.getBooking_Date());

            // Handle baggageID: Set NULL if no baggage is selected
            if (booking.getBaggage().getBaggageID() == null) {
                pstmt.setNull(6, java.sql.Types.INTEGER);
            } else {
                pstmt.setInt(6, booking.getBaggage().getBaggageID());
            }

            pstmt.setString(7, booking.getPaymentMethod());
            pstmt.setDouble(8, booking.getTotalPrice());
            
            int rowsInserted = pstmt.executeUpdate();
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

    

    
    public int getBookingID() {
        String getBookingIdSQL = "SELECT MAX(BookingID) FROM booking";
        int newBookingId = 1; // Default Booking ID if no records exist

        try (Connection conn = DatabaseConnection.doConnection();
             Statement stmt = conn.createStatement();
             ResultSet result = stmt.executeQuery(getBookingIdSQL)) {

            if (result.next() && result.getInt(1) != 0) { 
                newBookingId = result.getInt(1) + 1; 
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return newBookingId;
    }
    



    public Integer getBaggageID(int addOnWeight, double addOnPrice) {
        if (addOnWeight == 0 && addOnPrice == 0.0) {
            return null; // Return null when no baggage is selected
        }

        Integer baggageID = null; // Use Integer to allow null values

        String sql = "SELECT baggageID FROM baggage WHERE addOnWeight = ? AND addOnPrice = ?";

        try (Connection conn = DatabaseConnection.doConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, addOnWeight);
            stmt.setDouble(2, addOnPrice);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                baggageID = rs.getInt("baggageID");
            } 
        } catch (Exception e) {
            e.printStackTrace();
        }

        return baggageID;
    }


    
    public String getAirlineName(int airlineID) {
    	String airlineName = null;
    	 String sql = "SELECT Airline_Name FROM airlines WHERE Airline_ID= ? ";

  	    try (Connection conn = DatabaseConnection.doConnection()) {
  	        if (conn != null) {
  	            PreparedStatement stmt = conn.prepareStatement(sql);
  	            stmt.setInt(1, airlineID);
  	            
  	            
  	            ResultSet rs = stmt.executeQuery();

  	            if (rs.next()) {
  	              airlineName  = rs.getString("Airline_Name"); 
  	                
  	            } else {
  	                System.out.println("No matching airline name found for the given airline id.");
  	            }
  	        }
  	    } catch (Exception e) {
  	        e.printStackTrace(); // Log the exception for debugging
  	    }

  	    return airlineName;
  	    
    }
    
    
   
}
