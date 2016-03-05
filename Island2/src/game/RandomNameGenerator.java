package game;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class RandomNameGenerator {
	ArrayList<String> names;
	ArrayList<String> surnames;
	Path namepath = Paths.get("C:/Users/Christoffer Arnlund/Documents/Island2/Island2/src/util/names.txt");
	Path surnamepath = Paths.get("C:/Users/Christoffer Arnlund/Documents/Island2/Island2/src/util/surnames.txt");
	private File nameFile;
	private File surnameFile;

	public RandomNameGenerator() {
	nameFile = new File(namepath.toAbsolutePath().toUri());
	surnameFile = new File(surnamepath.toAbsolutePath().toUri());
		
		names = new ArrayList<String>();
		surnames = new ArrayList<String>();
	}

	public void parseNames() {
		Scanner scan;
		try {
			scan = new Scanner(nameFile);
			while (scan.hasNextLine()) {
				String s = scan.nextLine();
				names.add(s);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			scan = new Scanner(surnameFile);
			while (scan.hasNextLine()) {
				String s = scan.nextLine();
				surnames.add(s);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String generateRandomName(){
		StringBuilder sb = new StringBuilder();
		Random rand = new Random();
		sb.append(names.get(rand.nextInt(names.size())));
		sb.append(" ");
		sb.append(surnames.get(rand.nextInt(surnames.size())));
		return sb.toString();
	}
}