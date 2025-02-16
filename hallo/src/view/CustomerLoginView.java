//CustomerLoginView
package view;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.CustomerController;
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
	private JTextField IDField;
	private  JTextField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerLoginView frame = new CustomerLoginView();
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
	public CustomerLoginView()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 745, 414);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(130, 182, 234));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("LOGIN MAIN MENU");
		lblNewLabel.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 27));
		lblNewLabel.setBounds(25, 11, 227, 83);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("CUSTOMER ID :");
		lblNewLabel_1.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 18));
		lblNewLabel_1.setBounds(10, 141, 103, 48);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("PASSWORD :");
		lblNewLabel_2.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 18));
		lblNewLabel_2.setBounds(25, 200, 102, 54);
		contentPane.add(lblNewLabel_2);
		
		IDField = new JTextField();
		IDField.setBounds(123, 140, 253, 35);
		contentPane.add(IDField);
		IDField.setColumns(10);
		
		passwordField = new JTextField();
		passwordField.setBounds(123, 210, 253, 36);
		contentPane.add(passwordField);
		passwordField.setColumns(10);
		
		 // Load and scale the image
        ImageIcon icon = new ImageIcon(this.getClass().getResource("/login icon.png"));
        Image img = icon.getImage();
        
        
        
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setBounds(441, 68, 261, 243);
	    Image scaledImg = img.getScaledInstance(lblNewLabel_3.getWidth(),  lblNewLabel_3 .getHeight(), Image.SCALE_SMOOTH);
	    lblNewLabel_3 .setIcon(new ImageIcon(scaledImg));
        
		contentPane.add(lblNewLabel_3);
		
		JButton btnNewButton = new JButton("BACK");
		btnNewButton.setBackground(new Color(192, 192, 192));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				 MainMenu frame = new MainMenu();
                 frame.setVisible(true);
                 dispose();
			}
		});
		btnNewButton.setBounds(82, 286, 125, 36);
		contentPane.add(btnNewButton);
		
		JButton btnOk = new JButton("OK");
		btnOk.setBackground(new Color(192, 192, 192));
		btnOk.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				int ID = Integer.parseInt(IDField.getText().trim());
				String Password = passwordField.getText().trim();

				Customer customer = new Customer();
				customer.setCustomerID(ID);
				customer.setPassword(Password);

				String role = "";
				 CustomerController custcontroller = new CustomerController();
				try {
					role = custcontroller.doLogin(customer);

					if (role == "-1") {
						JOptionPane.showMessageDialog(null, "You are not authorized!!!");
					} else {
						int idcore = Integer.parseInt( IDField.getText().trim());
						JOptionPane.showMessageDialog(null, "Successful!");
						CustomerMainMenu frame = new CustomerMainMenu(idcore);
						frame.setVisible(true);
						dispose();

					}

				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnOk.setBounds(248, 286, 125, 36);
		contentPane.add(btnOk);
	}
}
