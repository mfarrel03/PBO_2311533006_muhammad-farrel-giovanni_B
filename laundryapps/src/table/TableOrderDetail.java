package table;

import javax.swing.table.AbstractTableModel;
import model.OrderDetail;
import java.util.List;

public class TableOrderDetail extends AbstractTableModel {
    List<OrderDetail> OrderDetail;
    private String[] columnNames = {"ID Order Detail", "ID Order", "ID Layanan", "Jumlah", "Total"};

    public TableOrderDetail(List<OrderDetail> OrderDetail) {
        this.OrderDetail = OrderDetail;
    }

    @Override
    public int getRowCount() {
        return OrderDetail.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        OrderDetail orderDetail = OrderDetail.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return orderDetail.getIdOrderDetail();
            case 1:
                return orderDetail.getIdOrder();
            case 2:
                return orderDetail.getIdLayanan();
            case 3:
                return orderDetail.getJumlah();
            case 4:
                return orderDetail.getTotal();
            default:
                return null;
        }
    }
    
}