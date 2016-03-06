package gui;

import java.util.Map;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import game.ResourceManager;
import game.UpdateResources;

public class ResourceTableGen {
	public JFrame frame;
	public JTable table;
	public Object[] data1;
	public Object[] data2;
	public Object[][] rowData;
	public Object[] columnNames;
	public ResourceManager resourceManager;
	public UpdateResources ur;

	public ResourceTableGen(ResourceManager resourceManager, UpdateResources ur) {
		this.resourceManager = resourceManager;
		this.ur = ur;

	}

	public void loadResources() {

		Set<Map.Entry<String, Integer>> datas = resourceManager.resources.entrySet();
		int counter = 0;
		Object[] data1 = new Object[datas.size()];
		Object[] data2 = new Object[datas.size()];
		for (Map.Entry<String, Integer> entry : datas) {
			data1[counter] = entry.getKey();
			data2[counter] = entry.getValue();
			counter++;
		}

		rowData = new Object[data1.length][data1.length];

		// Inputs the values from the resourceManager to the proper place in
		// the
		// JTable data.
		for (int x = 0; x < data1.length - 1; x++) {
			rowData[x][0] = data1[x];
			rowData[x][1] = data2[x] + " (+" + ur.getResources()[x] + ")";

		}

		columnNames = new Object[] { "Resource", "Value" };
	}

	public DefaultTableModel generateTable() {
		loadResources();
		return new DefaultTableModel(rowData, columnNames);

	}

}