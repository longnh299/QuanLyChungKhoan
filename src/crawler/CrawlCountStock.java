package crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.*;

public class CrawlCountStock {

	private String link = "https://s.cafef.vn/TraCuuLichSu2/1/name/date.chn";
	
	public CrawlCountStock() {
		new File("E:\\java\\chungkhoan\\countStock.txt").delete();
	}

	public void crawlCountStock(String date, String name) throws IOException {
		link = link.replace("date", date);
		link = link.replace("name", name);
		Document doc = Jsoup.connect(link).get();
		// String vnindex =
		// doc.select("div#ctl00_ContentPlaceHolder1_ctl01_lblIndexPoint").text();
		String countup = doc.select("span#ctl00_ContentPlaceHolder1_ctl01_divToTalUp").text();
		String countceiling = doc.select("span#ctl00_ContentPlaceHolder1_ctl01_spanKichTran").text();
		String countnochange = doc.select("span#ctl00_ContentPlaceHolder1_ctl01_divTotalNochange").text();
		String countdown = doc.select("span#ctl00_ContentPlaceHolder1_ctl01_divTotalDown").text();
		String countfloor = doc.select("span#ctl00_ContentPlaceHolder1_ctl01_spanKichSan").text();
		// String klgd = doc.select("div#ctl00_ContentPlaceHolder1_ctl01_div1").text();
		// String title = doc.title();
		// String time[] = title.split(" ");

		PrintWriter pw1 = new PrintWriter("E:\\java\\chungkhoan\\countStock.txt");

		String thongtin = countup + countceiling + countnochange + countdown + countfloor;

		for (int i = 0; i < thongtin.length(); i++) {
			if (thongtin.charAt(i) != '0' && thongtin.charAt(i) != '1' && thongtin.charAt(i) != '2'
					&& thongtin.charAt(i) != '3' && thongtin.charAt(i) != '4' && thongtin.charAt(i) != '5'
					&& thongtin.charAt(i) != '6' && thongtin.charAt(i) != '7' && thongtin.charAt(i) != '8'
					&& thongtin.charAt(i) != '9' && thongtin.charAt(i) != '.' && thongtin.charAt(i) != ','
					&& thongtin.charAt(i) != '-' && thongtin.charAt(i) != '/') {

				thongtin = thongtin.replace(thongtin.charAt(i), ' ');
			}
		}

		thongtin = thongtin.replace(",", "");

		thongtin = thongtin.trim().replaceAll(" +", " "); // xóa khoảng trắng thừa giữa các từ.
		pw1.println(thongtin);
		pw1.close();
	}

	public static void main(String[] args) throws IOException {
		CrawlCountStock cr = new CrawlCountStock();
		cr.crawlCountStock("21/12/2020", "UPCOM");
	}
}
