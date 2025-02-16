package model;

public class Booking {
	private int bookingId;
	private String Booking_Date;
	private Customer customer;
	private Flight flight;
	private Seat seat;
	private Baggage baggage;
	private double totalPrice;
	private String paymentMethod;
	
	public Booking() {
		// TODO Auto-generated constructor stub
	}

	public Booking(int bookingID, String bookingDate, Customer customer, Flight flight, Seat seat, Baggage baggage, double totalPrice) {
        this.bookingId = bookingID;
        this.Booking_Date = bookingDate;
        this.customer = customer;
        this.flight = flight;
        this.seat = seat;
        this.baggage = baggage;
        this.totalPrice = totalPrice;
    }
	
	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public Seat getSeat() {
		return seat;
	}

	public void setSeat(Seat seat) {
		this.seat = seat;
	}

	public Baggage getBaggage() {
		return baggage;
	}

	public void setBaggage(Baggage baggage) {
		this.baggage = baggage;
	}
	
	public String getBooking_Date() {
		return Booking_Date;
	}

	public void setBooking_Date(String booking_Date) {
		Booking_Date = booking_Date;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	

	
	
	
}
