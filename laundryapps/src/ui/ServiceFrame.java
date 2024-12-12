package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import DAO.CustomerRepo;
import DAO.ServiceRepo;
import DAO.UserRepo;
import model.Costumer;
import model.Service;
import model.User;
import table.TableService;
import table.TableUser;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ServiceFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtJenis;
	private JTextField txtHarga;
	private JTextField txtStatus;
	private JTable tableService;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServiceFrame frame = new ServiceFrame();
					frame.setVisible(true);
					frame.loadTable();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ServiceFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 724, 484);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Jenis");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(31, 32, 51, 22);
		contentPane.add(lblNewLabel);
		
		JLabel lblAlamat = new JLabel("Harga");
		lblAlamat.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAlamat.setBounds(31, 68, 51, 22);
		contentPane.add(lblAlamat);
		
		JLabel lblNohp = new JLabel("Status");
		lblNohp.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNohp.setBounds(31, 109, 51, 22);
		contentPane.add(lblNohp);
		
		txtJenis = new JTextField();
		txtJenis.setBounds(92, 35, 431, 20);
		contentPane.add(txtJenis);
		txtJenis.setColumns(10);
		
		txtHarga = new JTextField();
		txtHarga.setColumns(10);
		txtHarga.setBounds(92, 71, 431, 20);
		contentPane.add(txtHarga);
		
		txtStatus = new JTextField();
		txtStatus.setColumns(10);
		txtStatus.setBounds(92, 112, 431, 20);
		contentPane.add(txtStatus);
		
		tableService = new JTable();
		tableService.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
                id = tableService.getValueAt(tableService.getSelectedRow(), 0).toString();
                txtJenis.setText(tableService.getValueAt(tableService.getSelectedRow(), 1).toString());
                Double harga = (Double) tableService.getValueAt(tableService.getSelectedRow(), 2);
                txtHarga.setText(String.valueOf(harga.intValue()));
                txtStatus.setText(tableService.getValueAt(tableService.getSelectedRow(), 3).toString());
            }
		});
		tableService.setBounds(10, 194, 688, 181);
		contentPane.add(tableService);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Service service = new Service();
				service.setJenis(txtJenis.getText());
				service.setHarga(txtHarga.getText());
                service.setStatus(txtStatus.getText());
                srvc.save(service);
                reset();
                loadTable();
			}
		});
		btnSave.setBounds(54, 160, 89, 23);
		contentPane.add(btnSave);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Service service = new Service();
				service.setId(id);
				service.setJenis(txtJenis.getText());
				service.setHarga(txtHarga.getText());
                service.setStatus(txtStatus.getText());
                srvc.update(service);
                reset();
                loadTable();
        	}
		});
		btnUpdate.setBounds(153, 160, 89, 23);
		contentPane.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
        		if(id != null) {
        			srvc.delete(id);
        			reset();
        			loadTable();
        		}else {
        			JOptionPane.showMessageDialog(null, "Silahkan Pilih Data yang Akan di Hapus");
        		}
        	}
		});
		btnDelete.setBounds(375, 160, 89, 23);
		contentPane.add(btnDelete);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(474, 160, 89, 23);
		contentPane.add(btnCancel);
		
		JScrollPane scrollPane = new JScrollPane(tableService);
        scrollPane.setBounds(10, 206, 674, 249);
        contentPane.add(scrollPane);
	}
	
	public void reset() {
        txtJenis.setText("");
        txtHarga.setText("");
        txtStatus.setText("");
    }

    ServiceRepo srvc = new ServiceRepo();
    List<Service> ls;
    public String id;

    public void loadTable() {
        ls = srvc.show();
        TableService tu = new TableService(ls);
        tableService.setModel(tu);
        tableService.getTableHeader().setVisible(true);
    }
}
