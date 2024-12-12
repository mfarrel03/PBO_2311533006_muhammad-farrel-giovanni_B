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
import model.OrderDetail;

public class OrderDetailRepo implements OrderDetailDAO {
    private Connection connection;

    final String insert = "INSERT INTO order_detail(id_order_detail, id_order, id_layanan, jumlah, total) VALUES(?,?,?,?,?);";
    final String select = "SELECT * FROM order_detail;";
    final String delete = "DELETE FROM order_detail WHERE id_order_detail=?;";
    final String update = "UPDATE order_detail SET id_order=?, id_layanan=?, jumlah=?, total=? WHERE id_order_detail=?;";

    

    public OrderDetailRepo() {
        connection = Database.koneksi();
    }
    @Override
    public void save(OrderDetail orderDetail) {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(insert);
            st.setString(1, orderDetail.getIdOrderDetail());
            st.setString(2, orderDetail.getIdOrder());
            st.setString(3, orderDetail.getIdLayanan());
            st.setInt(4, orderDetail.getJumlah());
            st.setDouble(5, orderDetail.getTotal());
            st.executeUpdate();
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

    @Override
    public List<OrderDetail> show() {
        List<OrderDetail> orderDetails = new ArrayList<>();
        Statement st = null;
        ResultSet rs = null;
        try {
            st = connection.createStatement();
            rs = st.executeQuery(select);
            while (rs.next()) {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setIdOrderDetail(rs.getString("id_order_detail"));
                orderDetail.setIdOrder(rs.getString("id_order"));
                orderDetail.setIdLayanan(rs.getString("id_layanan"));
                orderDetail.setJumlah(rs.getInt("jumlah"));
                orderDetail.setTotal(rs.getDouble("total"));
                orderDetails.add(orderDetail);
            }
        } catch (SQLException e) {
            Logger.getLogger(OrderDetailDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return orderDetails;
    }

    @Override
    public void update(OrderDetail orderDetail) {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(update);
            st.setString(1, orderDetail.getIdOrder());
            st.setString(2, orderDetail.getIdLayanan());
            st.setInt(3, orderDetail.getJumlah());
            st.setDouble(4, orderDetail.getTotal());
            st.setString(5, orderDetail.getIdOrderDetail());
            st.executeUpdate();
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

    @Override
    public void delete(String idOrderDetail) {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(delete);
            st.setString(1, idOrderDetail);
            st.executeUpdate();
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

	@Override
	public List<OrderDetail> findAll() {
		return null;
	}
	@Override
	public String total(String id_order) {
	    String query_total = "SELECT sum(total) as total from order_detail WHERE id_order= '" + id_order + "';";
	    Statement st;
	    ResultSet rs;
	    String result = "";
	    try {
	        st = connection.createStatement();
	        rs = st.executeQuery(query_total);
	        if (rs.next()) {
	            // Mengambil total sebagai double
	            double totalDouble = rs.getDouble(1);
	            // Mengonversi ke string tanpa pecahan
	            result = String.valueOf((int) totalDouble); // Casting ke int untuk menghilangkan pecahan
	        } else {
	            result = "0";
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return result;
	}

	
	public void deleteAll() {
	    Statement st = null;
	    try {
	        st = connection.createStatement();
	        st.executeUpdate("DELETE FROM order_detail;"); // Menghapus semua data dari tabel order_detail
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
	public List<OrderDetail> showByOrderId(String orderId) {
	    List<OrderDetail> orderDetails = new ArrayList<>();
	    PreparedStatement st = null;
	    ResultSet rs = null;
	    
	    String query = "SELECT * FROM order_detail WHERE id_order = ?;";
	    
	    try {
	        st = connection.prepareStatement(query);
	        st.setString(1, orderId);
	        rs = st.executeQuery();
	        while (rs.next()) {
	            OrderDetail orderDetail = new OrderDetail();
	            orderDetail.setIdOrderDetail(rs.getString("id_order_detail"));
	            orderDetail.setIdOrder(rs.getString("id_order"));
	            orderDetail.setIdLayanan(rs.getString("id_layanan"));
	            orderDetail.setJumlah(rs.getInt("jumlah"));
	            orderDetail.setTotal(rs.getDouble("total"));
	            orderDetails.add(orderDetail);
	        }
	    } catch (SQLException e) {
	        Logger.getLogger(OrderDetailDAO.class.getName()).log(Level.SEVERE, null, e);
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (st != null) st.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return orderDetails;
	}
	

}
