package Technologies;

import java.util.ArrayList;

public class Technology {
	protected ArrayList<Technology> preRequisites;
	private String name;
	protected int techPoints; 
	public Technology(String name){
		this.setName(name);
		preRequisites=new ArrayList<Technology>();
	}
	public void setPreRequisites(ArrayList<Technology> preRequisistesArray){
		preRequisites= preRequisistesArray;
	}
	public ArrayList<Technology> getPreRequisites(){
		return preRequisites;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
