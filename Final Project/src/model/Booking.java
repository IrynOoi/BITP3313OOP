package model;

public class Booking {
    private int bookingId;
    private String bookingDate;
    private Customer customer;
    private Flight flight;
    private Seat seat;
    private Baggage baggage;
    private double totalPrice;
    private String paymentMethod;
    private Airline airline; // Instead of airlineName, store Airline object

    public Booking() {
        // Default constructor
    }

    public Booking(int bookingID, String bookingDate, Customer customer, Flight flight, Seat seat, 
                   Baggage baggage, double totalPrice, Airline airline) {  // Use Airline object
        this.bookingId = bookingID;
        this.bookingDate = bookingDate;
        this.customer = customer;
        this.flight = flight;
        this.seat = seat;
        this.baggage = baggage;
        this.totalPrice = totalPrice;
        this.airline = airline;  // Store the airline object
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
        return bookingDate;
    }

    public void setBooking_Date(String bookingDate) {
        this.bookingDate = bookingDate;
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

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }
}
