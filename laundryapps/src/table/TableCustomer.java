package table;

import javax.swing.table.AbstractTableModel;

import model.Costumer;

import java.awt.Window;
import java.util.List;

public class TableCustomer extends AbstractTableModel{
	List<Costumer> ls;
	private String[] columnNames={"ID", "Nama", "Alamat", "Nohp"};
	public TableCustomer(List<Costumer> ls) {
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
			return ls.get(rowIndex).getNohp();	
		default:
			
			return null;
		}
	}

}
