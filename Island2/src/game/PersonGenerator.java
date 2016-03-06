package game;

import buildings.Building;

public class PersonGenerator {

	
	private BuildingManager bm;
	private PersonHandler ph;
	private RandomNameGenerator rand;

	public PersonGenerator(BuildingManager bm, PersonHandler ph){
		this.bm = bm;
		this.ph = ph;
		rand = new RandomNameGenerator();
		
	}
	public void nextTurn(){
		updatePersons();
	}
	private void updatePersons() {
		
		int notFullHouses =0;
		for(Building b : bm.existingBuildings){
			//If it is a house & the house is not full
			if(b.getName().equals("House")){
				if(!b.isFull()){
					//Increase the counter.
					notFullHouses++;					
				}
			}
		}
		
		//if the number of notfullhouses i greater than the number of unassigned people, add a new unassigned person. 
		if(notFullHouses > ph.getUnassignedList().size()){
			ph.addUnassigned(new Person(rand.generateRandomName()));
		}
		
	}
}
