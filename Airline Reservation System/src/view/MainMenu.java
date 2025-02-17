package view;

import java.awt.EventQueue;
import java.awt.Image; // Import for Image class
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Customer;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class MainMenu extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private static Customer customer;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainMenu frame = new MainMenu(customer);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public MainMenu(Customer customer)
    {
    	setTitle("Homepage");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 676, 404);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        
        // Set layout to GroupLayout for better resizing handling
        GroupLayout groupLayout = new GroupLayout(contentPane);
        contentPane.setLayout(groupLayout);
        
        // Load and scale the image
        ImageIcon icon = new ImageIcon(this.getClass().getResource("/mainmenu.jpg"));
        Image img = icon.getImage();
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(130, 182, 234));
        panel.setForeground(new Color(255, 255, 255));

        // GroupLayout setup for panel inside contentPane
        groupLayout.setHorizontalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addComponent(panel, GroupLayout.DEFAULT_SIZE, 660, Short.MAX_VALUE)
        );
        groupLayout.setVerticalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addComponent(panel, GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE)
        );
        
        panel.setLayout(null);
        
        // Create the JLabel for "MAIN MENU" with dynamic resizing
        JLabel lblNewLabel = new JLabel("MAIN MENU123");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 24));
        lblNewLabel.setBounds(32, 131, 235, 57); // Static bounds, but we could dynamically calculate this later
        panel.add(lblNewLabel);
        
        // Create the JLabel for the image, which will scale
        JLabel label1 = new JLabel("");
        label1.setBounds(338, 31, 312, 303); // Set size relative to the panel size
        panel.add(label1);
        Image scaledImg = img.getScaledInstance(label1.getWidth(), label1.getHeight(), Image.SCALE_SMOOTH);
        label1.setIcon(new ImageIcon(scaledImg));

        // Create "LOGIN" button
        JButton btnNewButton = new JButton("LOGIN");
        btnNewButton.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 18));
        btnNewButton.setForeground(new Color(0, 0, 0));
        btnNewButton.setBackground(new Color(192, 192, 192));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CustomerLoginView frame = new CustomerLoginView();
                frame.setVisible(true);
                dispose();
            }
        });
        btnNewButton.setBounds(53, 199, 180, 57);
        panel.add(btnNewButton);

        // Create "REGISTER" button
        JButton btnNewButton_1 = new JButton("REGISTER");
        btnNewButton_1.setBackground(new Color(192, 192, 192));
        btnNewButton_1.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 18));
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	CustomerRegistrationPage frame;
				try {
					frame = new CustomerRegistrationPage();
					frame.setVisible(true);
	                dispose();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
        btnNewButton_1.setBounds(53, 277, 180, 57);
        panel.add(btnNewButton_1);

        // Create the "AIRLINE RESERVATION SYSTEM" label
        JLabel lblNewLabel_1 = new JLabel("<html>AIRLINE RESERVATION SYSTEM</html>");
        lblNewLabel_1.setForeground(new Color(0, 0, 160));
        lblNewLabel_1.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.BOLD, 27));
        lblNewLabel_1.setBounds(25, 31, 261, 89);
        panel.add(lblNewLabel_1);
    }
}
