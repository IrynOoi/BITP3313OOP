package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTextArea;
//import javax.swing.JCheckBox;
import javax.swing.JPasswordField;
//import javax.swing.UIManager;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.sql.PreparedStatement;

import Database.DatabaseConnection;
import controller.CustomerController;
import model.Customer;

public class CustomerRegistrationPage extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
		private JTextField txtName;
		private JTextField txtContactNumber;
		private JTextField txtEmail;
		private JTextField txtUsername;
		private JPasswordField txtPassword;
		private JPasswordField txtRetypePassword;
		private String password;
		private String retypePassword;
		private Customer customer;
	
	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public CustomerRegistrationPage() throws ClassNotFoundException, SQLException {
		setTitle("Register");
		Connection conn = DatabaseConnection.doConnection();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 745, 414);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(130, 182, 234));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCustomerRegistration = new JLabel("CUSTOMER REGISTRATION");
			lblCustomerRegistration.setBackground(new Color(240, 240, 240));
			lblCustomerRegistration.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 25));
			lblCustomerRegistration.setBounds(246, 10, 252, 54);
		contentPane.add(lblCustomerRegistration);
		
		JLabel usernameStatus = new JLabel("");
			usernameStatus.setForeground(new Color(210, 0, 0));
			usernameStatus.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 15));
			usernameStatus.setBounds(418, 185, 265, 12);
		contentPane.add(usernameStatus);
		
		JLabel passwordStatus = new JLabel("");
			passwordStatus.setForeground(new Color(210, 0, 0));
			passwordStatus.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 15));
			passwordStatus.setBounds(418, 311, 175, 12);
		contentPane.add(passwordStatus);
		
		JLabel lblName = new JLabel("Name : ");
		lblName.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 20));
		lblName.setBounds(60, 54, 66, 46);
		contentPane.add(lblName);
		
		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(60, 88, 265, 26);
		contentPane.add(txtName);
		
		JLabel lblContactNumber = new JLabel("Contact Number : ");
		lblContactNumber.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 20));
		lblContactNumber.setBounds(60, 110, 146, 46);
		contentPane.add(lblContactNumber);
		
		txtContactNumber = new JTextField();
		txtContactNumber.setColumns(10);
		txtContactNumber.setBounds(60, 144, 265, 26);
		contentPane.add(txtContactNumber);
		
		JLabel lblEmail = new JLabel("Email : ");
		lblEmail.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 20));
		lblEmail.setBounds(60, 166, 66, 46);
		contentPane.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(60, 200, 265, 26);
		contentPane.add(txtEmail);
		
		JLabel lblAddress = new JLabel("Address : ");
		lblAddress.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 20));
		lblAddress.setBounds(60, 226, 97, 46);
		contentPane.add(lblAddress);
		
		JTextArea txtAddress = new JTextArea();
		txtAddress.setBounds(60, 260, 265, 63);
		txtAddress.setLineWrap(true);  // Enable line wrapping
		txtAddress.setWrapStyleWord(true);
		contentPane.add(txtAddress);
		
		JLabel lblRetypePassword = new JLabel("Retype Password : ");
		lblRetypePassword.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 20));
		lblRetypePassword.setBounds(418, 247, 175, 46);
		contentPane.add(lblRetypePassword);
		
		JLabel lblPassword = new JLabel("Password : ");
		lblPassword.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 20));
		lblPassword.setBounds(418, 192, 97, 46);
		contentPane.add(lblPassword);
		
		JLabel lblUsername = new JLabel("Username : ");
		lblUsername.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 20));
		lblUsername.setBounds(418, 122, 97, 46);
		contentPane.add(lblUsername);
		
		txtUsername = new JTextField();
		txtUsername.setColumns(10);
		txtUsername.setBounds(418, 155, 265, 26);
		contentPane.add(txtUsername);
		txtUsername.getDocument().addDocumentListener(new DocumentListener() {
		    @Override
		    public void insertUpdate(DocumentEvent e) {
		        validateUsername();
		    }

		    @Override
		    public void removeUpdate(DocumentEvent e) {
		        validateUsername();
		    }

		    @Override
		    public void changedUpdate(DocumentEvent e) {
		        validateUsername();
		    }

		    private void validateUsername() {
		        String username = txtUsername.getText();
		        if (username.isEmpty()) {
		            usernameStatus.setText("*Username cannot be empty!");
		            return;
		        }

		        try {
		            String query = "SELECT COUNT(*) FROM customer WHERE username = ?";
		            PreparedStatement pstmt = conn.prepareStatement(query);
		            pstmt.setString(1, username);
		            ResultSet rs = pstmt.executeQuery();
		            if (rs.next() && rs.getInt(1) > 0) {
		                usernameStatus.setText("*Username already in use.");
		            } else {
		                usernameStatus.setText(""); // Clear the message for a valid username
		            }
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		        }
		    }
		});
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(418, 226, 265, 26);
		contentPane.add(txtPassword);
		
		txtRetypePassword = new JPasswordField();
		txtRetypePassword.setBounds(418, 282, 265, 26);
		contentPane.add(txtRetypePassword);
		txtRetypePassword.getDocument().addDocumentListener(new DocumentListener() {
		public void insertUpdate(DocumentEvent e) {
			validatePasswords();
		}
		
		public void removeUpdate(DocumentEvent e) {
			validatePasswords();
		}
		
		public void changedUpdate(DocumentEvent e) {
			validatePasswords();
		}

		private void validatePasswords(){
			password = new String(txtPassword.getPassword());
			retypePassword = new String(txtRetypePassword.getPassword());
			
			if(!retypePassword.equals(password)) {
				passwordStatus.setText("*Passwords do not match.");
			}
			else
				passwordStatus.setText("");
		}
		});
		JLabel lblGender = new JLabel("Gender : ");
		lblGender.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 20));
		lblGender.setBounds(418, 54, 97, 46);
		contentPane.add(lblGender);
		
		JRadioButton rdbtnMale = new JRadioButton("Male");
		rdbtnMale.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 20));
		rdbtnMale.setBackground(new Color(130, 182, 234));
		rdbtnMale.setBounds(418, 93, 103, 21);
		contentPane.add(rdbtnMale);
		
		
		JRadioButton rdbtnFemale = new JRadioButton("Female");
		rdbtnFemale.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 20));
		rdbtnFemale.setBackground(new Color(130, 182, 234));
		rdbtnFemale.setBounds(534, 93, 103, 21);
		contentPane.add(rdbtnFemale);
		
		ButtonGroup genderGroup = new ButtonGroup();
		genderGroup.add(rdbtnMale);
		genderGroup.add(rdbtnFemale);

		final String[] gender = {null}; 

		rdbtnMale.addItemListener(e -> {
		    if (e.getStateChange() == ItemEvent.SELECTED) {
		        gender[0] = "M";
		    }
		});

		rdbtnFemale.addItemListener(e -> {
		    if (e.getStateChange() == ItemEvent.SELECTED) {
		        gender[0] = "F";
		    }
		});
		
		JButton btnRegister = new JButton("REGISTER");
		btnRegister.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        customer = new Customer();
		        customer.setCustomer_username(txtUsername.getText());
		        customer.setPassword(password);
		        customer.setCustomer_name(txtName.getText());
		        customer.setCustomer_address(txtAddress.getText());
		        customer.setCustomer_email(txtEmail.getText());
		        customer.setCustomer_phone(txtContactNumber.getText());
		        customer.setStatus("Active");
		        customer.setGender(gender[0]);
		        
		        // Try to register the customer and check the result
		        try {
		            CustomerController customerController = new CustomerController();
		            boolean isRegistered = customerController.UpdateRegistration(customer);
		            
		            // If registration is successful, show a success message
		            if (isRegistered) {
		                JOptionPane.showMessageDialog(null, "Registration Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
		                dispose();
		                CustomerLoginView loginView = new CustomerLoginView();
		                loginView.setVisible(true);
		            } else {
		                // If registration fails, show an error message
		                JOptionPane.showMessageDialog(null, "Registration Failed. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
		            }
		        } catch (ClassNotFoundException | SQLException ex) {
		            ex.printStackTrace();
		            JOptionPane.showMessageDialog(null, "An error occurred. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});

		btnRegister.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
		btnRegister.setBackground(new Color(192, 192, 192));
		btnRegister.setBounds(303, 346, 126, 21);
		contentPane.add(btnRegister);		
	}

	

}
