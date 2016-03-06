package gui;

import java.util.Set;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import game.BuildingManager;
import game.Person;
import game.PersonHandler;

public class PeopleTable {

	private PersonHandler personHandler;
	private Object[] data1;
	private Object[][] rowData;
	private Object[] columnNames;
	private Object[] data2;

	public PeopleTable(PersonHandler personHandler) {
		this.personHandler = personHandler;
	}

	public TableModel generateTable() {
		loadTable();
		return new DefaultTableModel(rowData, columnNames);
	}

	private void loadTable() {
		data1 = personHandler.getUnassignedList().toArray();
		data2 = personHandler.getAssignedList().toArray();

		// has to be +1 otherwise the row x 1 is not big enough
		rowData = new Object[data1.length + data2.length][2];
		// Inputs correctly to Table.
		for (int x = 0; x < data1.length; x++) {
			if (data1.length > 0) {
				rowData[x][0] = ((Person) data1[x]).getName();
				rowData[x][1] = "Unassigned";

			}

		}
		int counter =0;
		for (int x = data1.length; x < data1.length+data2.length; x++) {
			if (data2.length > 0) {
				rowData[x][0] = ((Person) data2[counter]).getName();
				rowData[x][1] = ((Person) data2[counter]).getBuilding().getName();
			}
			counter++;
		}

		columnNames = new Object[] { "Name", "Assigned To" };
	}
}