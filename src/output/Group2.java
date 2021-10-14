package output;

import input.SentencesInput;
import model.StockCode;

import java.lang.Math;
import java.util.ArrayList;

import controller.*;

public class Group2 extends SentencesInput implements RandomAndComplete {
	public Group2() {

	}

	@Override
	public String chooseRandomSentence() {
		int numberOfSentence = 0;
		for (String s : sentences[1]) {
			if (s == null)
				break;
			numberOfSentence++;
		}
		int rand = (int) (Math.random() * numberOfSentence);
		return sentences[1][rand];
	}

	@Override
	public String completeSentence(String s) {
		IndexData index = new IndexData();
		if (index.indexChange().startsWith("-")) {
			s = s.replace("(tăng điểm|giảm điểm)", "giảm điểm");
		} else if (index.indexChange().equals("0.00")) {
			s = s.replace("(tăng điểm|giảm điểm)", "với chỉ số thị trường không đổi");
		} else
			s = s.replace("(tăng điểm|giảm điểm)", "tăng điểm");
		ArrayList<StockCode> clist = stockInfo.ceilingList(index.nameIndex());
		ArrayList<StockCode> flist = stockInfo.floorList(index.nameIndex());

		s = s.replace("(countUp)", index.countUpStock());
		s = s.replace("(countCeiling)", index.countCeilingStock());
		s = s.replace("(countFloor)", index.countFloorStock());
		
		int sizeFloor = flist.size();
		int sizeCeiling = clist.size();
		for (int i = 1; i <= sizeFloor; i++) {
			int rand = (int) (Math.random() * flist.size());
			s = s.replace("(floorName" + i + ")", flist.get(rand).getName());
			flist.remove(rand);
		}
		for(int i = sizeFloor+1; i<=4; i++) {
			s = s.replace(" (floorName" + i + "),", "");
		}
		for (int i = 1; i <= sizeCeiling; i++) {
			int rand = (int) (Math.random() * clist.size());
			s = s.replace("(ceilingName" + i + ")", clist.get(rand).getName());
			clist.remove(rand);
		}
		for(int i = sizeCeiling+1; i<=4; i++) {
			s = s.replace(" (ceilingName" + i + "),", "");
		}
		s = s.replace("(countDown)", index.countDownStock());
		s = s.replace("(countStand)", index.countNochangeStock());

		return s;
	}

	public static void main(String[] args) {
		Group2 sentence2 = new Group2();
		System.out.println(sentence2.completeSentence(sentence2.chooseRandomSentence()));

	}
}
