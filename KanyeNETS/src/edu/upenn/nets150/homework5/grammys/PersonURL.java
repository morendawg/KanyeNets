package edu.upenn.nets150.homework5.grammys;

public class PersonURL implements Comparable<PersonURL>{

	private String name;
	private String URL;
	
	public PersonURL(String n, String u){
		name = n;
		URL = u;
	}
	
	public String getName(){
		return name;
	}
	
	public String getURL(){
		return URL;
	}
	
	@Override
	public String toString(){
		return name;
	}
	
	
	public boolean equals(PersonURL other)
	{
	    return this.name.equals(other.getName()); 
	}
	
	public int compareTo(PersonURL other) {
	    return this.name.compareTo(other.getName());
	}
}
