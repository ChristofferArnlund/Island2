package Technologies;

import java.util.ArrayList;

public class Tech2 extends Technology{
	private final static String name = "Tech2";

	public Tech2(){
		super(name);
		techPoints=20;
		preRequisites = new ArrayList<Technology>();
		preRequisites.add(new Tech3());
		
		
	}

	
}
