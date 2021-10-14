package crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.io.*;

public class CrawlDataHNXandUPCOM {

	private String link = "https://s.cafef.vn/TraCuuLichSu2/1/name/date.chn";

	public CrawlDataHNXandUPCOM() {
		new File("E:\\java\\chungkhoan\\stockdata.txt").delete();
	}

	public void crawlData(String date, String name) throws IOException {
		link = link.replace("name", name);
		link = link.replace("date", date);
		Document doc = Jsoup.connect(link).get();
		PrintWriter pw = new PrintWriter("E:\\java\\chungkhoan\\stockdata.txt");

		for (Element row : doc.select("table#table2sort tr")) {
			String stockcode = row.select("td.Item_DateItem_lsg").text();

			String change = row.select("td.Item_ChangePrice_lsg").text();
			change = change.replace("(", " ");
			change = change.replace(")", "");

			String price = row.select("td.Item_Price1").text();
			price = price.replace("  ", " ");

			/*if (price == "") {
				price = "";
			}
			else {
		
				price = price.split(" ", 3)[0] + " " + price.split(" ", 3)[2];	// Cắt price thành 3 chuỗi, ngăn nhau bới
			}*/														// dấu" ", bỏ qua chuối[1]
			String [] split = price.split(" ",3);
			price ="";
			int i=0;
			for(String a: split) {
				if(i!=1) price = price+ " " +a;
				i++;
			}
			String s = stockcode + " " + change + " " + price;
			s = s.replace(" % ", " ");
			s = s.replace("  ", " ");
			s = s.replace(",", "");

			pw.println(s);
		}
		pw.close();
	}

	public static void main(String[] args) throws IOException {
		CrawlDataHNXandUPCOM cr = new CrawlDataHNXandUPCOM();
		cr.crawlData("21/12/2020","UPCOM");
	}
}