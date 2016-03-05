package buildings;

import java.util.ArrayList;

public class House extends Building {

	public House() {
		setName("House");
		setGoldCost(0);
		setWoodCost(0);
		setStoneCost(0);
		setBuildingTime(1);
		setGenerateGold(1);
		setGenerateWood(1);
		setGenerateStone(1);
		setNbrPersons(1);
		setMaxNbrPersons(10);
	}

	@Override
	public String costToString() {
		return getGoldCost() + " " + getWoodCost() + " " + getStoneCost();
	}

	@Override
	public String genString() {
		return getGenerateGold() + " " + getGenerateWood() + " " + getGenerateStone() + " - " + actualNbrPersons() + "/"
				+ getNbrPersons();
	}

}
