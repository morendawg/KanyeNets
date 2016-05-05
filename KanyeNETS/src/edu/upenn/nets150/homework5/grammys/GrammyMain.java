package edu.upenn.nets150.homework5.grammys;

public class GrammyMain {

	public static void main(String[] args) {		
		/**
		 * Starts at Kanye's IMDb page, and then uses Jsoup to crawl the data.
		 */
		String kanyeAwards = "http://www.imdb.com/name/nm1577190/awards";
		JSoupQuery jSoupQuery = new JSoupQuery(kanyeAwards);
		jSoupQuery.getMasterData();
		jSoupQuery.printMasterData();
	}

}
