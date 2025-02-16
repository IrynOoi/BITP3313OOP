//FlightBookingController.java
package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Database.DatabaseConnection;
import model.Airline;
import model.Booking;
import model.Customer;
import model.Flight;

public class FlightBookingControl
{
	

	public List<String> fetchFlightTimes(String departure, String arrival, String date) {
	    List<String> flightTimes = new ArrayList<>();
	    try (Connection conn = DatabaseConnection.doConnection()) {
	        if (conn != null) {
	            // Correct query to match only the date portion
	            String query = "SELECT Departure_Time, Arrival_Time " +
	                           "FROM flight " +
	                           "WHERE Departure_airport = ? AND Arrival_airport = ? AND DATE(Departure_Time) = ?";
	            PreparedStatement stmt = conn.prepareStatement(query);
	            stmt.setString(1, departure);
	            stmt.setString(2, arrival);
	            stmt.setString(3, date); // Pass the date directly as YYYY-MM-DD

	            ResultSet rs = stmt.executeQuery();

	            if (!rs.isBeforeFirst()) {
	                // No data found
	                flightTimes.add("No flights available for the selected route and date.");
	            } else {
	                while (rs.next()) {
	                    String departureTime = rs.getString("Departure_Time");
	                    String arrivalTime = rs.getString("Arrival_Time");

	                    // Add formatted flight times to the list
	                    flightTimes.add("Departs: " + departureTime + ", Arrives: " + arrivalTime);
	                }
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return flightTimes;
	}
	
	public int flightID(String departure, String arrival, String date, String time) {
	    int flightID = -1; // Default value indicating no flight found

	    String sql = "SELECT Flight_ID FROM flight WHERE Departure_airport = ? AND Arrival_airport = ? AND DATE(Departure_Time) = ? AND TIME(Departure_Time) = ?";

	    try (Connection conn = DatabaseConnection.doConnection()) {
	        if (conn != null) {
	            PreparedStatement stmt = conn.prepareStatement(sql);
	            stmt.setString(1, departure);
	            stmt.setString(2, arrival);
	            stmt.setString(3, date);
	            stmt.setString(4, time);

	            ResultSet rs = stmt.executeQuery();

	            if (rs.next()) {
	                flightID = rs.getInt("Flight_ID"); // Retrieve Flight_ID
	            } else {
	            	System.out.println(departure + " " + arrival + " " + date + " " + time);
	                System.out.println("No matching flight found for the given criteria.");
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace(); // Log the exception for debugging
	    }

	    return flightID;
	}

	public Flight flightInformation(Flight flight) throws SQLException, ClassNotFoundException
	{
	
		String flightName = null;
		int AirlineID = 0;
		String Destination = null;
		String DepartureTime = null;
		String ArrivalTime = null;
		String DepartureAirport = null;
		String ArrivalAirport = null;
		int defaultBaggage = 0;
		
		String sql = "SELECT Flight_Name, Airline_ID, Destination, Departure_Time,Arrival_Time, Departure_airport, Arrival_airport, defaultBaggage FROM flight WHERE Flight_ID = ?";
		Connection conn = DatabaseConnection.doConnection();
	        if (conn != null) {
	            PreparedStatement stmt = conn.prepareStatement(sql);
	            stmt.setInt(1, flight.getFlightId());

	            ResultSet rs = stmt.executeQuery();

	            if (rs.next()) {
	                flightName = rs.getString("Flight_Name");
	                AirlineID = rs.getInt("Airline_ID");
	                Destination = rs.getString("Destination");
	                DepartureTime = rs.getString("Departure_Time");
	                ArrivalTime = rs.getString("Arrival_Time");
	                DepartureAirport = rs.getString("Departure_airport");
	                ArrivalAirport = rs.getString("Arrival_airport");
	                defaultBaggage = rs.getInt("defaultBaggage");
	            } 
	            
	            flight.setFlightName(flightName);
	            Airline airline=new Airline();
	            airline.setAirline_ID(AirlineID);
	            flight.setAirline(airline);
	            flight.setDestination(Destination);
	            flight.setDepartureTime(DepartureTime);
	            flight.setArrivalTime(ArrivalTime);
	            flight.setDepartureAirport(DepartureAirport);
	            flight.setArrivalAirport(ArrivalAirport);
	            flight.setDefaultBaggage(defaultBaggage);
	            
	            System.out.println(flight.getArrivalTime());
	            conn.close();
	    		return flight;
	          
	        }
	        else {
	        	conn.close();
	        	return null;
	        }
	    
	}
}
