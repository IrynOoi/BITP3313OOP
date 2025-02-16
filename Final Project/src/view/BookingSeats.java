package view;

import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.SeatController;
import model.Baggage;
import model.Booking;
import model.Customer;
import model.Seat;

import javax.swing.JButton;
import java.awt.Color;
import java.sql.SQLException;

public class BookingSeats extends JFrame {
    private static final long serialVersionUID = 1L;
    private static final Color OCCUPIED_COLOR = new Color(232, 185, 153);
    private static final Color AVAILABLE_COLOR = new Color(192, 192, 192);
    private static final Color SELECTED_COLOR = new Color(232, 185, 153);
    private JPanel contentPane;
    private JButton selectedButton = null; // Track the selected seat button
    private SeatController seatController;
    
    public BookingSeats(Booking booking) throws ClassNotFoundException, SQLException {
        this.seatController = new SeatController();
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 900, 650); // Adjusted frame size
        contentPane = new JPanel();
        contentPane.setBackground(new Color(130, 182, 234));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Header Label
        JLabel lblBookSeat = new JLabel("WHERE WOULD YOU LIKE TO SIT?");
        lblBookSeat.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 25));
        lblBookSeat.setBounds(300, 10, 500, 54); // Adjusted position
        contentPane.add(lblBookSeat);

        // Panels for seating layout

        // First Class Panel
        JPanel firstClassPanel = new JPanel();
        firstClassPanel.setBounds(50, 70, 800, 70); // Adjust panel size to fit the layout
        contentPane.add(firstClassPanel);
        firstClassPanel.setLayout(new GridLayout(1, 5, 10, 10)); // 1 row, 5 columns

        // First Class Label
        JLabel firstClassLabel = new JLabel("First Class");
        firstClassLabel.setFont(new Font("Arial", Font.BOLD, 18));
        firstClassLabel.setBounds(50, 50, 200, 20);
        firstClassLabel.setForeground(Color.ORANGE);
        contentPane.add(firstClassLabel);

        // First Class: Rows A5, B5, C5, D5, E5
        for (int i = 5; i >= 5; i--) {
            firstClassPanel.add(createSeatButton("A" + i,booking));
            firstClassPanel.add(createSeatButton("B" + i,booking));
            firstClassPanel.add(new JLabel());
            firstClassPanel.add(createSeatButton("C" + i,booking));
            firstClassPanel.add(createSeatButton("D" + i,booking));
            firstClassPanel.add(createSeatButton("E" + i,booking));
        }

        // Business Class Panel
        JPanel businessClassPanel = new JPanel();
        businessClassPanel.setBounds(50, 170, 800, 70); // Adjust panel size to fit the layout
        contentPane.add(businessClassPanel);
        businessClassPanel.setLayout(new GridLayout(1, 5, 10, 10)); // 1 row, 5 columns

        // Business Class Label
        JLabel businessClassLabel = new JLabel("Business Class");
        businessClassLabel.setFont(new Font("Arial", Font.BOLD, 18));
        businessClassLabel.setBounds(50, 150, 200, 20);
        businessClassLabel.setForeground(Color.BLUE);
        contentPane.add(businessClassLabel);

        // Business Class: Rows A4, B4, C4, D4, E4
        for (int i = 4; i >= 4; i--) {
            businessClassPanel.add(createSeatButton("A" + i,booking));
            businessClassPanel.add(createSeatButton("B" + i,booking));
            businessClassPanel.add(new JLabel());
            businessClassPanel.add(createSeatButton("C" + i,booking));
            businessClassPanel.add(createSeatButton("D" + i,booking));
            businessClassPanel.add(createSeatButton("E" + i,booking));
        }

        // Premium Economy Panel
        JPanel premiumEconomyPanel = new JPanel();
        premiumEconomyPanel.setBounds(50, 270, 800, 70); // Adjust panel size to fit the layout
        contentPane.add(premiumEconomyPanel);
        premiumEconomyPanel.setLayout(new GridLayout(1, 5, 10, 10)); // 1 row, 5 columns

        // Premium Economy Label
        JLabel premiumEconomyLabel = new JLabel("Premium Economy");
        premiumEconomyLabel.setFont(new Font("Arial", Font.BOLD, 18));
        premiumEconomyLabel.setBounds(50, 250, 200, 20);
        premiumEconomyLabel.setForeground(Color.GREEN);
        contentPane.add(premiumEconomyLabel);

        // Premium Economy: Rows A3, B3, C3, D3, E3
        for (int i = 3; i >= 3; i--) {
            premiumEconomyPanel.add(createSeatButton("A" + i,booking));
            premiumEconomyPanel.add(createSeatButton("B" + i,booking));
            premiumEconomyPanel.add(new JLabel());
            premiumEconomyPanel.add(createSeatButton("C" + i,booking));
            premiumEconomyPanel.add(createSeatButton("D" + i,booking));
            premiumEconomyPanel.add(createSeatButton("E" + i,booking));
        }

        // Economy Class Panel
        JPanel economyClassPanel = new JPanel();
        economyClassPanel.setBounds(50, 370, 800, 150); // Adjust panel size to fit the layout
        contentPane.add(economyClassPanel);
        economyClassPanel.setLayout(new GridLayout(2, 5, 10, 10)); // 2 rows, 5 columns

        // Economy Class Label
        JLabel economyClassLabel = new JLabel("Economy");
        economyClassLabel.setFont(new Font("Arial", Font.BOLD, 18));
        economyClassLabel.setBounds(50, 350, 200, 20);
        economyClassLabel.setForeground(Color.YELLOW);
        contentPane.add(economyClassLabel);

        // Economy: Rows A2, B2, C2, D2, E2 (Economy)
        for (int i = 2; i >= 1; i--) {
            economyClassPanel.add(createSeatButton("A" + i,booking));
            economyClassPanel.add(createSeatButton("B" + i,booking));
            economyClassPanel.add(new JLabel());
            economyClassPanel.add(createSeatButton("C" + i,booking));
            economyClassPanel.add(createSeatButton("D" + i,booking));
            economyClassPanel.add(createSeatButton("E" + i,booking));
        }

        // Confirm Seats button
        JButton confirmSeats = new JButton("Confirm Seat");
        confirmSeats.setBackground(new Color(249, 234, 174));
        confirmSeats.setBounds(684, 540, 150, 48);
        confirmSeats.addActionListener(e -> {
            try {
                if (selectedButton == null) {
                    JOptionPane.showMessageDialog(null, "No seat selected! Please select a seat.");
                    return;
                }

                String seatNo = selectedButton.getText(); // Extract seat number from button label
                
                // Get the Seat ID from seat number and flight ID
                int seatId = seatController.getSeatId(seatNo, booking.getFlight().getFlightId());
                Seat seat = new Seat();
                seat.setSeat_ID(seatId);
                
                // Fetch seat details from the database
                seatController.seatDetails(seat);
                
                booking.setSeat(seat);
                
                //Create Baggage 
                Baggage baggage = new Baggage();
                booking.setBaggage(baggage);
                
                // Proceed to make booking page
                MakeBooking makeBooking = new MakeBooking(booking);
                makeBooking.setVisible(true);
                
                // Close the current page
                dispose();
            } catch (ClassNotFoundException | SQLException e1) {
                e1.printStackTrace();
            }
        });

        contentPane.add(confirmSeats);
        
        JButton btnBack = new JButton("Back");
        btnBack.setBackground(new Color(249, 234, 174));
        btnBack.setBounds(10, 540, 150, 48);
        btnBack.addActionListener(e -> {
            Customer customer = booking.getCustomer();
            FlightBooking frame = new FlightBooking(customer);
            frame.setVisible(true);
            dispose();
        });
        contentPane.add(btnBack);
    }

    private JButton createSeatButton(String label, Booking booking) throws ClassNotFoundException, SQLException {
        JButton button = new JButton(label);
        
        boolean isOccupied = seatController.isSeatOccupied(label, booking.getFlight().getFlightId());
        
        // Check if the seat is occupied for the specific flight ID
        if (isOccupied) {
            button.setBackground(OCCUPIED_COLOR);
            button.setEnabled(false); // Disable button if seat is occupied
        } else {
            button.setBackground(AVAILABLE_COLOR);
            button.addActionListener(e -> {
                if (selectedButton != null) {
                    selectedButton.setBackground(AVAILABLE_COLOR); // Reset the previously selected button
                }
                button.setBackground(SELECTED_COLOR);  // Mark as selected
                selectedButton = button;  // Update the selected button
            });
        }
        return button;
    }

    
}
