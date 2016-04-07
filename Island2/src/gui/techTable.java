package gui;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Technologies.TechnologyHandler;

public class techTable {
	private Object[] data1;
	private Object[][] rowData;
	private Object[] columnNames;
	private Object[] data2;
	private TechnologyHandler th;

	public techTable(TechnologyHandler th) {
		this.th = th;

	}

	public TableModel generateModel() {
		loadTable();
		return new DefaultTableModel(rowData, columnNames);
	}

	private void loadTable() {
		columnNames = new Object[] {""};
		data1 = th.getAvailableTechs().keySet().toArray();
		rowData = new Object[data1.length][1];
		// Inputs correctly to Table.
		for (int x = 0; x < data1.length; x++) {
			if (data1.length > 0) {
				rowData[x][0] = data1[x];

			}

		}

	}
}
