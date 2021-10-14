package model;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;

public class Data {
	private String filename;
	protected ArrayList<StockCode> table;

	public Data(String filename, ArrayList<StockCode> table) {
		this.filename = filename;
		this.table = table;

	}

	// Đọc data của các mã cổ phiếu trên sàn từ đường dẫn filename
	public void input() {
		File file = new File(filename);
		try {
			Scanner sc = new Scanner(file);
			ArrayList<String> list = new ArrayList<>();
			while (sc.hasNextLine()) {
				list.add(sc.nextLine());
			}

			for (String s : list) {
				if (s.startsWith(" ")) {
					continue;
				}
				String name;
				float change1;
				float change2;
				float price;
				float openprice;
				float closeprice;
				float hprice;
				float lprice;
				int volume;
				name = s.split(" ")[0];
				change1 = Float.parseFloat(s.split(" ")[1]);
				change2 = Float.parseFloat(s.split(" ")[2]);
				closeprice = Float.parseFloat(s.split(" ")[3]);
				price = Float.parseFloat(s.split(" ")[4]);
				openprice = Float.parseFloat(s.split(" ")[5]);
				hprice = Float.parseFloat(s.split(" ")[6]);
				lprice = Float.parseFloat(s.split(" ")[7]);
				volume = Integer.parseInt(s.split(" ")[8]);
				table.add(new StockCode(name, change1, change2, closeprice, price, openprice, hprice, lprice, volume));
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
