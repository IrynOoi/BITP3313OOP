package view;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginView frame = new LoginView();
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
	public LoginView() {
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
		
		JLabel lblNewLabel_1 = new JLabel("USERNAME :");
		lblNewLabel_1.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 18));
		lblNewLabel_1.setBounds(25, 140, 103, 48);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("PASSWORD :");
		lblNewLabel_2.setFont(new Font("Tw Cen MT Condensed", Font.BOLD, 18));
		lblNewLabel_2.setBounds(26, 200, 102, 54);
		contentPane.add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(123, 140, 253, 35);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(123, 210, 253, 36);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
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
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnOk.setBounds(248, 286, 125, 36);
		contentPane.add(btnOk);
	}
}
