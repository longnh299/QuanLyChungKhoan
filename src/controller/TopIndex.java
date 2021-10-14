package controller;

import java.util.ArrayList;

import model.*;


public class TopIndex {
	private ArrayList<StockCode> stock = new ArrayList<>();;
	//lấy data của các mã cổ phiếu trong ngày hôm nay qua đường dẫn file tương ứng
	private GeneralInformation stockInfo = new GeneralInformation("E:\\java\\chungkhoan\\stockdata.txt", stock);
	
	public TopIndex() {
		// stock = new ArrayList<>();
		stockInfo.input();
	}

	// Không Có.
	public ArrayList<StockCode> allStockCode() {
		return stock;
	}

	// Top 10 Khối Lượng.
	public ArrayList<StockCode> top10Volume() {
		ArrayList<StockCode> toplist = new ArrayList<>();
		for (int i = 0; i <= 9; i++) {
			toplist.add(stockInfo.highestVolume());
			stock.remove(stockInfo.highestVolume());
		}
		return toplist;
	}

	// Top 10 Tăng Giá Mạnh Nhất.
	public ArrayList<StockCode> top10IncreasePrice() {
		ArrayList<StockCode> toplist = new ArrayList<>();
		for (int i = 0; i <= 9; i++) {
			toplist.add(stockInfo.highestChange());
			stock.remove(stockInfo.highestChange());
		}
		return toplist;
	}

	// Top 10 Tăng % Mạnh Nhất.
	public ArrayList<StockCode> top10IncreasePercent() {
		ArrayList<StockCode> toplist = new ArrayList<>();
		for (int i = 0; i <= 9; i++) {
			toplist.add(stockInfo.highestChangePercent());
			stock.remove(stockInfo.highestChangePercent());
		}
		return toplist;
	}

	// Top 10 Giảm Giá Mạnh Nhất.
	public ArrayList<StockCode> top10DecreasePrice() {
		ArrayList<StockCode> toplist = new ArrayList<>();
		for (int i = 0; i <= 9; i++) {
			toplist.add(stockInfo.lowestChange());
			stock.remove(stockInfo.lowestChange());
		}
		return toplist;
	}

	// Top 10 Giảm % Mạnh Nhất.
	public ArrayList<StockCode> top10DecreasePercent() {
		ArrayList<StockCode> toplist = new ArrayList<>();
		for (int i = 0; i <= 9; i++) {
			toplist.add(stockInfo.lowestChangePercent());
			stock.remove(stockInfo.lowestChangePercent());
		}
		return toplist;
	}

	// Top 10 Giá Cao Nhất.
	public ArrayList<StockCode> top10Price() {
		ArrayList<StockCode> toplist = new ArrayList<>();
		for (int i = 0; i <= 9; i++) {
			toplist.add(stockInfo.highestPrice());
			stock.remove(stockInfo.highestPrice());
		}
		return toplist;
	}

	public static void main(String args[]) {
		TopIndex t = new TopIndex();
		for (StockCode sc : t.top10Price()) {
			System.out.println(sc.getName()+ " " +sc.getOpenprice());
		}
	}
}
