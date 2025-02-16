//CustomerMainMenu
package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CustomerMainMenu extends JFrame 
{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try {
					int idnum = 0;
					CustomerMainMenu frame = new CustomerMainMenu(idnum);
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
	public CustomerMainMenu(int idcore) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 804, 416);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(130, 182, 234));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CUSTOMER DASHBOARD");
		lblNewLabel.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 24));
		lblNewLabel.setBounds(246, 21, 335, 66);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Display Your Information");
		btnNewButton.setBackground(new Color(192, 192, 192));
		btnNewButton.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 18));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				CustomerAcc frame = new CustomerAcc(idcore);
				frame.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(30, 98, 315, 48);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("Manage Booking");
		btnNewButton_2.setBackground(new Color(192, 192, 192));
		btnNewButton_2.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 18));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_2.setBounds(30, 247, 315, 54);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("View Payment");
		btnNewButton_3.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 18));
		btnNewButton_3.setBackground(new Color(192, 192, 192));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_3.setBounds(30, 174, 315, 48);
		contentPane.add(btnNewButton_3);
		
		JButton btnViewBooking = new JButton("View Booking");
		btnViewBooking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnViewBooking.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 18));
		btnViewBooking.setBackground(Color.LIGHT_GRAY);
		btnViewBooking.setBounds(430, 98, 315, 48);
		contentPane.add(btnViewBooking);
		
		JButton btnNewButton_1_1 = new JButton("Display Your Information");
		btnNewButton_1_1.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 18));
		btnNewButton_1_1.setBackground(Color.LIGHT_GRAY);
		btnNewButton_1_1.setBounds(430, 174, 315, 48);
		contentPane.add(btnNewButton_1_1);
		
		JButton btnNewButton_1_2 = new JButton("Display Your Information");
		btnNewButton_1_2.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 18));
		btnNewButton_1_2.setBackground(Color.LIGHT_GRAY);
		btnNewButton_1_2.setBounds(430, 253, 315, 48);
		contentPane.add(btnNewButton_1_2);
	}
}
