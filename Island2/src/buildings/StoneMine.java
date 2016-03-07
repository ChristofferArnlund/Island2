package buildings;

public class StoneMine extends Building{

	public StoneMine() {

		setName("Stone Mine");
		setGoldCost(5);
		setWoodCost(15);
		setStoneCost(5);
		setBuildingTime(8);
		setGenerateGold(0);
		setGenerateWood(0);
		setGenerateStone(2);
		setNbrPersons(5);
		setMaxNbrPersons(10);
	}
}