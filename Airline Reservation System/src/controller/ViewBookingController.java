package controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Database.DatabaseConnection;
import model.Booking;
import model.Customer;
import model.Flight;
import model.Seat;
import model.Airline;
import model.Baggage;

public class ViewBookingController {

	//search deleted booking
		public int searchBooking(int bookingID,int customerID) throws SQLException, ClassNotFoundException {
		    String query = "SELECT BookingID FROM booking WHERE deleted_at IS NOT NULL AND BookingID = ? AND Customer_ID = ? "; 

		    try (Connection conn = DatabaseConnection.doConnection();
		         PreparedStatement stmt = conn.prepareStatement(query)) {
		        
		        stmt.setInt(1, bookingID);
		        stmt.setInt(2, customerID); 

		        ResultSet rs = stmt.executeQuery();
		        
		        if (rs.next()) {  
		            int foundID = rs.getInt("BookingID");
		            return foundID; // Return the found ID
		        } else {
		            return -1; // Indicate that the booking was not found
		        }
		    }
		}
		
		//search not deleted bookings
	public List<Integer> searchAllBooking(int customerID) throws SQLException, ClassNotFoundException {
	    String query = "SELECT BookingID FROM booking WHERE deleted_at IS NULL AND Customer_ID = ?"; 
	    List<Integer> bookingIDs = new ArrayList<>();

	    try (Connection conn = DatabaseConnection.doConnection();
	         PreparedStatement stmt = conn.prepareStatement(query)) {
	        
	        stmt.setInt(1, customerID);
	        ResultSet rs = stmt.executeQuery();
	        
	        while (rs.next()) {  
	            bookingIDs.add(rs.getInt("BookingID")); // Add each BookingID to the list
	        }
	    }
	    
	    return bookingIDs; // Return the list of BookingIDs
	}


    // Fetch complete booking details
    public Booking getBookingDetails(int bookingID) throws ClassNotFoundException {
        Booking booking = null;
        String airlineName=null;
     
        try (Connection con = DatabaseConnection.doConnection()) {
            // Fixed SQL Query 
            String query = "SELECT b.BookingID, b.Booking_Date, c.customer_name, f.Destination, f.Departure_Time, "
                    + "f.Arrival_Time, a.Airline_Name, s.Seat_No, s.Seat_Class, s.Price, "
                    + "IFNULL(bg.addOnWeight, 0) AS addOnWeight, b.totalPrice, "
                    + "f.Departure_Airport, f.Arrival_Airport, f.defaultBaggage, bg.baggageID "
                    + "FROM booking b "
                    + "JOIN customer c ON b.Customer_ID = c.Customer_ID "
                    + "JOIN flight f ON b.Flight_ID = f.Flight_ID "
                    + "JOIN seats s ON b.Seat_ID = s.Seat_ID "
                    + "JOIN airlines a ON f.Airline_ID = a.Airline_ID "
                    + "LEFT JOIN baggage bg ON b.baggageID = bg.baggageID "
                    + "WHERE b.BookingID = ?";
            
            try (PreparedStatement stmt = con.prepareStatement(query)) {
                stmt.setInt(1, bookingID);
                ResultSet rs = stmt.executeQuery();
                
                if (rs.next()) {
                    // Creating related objects
                    Customer customer = new Customer(rs.getString("customer_name"));
                    Airline airline = new Airline(
                    	rs.getString("Airline_Name")
                    		);
                    
                    Flight flight = new Flight(
                        rs.getString("Destination"), 
                        rs.getString("Departure_Time"), 
                        rs.getString("Arrival_Time"),
                        rs.getString("Departure_Airport"), 
                        rs.getString("Arrival_Airport"),
                        rs.getInt("defaultBaggage"),
                       airline
                    );
                    Seat seat = new Seat(
                        rs.getString("Seat_No"), 
                        rs.getString("Seat_Class"), 
                        rs.getDouble("Price")
                    );
                    Baggage baggage = new Baggage(
                        rs.getInt("baggageID"), 
                        rs.getInt("addOnWeight")
                    );
                    
                   airlineName=rs.getString("Airline_Name");
                    
                    // Construct the Booking object
                    booking = new Booking(
                        rs.getInt("BookingID"), 
                        rs.getString("Booking_Date"), 
                        customer, flight, seat, 
                        baggage, rs.getDouble("totalPrice")
                    );
                }
                booking.getFlight().getAirline().setAirline_Name(airlineName); 
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return booking;
    }
    
    public void softDeleteBooking(int bookingID) throws SQLException, ClassNotFoundException {
        String query = "UPDATE booking SET deleted_at = NOW() WHERE BookingID = ?";
        try (Connection con = DatabaseConnection.doConnection()) {
        	PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, bookingID);
            stmt.executeUpdate();
        }
    }
    
    public void restoreBooking(int bookingID) throws SQLException, ClassNotFoundException {
        String query = "UPDATE Booking SET deleted_at = NULL WHERE BookingID = ?";
        try (Connection con = DatabaseConnection.doConnection()) {
        	PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, bookingID);
            stmt.executeUpdate();
        }
    }

}