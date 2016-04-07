package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import buildings.Building;
import buildings.House;
import game.BuildingManager;
import game.Person;
import game.PersonGenerator;
import game.PersonHandler;
import game.RandomNameGenerator;
import game.ResourceManager;
import game.TurnHandler;
import game.UpdateResources;

public class PersonTest {

	private Person person;
	private ResourceManager rm;
	private BuildingManager bm;
	private PersonHandler ph;
	private RandomNameGenerator rand;
	private PersonGenerator pg;
	private UpdateResources updateResources;
	
	@Before
	public void setUp() throws Exception {
		rand = new RandomNameGenerator();
		ph = new PersonHandler();
		rm = new ResourceManager();
		bm = new BuildingManager(rm);
		person = new Person("Pelle");
		pg = new PersonGenerator(bm, ph);
		updateResources = new UpdateResources();
	}

	@After
	public void tearDown() throws Exception {
		rm=null;
		bm= null;
		person= null;
		ph=null;
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
			h.assignPerson(person);
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
	
		pg.nextTurn();
		updateResources.nextTurn(bm, rm);
		
		
		assertEquals("did not produce",0, rm.resources.get("Gold").intValue());
	}
	
	@Test
	public void ifPersonInHouseProduceResources() {
		bm.existingBuildings.add(new House());
		Building house = (Building) bm.existingBuildings.get(0);
		ph.addUnassigned(person);
		ph.assign(ph.getUnassigned(0), house);
		
//		turnHandler.newTurn();
		pg.nextTurn();
		updateResources.nextTurn(bm, rm);
		
		assertEquals("did not produce",1, rm.resources.get("Gold").intValue());
	}
	@Test
	public void ifSeveralPersonNInHouseProduceResources() {
		bm.existingBuildings.add(new House());
		Building h = (Building) bm.existingBuildings.get(0);
		h.assignPerson(person);
		h.assignPerson(person);
		h.assignPerson(person);
		h.assignPerson(person);
		h.assignPerson(person);
		
//		turnHandler.newTurn();
		pg.nextTurn();
		updateResources.nextTurn(bm, rm);
		
		
		//sqrt(5/1)*1 = 2.2 ~2 
		assertEquals("did not produce",2, rm.resources.get("Gold").intValue());
	}
	
	@Test
	public void addToPersonHandlerList() {
	//if not assigned person should be in unassignedPeopleList.
		
		Person per = new Person("Per");
		
		ph.addUnassigned(per);
		assertEquals("did not insert","Per",ph.getUnassigned(0).getName());
		
	}
	@Test
	public void assignPersonAndCheckLists() {
		bm.existingBuildings.add(new House());
		Building h = (Building) bm.existingBuildings.get(0);
		ph.addUnassigned(person);
		ph.assign(ph.getUnassigned(0), h);
		assertEquals("did not put in building",1,h.actualNbrPersons());
		assertTrue("Did no remove person",ph.unassignedIsEmpty());
		assertEquals("dit not iterate and input correctly","Pelle",ph.getAssigned(0).getName());
	}
	@Test
	public void UnassignPersonAndCheckLists() {
		bm.existingBuildings.add(new House());
		Building h = (Building) bm.existingBuildings.get(0);
		ph.addUnassigned(person);
		ph.assign(ph.getUnassigned(0), h);
		ph.unassign(ph.getAssigned(0));
		assertEquals("dit not iterate and input correctly","Pelle",ph.getUnassigned(0).getName());
		assertTrue("Did no remove person",ph.assignedIsEmpty());
		assertEquals("did not put in building",0,h.actualNbrPersons());
	
	
	}
	@Test
	public void multipleAssignUnassign() {
		bm.existingBuildings.add(new House());
		bm.existingBuildings.add(new House());
		bm.existingBuildings.add(new House());
		bm.existingBuildings.add(new House());
		
		Building h1 = (Building) bm.existingBuildings.get(0);
		Building h2= (Building) bm.existingBuildings.get(1);
		Building h3 = (Building) bm.existingBuildings.get(2);
		Building h4 = (Building) bm.existingBuildings.get(3);
	
		ph.addUnassigned(new Person("p1"));
		ph.addUnassigned(new Person("p2"));
		ph.addUnassigned(new Person("p3"));
		ph.addUnassigned(new Person("p4"));
	
		ph.assign(ph.getUnassigned(0), h1);
		assertEquals("did not put in building",1,h1.actualNbrPersons());

		ph.assign(ph.getUnassigned(0), h2);
		assertEquals("did not put in building",1,h2.actualNbrPersons());

		ph.assign(ph.getUnassigned(0), h3);
		assertEquals("did not put in building",1,h3.actualNbrPersons());

		ph.assign(ph.getUnassigned(0), h4);
		assertEquals("did not put in building",1,h4.actualNbrPersons());
	
		ph.unassign(ph.getAssigned(0));;
		assertEquals("did not put in building",0,h1.actualNbrPersons());
		
		ph.unassign(ph.getAssigned(0));;
		assertEquals("did not put in building",0,h2.actualNbrPersons());
		
		assertEquals("did not put in building",1,h3.actualNbrPersons());
		assertEquals("did not put in building",1,h4.actualNbrPersons());
		
		ph.assign(ph.getUnassigned(0), h3);
		ph.assign(ph.getUnassigned(0), h3);
		assertEquals("did not put in building",3,h3.actualNbrPersons());

	}
	@Test
	public void test() {
		//fail("Not yet implemented");
	}
	
	
	
}