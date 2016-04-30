package edu.upenn.nets150.homework5.grammys;



import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import edu.upenn.nets150.homework5.url.URLManager;

public class JSoupQuery {
	private String host, protocol;
    private URLManager urlManager;
    
    //maps collaborator to url
    private TreeMap<String, TreeMap<String, TreeSet<PersonURL>>> masterData = new  TreeMap<String, TreeMap<String, TreeSet<PersonURL>>>();
    
	public JSoupQuery (String inputURL){
        try {
        	urlManager= new URLManager(inputURL);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // build new URL to establish connections to each country's page
        protocol = urlManager.getURL().getProtocol();
        host = urlManager.getURL().getHost();
       
        //get HTML content of page
        String page = urlManager.getContents();
        Document doc = Jsoup.parse(page);
        
        Elements allElements = doc.getAllElements();
        TreeMap<String, TreeSet<PersonURL>> kanyeSongs = new TreeMap<String, TreeSet<PersonURL>>();
        for (Element a : allElements){
        	if (a.tagName().equals("h3")){
        		if(a.text().equals("Grammy Awards")){
        			Element table = a.nextElementSibling();
        			Elements entries = table.getElementsByTag("tr");
        			for(Element entry : entries){
        				TreeSet<PersonURL> collabs = new TreeSet<PersonURL>();
        				Elements description = entry.getElementsByClass("award_description");
        				for(Element d : description){
            				Elements names = d.getElementsByTag("a");
            				for(Element n : names){
            					String endpoint = n.attr("href").split("\\?")[0];
            					String url = new String(protocol+"://"+host+endpoint+"/awards");
            					collabs.add(new PersonURL(n.text(), url));
            				}
        				}
        				Elements detail = entry.getElementsByClass("award_detail_notes");
        				Pattern p = Pattern.compile("\"(.*)\"");
            			Matcher m = p.matcher(detail.text());
            			if(m.find()){
            				String song = m.group(1);
            				if(song.charAt(song.length()-1)=='.'){
            					song = removeLastChar(song);
            				}
            				if(kanyeSongs.containsKey(song)){
            					kanyeSongs.get(song).addAll(collabs);
            				}else{
            					if(!collabs.isEmpty()){
                    				kanyeSongs.put(song, collabs);
            					}
            				}
            			}	
        			}
        		}
        	}
        }
        masterData.put("Kanye West", kanyeSongs);
        //Now we get all of kanye's Neighbors based on the above
        for (Entry<String, TreeSet<PersonURL>> entry : kanyeSongs.entrySet())
        {
        	for(PersonURL p : entry.getValue()){
        		
        		if(!masterData.containsKey(p.getName())){
        			getCollaborators(p.getName(), p.getURL());
        		}
        	}
        }
	}
	
	private static String removeLastChar(String str) {
        return str.substring(0,str.length()-1);
    }

	public void getCollaborators(String name, String inputURL){
        try {
        	urlManager= new URLManager(inputURL);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // build new URL to establish connections to each country's page
        protocol = urlManager.getURL().getProtocol();
        host = urlManager.getURL().getHost();
       
        //get HTML content of page
        String page = urlManager.getContents();
        Document doc = Jsoup.parse(page);
        
        Elements allElements = doc.getAllElements();
        TreeMap<String, TreeSet<PersonURL>> songs = new TreeMap<String, TreeSet<PersonURL>>();
        for (Element a : allElements){
        	if (a.tagName().equals("h3")){
        		if(a.text().equals("Grammy Awards")){
        			Element table = a.nextElementSibling();
        			Elements entries = table.getElementsByTag("tr");
        			for(Element entry : entries){
        				TreeSet<PersonURL> collabs = new TreeSet<PersonURL>();
        				Elements description = entry.getElementsByClass("award_description");
        				for(Element d : description){
            				Elements names = d.getElementsByTag("a");
            				for(Element n : names){
            					String endpoint = n.attr("href").split("\\?")[0];
            					String url = new String(protocol+"://"+host+endpoint+"/awards");
            					collabs.add(new PersonURL(n.text(), url));
            				}
        				}
        				Elements detail = entry.getElementsByClass("award_detail_notes");
        				Pattern p = Pattern.compile("\"(.*)\"");
            			Matcher m = p.matcher(detail.text());
            			if(m.find()){
            				String song = m.group(1);
            				if(song.charAt(song.length()-1)=='.'){
            					song = removeLastChar(song);
            				}
            				if(songs.containsKey(song)){
            					songs.get(song).addAll(collabs);
            				}else{
            					if(!collabs.isEmpty()){
                    				songs.put(song, collabs);
            					}
            				}
            			}	
        			}
        		}
        	}
        }
        masterData.put(name, songs);
	}
	
	public TreeMap<String, TreeMap<String, TreeSet<PersonURL>>> getMasterData(){
		return masterData;
	}
	
	public void printMasterData(){
		for (Entry<String, TreeMap<String, TreeSet<PersonURL>>> entry : masterData.entrySet())
        {
			System.out.println(entry.getKey());
			for(Entry<String, TreeSet<PersonURL>> song : entry.getValue().entrySet()){
				System.out.println("\t"+song.getKey()+"\t"+song.getValue());
			}
        }
	}

}
