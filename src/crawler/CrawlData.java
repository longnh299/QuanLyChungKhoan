package crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.io.*;

public class CrawlData {
	private String link = "https://s.cafef.vn/TraCuuLichSu2/1/HOSE/date.chn";
	
	public CrawlData() {
		new File("E:\\java\\chungkhoan\\stockdata.txt").delete();
	}

	public void crawlData(String date) throws IOException {
		link = link.replace("date", date);
		Document doc = Jsoup.connect(link).get();
		PrintWriter pw = new PrintWriter("E:\\java\\chungkhoan\\stockdata.txt");

		for (Element row : doc.select("table#table2sort tr")) {
			String stockcode = row.select("td.Item_DateItem_lsg").text();
			String price = row.select("td.Item_Price1").text();
			String change = row.select("td.Item_ChangePrice_lsg").text();
			change = change.replace("(", " ");
			change = change.replace(")", "");
			price = price.replace("  ", " ");
			String s = stockcode + " " + change + " " + price;
			s = s.replace(" % ", " ");
			s = s.replace("  ", " ");
			s = s.replace(",", "");
			pw.println(s);
		}
		pw.close();
	}

	public static void main(String[] args) throws IOException {
		CrawlData cr = new CrawlData();
		cr.crawlData("21/12/2020");
	}

}