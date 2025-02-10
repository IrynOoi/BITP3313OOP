package view;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Database.DatabaseConnection;
import controller.CustomerController;
import model.Customer;
import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsConfiguration;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateCustomerProfileView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtName, txtAddress, txtEmail, txtPhone, txtUsername;
    private JPasswordField txtPassword;
    private JRadioButton rdbtnMale, rdbtnFemale;
    private Customer customer;
	

	/**
	 * Create the frame.
	
	 */
    
	

    public UpdateCustomerProfileView(Customer customer) {

        setTitle("Update Profile");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 631, 443);
        JPanel contentPane = new JPanel();
        contentPane.setBackground(new Color(130, 182, 234));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lblTitle = new JLabel("Update Customer Profile");
        lblTitle.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 30));
        lblTitle.setBounds(180, 10, 300, 30);
        contentPane.add(lblTitle);

        JLabel lblName = new JLabel("Name:");
        lblName.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 20));
        lblName.setBounds(50, 60, 100, 25);
        contentPane.add(lblName);

        txtName = new JTextField(customer.getCustomer_name()); // Use customer details
        txtName.setBounds(200, 60, 300, 25);
        contentPane.add(txtName);

        JLabel lblAddress = new JLabel("Address:");
        lblAddress.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 20));
        lblAddress.setBounds(50, 100, 100, 25);
        contentPane.add(lblAddress);

        txtAddress = new JTextField(customer.getCustomer_address());
        txtAddress.setBounds(200, 100, 300, 25);
        contentPane.add(txtAddress);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 20));
        lblEmail.setBounds(50, 140, 100, 25);
        contentPane.add(lblEmail);

        txtEmail = new JTextField(customer.getCustomer_email());
        txtEmail.setBounds(200, 140, 300, 25);
        contentPane.add(txtEmail);

        JLabel lblPhone = new JLabel("Phone:");
        lblPhone.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 20));
        lblPhone.setBounds(50, 180, 100, 25);
        contentPane.add(lblPhone);

        txtPhone = new JTextField(customer.getCustomer_phone());
        txtPhone.setBounds(200, 180, 300, 25);
        contentPane.add(txtPhone);

        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 20));
        lblUsername.setBounds(50, 220, 100, 25);
        contentPane.add(lblUsername);

        txtUsername = new JTextField(customer.getCustomer_username());
        txtUsername.setBounds(200, 220, 300, 25);
        contentPane.add(txtUsername);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 20));
        lblPassword.setBounds(50, 260, 100, 25);
        contentPane.add(lblPassword);

        txtPassword = new JPasswordField(customer.getPassword());
        txtPassword.setBounds(200, 260, 300, 25);
        contentPane.add(txtPassword);

        JLabel lblGender = new JLabel("Gender:");
        lblGender.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 20));
        lblGender.setBounds(50, 300, 100, 25);
        contentPane.add(lblGender);

        rdbtnMale = new JRadioButton("Male", "M".equals(customer.getGender()));
        rdbtnMale.setBackground(new Color(130, 182, 234));
        rdbtnMale.setBounds(200, 300, 80, 25);
        contentPane.add(rdbtnMale);

        rdbtnFemale = new JRadioButton("Female", "F".equals(customer.getGender()));
        rdbtnFemale.setBackground(new Color(130, 182, 234));
        rdbtnFemale.setBounds(300, 300, 80, 25);
        contentPane.add(rdbtnFemale);

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(rdbtnMale);
        genderGroup.add(rdbtnFemale);

        JButton btnUpdate = new JButton("Update");
        btnUpdate.setFont(new Font("Tw Cen MT", Font.BOLD, 15));
        btnUpdate.setBounds(358, 352, 100, 30);
        btnUpdate.addActionListener(e -> {
            try {
                customer.setCustomer_name(txtName.getText());
                customer.setCustomer_address(txtAddress.getText());
                customer.setCustomer_email(txtEmail.getText());
                customer.setCustomer_phone(txtPhone.getText());
                customer.setCustomer_username(txtUsername.getText());
                customer.setPassword(new String(txtPassword.getPassword()));
                customer.setGender(rdbtnMale.isSelected() ? "M" : "F");

                CustomerController controller = new CustomerController();
                boolean isUpdated = controller.updateCustomerProfile(customer);
                if (isUpdated) {
                    JOptionPane.showMessageDialog(this, "Profile Updated Successfully!");
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to Update Profile.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "An error occurred while updating the profile.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        contentPane.add(btnUpdate);
        
        JButton btnMainMenu = new JButton("Back");
        btnMainMenu.setFont(new Font("Tw Cen MT", Font.BOLD, 15));
        btnMainMenu.setBounds(181, 352, 110, 30);
        btnMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerMainMenu frame = new CustomerMainMenu(customer);
                frame.setVisible(true);
                dispose();
			}
		});
        contentPane.add(btnMainMenu);
        
    }

}
