package edu.upenn.nets150.homework5.grammys;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;


public class GraphCreator {
	
	//List of all Person objects
	private static ArrayList<Person> personGraph;
	
	//Map linking Strings to Person objects
	private static Map<String, Person> foundPersons;

	public GraphCreator(TreeMap<String,TreeMap<String, TreeSet<PersonURL>>> input) {
		
		if (input == null) {
			System.out.println("input list is null");
		}
		
		personGraph = new ArrayList<Person>();
		foundPersons = new TreeMap<String, Person>();
		
		for (Entry<String, TreeMap<String, TreeSet<PersonURL>>> personEntry : input.entrySet()) {
			
			Person person = null;
			
			//get Person object from String
			String personString = personEntry.getKey();
			if (!foundPersons.keySet().contains(personString)) {
				person = new Person(personString);
				personGraph.add(person);	
				foundPersons.put(personString, person);
		    } else {
				person = foundPersons.get(personString);
				if (!personGraph.contains(person)) {
					personGraph.add(person);
				}
			}
			
			//Add collabs to each Person
			Map<String, TreeSet<PersonURL>> songMap = personEntry.getValue();
			for (Entry<String, TreeSet<PersonURL>> songEntry : songMap.entrySet()) {
				
				//song name
				String songString = songEntry.getKey();
				
				//list of collaborators
				TreeSet<PersonURL> urlSet = songEntry.getValue();
				ArrayList<String> collabList = new ArrayList<String>();
				for (PersonURL pURL : urlSet) {
					collabList.add(pURL.getName());
				}
				
				//iterate through list of collaborators
				for (String collabString : collabList) {
					
					//if person has not been encountered yet
					if (!foundPersons.keySet().contains(collabString)) {
						Person collabPerson = new Person(collabString);
						foundPersons.put(collabString, collabPerson);
						person.addCollab(collabPerson);
						
					//if person has already been encountered
					} else {
						person.addCollab(foundPersons.get(collabString));
					}
					
				}
				
			}
			
		}
		
	}
	
	public void printGraph() {
		
		for (Person p : personGraph) {
			
			System.out.println("Name: " + p.getName());
			for (Entry<Person, Integer> e : p.getCollabs().entrySet()) {
				
				String collabString = e.getKey().getName();
				int collabCount = e.getValue();
				System.out.println("Collaborator: " + collabString + " [" + collabCount + "]");
				
			}
			System.out.println();
			
		}
		
	}
	
}
