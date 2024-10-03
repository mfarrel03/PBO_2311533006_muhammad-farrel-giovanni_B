
package DAO;

import java.util.List;
import model.costumer;

public interface CostumerDAO {
    void save(costumer costumer);
    List<costumer> show();
    void update(costumer costumer);
    void delete(String id);
}
