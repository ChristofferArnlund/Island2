package game;

import java.util.ArrayList;

import buildings.Building;

public class PersonHandler {
	private ArrayList<Person> unassignedList;
	private ArrayList<Person> assignedList;
	
	public PersonHandler(){
		setUnassignedList(new ArrayList<Person>());
		setAssignedList(new ArrayList<Person>());

	}
	
	public void addUnassigned(Person person) {
		getUnassignedList().add(person);
		
	}
	public Person getUnassigned(int index){
		return getUnassignedList().get(index);
		
	}

	public void assign(Person p, Building b) {
		//Superimportant if, otherwise will do the assign here but not in building.
		if(b.assignPerson(p)){
			getUnassignedList().remove(p);
			p.setBuilding(b);
			addAssigned(p);			
		}
		
	}

	public boolean unassignedIsEmpty() {
		return getUnassignedList().isEmpty();
	}
	public boolean assignedIsEmpty() {
		return getAssignedList().isEmpty();
	}

	public Person getAssigned(int i) {
		return getAssignedList().get(i);
	}
	public boolean addAssigned(Person p) {
		return getAssignedList().add(p);
	}

	public void unassign(Person assigned) {
		Building b = assigned.getBuilding();
		b.unassign(assigned);

		assigned.setBuilding(null);
		getUnassignedList().add(assigned);
		getAssignedList().remove(assigned);
		
		
		
	}

	public ArrayList<Person> getUnassignedList() {
		return unassignedList;
	}

	public void setUnassignedList(ArrayList<Person> unassignedList) {
		this.unassignedList = unassignedList;
	}

	public ArrayList<Person> getAssignedList() {
		return assignedList;
	}

	public void setAssignedList(ArrayList<Person> assignedList) {
		this.assignedList = assignedList;
	}



	
}
