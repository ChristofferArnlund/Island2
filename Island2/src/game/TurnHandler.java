package game;

public class TurnHandler {
	private int counter;
	private BuildingManager buildingManager;
	private ResourceManager resourceManager;


	public TurnHandler(BuildingManager buildingManager ,ResourceManager resourceManager) {
		this.buildingManager = buildingManager;
		this.resourceManager = resourceManager;
		counter = 0;

	}

	public void newTurn() {
		counter++;
		
		buildingManager.newTurn();
		resourceManager.newTurn();
		
	}
}
