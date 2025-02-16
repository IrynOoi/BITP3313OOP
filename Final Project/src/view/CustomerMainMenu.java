//CustomerMainMenu
package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ViewBookingController;
import model.Customer;
import model.Flight;
import model.Seat;
import model.Baggage;
import model.Booking;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.awt.event.ActionEvent;

public class CustomerMainMenu extends JFrame 
{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	public CustomerMainMenu(Customer customer) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 803, 558);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(130, 182, 234));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("DASHBOARD");
		lblNewLabel.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 30));
		lblNewLabel.setBounds(324, 21, 151, 66);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("View Profile");
	    btnNewButton.setBackground(new Color(192, 192, 192));
	    btnNewButton.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 18));
	    btnNewButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            CustomerAcc frame = new CustomerAcc(customer); // Pass the customer object
	            frame.setVisible(true);
	            dispose();
	        }
	    });
	    btnNewButton.setBounds(246, 97, 315, 48);
	    contentPane.add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("Make Booking");
		btnNewButton_2.setBackground(new Color(192, 192, 192));
		btnNewButton_2.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 18));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				FlightBooking frame = new FlightBooking(customer);
				frame.setVisible(true);
				dispose();
			}
		});
		btnNewButton_2.setBounds(246, 234, 315, 54);
		contentPane.add(btnNewButton_2);
		
		JButton btnViewBooking = new JButton("View Booking");
		btnViewBooking.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 18));
		btnViewBooking.setBackground(Color.LIGHT_GRAY);
		btnViewBooking.setBounds(246, 311, 315, 48);
		btnViewBooking.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		        	ViewBookingController viewBookingController = new ViewBookingController();
		            // Load bookings for the customer
		            List<Integer> bookingIDs = viewBookingController.searchAllBooking(customer.getCustomerID());

		            if (bookingIDs.isEmpty()) {
		                // Show a message if no bookings found
		                JOptionPane.showMessageDialog(null, "No bookings found for this customer.", "Info", JOptionPane.INFORMATION_MESSAGE);
		            } else {
		                // If bookings exist, open the SelectViewBooking page
		                SelectViewBooking frame = new SelectViewBooking(customer);
		                frame.setVisible(true);
		                dispose();  // Close the current page
		            }
		        } catch (Exception ex) {
		            ex.printStackTrace();
		            JOptionPane.showMessageDialog(null, "An error occurred while searching for bookings.", "Error", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});
		contentPane.add(btnViewBooking);

		
		JButton btnNewButton_1_1 = new JButton("Update Profile");
		btnNewButton_1_1.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 18));
		btnNewButton_1_1.setBackground(Color.LIGHT_GRAY);
		btnNewButton_1_1.setBounds(246, 168, 315, 48);
		btnNewButton_1_1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            UpdateCustomerProfileView frame = new UpdateCustomerProfileView(customer);
		            frame.setVisible(true);
		            dispose();
		        } catch (Exception ex) {
		            ex.printStackTrace();
		        }
		    }
		});

		contentPane.add(btnNewButton_1_1);
		
		JButton btnNewButton_1_2 = new JButton("Log Out");
		btnNewButton_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                MainMenu frame = new MainMenu(customer);
                frame.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1_2.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 18));
		btnNewButton_1_2.setBackground(Color.LIGHT_GRAY);
		btnNewButton_1_2.setBounds(246, 452, 315, 48);
		contentPane.add(btnNewButton_1_2);
		
		JButton btnNewButton_1_2_1 = new JButton("Restore Booking");
		btnNewButton_1_2_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SearchViewBooking frame = new SearchViewBooking(customer);
                frame.setVisible(true);
                dispose();
            }
        });
		btnNewButton_1_2_1.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 18));
		btnNewButton_1_2_1.setBackground(Color.LIGHT_GRAY);
		btnNewButton_1_2_1.setBounds(246, 380, 315, 48);
		contentPane.add(btnNewButton_1_2_1);
	}
}
