package game;

import buildings.Building;

public class UpdateResources {

	private int[] resources;

	public UpdateResources() {
		this.resources=resources;
		setResources(new int[4]);
	}

	public void nextTurn(BuildingManager bm,ResourceManager rm) {
		int addGold = 0;
		int addWood = 0;
		int addStone = 0;
		for (Building b : bm.existingBuildings) {
			if (b.isFull()) {
				double multiplier = Math.sqrt(b.actualNbrPersons() / b.getNbrPersons());
				addGold += (int) Math.round(b.getGenerateGold() * multiplier);
				addWood += (int) Math.round(b.getGenerateWood() * multiplier);
				addStone += (int) Math.round(b.getGenerateStone() * multiplier);

			}

		}
		getResources()[0] = addGold;
		getResources()[1] = addWood;
		getResources()[2] = addStone;
		rm.addResource("Gold", getResources()[0]);
		rm.addResource("Wood", getResources()[1]);
		rm.addResource("Stone", getResources()[2]);

	}

	public int[] getResources() {
		
		return resources;
	}

	public void setResources(int[] resources) {
		this.resources = resources;
	}


}