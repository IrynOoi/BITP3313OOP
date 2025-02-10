//BookingSeats.java
package view;



import java.awt.EventQueue;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BookingSeats extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    
    // List to keep track of temporarily booked seats
    private List<JButton> tempBookedSeats = new ArrayList<>();

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    BookingSeats frame = new BookingSeats();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public BookingSeats()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 772, 495);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(130, 182, 234));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(73, 11, 655, 375);
        contentPane.add(panel);
        panel.setLayout(new GridLayout(5, 5, 0, 0));

        // Add seat buttons
        JButton A1 = createSeatButton("A1");
        panel.add(A1);

        JButton B1 = createSeatButton("B1");
        panel.add(B1);

        JButton C1 = createSeatButton("C1");
        panel.add(C1);

        JButton D1 = createSeatButton("D1");
        panel.add(D1);

        JButton E1 = createSeatButton("E1");
        panel.add(E1);

        JButton A2 = createSeatButton("A2");
        panel.add(A2);

        JButton B2 = createSeatButton("B2");
        panel.add(B2);

        JButton C2 = createSeatButton("C2");
        panel.add(C2);

        JButton D2 = createSeatButton("D2");
        panel.add(D2);

        JButton E2 = createSeatButton("E2");
        panel.add(E2);

        JButton A3 = createSeatButton("A3");
        panel.add(A3);

        JButton B3 = createSeatButton("B3");
        panel.add(B3);

        JButton C3 = createSeatButton("C3");
        panel.add(C3);

        JButton D3 = createSeatButton("D3");
        panel.add(D3);

        JButton E3 = createSeatButton("E3");
        panel.add(E3);

        JButton A4 = createSeatButton("A4");
        panel.add(A4);

        JButton B4 = createSeatButton("B4");
        panel.add(B4);

        JButton C4 = createSeatButton("C4");
        panel.add(C4);

        JButton D4 = createSeatButton("D4");
        panel.add(D4);

        JButton E4 = createSeatButton("E4");
        panel.add(E4);

        JButton A5 = createSeatButton("A5");
        panel.add(A5);

        JButton B5 = createSeatButton("B5");
        panel.add(B5);

        JButton C5 = createSeatButton("C5");
        panel.add(C5);

        JButton D5 = createSeatButton("D5");
        panel.add(D5);

        JButton E5 = createSeatButton("E5");
        panel.add(E5);

        // Cancel Booking button
        JButton CancelBooking = new JButton("Cancel Booking");
        CancelBooking.setBackground(new Color(249, 234, 174));
        CancelBooking.setBounds(415, 397, 139, 48);
        CancelBooking.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cancelTemporaryBookings();
            }
        });
        contentPane.add(CancelBooking);

        // Confirm Booking button (functionality can be extended as needed)
        JButton ConfirmBooking = new JButton("Confirm Booking");
        ConfirmBooking.setBackground(new Color(249, 234, 174));
        ConfirmBooking.setBounds(201, 397, 130, 48);
        ConfirmBooking.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                confirmTemporaryBookings();
            }
        });
        contentPane.add(ConfirmBooking);
    }

    // Method to create a seat button with an action listener
    private JButton createSeatButton(String label) {
        JButton button = new JButton(label);
        button.setBackground(new Color(192, 192, 192)); // Default color (available)
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Book the seat temporarily
                button.setBackground(new Color(232,185,153)); // Red for booked
                button.setEnabled(false); // Disable the button after booking
                tempBookedSeats.add(button); // Add to the list of temporarily booked seats
            }
        });
        return button;
    }

    // Method to cancel temporary bookings
    private void cancelTemporaryBookings() {
        for (JButton button : tempBookedSeats) {
            button.setBackground(new Color(192, 192, 192)); // Revert to default color (available)
            button.setEnabled(true); // Re-enable the button
        }
        tempBookedSeats.clear(); // Clear the list of temporary bookings
    }

    // Method to confirm temporary bookings (stub for now)
    private void confirmTemporaryBookings()
    {
        // Logic to confirm bookings and save to database (if needed)
        // Clear the list since they're now confirmed
        tempBookedSeats.clear();
    }
}
