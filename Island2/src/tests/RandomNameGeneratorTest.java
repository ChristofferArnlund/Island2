package tests;

import org.junit.Test;

import game.RandomNameGenerator;

public class RandomNameGeneratorTest {

	@Test
	public void makeName(){
		RandomNameGenerator r = new RandomNameGenerator();
		r.parseNames();
		for(int i =0;i<100;i++){
			System.out.println(r.generateRandomName());
		}
//		assertThat("",r.generateRandomName());
	}
}
