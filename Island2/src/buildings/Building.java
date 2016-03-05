package buildings;


public abstract class Building {
public String name;
public  int goldCost;
public  int woodCost;
public  int stoneCost;
private int buildingTime;
public int generateGold;
public int generateWood;
public int generateStone;

public String toString() {
	return name;
}
public abstract String costToString();
public abstract String genString();

public int getBuildingTime() {
	return buildingTime;
}
public void setBuildingTime(int buildingTime) {
	this.buildingTime = buildingTime;
}
}
