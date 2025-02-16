package view;

import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import controller.CustomerController;
import model.Booking;
import model.Customer;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Image;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class CustomerLoginView extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField usernameField;  
    private Customer customer;
    private Booking booking;
    private JPasswordField passwordField;
  
    public CustomerLoginView() {
    	setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 745, 414);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(130, 182, 234));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("LOGIN");
        lblNewLabel.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 27));
        lblNewLabel.setBounds(25, 11, 227, 83);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("USERNAME :");
        lblNewLabel_1.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 18));
        lblNewLabel_1.setBounds(25, 132, 103, 48);
        contentPane.add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("PASSWORD :");
        lblNewLabel_2.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 18));
        lblNewLabel_2.setBounds(25, 200, 102, 54);
        contentPane.add(lblNewLabel_2);

        usernameField = new JTextField();
        usernameField.setBounds(123, 140, 253, 35);
        contentPane.add(usernameField);
        usernameField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setBounds(123, 211, 253, 35);
        contentPane.add(passwordField);
        
        // Load and scale the image
        ImageIcon icon = new ImageIcon(this.getClass().getResource("/login icon.png"));
        Image img = icon.getImage();
        JLabel lblNewLabel_3 = new JLabel("");
        lblNewLabel_3.setBounds(441, 68, 261, 243);
        Image scaledImg = img.getScaledInstance(lblNewLabel_3.getWidth(), lblNewLabel_3.getHeight(), Image.SCALE_SMOOTH);
        lblNewLabel_3.setIcon(new ImageIcon(scaledImg));
        contentPane.add(lblNewLabel_3);

        JButton btnNewButton = new JButton("BACK");
        btnNewButton.setBackground(new Color(192, 192, 192));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainMenu frame = new MainMenu(customer);
                frame.setVisible(true);
                dispose();
            }
        });
        btnNewButton.setBounds(82, 286, 125, 36);
        contentPane.add(btnNewButton);

        JButton btnOk = new JButton("OK");
        btnOk.setBackground(new Color(192, 192, 192));
        btnOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText().trim();
                String password = passwordField.getText().trim();

                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in both fields!");
                    return;
                }

                Customer inputCustomer = new Customer();
                inputCustomer.setCustomer_username(username);
                inputCustomer.setPassword(password);

                CustomerController custController = new CustomerController();
                try {
                    // Attempt to log in and get the Customer object
                    Customer loggedInCustomer = custController.customerLogin(inputCustomer);

                    if (loggedInCustomer != null) {
                        // Login successful
                        JOptionPane.showMessageDialog(null, "Login Successful!");
                        dispose();

                        // Pass the logged-in customer to the next frame
                        CustomerMainMenu frame = new CustomerMainMenu(loggedInCustomer);
                        frame.setVisible(true);
                    } else {
                        // Login failed
                        JOptionPane.showMessageDialog(null, "Invalid Login! Please check your credentials.");
                    }
                } catch (ClassNotFoundException | SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "An error occurred. Please try again later.");
                }
            }
        });
        btnOk.setBounds(248, 286, 125, 36);
        contentPane.add(btnOk);
        
    }
}
