package guiTest;

import static org.junit.Assert.*;

import javax.swing.JTable;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import gui.BuildingTableGen;

public class BuildingTableGenTest {

	private BuildingTableGen buildingTableGen;

	@Before
	public void setUp() throws Exception {
		buildingTableGen = new BuildingTableGen();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void checkifInputingCorrectly() {
		JTable j = new JTable(new BuildingTableGen().generateTable());
		assertEquals("not inputing correctly","House",j.getValueAt(0, 0).toString());
		assertEquals("not inputing correctly","0 0 0",j.getValueAt(0, 1).toString());
		
	}

}
