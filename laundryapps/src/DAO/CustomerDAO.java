package DAO;

import java.util.List;
import model.Costumer;

public interface CustomerDAO {
    void save(Costumer costumer);
    List<Costumer> show();
    void update(Costumer costumer);
    void delete(String id);
}
