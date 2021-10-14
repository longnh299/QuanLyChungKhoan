package crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.io.*;

public class CrawlIndexPoint {
	public CrawlIndexPoint() {
		new File("E:\\java\\chungkhoan\\dateandpoint.txt").delete();
	}

	public void crawlIndexPoint(String link, String table) throws IOException, ArrayIndexOutOfBoundsException {
		Document doc = Jsoup.connect(link).get();
		PrintWriter pw3 = new PrintWriter("E:\\java\\chungkhoan\\dateandpoint.txt");
		String date_and_point[] = new String[20];
		int i = 0;
		for (Element row : doc.select(table)) {
			String info_index = row.text();
			i++;
			if (i<=2) continue;
			info_index = info_index.replace("/2020", "");
			String[] index = info_index.split(" ");
			String datepoint = index[0] + " " + index[1];
			datepoint = datepoint.trim();
			date_and_point[i-3] = datepoint;
			if (i-3 == 20) break;
			
		}
		for (int j = 0; j < date_and_point.length / 2; j++) {
			String temp = date_and_point[j];
			date_and_point[j] = date_and_point[date_and_point.length - j - 1];
			date_and_point[date_and_point.length - j - 1] = temp;
		}
		for (String s : date_and_point) {
			s = s.replace(",", "");
			pw3.println(s);
		}
		pw3.close();

	}

	public static void main(String[] args) throws IOException {
		String link = "https://s.cafef.vn/Lich-su-giao-dich-VNINDEX-1.chn#data";
		CrawlIndexPoint cr = new CrawlIndexPoint();
		cr.crawlIndexPoint(link, "table#GirdTable2 tr");
	}
}
