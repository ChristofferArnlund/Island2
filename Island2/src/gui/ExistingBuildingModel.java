package gui;

import javax.swing.table.DefaultTableModel;

import buildings.Building;
import game.BuildingManager;

public class ExistingBuildingModel{

	private Object[][] rowData;
	private Object[] columnNames;
	private BuildingManager buildingManager;
	private Object[] data1;

	public ExistingBuildingModel(BuildingManager buildingManager) {
		this.buildingManager = buildingManager;

	}

	private void loadTable() {
		data1 =buildingManager.existingBuildings.toArray();
		// has to be +1 otherwise the row x 1 is not big enough
		rowData = new Object[data1.length][data1.length+1];
		// Inputs correctly to Table.
		for (int x = 0; x < data1.length; x++) {
			rowData[x][0] = data1[x].toString();
			rowData[x][1] = ((Building) data1[x]).genString();

		}

		columnNames = new Object[] { "Building", "Time Left" };
	}

	public DefaultTableModel generateModel() {
		loadTable();
		return new DefaultTableModel(rowData, columnNames);

	}

}