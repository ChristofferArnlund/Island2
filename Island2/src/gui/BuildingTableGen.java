package gui;


import javax.swing.table.DefaultTableModel;

import buildings.AllBuildingsList;
import buildings.Building;

public class BuildingTableGen{

	private Object[][] rowData;
	private Object[] columnNames;
	public BuildingTableGen() {
		
		
	}
	
	private void loadTable() {
		AllBuildingsList allbuildingsList = new AllBuildingsList();
		Object[] buildings = allbuildingsList.getAllBuildings().toArray();
		//has to be +1 otherwise it may overflow
			rowData = new Object[buildings.length][buildings.length+1];
			//Inputs correctly to Table.
			for (int x = 0; x < buildings.length ; x++) {
				rowData[x][0] = buildings[x].toString();
				rowData[x][1] = ((Building) buildings[x]).costToString();
			
		}

		columnNames = new Object[] { "Building", "Cost" };
	}
	public DefaultTableModel generateTable(){
		loadTable();
		return new DefaultTableModel(rowData,columnNames);
		
	}
	
}