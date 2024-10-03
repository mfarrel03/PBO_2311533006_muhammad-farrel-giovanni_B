package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import DAO.CostumerRepo;
import DAO.ServiceRepo;
import DAO.UserRepo;
import model.costumer;
import model.service;
import model.User;
import table.TableCostumer;
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

public class CostumerFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNama;
	private JTextField txtAlamat;
	private JTextField txtNohp;
	private JTable tableCostumer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CostumerFrame frame = new CostumerFrame();
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
	public CostumerFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 724, 484);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nama");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(31, 32, 51, 22);
		contentPane.add(lblNewLabel);
		
		JLabel lblAlamat = new JLabel("Alamat");
		lblAlamat.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAlamat.setBounds(31, 68, 51, 22);
		contentPane.add(lblAlamat);
		
		JLabel lblNohp = new JLabel("NoHp");
		lblNohp.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNohp.setBounds(31, 109, 51, 22);
		contentPane.add(lblNohp);
		
		txtNama = new JTextField();
		txtNama.setBounds(92, 35, 431, 20);
		contentPane.add(txtNama);
		txtNama.setColumns(10);
		
		txtAlamat = new JTextField();
		txtAlamat.setColumns(10);
		txtAlamat.setBounds(92, 71, 431, 20);
		contentPane.add(txtAlamat);
		
		txtNohp = new JTextField();
		txtNohp.setColumns(10);
		txtNohp.setBounds(92, 112, 431, 20);
		contentPane.add(txtNohp);
		
		tableCostumer = new JTable();
		tableCostumer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
                id = tableCostumer.getValueAt(tableCostumer.getSelectedRow(), 0).toString();
                txtNama.setText(tableCostumer.getValueAt(tableCostumer.getSelectedRow(), 1).toString());
                txtAlamat.setText(tableCostumer.getValueAt(tableCostumer.getSelectedRow(), 2).toString());
                txtNohp.setText(tableCostumer.getValueAt(tableCostumer.getSelectedRow(), 3).toString());
            }
		});
		tableCostumer.setBounds(10, 194, 688, 228);
		contentPane.add(tableCostumer);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				costumer costumer = new costumer();
				costumer.setNama(txtNama.getText());
				costumer.setAlamat(txtAlamat.getText());
				costumer.setNomor_hp(txtNohp.getText());
                cstmr.save(costumer);
                reset();
                loadTable();
			}
		});
		btnSave.setBounds(54, 160, 89, 23);
		contentPane.add(btnSave);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				costumer costumer = new costumer();
				costumer.setNama(txtNama.getText());
				costumer.setAlamat(txtAlamat.getText());
				costumer.setNomor_hp(txtNohp.getText());
                cstmr.update(costumer);
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
        			cstmr.delete(id);
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
		
		JScrollPane scrollPane = new JScrollPane(tableCostumer);
        scrollPane.setBounds(10, 206, 674, 249);
        contentPane.add(scrollPane);
	}
	
	public void reset() {
        txtNama.setText("");
        txtAlamat.setText("");
        txtNohp.setText("");
    }

    CostumerRepo cstmr = new CostumerRepo();
    List<costumer> ls;
    public String id;

    public void loadTable() {
        ls = cstmr.show();
        TableCostumer tu = new TableCostumer(ls);
        tableCostumer.setModel(tu);
        tableCostumer.getTableHeader().setVisible(true);
    }
}