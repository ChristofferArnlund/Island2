package game;

import java.util.ArrayList;
import java.util.Iterator;

import buildings.Building;

public class BuildingManager {
	public ArrayList<Building> buildingQueue;
	public ArrayList<Building> existingBuildings;
	private ResourceManager resourceManager;

	public BuildingManager(ResourceManager resourceManager) {
		this.resourceManager = resourceManager;
		buildingQueue = new ArrayList<Building>();
		existingBuildings = new ArrayList<Building>();

	}

	/**
	 * Add to queue if there is enough resources
	 * returns true if added to queue else false
	 * @param building
	 */
	public boolean addToQueue(Building building) {
		if(build(building)){
			buildingQueue.add(building);
			return true;
			
		}
		return false;
	}

	public void newTurn() {
		updateResources();
		updateQueue();
	}

	private void updateResources() {
		for (Building b : existingBuildings) {
			resourceManager.addResource("Gold", b.generateGold);
			resourceManager.addResource("Wood", b.generateWood);
			resourceManager.addResource("Stone", b.generateStone);
		}

	}

	public boolean build(Building building) {
		return resourceManager.enoughResources(building.goldCost,
				building.woodCost, building.woodCost);
	}

	private void updateQueue() {
		Iterator<Building> itr = buildingQueue.iterator();
		Building building;
		while (itr.hasNext()) {

			building = itr.next();
			// if buildingTime is zero then add to existingBuilding list and
			// remove from Queue
			// else decrease time with one turn.
			if (building.getBuildingTime() == 0) {
				existingBuildings.add(building);
				itr.remove();
			} else {
				building.setBuildingTime(building.getBuildingTime() - 1);
			}
		}
	}

}
