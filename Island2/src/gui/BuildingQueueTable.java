package gui;

import javax.swing.JTable;

import buildings.Building;
import game.BuildingManager;

public class BuildingQueueTable {

	private Object[][] rowData;
	private Object[] columnNames;
	private BuildingManager buildingManager;
	private Object[] data1;

	public BuildingQueueTable(BuildingManager buildingManager) {
		this.buildingManager = buildingManager;

	}

	private void loadTable() {
		data1 =buildingManager.buildingQueue.toArray();
		// has to be +1 otherwise the row x 1 is not big enough
		rowData = new Object[data1.length][data1.length+1];
		// Inputs correctly to Table.
		for (int x = 0; x < data1.length; x++) {
			rowData[x][0] = data1[x].toString();
			rowData[x][1] = ((Building) data1[x]).getBuildingTime();

		}

		columnNames = new Object[] { "Building", "Time Left" };
	}

	public JTable generateTable() {
		loadTable();
		return new JTable(rowData, columnNames);

	}

}