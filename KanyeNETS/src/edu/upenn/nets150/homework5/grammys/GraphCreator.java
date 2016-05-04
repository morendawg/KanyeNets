package edu.upenn.nets150.homework5.grammys;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;


public class GraphCreator {
	
	//List of all Person objects
	private static ArrayList<Person> personGraph;
	
	//Map linking Strings to Person objects
	private static Map<String, Person> foundPersons;

	public GraphCreator(Map<String, Map<String, ArrayList<String>>> input) {
		
		if (input == null) {
			System.out.println("input list is null");
		}
		
		for (Entry<String, Map<String, ArrayList<String>>> personEntry : input.entrySet()) {
			
			Person person = null;
			
			//get Person object from String
			String personString = personEntry.getKey();
			if (!foundPersons.keySet().contains(personString)) {
				person = new Person(personString);
				personGraph.add(person);
			} else {
				person = foundPersons.get(personString);
			}
			
			//Add collabs to each Person
			Map<String, ArrayList<String>> songMap = personEntry.getValue();
			for (Entry<String, ArrayList<String>> songEntry : songMap.entrySet()) {
				
				//song name
				String songString = songEntry.getKey();
				
				//list of collaborators
				ArrayList<String> collabList = songEntry.getValue();
				
				//iterate through list of collaborators
				for (String collabString : collabList) {
					
					//if person has not been encountered yet
					if (!foundPersons.keySet().contains(collabString)) {
						Person collabPerson = new Person(collabString);
						person.addCollab(collabPerson);
						
					//if person has already been encountered
					} else {
						person.addCollab(foundPersons.get(collabString));
					}
					
				}
				
			}
			
		}
		
	}
	
}
