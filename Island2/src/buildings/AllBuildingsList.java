package buildings;

import java.util.ArrayList;

public class AllBuildingsList {
	private ArrayList<Building> allBuildings;
	public AllBuildingsList(){
		setAllBuildings(new ArrayList<Building>());
		getAllBuildings().add(new House());
		getAllBuildings().add(new WoodCutterLodge());
		getAllBuildings().add(new StoneMine());
	}
	public ArrayList<Building> getAllBuildings() {
		return allBuildings;
	}
	public void setAllBuildings(ArrayList<Building> allBuildings) {
		this.allBuildings = allBuildings;
	}
	
}
