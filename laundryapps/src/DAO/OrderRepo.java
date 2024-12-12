package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import config.Database;
import model.Order;


public class OrderRepo implements OrderDAO {
    private Connection connection;

    final String insert = "INSERT INTO `order` (id_order, nama, pembayaran, total, tanggal, tanggal_peng, status, status_pembayaran) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
    final String select = "SELECT * FROM `order`;";
    final String delete = "DELETE FROM `order` WHERE id_order=?;";
    final String update = "UPDATE `order` SET nama=?, pembayaran=?, total=?, tanggal=?, tanggal_peng=?, status=?, status_pembayaran=? WHERE id_order=?;";

    public OrderRepo() {
        connection = Database.koneksi();
    }

    @Override
    public void save(Order order) {
        PreparedStatement st = null;
        try {
           
            System.out.println("Menyimpan Order: ");
            System.out.println("ID: " + order.getId());
            System.out.println("Nama Customer: " + order.getNama_costumer());
            System.out.println("Pembayaran: " + order.getpembayaran());
            System.out.println("Tanggal: " + order.getTanggal());
            System.out.println("Tanggal Peng: " + order.getTanggal_selesai());
            System.out.println("Status: " + order.getStatus());
            System.out.println("Status Pembayaran: " + order.getStatus_pembayaran());
            System.out.println("Total sebelum konversi: " + order.getTotal());

            
            int total = Integer.parseInt(order.getTotal().replace("Rp. ", "").replace(".", "").trim());
            System.out.println("Total setelah konversi: " + total); 

            st = connection.prepareStatement(insert);
            st.setString(1, order.getId());
            st.setString(2, order.getNama_costumer());
            st.setString(3, order.getpembayaran());
            st.setInt(4, total); 
            st.setString(5, order.getTanggal());
            st.setString(6, order.getTanggal_selesai());
            st.setString(7, order.getStatus());
            st.setString(8, order.getStatus_pembayaran());
            st.executeUpdate(); 
            System.out.println("Order berhasil disimpan."); 

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Format total tidak valid: " + order.getTotal());
        } finally {
            try {
                if (st != null) st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Order> show() {
        List<Order> orders = new ArrayList<>();
        Statement st = null;
        ResultSet rs = null;
        try {
            st = connection.createStatement();
            rs = st.executeQuery(select);
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getString("id_order"));
                order.setNama_costumer(rs.getString("nama"));
                order.setTotal(rs.getString("total"));
                order.setTanggal(rs.getString("tanggal"));
                order.setTanggal_selesai(rs.getString("tanggal_peng"));
                order.setStatus(rs.getString("status"));
                order.setStatus_pembayaran(rs.getString("status_pembayaran"));
                orders.add(order);
            }
        } catch (SQLException e) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return orders;
    }

    @Override
    public void update(Order order) {
        PreparedStatement st = null;
        try {
            
            System.out.println("Memperbarui Order: ");
            System.out.println("ID: " + order.getId());
            System.out.println("Nama Customer: " + order.getNama_costumer());
            System.out.println("Pembayaran: " + order.getpembayaran());
            System.out.println("Tanggal: " + order.getTanggal());
            System.out.println("Tanggal Peng: " + order.getTanggal_selesai());
            System.out.println("Status: " + order.getStatus());
            System.out.println("Status Pembayaran: " + order.getStatus_pembayaran());
            System.out.println("Total sebelum update: " + order.getTotal());

            
            int total = Integer.parseInt(order.getTotal().replace("Rp. ", "").replace(".", "").trim());
            System.out.println("Total setelah konversi: " + total); 

            st = connection.prepareStatement(update);
            st.setString(1, order.getNama_costumer());
            st.setString(2, order.getpembayaran());
            st.setInt(3, total); 
            st.setString(4, order.getTanggal());
            st.setString(5, order.getTanggal_selesai());
            st.setString(6, order.getStatus());
            st.setString(7, order.getStatus_pembayaran());
            st.setString(8, order.getId());
            st.executeUpdate();
            System.out.println("Order berhasil diperbarui."); 

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Format total tidak valid: " + order.getTotal());
        } finally {
            try {
                if (st != null) st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(String id) {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(delete);
            st.setString(1, id);
            st.executeUpdate();
            System.out.println("Order dengan ID " + id + " berhasil dihapus.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public String getLastOrderId() {
        String lastOrderId = null;
        String query = "SELECT id_order FROM `order` ORDER BY id_order DESC LIMIT 1";

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                lastOrderId = resultSet.getString("id_order");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lastOrderId;
    }

    
    public String generateNewOrderId() {
        String lastId = getLastOrderId(); 
        if (lastId != null && lastId.startsWith("TRX-")) {
            int nextId = Integer.parseInt(lastId.substring(4)) + 1;
            return String.format("TRX-%04d", nextId);
        } else {
            return "TRX-0001"; 
        }
    }
    
    public Order getOrderById(String id) {
        String query = "SELECT * FROM `order` WHERE id_order = ?";
        Order order = null;
        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setString(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    order = new Order();
                    order.setId(rs.getString("id_order"));
                    order.setNama_costumer(rs.getString("nama"));
                    order.setTotal(rs.getString("total"));
                    order.setTanggal(rs.getString("tanggal"));
                    order.setTanggal_selesai(rs.getString("tanggal_peng"));
                    order.setStatus(rs.getString("status"));
                    order.setStatus_pembayaran(rs.getString("status_pembayaran"));
                    order.setpembayaran(rs.getString("pembayaran"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

}