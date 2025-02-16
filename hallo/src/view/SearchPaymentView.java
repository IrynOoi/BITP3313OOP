package view;

import controller.PaymentController;
import model.Payment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchPaymentView extends JFrame {
    private JTextField bookingIDTxt;

    public SearchPaymentView(int customerID) {
        setTitle("Search Payment");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 691, 345);
        JPanel contentPane = new JPanel();
        contentPane.setBackground(new Color(130, 182, 234));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lblTitle = new JLabel("Search Payment");
        lblTitle.setForeground(new Color(255, 255, 255));
        lblTitle.setFont(new Font("Segoe Script", Font.BOLD, 30));
        lblTitle.setBounds(215, 54, 274, 39);
        contentPane.add(lblTitle);

        JLabel lblBookingID = new JLabel("Booking ID:");
        lblBookingID.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 20));
        lblBookingID.setBounds(162, 123, 100, 25);
        contentPane.add(lblBookingID);

        bookingIDTxt = new JTextField();
        bookingIDTxt.setBounds(316, 125, 200, 25);
        contentPane.add(bookingIDTxt);

        JButton btnSearch = new JButton("Search");
        btnSearch.setFont(new Font("Tw Cen MT", Font.BOLD, 15));
        btnSearch.setBounds(383, 191, 100, 30);
		btnSearch.addActionListener(e -> searchPayment(customerID));
        contentPane.add(btnSearch);
        
        JButton btnMainMenu = new JButton("Main Menu");
        btnMainMenu.setFont(new Font("Tw Cen MT", Font.BOLD, 15));
        btnMainMenu.setBounds(196, 191, 119, 30);
        btnMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerMainMenu frame = new CustomerMainMenu(customerID);
                frame.setVisible(true);
                dispose();
			}
		});
        contentPane.add(btnMainMenu);
    }

    private void searchPayment(int customerID) {
        String bookingID = bookingIDTxt.getText();

        if (bookingID.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Booking ID cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        PaymentController controller = new PaymentController();
        try {
            Payment payment = controller.searchPayment(customerID, Integer.parseInt(bookingID));
            if (payment != null) {
                new ViewPaymentView(payment,customerID).setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "No payment found!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
