package ui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTable;
import DAO.OrderRepo;
import model.Order;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener; 
import java.awt.event.ActionEvent; 

public class OrderFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable tableOrder;
    private OrderRepo orderRepo; 
    private List<Order> orders;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    OrderFrame frame = new OrderFrame();
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
    public OrderFrame() {
        orderRepo = new OrderRepo();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 953, 465);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Data Orderan");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel.setBounds(10, 27, 113, 28);
        contentPane.add(lblNewLabel);
        
        JButton btnBuatOrder = new JButton("Buat Orderan");
        btnBuatOrder.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnBuatOrder.setBounds(10, 52, 174, 54);
        contentPane.add(btnBuatOrder);
        
        JButton btnHapus = new JButton("Hapus");
        btnHapus.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnHapus.setBounds(630, 52, 131, 54);
        contentPane.add(btnHapus);
        
        JButton btnEdit = new JButton("Edit/Detail");
        btnEdit.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnEdit.setBounds(786, 52, 131, 54);
        contentPane.add(btnEdit);
        
        tableOrder = new JTable();
        tableOrder.setBounds(10, 118, 919, 300);
        contentPane.add(tableOrder);
        
        JScrollPane scrollPane = new JScrollPane(tableOrder);
        scrollPane.setBounds(10, 118, 919, 300); 
        contentPane.add(scrollPane); 
 

        btnEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tableOrder.getSelectedRow();
                if (selectedRow >= 0) {
                    String orderId = tableOrder.getValueAt(selectedRow, 0).toString();
                    Order selectedOrder = orderRepo.getOrderById(orderId);
                    if (selectedOrder != null) {
                        OrderDetailFrame detailFrame = new OrderDetailFrame(selectedOrder);
                        detailFrame.setVisible(true);
                        detailFrame.addWindowListener(new java.awt.event.WindowAdapter() {
                            @Override
                            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                                loadTable(); 
                            }
                        });
                    } else {
                        JOptionPane.showMessageDialog(null, "Data order tidak ditemukan!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Pilih baris data terlebih dahulu!", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

       
        btnBuatOrder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                OrderDetailFrame orderDetailFrame = new OrderDetailFrame();
                orderDetailFrame.setVisible(true); 
                dispose(); 
            }
        });
    }
    
    public void loadTable() {
        orders = orderRepo.show();
        String[] columnNames = {"ID Order", "Nama", "Tanggal", "Tanggal Selesai", "Status", "Status Pembayaran", "Total"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0); 

        for (Order order : orders) {
            System.out.println("Nama Costumer: " + order.getNama_costumer());
            Object[] rowData = {
                order.getId(), 
                order.getNama_costumer(), 
                order.getTanggal(),
                order.getTanggal_selesai(),
                order.getStatus(),
                order.getStatus_pembayaran(),
                order.getTotal()
            };
            model.addRow(rowData); 
        }

        tableOrder.setModel(model); 
        tableOrder.getTableHeader().setVisible(true); 
    }
    
    
}
