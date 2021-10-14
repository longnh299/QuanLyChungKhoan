package output;

import input.SentencesInput;
import java.lang.Math;
import controller.*;

public class Group1 extends SentencesInput implements RandomAndComplete {
	public Group1() {

	}

	@Override
	public String chooseRandomSentence() {
		int numberOfSentence = 0;
		for (String s : sentences[0]) {
			if (s == null)
				break;
			numberOfSentence++;
		}
		int rand = (int) (Math.random() * numberOfSentence);
		return sentences[0][rand];
	}

	@Override
	public String completeSentence(String s) {
		IndexData index = new IndexData();
		if (s == null)
			return "";
		s = s.replace("(time)", index.date());
		s = s.replace("(tenChiSo)", index.nameIndex());
		s = s.replace("(openIndex)", index.openIndex());
		s = s.replace("(lowestIndex)", index.lowestIndex());
		s = s.replace("(highestIndex)", index.highestIndex());

		if (index.closeIndex() == index.highestIndex()) {
			s = s.replace("(cao nhất|thấp nhất| )", "cao nhất");
		} else if (index.closeIndex() == index.lowestIndex()) {
			s = s.replace("(cao nhất|thấp nhất| )", "thấp nhất");
		} else {
			s = s.replace("(cao nhất|thấp nhất| )", "");
		}

		s = s.replace("(closeIndex)", index.closeIndex());

		if (index.indexChange().startsWith("-")) {
			s = s.replace("(tăng|giảm)", "giảm");
			String indexchange = index.indexChange();
			String percentchange = index.percentChange();
			indexchange = indexchange.replace("-", "");
			percentchange = percentchange.replace("-", "");
			s = s.replace("(indexOfChange)", indexchange);
			s = s.replace("(percentChange)", percentchange);
		} else {
			s = s.replace("(tăng|giảm)", "tăng");
			s = s.replace("(indexOfChange)", index.indexChange());
			s = s.replace("(percentChange)", index.percentChange());
		}

		s = s.replace("(indexpointYesterday)", index.indexPointYesterday());
		s = s.replace("(KLGD khop lenh)", index.klgd());

		float gtgd = Float.valueOf(index.gtgd()) / 1000000000.00f;
		s = s.replace("(GTGD khop lenh)", String.valueOf(gtgd));
		s = s.replace("(KLGD thoa thuan)", index.klgdThoaThuan());

		gtgd = Float.valueOf(index.gtgdThoaThuan()) / 1000000000.00f;
		s = s.replace("(GTGD thoa thuan)", String.valueOf(gtgd));
		return s;
	}

	public static void main(String[] args) {
		Group1 sentence1 = new Group1();
		System.out.println(sentence1.completeSentence(sentence1.chooseRandomSentence()));

	}
}
