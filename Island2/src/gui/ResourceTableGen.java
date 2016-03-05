package gui;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import game.ResourceManager;

public class ResourceTableGen{
	public JFrame frame;
	public JTable table;
	public Object[] data1;
	public Object[] data2;
	public Object[][] rowData;
	public Object[] columnNames;
	public ResourceManager resourceManager;

	public ResourceTableGen(ResourceManager resourceManager) {
		this.resourceManager = resourceManager;
		
		
	}
	
	public void loadResources() {
		data1 = resourceManager.resources.keySet().toArray();
		data2 = resourceManager.resources.values().toArray();
		
			rowData = new Object[data1.length][data1.length];

			// Inputs the values from the resourceManager to the proper place in
			// the
			// JTable data.
			for (int x = 0; x < data1.length - 1; x++) {
				rowData[x][0] = data1[x];
				rowData[x][1] = data2[x];

			
		}

		columnNames = new Object[] { "Resource", "Value" };
	}
	public DefaultTableModel generateTable(){
		loadResources();
		return new DefaultTableModel(rowData,columnNames);
		
	}
	
}