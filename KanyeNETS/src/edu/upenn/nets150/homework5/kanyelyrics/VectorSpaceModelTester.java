package edu.upenn.nets150.homework5.kanyelyrics;

import java.util.ArrayList;

/**
 * the tester class.
 * @author swapneel
 */
public class VectorSpaceModelTester {

	public static void main(String[] args) {
		
		Document d1 = new Document("bible.txt");
		Document d2 = new Document("dhammapada.txt");
		Document d3 = new Document("paine.txt");
		Document d4 = new Document("quran.txt");
		Document d5 = new Document("talmud.txt");
		Document d6 = new Document("vedas.txt"); 

		
		ArrayList<Document> documents = new ArrayList<Document>();
		documents.add(d1);
		documents.add(d2);
		documents.add(d3);
		documents.add(d4);
		documents.add(d5);
		documents.add(d6);
		
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
