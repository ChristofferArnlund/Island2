package guiTest;

import static org.junit.Assert.*;

import javax.swing.JTable;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import buildings.Building;
import buildings.House;
import game.BuildingManager;
import game.Person;
import game.PersonGenerator;
import game.PersonHandler;
import game.ResourceManager;
import game.TurnHandler;
import game.UpdateResources;
import gui.PeopleTable;

public class PeopleTableTest {

	private PersonHandler ph;
	private ResourceManager resourceManager;
	private BuildingManager buildingManager;
	private PersonGenerator pg;
	private UpdateResources updateResources;

	@Before
	public void setUp() throws Exception {
		resourceManager = new ResourceManager();
		buildingManager = new BuildingManager(resourceManager);
		ph = new PersonHandler();
		pg = new PersonGenerator(buildingManager, ph);
		updateResources = new UpdateResources();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void add() {
		ph.addUnassigned(new Person("Hej"));

		JTable j = new JTable(new PeopleTable(ph).generateTable());
		assertEquals("did not input", "Hej", j.getValueAt(0, 0).toString());
	}

	@Test
	public void addUnandAssign() {
		Building house = new House();
		Person p = new Person("Hej");
		ph.addUnassigned(p);
		ph.assign(p, house);
		JTable j = new JTable(new PeopleTable(ph).generateTable());
		assertEquals("did not input", "Hej", j.getValueAt(0, 0).toString());
	}

	@Test
	public void addUnandAssignAfterTurns() {
		Building house = new House();
		Building house1 = new House();
		Building house2 = new House();
		Building house3 = new House();

		buildingManager.existingBuildings.add(house);
		buildingManager.existingBuildings.add(house1);

		buildingManager.existingBuildings.add(house2);

		buildingManager.existingBuildings.add(house3);

//		turnHandler.newTurn();
		pg.nextTurn();
		JTable j = new JTable(new PeopleTable(ph).generateTable());
		assertEquals("did not input", 1, j.getRowCount());
//		turnHandler.newTurn();
		pg.nextTurn();
		
		assertEquals("did not input", 2, ph.getUnassignedList().size());
		j.setModel(new PeopleTable(ph).generateTable());
		assertEquals("did not input", 2, j.getRowCount());
//		turnHandler.newTurn();
//		turnHandler.newTurn();
		pg.nextTurn();
		pg.nextTurn();
		
		j.setModel(new PeopleTable(ph).generateTable());
		assertEquals("did not input", 4, j.getRowCount());
		
		ph.assign(ph.getUnassigned(0), buildingManager.existingBuildings.get(0));
		j.setModel(new PeopleTable(ph).generateTable());
		assertEquals("did not input", 4, j.getRowCount());
		

		
		
	}

}
