package game;

import buildings.Building;

public class Person {

	private String name;
	private Building building;

	public Person(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Building getBuilding() {
		return building;
	}

	public void setBuilding(Building building) {
		this.building = building;
	}

}
