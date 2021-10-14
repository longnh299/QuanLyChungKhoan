package output;

import input.SentencesInput;
import model.StockCode;

import java.lang.Math;
import java.util.ArrayList;

import controller.*;

public class Group5 extends SentencesInput implements RandomAndComplete {
	public Group5() {

	}

	@Override
	public String chooseRandomSentence() {
		int numberOfSentence = 0;
		for (String s : sentences[4]) {
			if (s == null)
				break;
			numberOfSentence++;
		}
		int rand = (int) (Math.random() * numberOfSentence);
		return sentences[4][rand];
	}

	@Override
	public String completeSentence(String s) {
		TopIndex topIndex = new TopIndex();
		// new IndexData();
		ArrayList<StockCode> topVolume = topIndex.top10Volume();
		int countUp = 0, countDown = 0, countNoChange = 0;
		for (int i = 1; i <= 10; i++) {
			StockCode volume = topVolume.get(i - 1);
			float change = volume.getChange();
			if (change > 0.00f)
				countUp++;
			else if (change == 0.00f)
				countNoChange++;
			else
				countDown++;
			s = s.replace("(topVolumeName" + i + ")", volume.getName());
			s = s.replace("(topVolume" + i + ")", String.valueOf(volume.getVolume()));
		}
		s = s.replace("(countTopVolumeDown)", String.valueOf(countDown));
		s = s.replace("(countTopVolumeStand)", String.valueOf(countNoChange));
		s = s.replace("(countTopVolumeUp)", String.valueOf(countUp));
		int max = countUp;
		if (max < countDown)
			max = countDown;
		if (max < countNoChange)
			max = countNoChange;
		if (max == countUp)
			s = s.replace("(tăng giá|giảm giá)", "tăng giá");
		else if (max == countDown)
			s = s.replace("(tăng giá|giảm giá)", "giảm giá");
		else if (max == countNoChange)
			s = s.replace("(tăng giá|giảm giá)", "tích lũy");
		return s;
	}

	public static void main(String[] args) {
		Group5 sentence5 = new Group5();
		System.out.println(sentence5.completeSentence(sentence5.chooseRandomSentence()));
	}

}
