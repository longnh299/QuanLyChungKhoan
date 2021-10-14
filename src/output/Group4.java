package output;

import input.SentencesInput;
import model.StockCode;

import java.lang.Math;
import java.util.ArrayList;

import controller.*;

public class Group4 extends SentencesInput implements RandomAndComplete {
	public Group4() {

	}

	@Override
	public String chooseRandomSentence() {
		int numberOfSentence = 0;
		for (String s : sentences[3]) {
			if (s == null)
				break;
			numberOfSentence++;
		}
		int rand = (int) (Math.random() * numberOfSentence);
		return sentences[3][rand];
	}

	@Override
	public String completeSentence(String s) throws ArrayIndexOutOfBoundsException {
		TopIndex topIndex = new TopIndex();
		IndexData index = new IndexData();
		ArrayList<StockCode> top10IncreasePrice = topIndex.top10IncreasePrice();
		ArrayList<StockCode> top10DecreasePrice = topIndex.top10DecreasePrice();
		ArrayList<StockCode> topModest = stockInfo.modestUp();
		if (top10IncreasePrice.get(0).getChange() < 0.0f) {// nếu mã tăng nhiều nhất mà vẫn giảm thì thị trường giảm
															// toàn bộ
			s = s.replace("(tăng mạnh nhất sàn|giảm ít nhất sàn)", "giảm ít nhất sàn");
			s = s.replace(
					"(kéo thị trường tăng điểm|vớt vát phần nào cho thị trường đang giảm điểm|ảnh hường tới thị trường ít nhất)",
					"ảnh hường tới thị trường ít nhất");
			s = s.replace(
					"(làm giảm sự hưng phấn của thị trường|là nguyên nhân chính gây nên sự giảm điểm của thị trường|tăng ít nhất)",
					"là nguyên nhân chính gây nên sự giảm điểm của thị trường");
		} else if (top10IncreasePrice.get(0).getChange() > 0.0f && Float.valueOf(index.indexChange()) >= 0.0f) {
			s = s.replace("(tăng mạnh nhất sàn|giảm ít nhất sàn)", "tăng mạnh nhất sàn");
			s = s.replace(
					"(kéo thị trường tăng điểm|vớt vát phần nào cho thị trường đang giảm điểm|ảnh hường tới thị trường ít nhất)",
					"kéo thị trường tăng điểm");
			s = s.replace(
					"(làm giảm sự hưng phấn của thị trường|là nguyên nhân chính gây nên sự giảm điểm của thị trường|tăng ít nhất)",
					"làm giảm sự hưng phấn của thị trường");
		} else {
			s = s.replace("(tăng mạnh nhất sàn|giảm ít nhất sàn)", "tăng mạnh nhất sàn");
			s = s.replace(
					"(kéo thị trường tăng điểm|vớt vát phần nào cho thị trường đang giảm điểm|ảnh hường tới thị trường ít nhất)",
					"vớt vát phần nào cho thị trường đang giảm điểm");
			s = s.replace(
					"(làm giảm sự hưng phấn của thị trường|là nguyên nhân chính gây nên sự giảm điểm của thị trường|tăng ít nhất)",
					"là nguyên nhân chính gây nên sự giảm điểm của thị trường");
		}
		for (int i = 1; i <= 3; i++) {
			StockCode inCrease = top10IncreasePrice.get(i - 1);
			StockCode deCrease = top10DecreasePrice.get(i - 1);
			float giam = deCrease.getChange();
			float tang = inCrease.getChange();
			s = s.replace("(IncreaseName" + i + ")", inCrease.getName());
			// xét xem mã inCrease này tăng hay giảm điểm
			if (tang >= 0.0f)
				s = s.replace("(tăng" + i + ")", "tăng");
			else {
				s = s.replace("tăng" + i + ")", "giảm");
				tang = -tang;
			}
			s = s.replace("(Increase" + i + ")", String.valueOf(tang));

			s = s.replace("(DecreaseName" + i + ")", deCrease.getName());
			// xét xem mã deCrease này tăng hay giảm điểm
			if (giam <= 0.0f) {
				s = s.replace("(giảm" + i + ")", "giảm");
				giam = -giam;
			}
			else
				s = s.replace("(giảm" + i + ")", "tăng");
			s = s.replace("(Decrease" + i + ")", String.valueOf(giam));
		}
		for (int i = 3; i <= 10; i++) {
			StockCode inCrease = top10IncreasePrice.get(i - 1);
			StockCode deCrease = top10DecreasePrice.get(i - 1);
			s = s.replace("(IncreaseName" + i + ")", inCrease.getName());
			s = s.replace("(DecreaseName" + i + ")", deCrease.getName());
			// xét xem mã deCrease này tăng hay giảm điểm
		}
		
		int topModest_size = topModest.size();
		for (int i = 1; i <= topModest_size; i++) {
			int rand = (int) (Math.random() * topModest.size());
			s = s.replace("(modestName" + i + ")", topModest.get(rand).getName());
			topModest.remove(rand);
		}
		for (int i = topModest_size+1; i <= 10; i++) {
		
			s = s.replace(" (modestName" + i + "),","");
		}

		return s;
	}

	public static void main(String[] args) {
		Group4 sentence4 = new Group4();
		System.out.println(sentence4.completeSentence(sentence4.chooseRandomSentence()));
	}
}
