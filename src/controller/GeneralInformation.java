package controller;

import model.*;

import java.util.ArrayList;

public class GeneralInformation extends Data {
	public GeneralInformation(String filename, ArrayList<StockCode> table) {
		super(filename, table);
		//input(); //test phải chạy input() trước
	}

	public int countUp() {
		// data = new ArrayList<>();
		int count = 0;
		for (StockCode sc : table) {
			if (sc.getChange() > 0)
				count++;
		}
		return count;
	}

	public int countDown() {
		int count = 0;
		for (StockCode sc : table) {
			if (sc.getChange() < 0)
				count++;
		}
		return count;
	}

	public int countStand() {
		int count = 0;
		for (StockCode sc : table) {
			if (sc.getChange() == 0.00f)
				count++;
		}
		return count;
	}

	public StockCode highestVolume() {
		StockCode max = new StockCode("", 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0);
		for (StockCode sc : table) {
			if (sc.getVolume() > max.getVolume())
				max = new StockCode(sc.getName(), sc.getChange(), sc.getChangePercent(), sc.getCloseprice(),
						sc.getPrice(), sc.getOpenprice(), sc.getHprice(), sc.getLprice(), sc.getVolume());
		}
		return max;
	}

	public StockCode highestPrice() {
		StockCode max = new StockCode("", 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0);
		for (StockCode sc : table) {
			if (sc.getCloseprice() > max.getCloseprice())
				max = new StockCode(sc.getName(), sc.getChange(), sc.getChangePercent(), sc.getCloseprice(),
						sc.getPrice(), sc.getOpenprice(), sc.getHprice(), sc.getLprice(), sc.getVolume());
		}
		return max;
	}

	public StockCode highestChange() {
		StockCode max = new StockCode("", 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0);
		for (StockCode sc : table) {
			if (sc.getChange() > max.getChange())
				max = new StockCode(sc.getName(), sc.getChange(), sc.getChangePercent(), sc.getCloseprice(),
						sc.getPrice(), sc.getOpenprice(), sc.getHprice(), sc.getLprice(), sc.getVolume());
		}
		return max;
	}

	public StockCode highestChangePercent() {
		StockCode max = new StockCode("", 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0);
		for (StockCode sc : table) {
			if (sc.getChangePercent() > max.getChangePercent())
				max = new StockCode(sc.getName(), sc.getChange(), sc.getChangePercent(), sc.getCloseprice(),
						sc.getPrice(), sc.getOpenprice(), sc.getHprice(), sc.getLprice(), sc.getVolume());
		}
		return max;
	}

	public StockCode lowestChange() {
		StockCode min = new StockCode("", 999.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0);
		for (StockCode sc : table) {
			if (sc.getChange() < min.getChange())
				min = new StockCode(sc.getName(), sc.getChange(), sc.getChangePercent(), sc.getCloseprice(),
						sc.getPrice(), sc.getOpenprice(), sc.getHprice(), sc.getLprice(), sc.getVolume());
		}
		return min;
	}

	public StockCode lowestChangePercent() {
		StockCode min = new StockCode("", 0.0f, 999.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0);
		for (StockCode sc : table) {
			if (sc.getChangePercent() < min.getChangePercent())
				min = new StockCode(sc.getName(), sc.getChange(), sc.getChangePercent(), sc.getCloseprice(),
						sc.getPrice(), sc.getOpenprice(), sc.getHprice(), sc.getLprice(), sc.getVolume());
		}
		return min;
	}

	public ArrayList<StockCode> ceilingList(String tensan) {
		ArrayList<StockCode> list = new ArrayList<>();
		float max;
		if (tensan == "HNX-INDEX")
			max = 10.0f;
		else if (tensan == "UPCOM-INDEX")
			max = 15.0f;
		else
			max = 7.0f;
		for (StockCode sc : table) {
			float change = sc.getChange();
			float price = sc.getPrice();
			if (price < 5.0f / max)
				continue; // bỏ những mã rác

			if ((change + 0.05f) * 100 / price > max && (change - 0.05f) * 100 / price < max) {
				list.add(sc);
			}
		}
		return list;
	}

	public ArrayList<StockCode> floorList(String tensan) {
		ArrayList<StockCode> list = new ArrayList<>();
		float min;
		if (tensan == "HNX-INDEX")
			min = -10.0f;
		else if (tensan == "UPCOM-INDEX")
			min = -15.0f;
		else
			min = -7.0f;
		for (StockCode sc : table) {
			float change = sc.getChange();
			float price = sc.getPrice();
			if (price < -5.0f / min)
				continue; // bỏ những mã rác
			else if ((change - 0.05f) * 100 / price < min && (change + 0.05f) * 100 / price > min) {
				list.add(sc);
			}
		}
		return list;
	}

	public ArrayList<StockCode> modestUp() {
		ArrayList<StockCode> list = new ArrayList<>();
		for (StockCode sc : table) {
			if (sc.getChangePercent() >= 0.1 && sc.getChangePercent() <= 0.5) {
				list.add(sc);
			}
		}
		return list;
	}

	public static void main(String[] args) {
		ArrayList<StockCode> table = new ArrayList<>();
		GeneralInformation stocksinfo = new GeneralInformation("E:\\java\\chungkhoan\\stockdata.txt", table);
		ArrayList<StockCode> top = stocksinfo.modestUp();
		for (StockCode sc : top) {
			System.out.println(sc.getName() + " " + sc.getChangePercent());
		}
	}
}
