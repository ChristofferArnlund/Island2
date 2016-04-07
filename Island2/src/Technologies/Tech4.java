package Technologies;

import java.util.ArrayList;

public class Tech4 extends Technology{
	private final static String name = "Tech2";

	public Tech4(){
		super(name);
		preRequisites = new ArrayList<Technology>();
		preRequisites.add(new Tech1());
		preRequisites.add(new Tech2());
		preRequisites.add(new Tech3());
		techPoints=40;

		
		
	}

	
}
