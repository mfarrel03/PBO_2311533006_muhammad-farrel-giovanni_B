package table;

import javax.swing.table.AbstractTableModel;

import model.costumer;
import java.util.List;

public class TableCostumer extends AbstractTableModel{
	List<costumer> ls;
	private String[] columnNames={"ID", "Nama", "Alamat", "Nohp"};
	public TableCostumer(List<costumer> ls) {
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
			return ls.get(rowIndex).getNama();
		case 2:
			return ls.get(rowIndex).getAlamat();
		case 3:
			return ls.get(rowIndex).getNomor_hp();	
		default:
			
			return null;
		}
	}

}