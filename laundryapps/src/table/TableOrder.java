package table;

import javax.swing.table.AbstractTableModel;
import model.Order;
import java.util.List;

public class TableOrder extends AbstractTableModel {
    List<Order> ls;
    private String[] columnNames = {"ID", "ID Costumer", "ID User", "Total", "Tanggal", "Tanggal Pengembalian", "Status", "Status Pembayaran"};

    public TableOrder(List<Order> ls) {
        this.ls = ls;
    }

    @Override
    public int getRowCount() {
        return ls.size();
    }

    @Override
    public int getColumnCount() {
        return 8;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return ls.get(rowIndex).getId();
            case 1:
                return ls.get(rowIndex).getTotal();
            case 2:
                return ls.get(rowIndex).getTanggal();
            case 3:
                return ls.get(rowIndex).getTanggal_selesai();
            case 4:
                return ls.get(rowIndex).getStatus();
            case 5:
                return ls.get(rowIndex).getStatus_pembayaran();
            default:
                return null;
        }
    }
}
