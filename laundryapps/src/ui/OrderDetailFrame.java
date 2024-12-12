package ui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.text.SimpleDateFormat;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;

import DAO.CustomerRepo;
import DAO.OrderDetailRepo;
import DAO.OrderRepo;
import DAO.ServiceRepo;
import model.Costumer;
import model.Order;
import model.OrderDetail;
import model.Service;
import table.TableCustomer;
import table.TableOrderDetail;
import table.TableService;
import DAO.ServiceDAO;
import DAO.ServiceRepo;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class OrderDetailFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtidOrder;
	private JTextField txtTanggal;
	private JTextField txtTanggalPeng;
	private JTextField textHargakg;
	private JTextField textJumlah;
	private JTextField textTotal;
	private JTable tableLayanan;
	private JLabel lblRp; 
	private JSpinner spinnerTanggalPeng;
	private OrderRepo orderRepo;
	private JSpinner spinnerTanggal;
	private TableOrderDetail tableOrderDetailModel;
	private JComboBox<String> comboboxStatus;
	private JComboBox<String> comboboxPembayaran;
	private JComboBox<String> comboBoxStatusPembayaran;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderDetailFrame frame = new OrderDetailFrame();
					frame.setVisible(true);
					frame.loadTable();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public OrderDetailFrame() {
		orderRepo = new OrderRepo();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 677, 599);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		spinnerTanggal = new JSpinner(new SpinnerDateModel());
	    JSpinner.DateEditor editorTanggal = new JSpinner.DateEditor(spinnerTanggal, "dd/MM/yyyy");
	    spinnerTanggal.setEditor(editorTanggal);
	    spinnerTanggal.setBounds(10, 140, 190, 20); 
	    contentPane.add(spinnerTanggal);

	    spinnerTanggalPeng = new JSpinner(new SpinnerDateModel());
	    JSpinner.DateEditor editorTanggalPeng = new JSpinner.DateEditor(spinnerTanggalPeng, "dd/MM/yyyy");
	    spinnerTanggalPeng.setEditor(editorTanggalPeng);
	    spinnerTanggalPeng.setBounds(10, 192, 190, 20); 
	    contentPane.add(spinnerTanggalPeng);
		
		JLabel lblNewLabel = new JLabel("Order ID");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(10, 11, 89, 20);
		contentPane.add(lblNewLabel);
		
		JLabel lblPelanggan = new JLabel("Pelanggan");
		lblPelanggan.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPelanggan.setBounds(10, 65, 89, 20);
		contentPane.add(lblPelanggan);
		
		txtidOrder = new JTextField();
		txtidOrder.setBounds(10, 34, 190, 20);
		contentPane.add(txtidOrder);
		txtidOrder.setColumns(10);
		
		 txtidOrder.setText(orderRepo.generateNewOrderId());
		
		JLabel lblTanggal = new JLabel("Tanggal");
		lblTanggal.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTanggal.setBounds(10, 119, 150, 20);
		contentPane.add(lblTanggal);
		
		txtTanggal = new JTextField();
		txtTanggal.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        txtTanggal.setText(((JSpinner.DateEditor) spinnerTanggal.getEditor()).getFormat().format(spinnerTanggal.getValue()));
		        spinnerTanggal.setVisible(true);
		    }
		});
		txtTanggal.setColumns(10);
		txtTanggal.setBounds(10, 140, 190, 20);
		contentPane.add(txtTanggal);
		
		JLabel lblTanggalPengembalian = new JLabel("Tanggal Pengembalian");
		lblTanggalPengembalian.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTanggalPengembalian.setBounds(10, 170, 150, 20);
		contentPane.add(lblTanggalPengembalian);
		
		txtTanggalPeng = new JTextField();
		txtTanggalPeng.setColumns(10);
		txtTanggalPeng.setBounds(10, 192, 190, 20);
		contentPane.add(txtTanggalPeng);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblStatus.setBounds(10, 226, 89, 20);
		contentPane.add(lblStatus);
		
		comboboxStatus = new JComboBox();
		comboboxStatus.setModel(new DefaultComboBoxModel(new String[] {"Proses"}));
		comboboxStatus.setBounds(10, 252, 190, 22);
		contentPane.add(comboboxStatus);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setBounds(10, 301, 89, 20);
		contentPane.add(lblTotal);
		
		JLabel lblRp = new JLabel("Rp.");
		lblRp.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblRp.setBounds(10, 320, 89, 20);
		contentPane.add(lblRp);
		
		JLabel lblPembayaran = new JLabel("Pembayaran");
		lblPembayaran.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPembayaran.setBounds(10, 349, 89, 20);
		contentPane.add(lblPembayaran);
		
		comboboxPembayaran = new JComboBox();
		comboboxPembayaran.setModel(new DefaultComboBoxModel(new String[] {"Cash"}));
		comboboxPembayaran.setBounds(10, 368, 190, 22);
		contentPane.add(comboboxPembayaran);
		
		JLabel lblStatusPembayaran = new JLabel("Status Pembayaran");
		lblStatusPembayaran.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblStatusPembayaran.setBounds(10, 401, 150, 20);
		contentPane.add(lblStatusPembayaran);
		
		comboBoxStatusPembayaran = new JComboBox();
		comboBoxStatusPembayaran.setModel(new DefaultComboBoxModel(new String[] {"Lunas"}));
		comboBoxStatusPembayaran.setBounds(10, 420, 190, 22);
		contentPane.add(comboBoxStatusPembayaran);
		
		JButton btnSimpanOrder = new JButton("Simpan");
		btnSimpanOrder.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String orderId = txtidOrder.getText().trim();
		        if (orderId.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "ID Order tidak boleh kosong!", "Error", JOptionPane.ERROR_MESSAGE);
		            return;
		        }

		        
		        String nama = txtPelanggan.getText().trim();
		        Date tanggal = (Date) spinnerTanggal.getValue();
		        Date tanggalPeng = (Date) spinnerTanggalPeng.getValue();
		        String status = comboboxStatus.getSelectedItem().toString();
		        String pembayaran = comboboxPembayaran.getSelectedItem().toString();
		        String statusPembayaran = comboBoxStatusPembayaran.getSelectedItem().toString();
		        String totalText = lblRp.getText().trim();

		        
		        Order order = new Order();
		        order.setId(orderId); 
		        order.setNama_costumer(nama);
		        
		        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		        order.setTanggal(sdf.format(tanggal));
		        order.setTanggal_selesai(sdf.format(tanggalPeng));	    
		        order.setStatus(status);
		        order.setTotal(totalText);
		        order.setpembayaran(pembayaran);
		        order.setStatus_pembayaran(statusPembayaran);

		        
		        OrderRepo orderRepo = new OrderRepo();
		        if (orderRepo.getOrderById(orderId) != null) {
		            orderRepo.update(order); 
		            JOptionPane.showMessageDialog(null, "Order berhasil diperbarui!", "Informasi", JOptionPane.INFORMATION_MESSAGE);
		        } else {
		            orderRepo.save(order);
		            JOptionPane.showMessageDialog(null, "Order berhasil disimpan!", "Informasi", JOptionPane.INFORMATION_MESSAGE);
		        }

		        reset();
		        dispose();
		    }
		});

		btnSimpanOrder.setBounds(10, 473, 89, 23);
		contentPane.add(btnSimpanOrder);
		
		JButton btnNewButton_1 = new JButton("Batal");
		btnNewButton_1.setBounds(109, 473, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblLayanan = new JLabel("Layanan");
		lblLayanan.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblLayanan.setBounds(277, 14, 89, 20);
		contentPane.add(lblLayanan);
		
		JLabel lblHargakg = new JLabel("HargaKg");
		lblHargakg.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblHargakg.setBounds(277, 143, 150, 20);
		contentPane.add(lblHargakg);
		
		textHargakg = new JTextField();
		textHargakg.setColumns(10);
		textHargakg.setBounds(277, 170, 126, 20);
		contentPane.add(textHargakg);
		
		JLabel lblJumlah = new JLabel("Jumlah");
		lblJumlah.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblJumlah.setBounds(277, 195, 150, 20);
		contentPane.add(lblJumlah);
		
		textJumlah = new JTextField();
		textJumlah.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				double hargaKg = Double.parseDouble(textHargakg.getText());
		        int jumlah = Integer.parseInt(textJumlah.getText());
		        int total = (int) (hargaKg * jumlah); 
		        
		        System.out.println("Harga per Kg: " + hargaKg);
		        System.out.println("Jumlah: " + jumlah);
		        System.out.println("Total yang dihitung: " + total);
		        textTotal.setText(String.valueOf(total));
			}
		});
		textJumlah.setColumns(10);
		textJumlah.setBounds(277, 215, 126, 20);
		contentPane.add(textJumlah);
		
		textTotal = new JTextField();
		textTotal.setColumns(10);
		textTotal.setBounds(453, 215, 175, 20);
		contentPane.add(textTotal);
		
		JLabel lblTotal_1 = new JLabel("Total");
		lblTotal_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTotal_1.setBounds(453, 195, 150, 20);
		contentPane.add(lblTotal_1);
		
		JButton btnSave = new JButton("Simpan");
		btnSave.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String orderId = txtidOrder.getText().trim(); 
		        
		        if (orderId.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "ID Order tidak boleh kosong!", "Error", JOptionPane.ERROR_MESSAGE);
		            return; 
		        }
		        
		        
		        OrderDetail detail = new OrderDetail();
		        detail.setIdLayanan(id);
		        detail.setIdOrder(orderId);
		        int jumlah = Integer.parseInt(textJumlah.getText());
		        detail.setJumlah(jumlah);
		        int total = (int) Double.parseDouble(textTotal.getText());
		        detail.setTotal(total);
		        orderDetailRepo.save(detail);
		        
		        
		        reset();
		        loadTableOrderDetail(orderId); 
		        lblRp.setText("Rp. " + orderDetailRepo.total(orderId));
		    }
		});
		btnSave.setBounds(277, 252, 89, 23);
		contentPane.add(btnSave);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (id1 == null) {
		            JOptionPane.showMessageDialog(null, "Silahkan pilih detail yang akan diupdate", "Error", JOptionPane.ERROR_MESSAGE);
		            return;
		        }

		        OrderDetail detail = new OrderDetail();
		        detail.setIdOrderDetail(id1);
		        detail.setIdLayanan(id);
		        detail.setIdOrder(txtidOrder.getText());
		        int jumlah = Integer.parseInt(textJumlah.getText());
		        detail.setJumlah(jumlah);
		        double total = Double.parseDouble(textTotal.getText());
		        detail.setTotal(total);
		        
		        orderDetailRepo.update(detail);
		        
		        String orderId = txtidOrder.getText(); 
		        lblRp.setText("Rp. " + orderDetailRepo.total(orderId));
		        reset();
		        loadTableOrderDetail(orderId);
        	}
		});
		btnUpdate.setBounds(376, 252, 89, 23);
		contentPane.add(btnUpdate);
		
		JButton btnDelete = new JButton("Hapus");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (id1 != null) {
		            orderDetailRepo.delete(id1);
		            String orderId = txtidOrder.getText();
		            lblRp.setText("Rp. " + orderDetailRepo.total(orderId));
		            reset();
		            loadTableOrderDetail(orderId);
		        } else {
		            JOptionPane.showMessageDialog(null, "Silahkan Pilih Data yang Akan di Hapus");
		        }
        	}
		});
		btnDelete.setBounds(478, 252, 89, 23);
		contentPane.add(btnDelete);
		
		JButton btnBatal = new JButton("Batal");
		btnBatal.setBounds(572, 252, 89, 23);
		contentPane.add(btnBatal);
		
		tableLayanan = new JTable();
		tableLayanan.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 id = tableLayanan.getValueAt(tableLayanan.getSelectedRow(), 0).toString();
	                textHargakg.setText(tableLayanan.getValueAt(tableLayanan.getSelectedRow(), 2).toString());
			}
		});
		tableLayanan.setBounds(276, 45, 323, 100);
		contentPane.add(tableLayanan);
		
		
		tableOrderDetail = new JTable();
		tableOrderDetail.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				id = tableLayanan.getValueAt(tableLayanan.getSelectedRow(), 2).toString();
				id1 = tableOrderDetail.getValueAt(tableOrderDetail.getSelectedRow(), 0).toString(); 
		        txtidOrder.setText(tableOrderDetail.getValueAt(tableOrderDetail.getSelectedRow(), 1).toString()); 
		        textJumlah.setText(tableOrderDetail.getValueAt(tableOrderDetail.getSelectedRow(), 3).toString());
		        textTotal.setText(tableOrderDetail.getValueAt(tableOrderDetail.getSelectedRow(), 4).toString());
            }
		});
		tableOrderDetail.setBounds(277, 304, 323, 138);
		contentPane.add(tableOrderDetail);
		
		JScrollPane scrollPane = new JScrollPane(tableOrderDetail);
        scrollPane.setBounds(277, 285, 376, 178);
        contentPane.add(scrollPane);
        scrollPane.setViewportView(tableOrderDetail);
        
        JScrollPane scrollPane1 = new JScrollPane(tableLayanan);
        scrollPane1.setBounds(277, 41, 376, 98);
        contentPane.add(scrollPane1);
        
        txtPelanggan = new JTextField();
        txtPelanggan.setBounds(10, 95, 190, 19);
        contentPane.add(txtPelanggan);
        txtPelanggan.setColumns(10);
        txtPelanggan.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	CustomerFrame customerFrame = new CustomerFrame(OrderDetailFrame.this);
                customerFrame.setVisible(true);
                customerFrame.loadTable();
                
            }
        });
	}
	public void reset() {
        textJumlah.setText("");
        textTotal.setText("");
    }
    ServiceRepo srvc = new ServiceRepo();
    List<Service> ls;
    public String id;
    
    OrderDetailRepo orderDetailRepo = new OrderDetailRepo();
    List<OrderDetail> ls1;
    public String id1;
    private JTable tableOrderDetail;
    private JTextField txtPelanggan;
    
    public void loadTableOrderDetail(String orderId) {
        ls1 = orderDetailRepo.showByOrderId(orderId); 
        TableOrderDetail tu = new TableOrderDetail(ls1);
        tableOrderDetail.setModel(tu);
        tableOrderDetail.getTableHeader().setVisible(true);
    }

    public void loadTable() {
        ls = srvc.show();
        TableService tu = new TableService(ls);
        tableLayanan.setModel(tu);
        tableLayanan.getTableHeader().setVisible(true);
    }
    
    CustomerRepo cstmr = new CustomerRepo();
    List<Costumer> ls2;
    public String id2;
    private JTable tableCustomer;

    public void loadTableCostumer() {
        ls2 = cstmr.show();
        TableCustomer tu = new TableCustomer(ls2);
        tableCustomer.setModel(tu);
        tableCustomer.getTableHeader().setVisible(true);
    }
    public JTextField getTxtPelanggan() {
        return txtPelanggan;
    }
    
    public OrderDetailFrame(Order order) {
        this();
        loadOrderDetails(order); 
        loadTable();
    }

    private void loadOrderDetails(Order order) {
        txtidOrder.setText(order.getId()); 
        txtPelanggan.setText(order.getNama_costumer()); 
        txtTanggal.setText(order.getTanggal()); 
        txtTanggalPeng.setText(order.getTanggal_selesai()); 
        comboboxStatus.setSelectedItem(order.getStatus()); 
        comboboxPembayaran.setSelectedItem(order.getpembayaran());
        comboBoxStatusPembayaran.setSelectedItem(order.getStatus_pembayaran()); 
    }

    
    

}