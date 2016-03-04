package buildings;


public class House extends Building{
	

	public House(){
		name="House";
		goldCost=0;
		woodCost = 0;
		stoneCost =0;
		setBuildingTime(1);
		generateGold=1;
		generateWood=1;
		generateStone=1;
		
	}

	@Override
	public String costToString() {
		return goldCost +" "+ woodCost +" "+ stoneCost;
	}





}
