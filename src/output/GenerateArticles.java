package output;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;


public class GenerateArticles {
	
	private Group1 g1 = new Group1();
	private Group2 g2 = new Group2();
	private Group3 g3 = new Group3();
	private Group4 g4 = new Group4();
	private Group5 g5 = new Group5();
	private Group6 g6 = new Group6();
	//public IndexData generalIndex = new IndexData();

	public void generateArticle(String filename) {
		 
		File file = new File(filename);
		try {
			PrintWriter pw = new PrintWriter(file);
			pw.println("       " + g1.completeSentence(g1.chooseRandomSentence()));
			pw.println();
			pw.println("       " + g2.completeSentence(g2.chooseRandomSentence()));
			pw.println();
			pw.println("       " + g3.completeSentence(g3.chooseRandomSentence()));
			pw.println();
			pw.println("       " + g4.completeSentence(g4.chooseRandomSentence()));
			pw.println();
			pw.println("       " + g5.completeSentence(g5.chooseRandomSentence()));
			pw.println();
			pw.println("       " + g6.completeSentence(g6.chooseRandomSentence()));
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	// test
	public static void main(String[] args) {
		GenerateArticles g = new GenerateArticles();
		g.generateArticle("E:\\java\\chungkhoan\\output.txt");
	}
}
