package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Technologies.Tech1;
import Technologies.Tech2;
import Technologies.Tech3;
import Technologies.Tech4;
import Technologies.Technology;
import Technologies.TechnologyHandler;

public class TechTest {

	private TechnologyHandler th;

	@Before
	public void setUp() throws Exception {
		th = new TechnologyHandler();
	}

	@After
	public void tearDown() throws Exception {
		th=null;
	}

	@Test
	public void checkIfResearchRequirementsAreValid() {
		th.done(new Tech1());
		assertTrue("requirements are wrong",th.okToStart(new Tech3()));
		assertFalse("requirements are wrong",th.okToStart(new Tech2()));
		th.done(new Tech3());
		assertTrue("requirements are wrong",th.okToStart(new Tech2()));
		th.done(new Tech2());
		assertTrue("requirements are wrong",th.okToStart(new Tech4()));
	}
	@Test
	public void researchInTurns(){
		//Simulate "Fake" turns to not create a new instance of the TurnHandler
		
		th.startResearch(new Tech1());
		th.nextTurn(10);
		assertTrue("requirements are wrong",th.techExists(new Tech1()));
		
	}

}
