package edu.upenn.nets150.homework5.grammys;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class GraphCreator {

	// List of all Person objects
	private static ArrayList<Person> personGraph;

	// Map linking Strings to Person objects
	private static Map<String, Person> foundPersons;

	public GraphCreator(
			TreeMap<String, TreeMap<String, TreeSet<PersonURL>>> input) {

		// if the input graph is null, print an error message
		if (input == null) {
			System.out.println("input list is null");
		}

		// initalize the datastructures
		personGraph = new ArrayList<Person>();
		foundPersons = new TreeMap<String, Person>();

		// iterate through personURL objects
		for (Entry<String, TreeMap<String, TreeSet<PersonURL>>> personEntry : input
				.entrySet()) {

			Person person = null;

			// get Person object from String
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

			// Add collabs to each Person
			Map<String, TreeSet<PersonURL>> songMap = personEntry.getValue();
			for (Entry<String, TreeSet<PersonURL>> songEntry : songMap
					.entrySet()) {

				// song name
				String songString = songEntry.getKey();

				// list of collaborators
				TreeSet<PersonURL> urlSet = songEntry.getValue();
				ArrayList<String> collabList = new ArrayList<String>();
				for (PersonURL pURL : urlSet) {
					collabList.add(pURL.getName());
				}

				// iterate through list of collaborators
				for (String collabString : collabList) {

					// if person has not been encountered yet
					if (!foundPersons.keySet().contains(collabString)) {
						Person collabPerson = new Person(collabString);
						foundPersons.put(collabString, collabPerson);
						person.addCollab(collabPerson);

						// if person has already been encountered
					} else {
						person.addCollab(foundPersons.get(collabString));
					}

				}

			}

		}

	}

	// function to print the contents of the graph
	public void printGraph() {

		for (Person p : personGraph) {

			System.out.println("Name: " + p.getName());
			for (Entry<Person, Integer> e : p.getCollabs().entrySet()) {

				String collabString = e.getKey().getName();
				int collabCount = e.getValue();
				System.out.println("Collaborator: " + collabString + " ["
						+ collabCount + "]");

			}
			System.out.println();

		}

	}

	//compute and print neighborhood overlap
	public void printNBOverlap() {
		
		//initialize datastructures
		ArrayList<Person> kanyeList = new ArrayList<Person>();
		ArrayList<Person> collabsList = null;

		//add all of Kanye's collaborators to a set
		for (Entry<Person, Integer> e : foundPersons.get("Kanye West")
				.getCollabs().entrySet()) {
			kanyeList.add(e.getKey());
		}

		//print out the number of Kanye's collaborators
		System.out.println(kanyeList.size());
		
		//find instances of triadic closure between Kanye's collaborators
		for (Person p : personGraph) {
			double num = 0, denum = 0, nb = 0;
			collabsList = new ArrayList<Person>();
			for (Entry<Person, Integer> e : p.getCollabs().entrySet()) {
				collabsList.add(e.getKey());
			}
			collabsList.remove(foundPersons.get("Kanye West"));

			for (int j = 0; j < kanyeList.size(); j++) {
				for (int k = 0; k < collabsList.size(); k++) {
					if (kanyeList.get(j).equals(collabsList.get(k))) {
						num++;
					}
				}
			}
			denum = kanyeList.size() - 1 + collabsList.size() - num;
			nb = num / denum;
			System.out.println("Kanye's Neighborhood Overlap with "
					+ p.getName() + " is: " + nb);
		}
	}

}
