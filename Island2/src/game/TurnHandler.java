package game;

public class TurnHandler {
	private int counter;
	private BuildingManager buildingManager;
	private PersonGenerator personGenerator;
	private ResourceManager resourceManager;
	private UpdateResources updateResources;


	public TurnHandler(BuildingManager buildingManager ,ResourceManager resourceManager, PersonGenerator personGenerator,UpdateResources updateResources) {
		this.buildingManager = buildingManager;
		this.resourceManager = resourceManager;
		this.personGenerator = personGenerator;
		this.updateResources = updateResources;
		setCounter(0);

	}

	public void newTurn() {
		setCounter(getCounter() + 1);
		personGenerator.nextTurn();
		//Order of importance
		updateResources.nextTurn(buildingManager, resourceManager);
		buildingManager.newTurn();
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}
}
