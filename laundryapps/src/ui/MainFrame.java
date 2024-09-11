package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
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
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 502, 324);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Laundry Apps");
		lblNewLabel.setBounds(33, 23, 95, 17);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("PESANAN");
		btnNewButton.setBounds(22, 58, 111, 54);
		contentPane.add(btnNewButton);
		
		JButton btnLayanan = new JButton("LAYANAN");
		btnLayanan.setBounds(183, 58, 111, 54);
		contentPane.add(btnLayanan);
		
		JButton btnPelanggan = new JButton("PELANGGAN");
		btnPelanggan.setBounds(345, 58, 111, 54);
		contentPane.add(btnPelanggan);
		
		JButton btnPengguna = new JButton("PENGGUNA");
		btnPengguna.setBounds(22, 138, 111, 54);
		contentPane.add(btnPengguna);
		
		JButton btnLaporan = new JButton("LAPORAN");
		btnLaporan.setBounds(183, 138, 111, 54);
		contentPane.add(btnLaporan);
		
		JButton btnProfile = new JButton("PROFILE");
		btnProfile.setBounds(345, 138, 111, 54);
		contentPane.add(btnProfile);
		
		JButton btnNewButton_1 = new JButton("KELUAR");
		btnNewButton_1.setBounds(22, 212, 434, 37);
		contentPane.add(btnNewButton_1);
	}
}
