
package table;

import javax.swing.table.AbstractTableModel;

import model.service;
import java.util.List;

public class TableService extends AbstractTableModel{
	List<service> ls;
	private String[] columnNames={"ID", "Jenis", "Harga", "Status"};
	public TableService(List<service> ls) {
		this.ls = ls;
		
	}
	@Override
	public int getRowCount() {
		return ls.size();
	}
	
	@Override
	public int getColumnCount() {
		return 4;
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
			return ls.get(rowIndex).getJenis();
		case 2:
			return ls.get(rowIndex).getHarga();
		case 3:
			return ls.get(rowIndex).getStatus();	
		default:
			
			return null;
		}
	}

}
