package game;

import Technologies.TechnologyHandler;

public class TurnHandler {
	private int counter;
	private BuildingManager buildingManager;
	private PersonGenerator personGenerator;
	private ResourceManager resourceManager;
	private UpdateResources updateResources;
	private TechnologyHandler th;


	public TurnHandler(BuildingManager buildingManager ,ResourceManager resourceManager, PersonGenerator personGenerator,UpdateResources updateResources,TechnologyHandler th) {
		this.buildingManager = buildingManager;
		this.resourceManager = resourceManager;
		this.personGenerator = personGenerator;
		this.updateResources = updateResources;
		this.th = th;
		setCounter(0);

	}

	public void newTurn() {
		setCounter(getCounter() + 1);
		personGenerator.nextTurn();
		//Order of importance
		updateResources.nextTurn(buildingManager, resourceManager);
		buildingManager.newTurn();
		th.nextTurn(resourceManager.resources.get("TechPoints"));
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}
}
