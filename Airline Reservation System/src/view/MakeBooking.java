package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

import model.Booking;
import model.Customer;
import model.Flight;
import model.Seat;
import model.Baggage;
import controller.BookingController;
import controller.CustomerController;
import controller.FlightBookingControl;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JSeparator;
import javax.swing.JTextField;

public class MakeBooking extends JFrame {
	
	private JPanel contentPane;
	private JComboBox<String> comboBoxAddOnWeight;
	
	 // Map for baggage prices
    private static final Map<String, Double> baggagePrices = new HashMap<>();
    private JTextField bookingIDTxt;
    private JTextField bookingDateTxt;
    private JTextField customerNameTxt;
    private JTextField AirlineTxt;
    private JTextField DestinationTxt;
    private JTextField DepartureTxt;
    private JTextField ArrivalTxt;
    private JTextField SeatNoTxt;
    private JTextField totalAmountTxt;
    private JTextField addOnPriceTxt;
    
    static {
        baggagePrices.put("0", 0.0);
        baggagePrices.put("15", 61.60);
        baggagePrices.put("20", 78.40);
        baggagePrices.put("25", 96.60);
        baggagePrices.put("30", 123.30);
        baggagePrices.put("40", 190.40);
        baggagePrices.put("50", 247.80);
        baggagePrices.put("60", 334.60);
    }

