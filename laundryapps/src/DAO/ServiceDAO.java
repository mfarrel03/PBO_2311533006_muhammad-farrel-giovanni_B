package DAO;

import java.util.List;
import model.service;

public interface ServiceDAO {
    void save(service service);
    List<service> show();
    void delete(String id);
    void update(service service);
}
