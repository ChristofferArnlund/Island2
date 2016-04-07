package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import buildings.House;
import game.BuildingManager;
import game.Person;
import game.PersonGenerator;
import game.PersonHandler;
import game.ResourceManager;
import game.TurnHandler;
import game.UpdateResources;

public class PersonGeneratorTest {

	private BuildingManager buildingManager;
	private ResourceManager resourceManager;
	private PersonHandler ph;
	private PersonGenerator pg;

	@Before
	public void setUp() throws Exception {
		resourceManager = new ResourceManager();
		buildingManager = new BuildingManager(resourceManager);
		ph = new PersonHandler();
		pg = new PersonGenerator(buildingManager, ph);
		}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void newPersonIfCheckOk() {
		buildingManager.existingBuildings.add(new House());
		
		pg.nextTurn();
		assertEquals("did not create new Unassigned",1,ph.getUnassignedList().size());
	}
	
	@Test
	public void newPersonSeveralHousesIfCheckOk() {
		buildingManager.existingBuildings.add(new House());
		buildingManager.existingBuildings.add(new House());
		buildingManager.existingBuildings.add(new House());
		
		pg.nextTurn();
		assertEquals("did not create new Unassigned",1,ph.getUnassignedList().size());
		

		pg.nextTurn();
		assertEquals("did not create new Unassigned",2,ph.getUnassignedList().size());
		

		pg.nextTurn();
		assertEquals("did not create new Unassigned",3,ph.getUnassignedList().size());
		pg.nextTurn();
		assertEquals("did not create new Unassigned",3,ph.getUnassignedList().size());
		
		ph.assign(ph.getUnassigned(0),buildingManager.existingBuildings.get(0) );
		
		assertEquals("did not create new Unassigned",2,ph.getUnassignedList().size());
		assertEquals("did not create new Unassigned",1,ph.getAssignedList().size());
		
		ph.assign(ph.getUnassigned(0),buildingManager.existingBuildings.get(1) );
		ph.assign(ph.getUnassigned(0),buildingManager.existingBuildings.get(2) );
		
		assertEquals("did not create new Unassigned",0,ph.getUnassignedList().size());
		assertEquals("did not create new Unassigned",3,ph.getAssignedList().size());
		
	}

}