	private static final long serialVersionUID = 1L;
	private JTextField ClassTxt;
	private JTextField DefaultBaggageWeightTxt;
	private JTextField PriceTxt;

	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	
	public MakeBooking(Booking booking) throws ClassNotFoundException, SQLException {
		setTitle("Confirm Booking");
		BookingController bookcontroller = new BookingController();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 843, 651);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(130, 182, 234));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblViewBooking = new JLabel("MAKE BOOKING");
			lblViewBooking.setFont(new Font("Segoe Script", Font.PLAIN, 30));
			lblViewBooking.setBounds(288, 10, 332, 54);
		contentPane.add(lblViewBooking);
		
		JLabel lblBookingID = new JLabel("Booking ID : ");
			lblBookingID.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 20));
			lblBookingID.setBounds(60, 54, 100, 46);
		contentPane.add(lblBookingID);
		
		
		JLabel lblBookingDate = new JLabel("Booking Date : ");
			lblBookingDate.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 20));
			lblBookingDate.setBounds(441, 60, 126, 34);
		contentPane.add(lblBookingDate);
		
		JLabel lblCustomerName = new JLabel("Name : ");
			lblCustomerName.setFont(new Font("Tw Cen MT Condensed Bold", Font.PLAIN, 20));
			lblCustomerName.setBounds(60, 100, 66, 46);
		contentPane.add(lblCustomerName);
		
		JLabel lblDestination = new JLabel("Destination : ");
			lblDestination.setFont(new Font("Tw Cen MT Condensed Bold", Font.PLAIN, 20));
			lblDestination.setBounds(60, 193, 100, 46);
		contentPane.add(lblDestination);
		
		JLabel lblDeparture = new JLabel("Departure : ");
			lblDeparture.setFont(new Font("Tw Cen MT Condensed Bold", Font.PLAIN, 20));
			lblDeparture.setBounds(60, 238, 100, 46);
		contentPane.add(lblDeparture);
	
		JLabel lblArrival = new JLabel("Arrival : ");
			lblArrival.setFont(new Font("Tw Cen MT Condensed Bold", Font.PLAIN, 20));
			lblArrival.setBounds(441, 238, 71, 46);
		contentPane.add(lblArrival);
	
		JLabel lblAirline = new JLabel("Airline : ");
			lblAirline.setFont(new Font("Tw Cen MT Condensed Bold", Font.PLAIN, 20));
			lblAirline.setBounds(60, 150, 100, 46);
		contentPane.add(lblAirline);

		JLabel lblDefaultBaggage = new JLabel("Default Baggage Weight : ");
			lblDefaultBaggage.setFont(new Font("Tw Cen MT Condensed Bold", Font.PLAIN, 20));
			lblDefaultBaggage.setBounds(441, 286, 200, 46);
		contentPane.add(lblDefaultBaggage);
		
		JLabel lblAddOnPrice = new JLabel("Add On Price:");
		lblAddOnPrice.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 20));
		lblAddOnPrice.setBounds(441, 373, 100, 46);
		contentPane.add(lblAddOnPrice);
		
		addOnPriceTxt = new JTextField();
		addOnPriceTxt.setEditable(false);
		addOnPriceTxt.setColumns(10);
		addOnPriceTxt.setBounds(551, 385, 90, 27);
		contentPane.add(addOnPriceTxt);
		addOnPriceTxt.setText("0.0");
		
		JComboBox comboBoxAddOnWeight = new JComboBox();
		comboBoxAddOnWeight.setModel(new DefaultComboBoxModel(new String[] {"0", "15", "20", "25", "30", "40", "50", "60"}));
		comboBoxAddOnWeight.setSelectedIndex(0);
		comboBoxAddOnWeight.setFont(new Font("Tw Cen MT", Font.PLAIN, 15));
		comboBoxAddOnWeight.setBounds(562, 342, 58, 27);
		contentPane.add(comboBoxAddOnWeight);
	
		
		// Event Listener for ComboBox
		comboBoxAddOnWeight.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String selectedWeight = (String) comboBoxAddOnWeight.getSelectedItem();
		        double price = baggagePrices.getOrDefault(selectedWeight, 0.0);

		        // Update Baggage in Booking Object
		        int weight = Integer.parseInt(selectedWeight);
		        Integer baggageID;

		        if (weight == 0) {
		            baggageID = null;  // Explicitly setting it to NULL
		        } else {
		            baggageID = bookcontroller.getBaggageID(weight, price);
		        }

		        booking.getBaggage().setBaggageID(baggageID);

		        // Debugging prints
		        System.out.println("Selected Weight: " + weight);
		        System.out.println("Baggage ID: " + baggageID);
		        System.out.println("Add-On Price: RM " + price);
		        
		        booking.getBaggage().setAddOnWeight(weight);
		        booking.getBaggage().setAddOnPrice(price);

		        // Recalculate Total Price
		        double totalPrice;
		        try {
		            totalPrice = BookingController.calculateTotalPrice(booking);
		            totalAmountTxt.setText("RM " + totalPrice);
		            addOnPriceTxt.setText("RM " + price);
		            booking.setTotalPrice(totalPrice);
		        } catch (ClassNotFoundException | SQLException e1) {
		            e1.printStackTrace();
		        }
		    }
		});

        
		JLabel lblAddOnBaggage = new JLabel("Add On Weight:");
		lblAddOnBaggage.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 20));
		lblAddOnBaggage.setBounds(441, 329, 126, 46);
		contentPane.add(lblAddOnBaggage);
		
		
		JLabel lblTotalAmount = new JLabel("Total Amount:");
		lblTotalAmount.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 20));
		lblTotalAmount.setBounds(201, 494, 120, 34);
		contentPane.add(lblTotalAmount);
	    
		int bookingID=bookcontroller.getBookingID();
		booking.setBookingId(bookingID);
		bookingIDTxt = new JTextField(String.valueOf(booking.getBookingId()));
		System.out.println(bookingIDTxt);
		bookingIDTxt.setEditable(false);
		bookingIDTxt.setBounds(162, 63, 159, 27);
		contentPane.add(bookingIDTxt);
		bookingIDTxt.setColumns(10);
		
		 // Get current date and time
        LocalDateTime bookingDate = LocalDateTime.now();

        // Format it as a readable string
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = bookingDate.format(formatter);

		bookingDateTxt = new JTextField(formattedDate);
		bookingDateTxt.setEditable(false);
		bookingDateTxt.setColumns(10);
		bookingDateTxt.setBounds(577, 63, 159, 27);
		contentPane.add(bookingDateTxt);
		
		customerNameTxt = new JTextField(booking.getCustomer().getCustomer_name());
		customerNameTxt.setEditable(false);
		customerNameTxt.setColumns(10);
		customerNameTxt.setBounds(157, 110, 252, 25);
		contentPane.add(customerNameTxt);
		
		int airlineID=booking.getFlight().getAirline().getAirline_ID();
		String airlineName=bookcontroller.getAirlineName(airlineID);
		booking.getFlight().getAirline().setAirline_Name(airlineName);
		AirlineTxt = new JTextField(airlineName);
		AirlineTxt.setEditable(false);
		AirlineTxt.setColumns(10);
		AirlineTxt.setBounds(157, 158, 252, 25);
		contentPane.add(AirlineTxt);
		
		String destination=booking.getFlight().getDestination();
		DestinationTxt = new JTextField(destination);
		DestinationTxt.setEditable(false);
		DestinationTxt.setColumns(10);
		DestinationTxt.setBounds(157, 206, 252, 25);
		contentPane.add(DestinationTxt);
		
		String departureTime = booking.getFlight().getDepartureTime();
		String departureAirport=booking.getFlight().getDepartureAirport();
		DepartureTxt = new JTextField(departureAirport+" "+departureTime);
		DepartureTxt.setEditable(false);
		DepartureTxt.setColumns(10);
		DepartureTxt.setBounds(158, 251, 252, 25);
		contentPane.add(DepartureTxt);
		
		String arrivalTime = booking.getFlight().getArrivalTime();
		String arrivalAirport=booking.getFlight().getArrivalAirport();
		ArrivalTxt = new JTextField(arrivalAirport+" "+arrivalTime);
		ArrivalTxt.setEditable(false);
		ArrivalTxt.setColumns(10);
		ArrivalTxt.setBounds(519, 251, 231, 25);
		contentPane.add(ArrivalTxt);
	
		SeatNoTxt = new JTextField(booking.getSeat().getSeat_No());
		SeatNoTxt.setEditable(false);
		SeatNoTxt.setColumns(10);
		SeatNoTxt.setBounds(157, 299, 71, 25);
		contentPane.add(SeatNoTxt);
        
		double totalPrice=BookingController.calculateTotalPrice(booking);
		totalAmountTxt = new JTextField();
		totalAmountTxt.setEditable(false);
		totalAmountTxt.setColumns(10);
		totalAmountTxt.setBounds(342, 501, 245, 25);
		contentPane.add(totalAmountTxt);
		totalAmountTxt.setText("RM "+totalPrice);
		
		JLabel lblSeatNo = new JLabel("Seat No :");
		lblSeatNo.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 20));
		lblSeatNo.setBounds(60, 294, 126, 34);
		contentPane.add(lblSeatNo);
		
		JLabel lblSeatClass = new JLabel("Class :");
		lblSeatClass.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 20));
		lblSeatClass.setBounds(60, 341, 126, 34);
		contentPane.add(lblSeatClass);
		
		
		ClassTxt = new JTextField(booking.getSeat().getSeat_Class());
		ClassTxt.setEditable(false);
		ClassTxt.setColumns(10);
		ClassTxt.setBounds(157, 348, 159, 25);
		contentPane.add(ClassTxt);
		
		int defaultBaggage=booking.getFlight().getDefaultBaggage();
		DefaultBaggageWeightTxt = new JTextField(String.valueOf(defaultBaggage));
		DefaultBaggageWeightTxt.setEditable(false);
		DefaultBaggageWeightTxt.setColumns(10);
		DefaultBaggageWeightTxt.setBounds(634, 299, 71, 25);
		contentPane.add(DefaultBaggageWeightTxt);
		
		
		JLabel lblPrice = new JLabel("Price :");
		lblPrice.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 20));
		lblPrice.setBounds(60, 385, 126, 34);
		contentPane.add(lblPrice);
		
		PriceTxt = new JTextField("RM " + booking.getSeat().getPrice());
		PriceTxt.setEditable(false);
		PriceTxt.setColumns(10);
		PriceTxt.setBounds(157, 386, 159, 25);
		contentPane.add(PriceTxt);
		
		JLabel lblPaymentMethod = new JLabel("Payment Method :");
		lblPaymentMethod.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 20));
		lblPaymentMethod.setBounds(201, 457, 153, 34);
		contentPane.add(lblPaymentMethod);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Credit Card", "Debit Card", "Cash", "PayPal", "Bank Transfer"}));
		comboBox.setBounds(342, 466, 238, 21);
		contentPane.add(comboBox);
		

		 JButton btnConfirm = new JButton("CONFIRM");
			btnConfirm.setFont(new Font("Tw Cen MT", Font.BOLD, 15));
				btnConfirm.setBounds(457, 555, 115, 34);
			btnConfirm.addActionListener(e -> {
		            try {
		                booking.setBookingId(bookingID);
		                booking.setBooking_Date(formattedDate);
		                String selectedPaymentMethod = comboBox.getSelectedItem().toString();
		                booking.setPaymentMethod(selectedPaymentMethod);
		                double finalPrice = BookingController.calculateTotalPrice(booking);
		                booking.setTotalPrice(finalPrice);
		                System.out.println("Total Price before DB insert: " + booking.getTotalPrice());
		                boolean isInserted = BookingController.insertBooking(booking);
		                if (isInserted) {
		                    JOptionPane.showMessageDialog(this, "Booking added succesfully");
		                    dispose();
		                    Customer customer=booking.getCustomer();
		                    CustomerMainMenu customerMenu = new CustomerMainMenu(customer);
		    		    	customerMenu.setVisible(true);
		    		    	dispose();
		                    
		                } else {
		                    JOptionPane.showMessageDialog(this, "Failed to make booking.", "Error", JOptionPane.ERROR_MESSAGE);
		                }
		            } catch (SQLException | ClassNotFoundException ex) {
		                ex.printStackTrace();
		                JOptionPane.showMessageDialog(this, "An error occurred while make booking.", "Error", JOptionPane.ERROR_MESSAGE);
		            }
		            
		        });
			
			contentPane.add(btnConfirm);
		
		
		JButton btnBack = new JButton("BACK");
		btnBack.setFont(new Font("Tw Cen MT", Font.BOLD, 15));
			btnBack.setBounds(331, 555, 81, 34);
			btnBack.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			    	try {
						BookingSeats frame = new BookingSeats(booking);
						frame.setVisible(true);
						dispose();
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			    }
			});
		contentPane.add(btnBack);
	}
}
