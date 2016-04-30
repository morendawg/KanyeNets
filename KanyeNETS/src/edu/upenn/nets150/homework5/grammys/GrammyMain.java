package edu.upenn.nets150.homework5.grammys;

import java.util.TreeMap;
import java.util.TreeSet;

public class GrammyMain {

	public static void main(String[] args) {		
		String kanyeAwards = "http://www.imdb.com/name/nm1577190/awards";
		JSoupQuery jSoupQuery = new JSoupQuery(kanyeAwards);
		TreeMap<String, TreeMap<String, TreeSet<PersonURL>>> masterData = jSoupQuery.getMasterData();
		jSoupQuery.printMasterData();
	}

}
