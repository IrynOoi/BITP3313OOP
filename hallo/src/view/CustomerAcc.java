//CustomerAcc.java
package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.CustomerController;
import model.Customer;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.JButton;

public class CustomerAcc extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel CusIDTextField;
	private JTextField CusTxt;
	private JTextField Cus_nametxt;
	private JTextField Username_txt;
	private JTextField Phone_no_txt;
	private JTextField Emailtxt;
	private JTextField Addresstxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					int idnum=0;
					CustomerAcc frame = new CustomerAcc(idnum);
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
	public CustomerAcc(int idnum) 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 817, 503);
		CusIDTextField = new JPanel();
		CusIDTextField.setBackground(new Color(130, 182, 234));
		CusIDTextField.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(CusIDTextField);
		CusIDTextField.setLayout(null);
		
		JLabel Title = new JLabel("CUSTOMER ACCOUNT INFORMATION");
		Title.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 21));
		Title.setBounds(228, 11, 303, 75);
		CusIDTextField.add(Title);
		
		JLabel CustomerID = new JLabel("Customer ID :");
		CustomerID.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 20));
		CustomerID.setBounds(57, 114, 136, 26);
		CusIDTextField.add(CustomerID);
		
		JLabel Customer_Name = new JLabel("Customer Name :");
		Customer_Name.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 20));
		Customer_Name.setBounds(46, 182, 136, 26);
		CusIDTextField.add(Customer_Name);
		
		JLabel Username = new JLabel("Username :");
		Username.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 20));
		Username.setBounds(46, 234, 104, 26);
		CusIDTextField.add(Username);
		
		JLabel lblNewLabel_3 = new JLabel("Email :");
		lblNewLabel_3.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(46, 348, 147, 26);
		CusIDTextField.add(lblNewLabel_3);
		
		JLabel Phone_no = new JLabel("Phone Number :");
		Phone_no.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 20));
		Phone_no.setBounds(34, 290, 147, 26);
		CusIDTextField.add(Phone_no);
		
		JLabel Address = new JLabel("Address :");
		Address.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 20));
		Address.setBounds(46, 405, 147, 26);
		CusIDTextField.add(Address);
		
		CusTxt = new JTextField();
		CusTxt.setBounds(203, 97, 362, 37);
		CusIDTextField.add(CusTxt);
		CusTxt.setColumns(10);
		
		Cus_nametxt = new JTextField();
		Cus_nametxt.setColumns(10);
		Cus_nametxt.setBounds(203, 161, 362, 37);
		CusIDTextField.add(Cus_nametxt);
		
		Username_txt = new JTextField();
		Username_txt.setColumns(10);
		Username_txt.setBounds(203, 232, 362, 37);
		CusIDTextField.add(Username_txt);
		
		Phone_no_txt = new JTextField();
		Phone_no_txt.setColumns(10);
		Phone_no_txt.setBounds(203, 288, 362, 37);
		CusIDTextField.add(Phone_no_txt);
		
		Emailtxt = new JTextField();
		Emailtxt.setColumns(10);
		Emailtxt.setBounds(203, 346, 362, 37);
		CusIDTextField.add(Emailtxt);
		
		Addresstxt = new JTextField();
		Addresstxt.setColumns(10);
		Addresstxt.setBounds(203, 403, 362, 37);
		CusIDTextField.add(Addresstxt);
		
		
		CusTxt.setEditable(false);
		Cus_nametxt.setEditable(false);
		Username_txt.setEditable(false);
		Phone_no_txt.setEditable(false);
		Emailtxt.setEditable(false);
		Addresstxt.setEditable(false);
		
		JButton BACKbtn = new JButton("BACK");
		BACKbtn.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 20));
		BACKbtn.setBackground(new Color(192, 192, 192));
		BACKbtn.setBounds(660, 410, 117, 43);
		CusIDTextField.add(BACKbtn);
		BACKbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				CustomerMainMenu frame = new CustomerMainMenu(idnum);
                 frame.setVisible(true);
                 dispose();
			}
		});
		
		
		try {
		    Customer customer = new Customer();
		    customer.setCustomerID(idnum);

		    CustomerController customerController = new CustomerController();
		    Customer fetchedCustomer = customerController.showProfile(customer);

		    // Check if the result is null
		    if (fetchedCustomer != null) {
		        CusTxt.setText(Integer.toString(fetchedCustomer.getCustomerID()));
		        Cus_nametxt.setText(fetchedCustomer.getCustomer_name());
		        Phone_no_txt.setText(fetchedCustomer.getCustomer_phone());
		        Emailtxt.setText(fetchedCustomer.getCustomer_email());
		        Addresstxt.setText(fetchedCustomer.getCustomer_address());
		        Username_txt.setText(fetchedCustomer.getCustomer_username());
		    } else {
		        JOptionPane.showMessageDialog(this, "Customer not found!", "Error", JOptionPane.ERROR_MESSAGE);
		    }
		} catch (ClassNotFoundException e) {
		    e.printStackTrace();
		    JOptionPane.showMessageDialog(this, "Database driver not found. Please contact support.", "Database Error", JOptionPane.ERROR_MESSAGE);
		} catch (SQLException e) {
		    e.printStackTrace();
		    JOptionPane.showMessageDialog(this, "An error occurred while accessing the database: " + e.getMessage(), "SQL Error", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e) {
		    e.printStackTrace();
		    JOptionPane.showMessageDialog(this, "An unexpected error occurred: " + e.getMessage(), "Unexpected Error", JOptionPane.ERROR_MESSAGE);
		}


		
		
	}
}
