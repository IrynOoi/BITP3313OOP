package view;

import controller.ViewBookingController;
import model.Booking;
import model.Customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchViewBooking extends JFrame {
	private JPanel contentPane;
    private JTextField bookingIDTxt;
    private ViewBookingController viewBookingController;

    public SearchViewBooking(Customer customer) {
        viewBookingController = new ViewBookingController();

        setTitle("Restore Booking");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 300);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(130, 182, 234));
        setContentPane(contentPane);
		contentPane.setLayout(null);
        
		JLabel lblSearchBooking = new JLabel ("RESTORE BOOKING");
		lblSearchBooking.setFont(new Font("Segoe Script", Font.PLAIN, 30));
		lblSearchBooking.setBounds(135, 10, 355, 54);
		contentPane.add(lblSearchBooking);

        JLabel lblBookingID = new JLabel("Enter Booking ID:");
        lblBookingID.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 20));
        lblBookingID.setBounds(150, 115, 300, 25);
        getContentPane().add(lblBookingID);

        bookingIDTxt = new JTextField();
        bookingIDTxt.setBounds(300, 115, 150, 25);
        getContentPane().add(bookingIDTxt);

        JButton btnRestore = new JButton("Restore");
        btnRestore.setBounds(320, 200, 100, 30);
        getContentPane().add(btnRestore);
        
        JButton btnBack = new JButton("Back");
        btnBack.setBounds(170, 200, 100, 30);
        getContentPane().add(btnBack);
        
        btnBack.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	CustomerMainMenu customerMenu = new CustomerMainMenu(customer);
		    	customerMenu.setVisible(true);
		    	dispose();
		    }
		});

        btnRestore.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int enteredBookingID = Integer.parseInt(bookingIDTxt.getText());
                    int customerID=customer.getCustomerID();
                    int foundBookingID = viewBookingController.searchBooking(enteredBookingID,customerID);

                    if (foundBookingID != -1) {
                        // Use ViewBookingDashboard.this or contentPane as the parent component for the dialog
                        int confirmRetrieve = JOptionPane.showConfirmDialog(SearchViewBooking.this, 
                            "Booking ID found. \n Are you sure you want to retrieve Booking " + foundBookingID+" ?", 
                            "Confirm Retrieve", 
                            JOptionPane.YES_NO_OPTION);

                        if (confirmRetrieve == JOptionPane.YES_OPTION) {
                            ViewBookingController controller = new ViewBookingController();
                            controller.restoreBooking(foundBookingID);                            
                            ViewBookingDashboard viewBooking = new ViewBookingDashboard(foundBookingID, customer);
                            viewBooking.setVisible(true);
                            dispose();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Booking ID not found.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid Booking ID format.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "An error occurred while searching.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }
}
