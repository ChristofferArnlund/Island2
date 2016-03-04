package guiTest;

import static org.junit.Assert.*;

import javax.swing.JTable;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import buildings.House;
import game.BuildingManager;
import game.ResourceManager;
import gui.BuildingQueueTable;

public class BuildingQueueTableTest {

	private BuildingQueueTable buildingQueueTable;
	private BuildingManager b;
	private ResourceManager r;

	@Before
	public void setUp() throws Exception {
		r = new ResourceManager();
	b = new BuildingManager(r);
		buildingQueueTable = new BuildingQueueTable(b);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		b.addToQueue(new House());
		JTable j = buildingQueueTable.generateTable();
		
		assertEquals("not inputing correctly","House",j.getValueAt(0, 0).toString());

	}

}
