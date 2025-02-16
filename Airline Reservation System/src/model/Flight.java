package model;

public class Flight {
	private int flightId;
	private Airline airline;
	private String flightName;
	private String Destination;
	private String DepartureTime;
	private String ArrivalTime;
	private String DepartureAirport;
	private String ArrivalAirport;
	private int defaultBaggage;
	
	public Flight() {
		// TODO Auto-generated constructor stub
	}

	public Flight(String destination, String departureTime,
			String arrivalTime, String departureAirport, String arrivalAirport, int defaultBaggage, Airline airline) {
		Destination = destination;
		DepartureTime = departureTime;
		ArrivalTime = arrivalTime;
		DepartureAirport = departureAirport;
		ArrivalAirport = arrivalAirport;
		this.defaultBaggage = defaultBaggage;
		this.airline=airline;
	}

	public int getFlightId() {
		return flightId;
	}
 
	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}

	

	public Airline getAirline() {
		return airline;
	}

	public void setAirline(Airline airline) {
		this.airline = airline;
	}

	public String getFlightName() {
		return flightName;
	}

	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}

	public String getDestination() {
		return Destination;
	}

	public void setDestination(String destination) {
		Destination = destination;
	}

	public String getDepartureTime() {
		return DepartureTime;
	}

	public void setDepartureTime(String departureTime) {
		DepartureTime = departureTime;
	}

	public String getArrivalTime() {
		return ArrivalTime;
	}

	public void setArrivalTime(String arrivalTime) {
		ArrivalTime = arrivalTime;
	}

	public String getDepartureAirport() {
		return DepartureAirport;
	}

	public void setDepartureAirport(String departureAirport) {
		DepartureAirport = departureAirport;
	}

	public String getArrivalAirport() {
		return ArrivalAirport;
	}

	public void setArrivalAirport(String arrivalAirport) {
		ArrivalAirport = arrivalAirport;
	}

	public int getDefaultBaggage() {
		return defaultBaggage;
	}

	public void setDefaultBaggage(int defaultBaggage) {
		this.defaultBaggage = defaultBaggage;
	}
}
