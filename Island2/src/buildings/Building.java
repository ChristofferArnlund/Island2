package buildings;

import java.util.ArrayList;

import game.Person;

public abstract class Building {
	private String name;
	private int goldCost;
	private int woodCost;
	private int stoneCost;
	private int buildingTime;
	private int generateGold;
	private int generateWood;
	private int generateStone;
	ArrayList<Person> personList;
	private int nbrPersons;
	private int maxNbrPersons;

	public Building() {
		personList = new ArrayList<Person>();
	}

	public String toString() {
		return getName();
	}

	public abstract String costToString();

	public abstract String genString();

	public int getBuildingTime() {
		return buildingTime;
	}

	public void setBuildingTime(int buildingTime) {
		this.buildingTime = buildingTime;
	}

	public boolean isFull() {

		return personList.size() >= getNbrPersons();
	}

	public int actualNbrPersons() {
		return personList.size();
	}

	public boolean addPerson(Person person) {
		if (actualNbrPersons() < getMaxNbrPersons()) {
			personList.add(person);
			return true;
		}
		return false;

	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	public int getGoldCost() {
		return goldCost;
	}

	public void setGoldCost(int goldCost) {
		this.goldCost = goldCost;
	}

	public int getWoodCost() {
		return woodCost;
	}

	public void setWoodCost(int woodCost) {
		this.woodCost = woodCost;
	}

	public int getStoneCost() {
		return stoneCost;
	}

	public void setStoneCost(int stoneCost) {
		this.stoneCost = stoneCost;
	}

	public int getGenerateGold() {
		return generateGold;
	}

	public void setGenerateGold(int generateGold) {
		this.generateGold = generateGold;
	}

	public int getGenerateWood() {
		return generateWood;
	}

	public void setGenerateWood(int generateWood) {
		this.generateWood = generateWood;
	}

	public int getGenerateStone() {
		return generateStone;
	}

	public void setGenerateStone(int generateStone) {
		this.generateStone = generateStone;
	}

	public int getNbrPersons() {
		return nbrPersons;
	}

	public void setNbrPersons(int nbrPersons) {
		this.nbrPersons = nbrPersons;
	}

	public int getMaxNbrPersons() {
		return maxNbrPersons;
	}

	public void setMaxNbrPersons(int maxNbrPersons) {
		this.maxNbrPersons = maxNbrPersons;
	}

}
