package kanyeTweets;
import java.util.*;
import twitter4j.*;
import twitter4j.conf.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

//You'll need a ConsumerKey, ConsumerSecret AccessToken, and AccessTokenSecret from Twitter Apps
public class twitterScrape {

	public static void main(String[] a) throws IOException {

	    ConfigurationBuilder cb = new ConfigurationBuilder();
	    cb.setOAuthConsumerKey("b8nRrNvy5K07C9cnqSw6xu8jw");
	    cb.setOAuthConsumerSecret("LcJE1QlYA8h8IhRiaSxNqWTLrV3i3DMgJS1XxfkPRPRayVZ4MF");
	    cb.setOAuthAccessToken("44753779-Qj5WTrXRK2cEVyHNixGLX6loojswBD8w9l789wqpK");
	    cb.setOAuthAccessTokenSecret("UVYTXbxAjQbpIK0XPKtSEsIkH9ZbYc4TYEK5ZBxFjjRKU");

	    Twitter twitter = new TwitterFactory(cb.build()).getInstance();

	    int pageno = 1;
	    String user = "kanyewest";
	    ArrayList<ResponseList<Status>> statuses = new ArrayList<ResponseList<Status>>();
	    StringBuilder spaceSeparated = new StringBuilder();
//	    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("kanyeTweets.txt", true)));
	   
	    
	    while (true) {

	      try {
	    	// Number of tweets from Kanye  
	        int size = statuses.size(); 
	        // Goes to next page of Tweets
	        Paging page = new Paging(pageno++, 100);
	        // Adds all Tweets on page from Kanye to statuses
	        statuses.add(twitter.getUserTimeline(user, page));
	        
	        for(Object s : statuses){
	        	spaceSeparated.append(s.toString().replaceAll("'", ""));
	        }
	        
	        // Breaks while loop if end of status is reached.
	        if (statuses.size() == size)
		          break;
	        
	        // Pattern-matches for actual tweets from source
	        for(int i=1; i<statuses.size(); i++){
	        	Pattern r = Pattern.compile("((?<=text=).*?(?=, source))");
				String source = spaceSeparated.toString();
				Matcher m = r.matcher(source);
				
				// For every tweet, writes into file kanyeTweets.txt
				while (m.find()){
//					System.out.println(m.group(1)); // prints out all tweets
					
					try(FileWriter fw = new FileWriter("kanyeTweets.txt", true);
						    BufferedWriter bw = new BufferedWriter(fw);
						    PrintWriter out = new PrintWriter(bw))
						{
						    out.println(m.group(1));
						} catch (IOException e) {
						}	
				}
	        }
	        
	      }
	      catch(TwitterException e) {

	        e.printStackTrace();
	      }
	    }
	    
	    
	  
	}
	
}
