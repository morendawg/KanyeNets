package edu.upenn.nets150.homework5.grammys;


import java.util.Map;
import java.util.TreeMap;


public class Person implements Comparable<Person> {
	
	//name of the artist
	private String name;
	
	//list of collaborators (nodes)
	private Map<Person, Integer> collabs;

	// Person constructor
	public Person(String name) {
		
		if (name == null) {
			System.out.println("name is null");
		}
		
		this.name = name;
		this.collabs = new TreeMap<Person, Integer>();
		
	}
	
	//get the Person's name
	public String getName() {
		
		return this.name;
		
	}
	
	//compare two Person's based on name
	public int compareTo(Person p) {
		
		if (this.getName().equals(p.getName())) {
			return 0;
		} else {
			return -1;
		}
		
	}
	
	//add a collaborator to the Person's set
	public void addCollab(Person p) {
		
		if (collabs.keySet().contains(p)) {
			System.out.println("MULTIPLE COUNT");
			collabs.put(p, collabs.get(p) + 1);
		} else {
			collabs.put(p, 1);
		}
		
	}

	//remove a collaborator from the Person's set
	public void removeCollab(Person p) {
		
		if(collabs.keySet().contains(p)) {
			collabs.remove(p);
		}
		
	}
	
	//return the map from collaborators to edge weights
	public Map<Person, Integer> getCollabs() {
		
		return collabs;
		
	}
	
	//get the number of collaborators for a person
	public int getCollabSize() {
		
		return collabs.size();
		
	}
	
}
