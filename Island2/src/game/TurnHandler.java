package game;

public class TurnHandler {
	private int counter;
	private BuildingManager buildingManager;
	private ResourceManager resourceManager;


	public TurnHandler(BuildingManager buildingManager ,ResourceManager resourceManager) {
		this.buildingManager = buildingManager;
		this.resourceManager = resourceManager;
		setCounter(0);

	}

	public void newTurn() {
		setCounter(getCounter() + 1);
		buildingManager.newTurn();
		resourceManager.newTurn();
		
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}
}
