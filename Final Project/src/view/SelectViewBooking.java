package view;

import controller.ViewBookingController;
import model.Customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class SelectViewBooking extends JFrame {
    private JPanel contentPane;
    private JList<Integer> bookingList; // List to display Booking IDs
    private DefaultListModel<Integer> listModel; // Model to hold booking IDs
    private ViewBookingController viewBookingController;

    public SelectViewBooking(Customer customer) {
        viewBookingController = new ViewBookingController();

        setTitle("View Booking");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(653, 339);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(130, 182, 234));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblSearchBooking = new JLabel("VIEW BOOKING");
        lblSearchBooking.setFont(new Font("Segoe Script", Font.PLAIN, 30));
        lblSearchBooking.setBounds(202, 10, 259, 54);
        contentPane.add(lblSearchBooking);

        JLabel lblBookings = new JLabel("Select Booking You Want To View");
        lblBookings.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 20));
        lblBookings.setBounds(191, 73, 279, 25);
        contentPane.add(lblBookings);

        // Create List Model
        listModel = new DefaultListModel<>();
        bookingList = new JList<>(listModel);
        bookingList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(bookingList);
        scrollPane.setBounds(49, 108, 518, 101);
        contentPane.add(scrollPane);

        JButton btnView = new JButton("View Booking");
        btnView.setBounds(341, 233, 120, 30);
        contentPane.add(btnView);

        JButton btnBack = new JButton("Back");
        btnBack.setBounds(191, 233, 100, 30);
        contentPane.add(btnBack);
        
       

        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CustomerMainMenu customerMenu = new CustomerMainMenu(customer);
                customerMenu.setVisible(true);
                dispose();
            }
        });

        btnView.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Integer selectedBookingID = bookingList.getSelectedValue();
                if (selectedBookingID != null) {
                    JOptionPane.showMessageDialog(null, "Opening Booking ID: " + selectedBookingID, 
                            "Success", JOptionPane.INFORMATION_MESSAGE);
                    ViewBookingDashboard viewBooking;
					try {
						viewBooking = new ViewBookingDashboard(selectedBookingID, customer);
						viewBooking.setVisible(true);
	                    dispose();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                    
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a booking.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Load booking IDs
        loadBookings(customer);
    }

    private void loadBookings(Customer customer) {
   
        try {
            List<Integer> bookingIDs = viewBookingController.searchAllBooking(customer.getCustomerID());
            if (bookingIDs.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No bookings found for this customer.", "Info", JOptionPane.INFORMATION_MESSAGE);
            } else {
                for (Integer id : bookingIDs) {
                    listModel.addElement(id);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading bookings.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}