package edu.upenn.nets150.homework5.kanyelyrics;
import java.util.*;
import twitter4j.*;
import twitter4j.conf.*;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;


//You'll need a ConsumerKey, ConsumerSecret AccessToken, and AccessTokenSecret from Twitter Apps
public class TwitterScrape {

	public static void main(String[] a) throws IOException {

		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setOAuthConsumerKey("KNZdZiyvIuI8Bdw1jCfjWBK4O");
		cb.setOAuthConsumerSecret("AfSY7WiVPx4FEE9rGVeZK1lDwHYQfys9ki1vHllihm7FoY8WzF");
		cb.setOAuthAccessToken("1255797912-s2TVSBqB8lHE29AUPKGVZsoIgz9p08gn4Uz8Obv");
		cb.setOAuthAccessTokenSecret("WdDK8LnUKmHQlpsT8zPp1F4FVy6qzDLu8x3bxgUB3PW7m");

		Twitter twitter = new TwitterFactory(cb.build()).getInstance();

		int pageno = 1;
		String user = "kanyewest";
		ArrayList<Status> statuses = new ArrayList<Status>();
		StringBuilder spaceSeparated = new StringBuilder();

		// Gets the Tweet elements from Kanye's profile
		try {
			ResponseList<Status> statusesList;
			do {
				Paging page = new Paging(pageno,320);
				// Adds all Tweets on page from Kanye to statuses
				statusesList = twitter.getUserTimeline(user, page);
				statuses.addAll(statusesList);
				++pageno;
			} while (!statusesList.isEmpty());
		}
		catch (TwitterException te) {
			System.out.println("Couldn't connect: " + te);
		}; 
		
		// Splits the Tweet elements into a String, separated by new lines
        for(Status s : statuses){
	        	spaceSeparated.append(s.getText() + "\n");
        	}
			
		// Writes all the Tweets into file kanyeTweets.txt
		try(FileWriter fw = new FileWriter("kanyeTweets.txt", true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw))
		{
			out.println(spaceSeparated);
		} catch (IOException e) {
		}	
	}

}