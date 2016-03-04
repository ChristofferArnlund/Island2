package gui;


import javax.swing.JTable;

import buildings.Building;
import buildings.House;

public class BuildingTableGen{

	private Object[][] rowData;
	private Object[] columnNames;
	public BuildingTableGen() {
		
		
	}
	
	private void loadTable() {
		Building[] buildingName = new Building[1];
		buildingName[0]= new House();
		//has to be +1 otherwise it may overflow
			rowData = new Object[buildingName.length][buildingName.length+1];
			//Inputs correctly to Table.
			for (int x = 0; x < buildingName.length ; x++) {
				rowData[x][0] = buildingName[x].toString();
				rowData[x][1] = buildingName[x].costToString();
			
		}

		columnNames = new Object[] { "Building", "Cost" };
	}
	public JTable generateTable(){
		loadTable();
		return new JTable(rowData,columnNames);
		
	}
	
}