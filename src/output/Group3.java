package output;

import input.SentencesInput;
import java.lang.Math;
import controller.*;

public class Group3 extends SentencesInput implements RandomAndComplete {
	public Group3() {

	}

	@Override
	public String chooseRandomSentence() {
		int numberOfSentence = 0;
		for (String s : sentences[2]) {
			if (s == null)
				break;
			numberOfSentence++;
		}
		int rand = (int) (Math.random() * numberOfSentence);
		return sentences[2][rand];
	}

	@Override
	public String completeSentence(String s) {
		IndexData index = new IndexData();
		float countUp = Float.valueOf(index.countUpStock());
		float countDown = Float.valueOf(index.countDownStock());
		float countNoChange = Float.valueOf(index.countNochangeStock());
		float sum = countUp + countDown + countNoChange;
		countUp = countUp / sum * 100;
		countDown = countDown / sum * 100;
		countNoChange = countNoChange / sum * 100;
		
		s = s.replace("(%countUp)", String.format("%.2f", countUp));
		s = s.replace("(%countDown)", String.format("%.2f",countDown));
		s = s.replace("(%countStand)", String.format("%.2f",countNoChange));
		
		Boolean decrease = index.indexChange().startsWith("-");
		if (countUp > countDown && (decrease == false)) {
			s = s.replace("(sự tăng giá|sự giảm giá)",
					"sự tăng giá cùng với sự vững giá của các mã cổ phiếu không biến động giá so với tham chiếu đang chiếm ưu thế");
			s = s.replace("(sắc xanh|sắc đỏ)", "sắc xanh");
		} else if (countUp < countDown && (decrease == false)) {
			s = s.replace("(sự tăng giá|sự giảm giá)",
					"số mã tăng tuy ít hơn số mã giảm nhưng các mã trụ của thị trường vẫn làm tốt nhiệm vụ vì thế");
			s = s.replace("(sắc xanh|sắc đỏ)", "sắc xanh");
		} else if (countUp > countDown && (decrease == true)) {
			s = s.replace("(sự tăng giá|sự giảm giá)",
					"tuy số mã tăng chiếm ưu thế nhưng do có quá nhiều mã giảm sâu vì vậy");
			s = s.replace("(sắc xanh|sắc đỏ)", "sắc đỏ");
		} else {
			s = s.replace("(sự tăng giá|sự giảm giá)",
					"sự giảm giá cùng với sự chững giá của các mã cổ phiếu không biến động giá so với tham chiếu");
			s = s.replace("(sắc xanh|sắc đỏ)", "sắc đỏ");
		}

		return s;
	}

	public static void main(String[] args) {
		Group3 sentence3 = new Group3();
		System.out.println(sentence3.completeSentence(sentence3.chooseRandomSentence()));
	}

}
