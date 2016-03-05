package game;

public class Person {
	private RandomNameGenerator randomName;
	private String name;

	public Person(RandomNameGenerator randomName){
		this.randomName = randomName;
		name = randomName.generateRandomName();
	}

	public String getName() {
		return name;
	}

}
