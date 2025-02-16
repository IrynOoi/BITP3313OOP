package view;

import java.awt.EventQueue;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.FlightBookingControl;
import model.Booking;
import model.Customer;
import model.Flight;

import java.awt.Color;
//import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;

public class FlightBooking extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;


	/**
	 * Create the frame.
	 */
	public FlightBooking(Customer customer) {
		setTitle("Select Flight");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 745, 414);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(130, 182, 234));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("READY TO TAKE OFF?");
		lblNewLabel.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 30));
		lblNewLabel.setBounds(29, 10, 246, 41);
		contentPane.add(lblNewLabel);
		
		ImageIcon icon = new ImageIcon(this.getClass().getResource("/Airplane_Clipart.png"));
        Image img = icon.getImage();
        
		JLabel lblAirplane = new JLabel("");
		lblAirplane.setBounds(410, 10, 311, 165);
	    Image scaledImg = img.getScaledInstance(lblAirplane.getWidth(),  lblAirplane .getHeight(), Image.SCALE_SMOOTH);
	    lblAirplane .setIcon(new ImageIcon(scaledImg));
        
		contentPane.add(lblAirplane);
		
		JLabel lblDepartureDestination = new JLabel("From");
		lblDepartureDestination.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 20));
		lblDepartureDestination.setBounds(29, 61, 63, 23);
		contentPane.add(lblDepartureDestination);
		
		JComboBox<String> DepartureDestinationComboBox = new JComboBox<>();
		DepartureDestinationComboBox.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
		DepartureDestinationComboBox.addItem("Kuala Lumpur International Airport (KLIA)");
		DepartureDestinationComboBox.addItem("Langkawi International Airport");
		DepartureDestinationComboBox.addItem("Kuching International Airport");
		DepartureDestinationComboBox.addItem("Penang International Airport");
		DepartureDestinationComboBox.setBounds(29, 84, 371, 30);  // Adjust position and size
		contentPane.add(DepartureDestinationComboBox);
		
		JLabel lblArrivalDestination = new JLabel("To");
		lblArrivalDestination.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 20));
		lblArrivalDestination.setBounds(29, 124, 63, 23);
		contentPane.add(lblArrivalDestination);
		
		JComboBox<String> ArrivalDestinationComboBox = new JComboBox<String>();
		ArrivalDestinationComboBox.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
		ArrivalDestinationComboBox.addItem("JFK International");
		ArrivalDestinationComboBox.addItem("Heathrow");
		ArrivalDestinationComboBox.addItem("Charles de Gaulle");
		ArrivalDestinationComboBox.addItem("Changi");
		ArrivalDestinationComboBox.setBounds(29, 145, 371, 30);
		contentPane.add(ArrivalDestinationComboBox);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 20));
		lblDate.setBounds(29, 197, 52, 23);
		contentPane.add(lblDate);
		
		JSpinner spinnerDay = new JSpinner();
		spinnerDay.setBounds(56, 220, 63, 30);
		contentPane.add(spinnerDay);
		
		JSpinner spinnerMonth = new JSpinner();
		spinnerMonth.setBounds(175, 220, 63, 30);
		contentPane.add(spinnerMonth);
		
		JSpinner spinnerYear = new JSpinner();
		spinnerYear.setBounds(303, 220, 98, 30);
		contentPane.add(spinnerYear);
		
		spinnerDay.setModel(new SpinnerNumberModel(1, 1, 31, 1)); 
		spinnerMonth.setModel(new SpinnerNumberModel(1, 1, 12, 1));
		spinnerYear.setModel(new SpinnerNumberModel(2025, 2024, 2026, 1)); // Initial value: 2025, Min: 2000, Max: 2100, Step: 1
		JSpinner.NumberEditor yearEditor = new JSpinner.NumberEditor(spinnerYear, "####"); // Custom format without commas
		spinnerYear.setEditor(yearEditor);	
		
		JLabel lblDay = new JLabel("DD");
		lblDay.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 20));
		lblDay.setBounds(29, 224, 35, 23);
		contentPane.add(lblDay);
		
		JLabel lblMonth = new JLabel("MM");
		lblMonth.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 20));
		lblMonth.setBounds(141, 224, 35, 23);
		contentPane.add(lblMonth);
		
		JLabel lblYear = new JLabel("YYYY");
		lblYear.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 20));
		lblYear.setBounds(257, 224, 52, 23);
		contentPane.add(lblYear);

		// Create a JComboBox for displaying flight times
		JComboBox<String> flightTimeComboBox = new JComboBox<>();
		flightTimeComboBox.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
		flightTimeComboBox.setBounds(29, 299, 371, 30); // Adjust position and size
		contentPane.add(flightTimeComboBox);
		
		JLabel lblAvailableFlights = new JLabel("Available Flight(s)");
		lblAvailableFlights.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 20));
		lblAvailableFlights.setBounds(29, 276, 146, 23);
		contentPane.add(lblAvailableFlights);
		
		
		JButton btnSearch = new JButton("SEARCH");
		btnSearch.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
		btnSearch.setBounds(475, 216, 98, 34);
		contentPane.add(btnSearch);

		btnSearch.addActionListener(e -> {
		    flightTimeComboBox.removeAllItems();
		    String departure = (String) DepartureDestinationComboBox.getSelectedItem();
		    String arrival = (String) ArrivalDestinationComboBox.getSelectedItem();

		    int day = (int) spinnerDay.getValue();
		    int month = (int) spinnerMonth.getValue();
		    int year = (int) spinnerYear.getValue();
		    String date = String.format("%04d-%02d-%02d", year, month, day); // Format date as YYYY-MM-DD

		    FlightBookingControl flightControl = new FlightBookingControl();
		    List<String> flightTimes = flightControl.fetchFlightTimes(departure, arrival, date);

		    if (flightTimes.isEmpty() || (flightTimes.size() == 1 && "No flights available.".equals(flightTimes.get(0)))) {
		        flightTimeComboBox.addItem("No flights available.");
		    } else {
		        for (String flightTime : flightTimes) {
		            flightTimeComboBox.addItem(flightTime); // Add only departure times
		        }
		    }

		    contentPane.repaint();
		    contentPane.revalidate();
		});

		JButton btnConfirm = new JButton("CONFIRM");
		btnConfirm.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
		btnConfirm.setBounds(475, 299, 98, 34);
		contentPane.add(btnConfirm);
		btnConfirm.addActionListener(new ActionListener() {
		    private Booking booking;

			public void actionPerformed(ActionEvent e) {
		        String departure = (String) DepartureDestinationComboBox.getSelectedItem();
		        String arrival = (String) ArrivalDestinationComboBox.getSelectedItem();
		        int day = (int) spinnerDay.getValue();
		        int month = (int) spinnerMonth.getValue();
		        int year = (int) spinnerYear.getValue();
		        String date = String.format("%04d-%02d-%02d", year, month, day);

		        // Retrieve selected flight time
		        String selectedFlightTime = (String) flightTimeComboBox.getSelectedItem(); 
		        if (selectedFlightTime == null || "No flights available.".equals(selectedFlightTime)) {
		            JOptionPane.showMessageDialog(contentPane, "Please select a valid flight time.");
		            return;
		        }

		        // Extract only the time portion from the selected flight time
		        String departureTimeFull = selectedFlightTime.split(",")[0].replace("Departs: ", "").trim();
		        String timeOnly = departureTimeFull.split(" ")[1]; // Extract the time part (e.g., "10:00:00")

		        FlightBookingControl flightController = new FlightBookingControl();
		        int flightID = flightController.flightID(departure, arrival, date, timeOnly);

		        if (flightID == -1) {
		            JOptionPane.showMessageDialog(contentPane, "Flight not found. Please verify your selection.");
		        } else {
		            Flight flight=new Flight();
		            flight.setFlightId(flightID);
		            try {
						flightController.flightInformation(flight);
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		            Booking booking = new Booking();
		            booking.setCustomer(customer);
		            booking.setFlight(flight);
		            
					try {
						BookingSeats seatBookingWindow = new BookingSeats(booking);
		                seatBookingWindow.setVisible(true);
						dispose();
			            System.out.println("Flight selected: " + flightID);
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		        }
		        
		    }
		});
		
		JButton btnBack = new JButton("BACK");
		btnBack.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
		btnBack.setBounds(602, 299, 98, 34);
		contentPane.add(btnBack);
		btnBack.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	CustomerMainMenu customerMenu = new CustomerMainMenu(customer);
		    	customerMenu.setVisible(true);
		    	dispose();
		    }
		});
	}
}
