package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import buildings.Building;
import buildings.House;
import game.BuildingManager;
import game.Person;
import game.RandomNameGenerator;
import game.ResourceManager;
import game.TurnHandler;

public class PersonTest {

	private Person person;
	private ResourceManager rm;
	private BuildingManager bm;
	private TurnHandler turnHandler;

	@Before
	public void setUp() throws Exception {
		RandomNameGenerator rand = new RandomNameGenerator();
		rm = new ResourceManager();
		bm = new BuildingManager(rm);
		person = new Person(rand);
		 turnHandler = new TurnHandler(bm,rm);

	}

	@After
	public void tearDown() throws Exception {
		rm=null;
		bm= null;
		person= null;
		turnHandler = null;
	}

	@Test
	public void checkName() {
		assertNotEquals("", person.getName());
	}

	@Test
	public void addPersonToBuilding() {
		bm.existingBuildings.add(new House());
		Building h = (Building) bm.existingBuildings.get(0);
		if (!h.isFull()) {
			h.addPerson(person);
		}
		assertTrue("Did not input peoplseto house", h.isFull());
	}
	// Not valid because should be aloud with more people -> Lower efficency
	// @Test
	// public void dontAddPersonToBuildingifFull(){
	// bm.existingBuildings.add(new House());
	// Building h = (Building) bm.existingBuildings.get(0);
	// h.addPerson(person);
	// //Second person should not be added
	// h.addPerson(person);
	//
	// assertEquals("Added but full!",h.per);
	//

	@Test
	public void ifPersonNotInHouseDontProduceResources() {
		bm.existingBuildings.add(new House());
		Building h = (Building) bm.existingBuildings.get(0);
		turnHandler.newTurn();
		assertEquals("did not produce",0, rm.resources.get("Gold").intValue());
	}
	
	@Test
	public void ifPersonNInHouseProduceResources() {
		bm.existingBuildings.add(new House());
		Building h = (Building) bm.existingBuildings.get(0);
		h.addPerson(person);
		turnHandler.newTurn();
		assertEquals("did not produce",1, rm.resources.get("Gold").intValue());
	}
	@Test
	public void ifSeveralPersonNInHouseProduceResources() {
		bm.existingBuildings.add(new House());
		Building h = (Building) bm.existingBuildings.get(0);
		h.addPerson(person);
		h.addPerson(person);
		h.addPerson(person);
		h.addPerson(person);
		h.addPerson(person);
		
		turnHandler.newTurn();
		//sqrt(5/1)*1 = 2.2 ~2 
		assertEquals("did not produce",2, rm.resources.get("Gold").intValue());
	}
	
	
	
	
	
}