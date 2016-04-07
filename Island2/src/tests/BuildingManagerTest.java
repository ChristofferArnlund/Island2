package tests;

import static org.junit.Assert.*;
import game.BuildingManager;
import game.Person;
import game.PersonGenerator;
import game.PersonHandler;
import game.RandomNameGenerator;
import game.ResourceManager;
import game.TurnHandler;
import game.UpdateResources;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import buildings.Building;
import buildings.House;

public class BuildingManagerTest {

	private ResourceManager resourceManager;
	private BuildingManager buildingManager;
	private RandomNameGenerator rand;
	private UpdateResources updateResources;

	@Before
	public void setUp() throws Exception {
		resourceManager = new ResourceManager();
		buildingManager = new BuildingManager(resourceManager);
		PersonGenerator pg = new PersonGenerator(buildingManager, new PersonHandler());
		updateResources = new UpdateResources();
		rand = new RandomNameGenerator();

	}

	@After
	public void tearDown() throws Exception {
		resourceManager = null;
		buildingManager = null;
	}

	@Test
	public void inputHouseInBuildingManagerQueue() {
		buildingManager.addToQueue(new House());

		buildingManager.newTurn();

		assertEquals("Building was not put in Queue", "House", buildingManager.buildingQueue.get(0).getName());
		assertTrue("Building was build", buildingManager.existingBuildings.isEmpty());

		buildingManager.newTurn();

		assertEquals("Building was not put in Queue", "House", buildingManager.existingBuildings.get(0).getName());
		assertTrue("Building was build", buildingManager.buildingQueue.isEmpty());

	}

	@Test
	public void noInputIfNotEnoughResources() {
		Building h = new House();
		// Sets resources of gold to zero
		resourceManager.resources.put("Gold", 0);
		// Sets price of house to 10 gold
		h.setGoldCost(10);
		assertFalse("Did input House altough not enough resources", buildingManager.addToQueue(h));
	}

	@Test
	public void checkUpdateResources() {
		House h = new House();
		h.assignPerson(new Person(rand.generateRandomName()));
		buildingManager.addToQueue(h);
		// two turns to build.
		updateResources.nextTurn(buildingManager, resourceManager);

		buildingManager.newTurn();
		updateResources.nextTurn(buildingManager, resourceManager);

		buildingManager.newTurn();

		// new turn to update resources.
		updateResources.nextTurn(buildingManager, resourceManager);

		buildingManager.newTurn();
		assertEquals("Did not update Resource Gold", 1, resourceManager.resources.get("Gold").intValue());

	}

	@Test
	public void checkMultipleUpdateResources() {
		House h1 = new House();
		House h2 = new House();
		House h3 = new House();
		h1.assignPerson(new Person(rand.generateRandomName()));
		h2.assignPerson(new Person(rand.generateRandomName()));
		h3.assignPerson(new Person(rand.generateRandomName()));
		buildingManager.addToQueue(h1);
		buildingManager.addToQueue(h2);
		buildingManager.addToQueue(h3);
		// two turns to build.

		updateResources.nextTurn(buildingManager, resourceManager);
		buildingManager.newTurn();
		updateResources.nextTurn(buildingManager, resourceManager);

		buildingManager.newTurn();
		// new turn to update resources.
		updateResources.nextTurn(buildingManager, resourceManager);

		buildingManager.newTurn();
		assertEquals("Did not update Resource Gold", 3, resourceManager.resources.get("Gold").intValue());

	}

}
