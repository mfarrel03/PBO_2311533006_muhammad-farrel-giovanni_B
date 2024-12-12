package DAO;

import java.util.List;
import model.OrderDetail;

public interface OrderDetailDAO {
    void save(OrderDetail orderDetail);
    List<OrderDetail> findAll();
    void update(OrderDetail orderDetail);
    void delete(String idOrderDetail);
	List<OrderDetail> show();
	public String total(String id_order);
}
