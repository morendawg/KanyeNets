package edu.upenn.nets150.homework5.kanyelyrics;

import java.io.PrintWriter;
import java.util.ArrayList;

import edu.upenn.nets150.homework5.url.URLManager;

public class TweetsMain {

	public static void main(String[] args) throws Exception {

		String kanyeREST = "http://www.kanyerest.xyz/api/album/";
		
		String collegeDropout = "the_college_dropout";
		String lateRegistration = "late_registration";
		String graduation = "graduation";
		String heartbreak = "808s_&amp;_heartbreak";

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
        
        URLManager kanyeCD = new URLManager(kanyeREST + collegeDropout);
		PrintWriter CD = new PrintWriter("the_college_dropout.txt", "UTF-8");
        kanyeCD.sendGet(CD);
        CD.close();
        
        URLManager kanyeLR = new URLManager(kanyeREST + lateRegistration);
		PrintWriter LR = new PrintWriter("late_registration.txt", "UTF-8");
        kanyeLR.sendGet(LR);
        LR.close();
        
        URLManager kanyeGRAD = new URLManager(kanyeREST + graduation);
		PrintWriter GRAD = new PrintWriter("graduation.txt", "UTF-8");
        kanyeGRAD.sendGet(GRAD);
        GRAD.close();
        
        URLManager kanye808 = new URLManager(kanyeREST + heartbreak);
		PrintWriter k808 = new PrintWriter("808s_and_heartbreak.txt", "UTF-8");
        kanye808.sendGet(k808);
        k808.close();
        
        Document d1 = new Document("my_beautiful_dark_twisted_fantasy.txt");
		Document d2 = new Document("watch_the_throne.txt");
		Document d3 = new Document("yeezus.txt");
		Document d4 = new Document("the_life_of_pablo.txt");
		Document d5 = new Document("the_college_dropout.txt");
		Document d6 = new Document("late_registration.txt");
		Document d7 = new Document("graduation.txt");
		Document d8 = new Document("808s_and_heartbreak.txt");

		
		ArrayList<Document> documents = new ArrayList<Document>();
		documents.add(d1);
		documents.add(d2);
		documents.add(d3);
		documents.add(d4);
		documents.add(d5);
		documents.add(d6);
		documents.add(d7);
		documents.add(d8);


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
