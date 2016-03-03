package tests;

import static org.junit.Assert.*;
import game.BuildingManager;
import game.ResourceManager;
import game.TurnHandler;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import buildings.House;

public class BuildingManagerTest {

	private ResourceManager resourceManager;
	private BuildingManager buildingManager;
	private TurnHandler turnHandler;

	@Before
	public void setUp() throws Exception {
		resourceManager = new ResourceManager();
		buildingManager = new BuildingManager(resourceManager);
		turnHandler = new TurnHandler(buildingManager, resourceManager);
		
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
		
		assertEquals("Building was not put in Queue","House",buildingManager.buildingQueue.get(0).name);
		assertTrue("Building was build",buildingManager.existingBuildings.isEmpty());
		turnHandler.newTurn();
		assertEquals("Building was not put in Queue","House",buildingManager.existingBuildings.get(0).name);
		assertTrue("Building was build",buildingManager.buildingQueue.isEmpty());
		
		
		
	}
	@Test
	public void noInputIfNotEnoughResources(){
		House h =new House();
		//Sets resources of gold to zero
		resourceManager.resources.put("Gold", 0);
		//Sets price of house to 10 gold
		h.goldCost=10;
		assertFalse("Did input House altough not enough resources",buildingManager.addToQueue(new House()));
		
	
		
		
	}

}
