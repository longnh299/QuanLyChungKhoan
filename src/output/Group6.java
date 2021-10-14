package output;

import input.SentencesInput;

import controller.*;

public class Group6 extends SentencesInput implements RandomAndComplete {
	public Group6() {

	}

	@Override
	public String chooseRandomSentence() {
		int numberOfSentence = 0;
		for (String s : sentences[5]) {
			if (s == null)
				break;
			numberOfSentence++;
		}
		int rand = (int) (Math.random() * numberOfSentence);
		return sentences[5][rand];
	}

	@Override
	public String completeSentence(String s) {
		IndexData index = new IndexData();
		float change = Float.valueOf(index.indexChange());
		int countUpContinuous = Integer.valueOf(index.countUpContinuous());
		int countDownContinuous = Integer.valueOf(index.countDownContinuous());
		if (change < 0.00f) {
			if (countDownContinuous >= 2) {
				s = s.replace(
						"(tiếp tục tăng điểm|tiếp tục giảm điểm|tăng lại sau khi giảm liên tục|hạ nhiệt sau khi tăng liên tục)",
						"tiếp tục giảm điểm");
				s = s.replace("(countUpContinuous|counDownContinuous)", index.countDownContinuous() + " liên tiếp");
				s = s.replace("(tích cực|tiêu cực|hồi phục|điều chỉnh)", "tiêu cực");
			} else if (countDownContinuous == 1 && countUpContinuous == 1) {
				s = s.replace(
						"(tiếp tục tăng điểm|tiếp tục giảm điểm|tăng lại sau khi giảm liên tục|hạ nhiệt sau khi tăng liên tục)",
						"tăng điểm");
				s = s.replace("phiên thứ (countUpContinuous|counDownContinuous)", "vào phiên hôm qua");
				s = s.replace("(tích cực|tiêu cực|hồi phục|điều chỉnh)", "điều chỉnh");
			} else {
				s = s.replace(
						"(tiếp tục tăng điểm|tiếp tục giảm điểm|tăng lại sau khi giảm liên tục|hạ nhiệt sau khi tăng liên tục)",
						"hạ nhiệt sau khi tăng liên tục");
				s = s.replace("phiên thứ (countUpContinuous|counDownContinuous)",
						index.countUpContinuous() + " phiên trước đó");
				s = s.replace("(tích cực|tiêu cực|hồi phục|điều chỉnh)", "điều chỉnh");
			}
		} else if (change > 0.00f) {
			if (countUpContinuous >= 2) {
				s = s.replace(
						"(tiếp tục tăng điểm|tiếp tục giảm điểm|tăng lại sau khi giảm liên tục|hạ nhiệt sau khi tăng liên tục)",
						"tiếp tục tăng điểm");
				s = s.replace("(countUpContinuous|counDownContinuous)", index.countUpContinuous() + " liên tiếp");
				s = s.replace("(tích cực|tiêu cực|hồi phục|điều chỉnh)", "tích cực");
			} else if (countUpContinuous == 1 && countDownContinuous == 1) {
				s = s.replace(
						"(tiếp tục tăng điểm|tiếp tục giảm điểm|tăng lại sau khi giảm liên tục|hạ nhiệt sau khi tăng liên tục)",
						"giảm điểm");
				s = s.replace("phiên thứ (countUpContinuous|counDownContinuous)", "vào phiên hôm qua");
				s = s.replace("(tích cực|tiêu cực|hồi phục|điều chỉnh)", "hồi phục");
			} else {
				s = s.replace(
						"(tiếp tục tăng điểm|tiếp tục giảm điểm|tăng lại sau khi giảm liên tục|hạ nhiệt sau khi tăng liên tục)",
						"tăng lại sau khi giảm liên tục");
				s = s.replace("phiên thứ (countUpContinuous|counDownContinuous)",
						index.countDownContinuous() + " phiên trước đó");
				s = s.replace("(tích cực|tiêu cực|hồi phục|điều chỉnh)", "hồi phục");
			}
		}
		s = s.replace("(averageIndex(5))", index.averageIndex(5));
		s = s.replace("(averageIndex(10))", index.averageIndex(10));
		s = s.replace("(averageIndex(20))", index.averageIndex(20));
		long volume = Long.valueOf(index.klgd());
		long average20Volume = Long.valueOf(index.averageVolume(20));
		float i = Float.valueOf(index.closeIndex());
		float index20average = Float.valueOf(index.averageIndex(20));
		
		if (volume > average20Volume) {
			s = s.replace("(giảm|tăng)", "tăng");
			s = s.replace("(averageVolume(20))", index.averageVolume(20));
			s = s.replace("(thận trọng|tích lũy)", "tích lũy cổ phiếu");
			if (i >= index20average) {
				s = s.replace("(lên khá cao|xuống khá thấp)", "lên khá cao");
				s = s.replace("(averageIndex)",
						"trung bình giá 20 ngày trước đó(MA20): " + index.averageIndex(20));
			}
			else {
				s = s.replace("(lên khá cao|xuống khá thấp)", "xuống khá thấp");
				s = s.replace("(averageIndex)",
						"trung bình giá 20 ngày trước đó(MA20): " + index.averageIndex(20));
			}
		}
		else {
			s = s.replace("(giảm|tăng)", "giảm");
			s = s.replace("(averageVolume(20))", index.averageVolume(20));
			s = s.replace("(thận trọng|tích lũy)", "thận trọng");
			if (i >= index20average) {
				s = s.replace("(lên khá cao|xuống khá thấp)", "lên khá cao");
				s = s.replace("(averageIndex)",
						"trung bình giá 20 ngày trước đó(MA20): " + index.averageIndex(20));
			}
			else {
				s = s.replace("(lên khá cao|xuống khá thấp)", "xuống khá thấp");
				s = s.replace("(averageIndex)",
						"trung bình giá 20 ngày trước đó(MA20): " + index.averageIndex(20));
			}
		}
		return s;
	}

	public static void main(String[] args) {
		Group6 sentence = new Group6();
		System.out.println(sentence.completeSentence(sentence.chooseRandomSentence()));
	}

}
