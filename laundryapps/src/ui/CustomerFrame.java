package ui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import DAO.CustomerRepo;
import model.Costumer;
import table.TableCustomer;

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

public class CustomerFrame extends JFrame {
    private OrderDetailFrame orderDetailFrame;

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtNama;
    private JTextField txtAlamat;
    private JTextField txtNohp;
    private JTable tableCostumer;
    public String id; // Untuk menyimpan ID pelanggan yang terpilih
    private CustomerRepo cstmr = new CustomerRepo(); // Inisialisasi repositori pelanggan
    private List<Costumer> ls; // List untuk menyimpan data pelanggan

    public CustomerFrame(OrderDetailFrame orderDetailFrame) {
        this.orderDetailFrame = orderDetailFrame;
        initialize(); // Panggil metode untuk menginisialisasi GUI
        loadTable(); // Muat data pelanggan ke dalam tabel setelah inisialisasi
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CustomerFrame frame = new CustomerFrame(new OrderDetailFrame());
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initialize() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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

        // Inisialisasi tableCostumer
        tableCostumer = new JTable();
        tableCostumer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = tableCostumer.getSelectedRow();
                if (selectedRow != -1) {
                    id = tableCostumer.getValueAt(selectedRow, 0).toString();
                    txtNama.setText(tableCostumer.getValueAt(selectedRow, 1).toString());
                    txtAlamat.setText(tableCostumer.getValueAt(selectedRow, 2).toString());
                    txtNohp.setText(tableCostumer.getValueAt(selectedRow, 3).toString());
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(tableCostumer);
        scrollPane.setBounds(10, 194, 688, 228);
        contentPane.add(scrollPane);

        JButton btnSave = new JButton("Save");
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Costumer costumer = new Costumer();
                costumer.setNama(txtNama.getText());
                costumer.setAlamat(txtAlamat.getText());
                costumer.setNohp(txtNohp.getText());
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
                Costumer costumer = new Costumer();
                costumer.setId(id);
                costumer.setNama(txtNama.getText());
                costumer.setAlamat(txtAlamat.getText());
                costumer.setNohp(txtNohp.getText());
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
                if (id != null) {
                    cstmr.delete(id);
                    reset();
                    loadTable();
                } else {
                    JOptionPane.showMessageDialog(null, "Silahkan Pilih Data yang Akan di Hapus");
                }
            }
        });
        btnDelete.setBounds(375, 160, 89, 23);
        contentPane.add(btnDelete);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.setBounds(474, 160, 89, 23);
        contentPane.add(btnCancel);

        JButton btnAdd = new JButton("Add");
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addSelectedCustomer();
            }
        });
        btnAdd.setBounds(258, 161, 89, 23);
        contentPane.add(btnAdd);
    }

    public void reset() {
        txtNama.setText("");
        txtAlamat.setText("");
        txtNohp.setText("");
    }

    public void loadTable() {
        ls = cstmr.show();
        TableCustomer tu = new TableCustomer(ls);
        tableCostumer.setModel(tu);
        tableCostumer.getTableHeader().setVisible(true);
    }

    private void addSelectedCustomer() {
        int selectedRow = tableCostumer.getSelectedRow(); // Pastikan menggunakan tableCostumer
        if (selectedRow != -1) {
            Costumer selectedCustomer = ls.get(selectedRow);
            orderDetailFrame.getTxtPelanggan().setText(selectedCustomer.getNama());
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Silakan pilih pelanggan terlebih dahulu.");
        }
    }
}
