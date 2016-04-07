package Technologies;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;

public class TechnologyHandler {
	private HashMap<String, Technology> doneTechs;
	private LinkedList<Technology> techQueue;
	private HashMap<String, Technology> allTechs;
	private HashMap<String, Technology> availableTechs;
	public TechnologyHandler() {
		doneTechs = new HashMap<String, Technology>();
		allTechs = new HashMap<String, Technology>();
		availableTechs = new HashMap<String, Technology>();
		techQueue = new LinkedList<Technology>();
		allTechs.put("Tech1", new Tech1());
		allTechs.put("Tech2", new Tech2());
		allTechs.put("Tech3", new Tech3());
		allTechs.put("Tech4", new Tech4());
		updateAvailableTechs();
	}

	public void done(Technology tech) {
		doneTechs.put(tech.getName(), tech);

	}

	public boolean okToStart(Technology tech) {
		if (tech.getPreRequisites().isEmpty()) {
			return true;
		} else {
			for (Technology t : tech.getPreRequisites()) {
				if (!techExists(t)) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean techExists(Technology tech) {
		if (doneTechs.containsKey(tech.getName())) {
			return true;
		}
		return false;
	}

	public void startResearch(Technology tech) {
		if (okToStart(tech)) {
			techQueue.add(tech);
		}

	}

	public void nextTurn(int i) {
		updateAvailableTechs();
		if (!techQueue.isEmpty()) {
			Technology currentResearch = techQueue.peek();
			currentResearch.techPoints = currentResearch.techPoints - i;
			if (currentResearch.techPoints <= 0) {
				done(techQueue.poll());
			}
		}
	}
	public void updateAvailableTechs(){
		
		for(Entry<String, Technology> pair: allTechs.entrySet()){
			if(okToStart(pair.getValue())){
			availableTechs.put(pair.getKey(), pair.getValue());
			}
		}
	}

	public HashMap<String, Technology> getAvailableTechs() {
		return availableTechs;
	}



}
