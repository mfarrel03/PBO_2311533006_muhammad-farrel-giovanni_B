package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsername;
	private JTextField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
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
	public LoginFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (User.login(txtUsername.getText(), txtPassword.getText())) {
					new MainFrame().setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(null,"Login Gagal");
				
			}
		);
		btnLogin.setBounds(167, 229, 89, 23);
		contentPane.add(btnLogin);
		
		JLabel lblNewLabel = new JLabel("Laundry Apps");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(28, 0, 119, 31);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Males nyuci,biar kami cuciin");
		lblNewLabel_1.setBounds(28, 27, 158, 14);
		contentPane.add(lblNewLabel_1);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(28, 88, 182, 42);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("USSERNAME");
		lblNewLabel_2.setBounds(28, 63, 108, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("PASSWORD");
		lblNewLabel_3.setBounds(28, 141, 89, 14);
		contentPane.add(lblNewLabel_3);
		
		txtPassword = new JTextField();
		txtPassword.setBounds(21, 166, 189, 42);
		contentPane.add(txtPassword);
		txtPassword.setColumns(10);
	}
}
