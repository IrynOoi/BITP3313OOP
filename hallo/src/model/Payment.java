package model;

public class Payment {
    private int paymentID;
    private int bookingID; // Changed from Booking to int
    private String paymentDate;
    private String paymentMethod;
    private double totalAmount;

    public Payment() {}

    public Payment(int paymentID, String paymentMethod, String paymentDate, double totalAmount, int bookingID) {
        this.paymentID = paymentID;
        this.paymentMethod = paymentMethod;
        this.paymentDate = paymentDate;
        this.totalAmount = totalAmount;
        this.bookingID = bookingID;
    }

    // Getters and Setters
    public int getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    public int getBookingID() {
        return bookingID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
