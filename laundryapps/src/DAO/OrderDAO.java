package DAO;

import java.util.List;
import model.Order;

public interface OrderDAO {
    void save(Order order);
    List<Order> show();
    void delete(String id);
    void update(Order order);
}
