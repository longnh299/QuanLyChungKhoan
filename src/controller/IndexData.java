package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class IndexData {
	private String data1; //data đếm số mã tăng, giảm, floor, ceiling
	private String[][] data2 = new String[20][11]; //data thống kê chỉ số, khối lượng, ... 20 ngày của chỉ số chung
	private String nameIndex;

	public IndexData() {
		data2 = new String[20][11];
		input();
	}

	private void input() {
		File file = new File("E:\\java\\chungkhoan\\countStock.txt"); // File lưu dữ liệu trong ngày
		try {
			Scanner sc = new Scanner(file);
			this.data1 = sc.nextLine();
			String check = data1.split(" ")[0];
			if (check.equals("0"))
				JOptionPane.showMessageDialog(null, "Không có dữ liệu vào ngày thứ 7 và chủ nhật");
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		file = new File("E:\\java\\chungkhoan\\crawlStatistics.txt");
		try {
			Scanner sc = new Scanner(file);
			String s;
			int line = 0;
			this.nameIndex = sc.nextLine(); // lấy tên của chỉ số
			while (sc.hasNextLine()) {
				s = sc.nextLine();
				// lưu dữ liệu trong ngày vào mảng
				for (int i = 0; i < 11; i++) {
					data2[line][i] = s.split(" ")[i];
				}
				line++;
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public String nameIndex() {
		return this.nameIndex;
	}

	public String date() {
		return data2[0][0];
	}

	public String closeIndex() {
		return data2[0][1];
	}

	public String indexChange() {
		return data2[0][2];
	}

	public String percentChange() {
		return data2[0][3];
	}

	public String countUpStock() {
		return data1.split(" ")[0];
	}

	public String countCeilingStock() {
		return data1.split(" ")[1];
	}

	public String countNochangeStock() {
		return data1.split(" ")[2];
	}

	public String countDownStock() {
		return data1.split(" ")[3];
	}

	public String countFloorStock() {
		return data1.split(" ")[4];
	}

	public String openIndex() {
		return data2[0][8];
	}

	public String highestIndex() {
		return data2[0][9];
	}

	public String lowestIndex() {
		return data2[0][10];
	}
	
	public String countUpContinuous() {
		int count = 0;
		for (int i = 1; i < 20; i++) {
			if (Float.valueOf(data2[i][2]) < 0.00f) {
				break;
			} else
				count++;
		}
		if (Float.valueOf(data2[0][2]) <= 0.00f)
			return String.valueOf(count);
		else
			return String.valueOf(count + 1);

	}

	public String countDownContinuous() {
		int count = 0;
		for (int i = 1; i < 20; i++) {
			if (Float.valueOf(data2[i][2]) > 0.00f) {
				break;
			} else
				count++;
		}
		if (Float.valueOf(data2[0][2]) >= 0.00f)
			return String.valueOf(count);
		else
			return String.valueOf(count + 1);
	}
	
	public String averageVolume(int number) {
		long sum = 0;
		for (int i = 0; i < number; i++) {
			int volume = Integer.valueOf(data2[i][4]);
			sum += volume / number;
		}
		return String.valueOf(sum);
	}

	public String averageIndex(int number) {
		float average = 0.00f;
		for (int i = 0; i < number; i++) {
			float index = Float.valueOf(data2[i][1]);
			average += index;
		}
		return String.format("%.2f", average/number);
	}

	public String klgdThoaThuan() {
		return data2[0][6];
	}

	public String gtgdThoaThuan() {
		return data2[0][7];
	}
	
	public String klgd() {
		return data2[0][4];
	}

	public String gtgd() {
		return data2[0][5];
	}

	public String indexPointYesterday() {
		return data2[1][1];
	}

	// test
	public void main(String[] args) throws IOException {
		IndexData vn = new IndexData();
		for (String[] line : vn.data2) {
			for (String col : line) {
				System.out.print(col + " ");
			}
			System.out.println();
		}
		System.out.print(vn.averageIndex(20));
	}
}

