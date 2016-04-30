package edu.upenn.nets150.homework5.kanyelyrics;

import java.io.PrintWriter;
import java.util.ArrayList;

import edu.upenn.nets150.homework5.url.URLManager;

public class TweetsMain {

	public static void main(String[] args) throws Exception {

		String kanyeREST = "http://www.kanyerest.xyz/api/album/";
		String myBeautifulDarkTwistedFantasy = "my_beautiful_dark_twisted_fantasy";
		String watchTheThrone = "watch_the_throne";
		String yeezus = "yeezus";
		String lifeOfPablo = "the_life_of_pablo";

		URLManager kanyeMBDTF = new URLManager(kanyeREST + myBeautifulDarkTwistedFantasy);
		PrintWriter MBDTF = new PrintWriter("my_beautiful_dark_twisted_fantasy.txt", "UTF-8");
        kanyeMBDTF.sendGet(MBDTF);
        MBDTF.close();
        
        URLManager kanyeWTT = new URLManager(kanyeREST + watchTheThrone);
		PrintWriter WTT = new PrintWriter("watch_the_throne.txt", "UTF-8");
        kanyeWTT.sendGet(WTT);
        WTT.close();
        
        URLManager kanyeYZS = new URLManager(kanyeREST + yeezus);
		PrintWriter YZS = new PrintWriter("yeezus.txt", "UTF-8");
        kanyeYZS.sendGet(YZS);
        YZS.close();
        
        URLManager kanyeTLOP = new URLManager(kanyeREST + lifeOfPablo);
		PrintWriter TLOP = new PrintWriter("the_life_of_pablo.txt", "UTF-8");
        kanyeTLOP.sendGet(TLOP);
        TLOP.close();
        
        Document d1 = new Document("my_beautiful_dark_twisted_fantasy.txt");
		Document d2 = new Document("watch_the_throne.txt");
		Document d3 = new Document("yeezus.txt");
		Document d4 = new Document("the_life_of_pablo.txt");

		
		ArrayList<Document> documents = new ArrayList<Document>();
		documents.add(d1);
		documents.add(d2);
		documents.add(d3);
		documents.add(d4);

		Corpus corpus = new Corpus(documents);
		
		VectorSpaceModel vectorSpace = new VectorSpaceModel(corpus);
		
		
		for (int i = 0; i < documents.size(); i++) {
			for (int j = i + 1; j < documents.size(); j++) {
				Document doc1 = documents.get(i);
				Document doc2 = documents.get(j);
				System.out.println("\nComparing " + doc1 + " and " +  doc2);
				System.out.println(vectorSpace.cosineSimilarity(doc1, doc2));
			}
		}
	}

}
