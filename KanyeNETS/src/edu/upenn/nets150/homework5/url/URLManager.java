package edu.upenn.nets150.homework5.url;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * This class is used by JSoupQuery and ExtraCredit to establish connections to websites.
 * Based on the code given to us on Canvas.
 * @author DanielMoreno
 *
 */
public class URLManager {
	
	private URL url;
    private HttpURLConnection httpConnection;
    
    /**
     * This method establishes the connection based on a String url
     * @param url
     */
    public URLManager(String url){
    	try{
            this.url = new URL(url);
            URLConnection connection = this.url.openConnection();
            httpConnection = (HttpURLConnection) connection;
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    /**
     * This method will print the status codes from the connection.
     */
    public String printStatusCode(){
        try {
            int code = httpConnection.getResponseCode();
            String message = httpConnection.getResponseMessage();

            return(code + " : " + message);
        } catch (Exception e){
            e.printStackTrace();
        }
		return null;
    }
    
    /**
     * This method returns the manager's URL in string format
     * @return
     */
    public URL getURL(){
        return this.url;
    }

    /**
     * gets HTML contents of URL
     * @return String containing HTML elements of page
     */
    public String getContents(){
        StringBuilder contents = new StringBuilder();
        Scanner in;
        try{
            in = new Scanner(httpConnection.getInputStream());
            while(in.hasNextLine()){
                String line = in.nextLine();
                contents.append(line);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return contents.toString();
    }
    
    public void sendGet(PrintWriter writer) throws Exception {
		// optional default is GET
		httpConnection.setRequestMethod("GET");

		//add request header
		//httpConnection.setRequestProperty("User-Agent", USER_AGENT);
		int responseCode = httpConnection.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(httpConnection.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		JSONParser parser = new JSONParser();
		JSONObject albumArray = (JSONObject) parser.parse(response.toString());
		JSONArray album = (JSONArray) albumArray.get("result");
		for (int i = 0; i < album.size(); i++) {
			JSONParser lyricsParser = new JSONParser();
			JSONObject song = (JSONObject) lyricsParser.parse(album.get(i).toString());
			writer.println(song.get("lyrics"));
		}
		


	}
}

