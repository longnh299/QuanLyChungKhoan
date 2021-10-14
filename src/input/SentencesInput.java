package input;

import model.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import controller.GeneralInformation;

import java.io.File;

public class SentencesInput {
	private String filepath = "E:\\java\\chungkhoan\\sentences\\formReport.txt"; 

	protected String[][] sentences;
	protected ArrayList<StockCode> table = new ArrayList<>();
	//lấy data của các mã cổ phiếu trong ngày hôm nay qua đường dẫn file tương ứng để dùng trong sinh câu mà không cần gọi lại đối tượng
	protected GeneralInformation stockInfo = new GeneralInformation("E:\\java\\chungkhoan\\stockdata.txt", table); 
	public SentencesInput() {
		inputSentences();
		stockInfo.input();
	}

	public void inputSentences() {
		sentences = new String[6][5];
		File file = new File(filepath);
		try (Scanner sc = new Scanner(file)) {
			int i = 0;
			int j = 0;
			sc.nextLine();
			while (sc.hasNextLine()) {
				String s = sc.nextLine();
				if (s.equals("Nhom" + i)) {
					i++;
					j = 0;
				} else if (s.equals((i - 1) + "." + j)) {
					continue;
				} else {
					sentences[i - 1][j] = s;// do i++ ở vòng if đầu nên đã tăng i bị tăng quá 1 đơn vi trước khi gán
					j++;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		SentencesInput s = new SentencesInput();
		int i = 0;
		for (String[] group : s.sentences) {
			System.out.println("Nhóm" + i);
			for (String sentence : group) {
				System.out.println(sentence);
			}
			i++;
		}
	}
}
