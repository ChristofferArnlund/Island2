package tests;

import static org.junit.Assert.*;
import game.BuildingManager;
import game.Person;
import game.RandomNameGenerator;
import game.ResourceManager;
import game.TurnHandler;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import buildings.Building;
import buildings.House;

public class BuildingManagerTest {

	private ResourceManager resourceManager;
	private BuildingManager buildingManager;
	private TurnHandler turnHandler;
	private RandomNameGenerator rand;

	@Before
	public void setUp() throws Exception {
		resourceManager = new ResourceManager();
		buildingManager = new BuildingManager(resourceManager);
		turnHandler = new TurnHandler(buildingManager, resourceManager);
		rand = new RandomNameGenerator();
		
	}

	@After
	public void tearDown() throws Exception {
		resourceManager = null;
		buildingManager = null;
		turnHandler = null;
	}

	@Test
	public void inputHouseInBuildingManagerQueue() {
		
		buildingManager.addToQueue(new House());
		turnHandler.newTurn();
		
		assertEquals("Building was not put in Queue","House",buildingManager.buildingQueue.get(0).getName());
		assertTrue("Building was build",buildingManager.existingBuildings.isEmpty());
		turnHandler.newTurn();
		assertEquals("Building was not put in Queue","House",buildingManager.existingBuildings.get(0).getName());
		assertTrue("Building was build",buildingManager.buildingQueue.isEmpty());
		
		
		
	}
	@Test
	public void noInputIfNotEnoughResources(){
		Building h =new House();
		//Sets resources of gold to zero
		resourceManager.resources.put("Gold", 0);
		//Sets price of house to 10 gold
		h.setGoldCost(10);
		assertFalse("Did input House altough not enough resources",buildingManager.addToQueue(h));
	}
	@Test
	public void checkUpdateResources(){
		House h = new House();
		h.addPerson(new Person(rand));
		buildingManager.addToQueue(h);
		//two turns to build.
		turnHandler.newTurn();
		turnHandler.newTurn();
		
		//new turn to update resources.
		turnHandler.newTurn();
		assertEquals("Did not update Resource Gold",1,resourceManager.resources.get("Gold").intValue());
		
	}
	@Test
	public void checkMultipleUpdateResources(){
		House h1 = new House();
		House h2 = new House();
		House h3 = new House();
		h1.addPerson(new Person(rand));
		h2.addPerson(new Person(rand));
		h3.addPerson(new Person(rand));
		buildingManager.addToQueue(h1);
		buildingManager.addToQueue(h2);
		buildingManager.addToQueue(h3);
		//two turns to build.
		turnHandler.newTurn();
		turnHandler.newTurn();
		//new turn to update resources.
		turnHandler.newTurn();
		assertEquals("Did not update Resource Gold",3,resourceManager.resources.get("Gold").intValue());
		
	}

}
