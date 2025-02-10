package controller;

import Database.DatabaseConnection;
import model.Payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentController {

    public PaymentController() {}

    public Payment searchPayment(int customerID, int bookingID) throws SQLException, ClassNotFoundException {
    	Connection conn = DatabaseConnection.doConnection();
        String query = "SELECT p.Payment_ID, p.Payment_method, p.Payment_date, b.BookingID, b.totalPrice "
                     + "FROM payments p "
                     + "JOIN booking b ON p.Booking_ID = b.BookingID "
                     + "WHERE b.BookingID = ? AND b.Customer_ID = ?";

        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setInt(1, bookingID);
        preparedStatement.setInt(2, customerID);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            Payment payment = new Payment();
            payment.setPaymentID(resultSet.getInt("Payment_ID"));
            payment.setBookingID(resultSet.getInt("BookingID"));
            payment.setPaymentDate(resultSet.getString("Payment_date"));
            payment.setPaymentMethod(resultSet.getString("Payment_method"));
            payment.setTotalAmount(resultSet.getDouble("totalPrice"));
            return payment;
        }
        return null; // No payment found
    }

}
