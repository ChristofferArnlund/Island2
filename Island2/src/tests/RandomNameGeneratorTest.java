package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import game.RandomNameGenerator;

public class RandomNameGeneratorTest {

	@Test
	public void makeName(){
		RandomNameGenerator r = new RandomNameGenerator();
//		for(int i =0;i<100;i++){
//			System.out.println(r.generateRandomName());
//		}
		assertNotSame("",r.generateRandomName());
	}
}
