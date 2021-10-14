package crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.io.*;

public class CrawlStatisticsIndexPoint {
	private String link = "https://s.cafef.vn/Lich-su-giao-dich-nameIndex-1.chn";

	public CrawlStatisticsIndexPoint() {
		new File("E:\\java\\chungkhoan\\crawlStatistics.txt").delete();
	}

	public void crawlStatisticsIndexPoint(String nameIndex) throws IOException, ArrayIndexOutOfBoundsException {
		link = link.replace("nameIndex", nameIndex);
		Document doc = Jsoup.connect(link).get();
		PrintWriter pw7 = new PrintWriter("E:\\java\\chungkhoan\\crawlStatistics.txt");
		pw7.println(nameIndex);
		int i = 0;
		String tableName;
		if(nameIndex=="VNINDEX") tableName = "table#GirdTable2 tr";
		else tableName = "table#GirdTable tr";
		for (Element row : doc.select(tableName)) {
			String s = row.text();
			i++;
			if(i<=2) continue;//bỏ qua 2 dòng đầu của bảng
			
			if(nameIndex == "VNINDEX") {
				String[] split = s.split(" ",6);
				s = split[0] + " " + split[1] + " " + split[2] + " " + split[3] + " " + split[5];
			}
				
			else {
				String[] split = s.split(" ",10);
				s = split[0] + " " + split[1] + " " + split[2] + " " + split[3] + " " + split[5] + " " + 
						split[6] + " " + split[7] + " " + split[8]+ " " + "0.0" + " " +split[9];
			}
				
			s = s.replace("  ", "");
			s = s.replace(",", "");
			s = s.replace("(", "");
			s = s.replace(")", "");
			s = s.replace("%", "");
			pw7.println(s);
				
			}
		pw7.close();
	}

	public static void main(String[] args) throws IOException {
		CrawlStatisticsIndexPoint cr = new CrawlStatisticsIndexPoint();
		cr.crawlStatisticsIndexPoint("HNX-INDEX");
	}
}
