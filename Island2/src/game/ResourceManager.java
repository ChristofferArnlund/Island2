package game;

import java.util.HashMap;

public class ResourceManager {
	public HashMap<String, Integer> resources;

	public ResourceManager() {

		resources = new HashMap<String, Integer>();
		resources.put("Gold", 0);
		resources.put("Wood", 0);
		resources.put("Stone", 0);
		resources.put("Food", 0);
		resources.put("Research", 0);

	}

	public boolean enoughResources(int goldCost, int woodCost, int stoneCost) {
		if (resources.get("Gold") >= goldCost && resources.get("Wood") >= woodCost
				&& resources.get("Stone") >= stoneCost){
			return true;
		}
			return false;
	}

	public  void newTurn() {
	
		
	}

	public void addResource(String resource, int amount) {
		int temp= resources.get(resource);
		resources.put(resource, temp+amount);
		
	}


	}


