package view;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.EventQueue;
import java.awt.Font;

import crawler.*;
import output.*;

public class General extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					General frame = new General();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public General() {
		setTitle("General");
		setIconifiable(true);
		setClosable(true);
		setMaximizable(true);
		setBounds(100, 30, 1000, 600);
		getContentPane().setLayout(null);

		// Chon ngay
		JLabel time = new JLabel("Nhập Ngày");
		time.setBounds(200, 20, 200, 50);
		time.setForeground(new Color(0, 0, 0));
		time.setAutoscrolls(true);
		time.setFont(new Font("Times New Roman", Font.BOLD, 25));
		getContentPane().add(time);

		// Nhập Time
		textField = new JTextField();
		textField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		textField.setBounds(170, 70, 200, 40);
		textField.setColumns(10);
		getContentPane().add(textField);

		// Tao vung de sinh bai bao
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(60, 150, 850, 400);
		getContentPane().add(scrollPane);

		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		scrollPane.setViewportView(textArea);
		// Thong bao
		JLabel notifi = new JLabel("(Không có dữ liệu thứ 7 và chủ nhật )");
		notifi.setFont(new Font("Times New Roman", Font.ITALIC, 23));
		notifi.setForeground(new Color(0, 0, 0));
		notifi.setBounds(330, 110, 400, 40);
		getContentPane().add(notifi);
		// Chon san
		JLabel masan = new JLabel("Chọn Sàn");
		masan.setBounds(440, 20, 200, 50);
		masan.setForeground(new Color(0, 0, 0));
		masan.setAutoscrolls(true);
		masan.setFont(new Font("Times New Roman", Font.BOLD, 25));
		getContentPane().add(masan);
		//
		JComboBox<String> comboBox = new JComboBox<>();
		comboBox.setBackground(new Color(205, 133, 63));
		comboBox.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "tên sàn","HOSE", "HNX", "UPCOM" }));
		comboBox.setBounds(410, 70, 180, 40);
		
		
		// Tao nut Sinh Bai Bao
		/*JButton outputSentences = new JButton("Sinh Bài Báo");
		outputSentences.setBounds(650, 50, 200, 60);
		outputSentences.setAlignmentX(Component.CENTER_ALIGNMENT);
		outputSentences.setFont(new Font("Times New Roman", Font.BOLD, 25));
		outputSentences.setBackground(new Color(243, 194, 70));*/
		
		comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (comboBox.getSelectedIndex() == 0) {
					new CrawlCountStock();
					new CrawlStatisticsIndexPoint();
					new CrawlData();
					new CrawlDataHNXandUPCOM();
					textArea.setText("");
				}
				if (comboBox.getSelectedIndex() == 1) {
					String time = textField.getText();
					CrawlCountStock cr1 = new CrawlCountStock();
					CrawlStatisticsIndexPoint cr2 = new CrawlStatisticsIndexPoint();
					CrawlData cr3 = new CrawlData();
					try {
						cr1.crawlCountStock(time, "HOSE");
						cr2.crawlStatisticsIndexPoint("VNINDEX");
						cr3.crawlData(time);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					File file = new File("E:\\java\\chungkhoan\\output.txt");
					GenerateArticles g = new GenerateArticles();
					g.generateArticle("E:\\java\\chungkhoan\\output.txt");
					try {
						Scanner scanner = new Scanner(file);
						String string = "";
						while (scanner.hasNextLine()) {
							string = string + "\n" + scanner.nextLine();
						}
						scanner.close();
						textArea.setText(string);
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				if (comboBox.getSelectedIndex() == 2) {
					String time = textField.getText();
					CrawlCountStock cr1 = new CrawlCountStock();
					CrawlStatisticsIndexPoint cr2 = new CrawlStatisticsIndexPoint();
					CrawlDataHNXandUPCOM cr3 = new CrawlDataHNXandUPCOM();
					try {
						cr1.crawlCountStock(time, "HASTC");
						cr2.crawlStatisticsIndexPoint("HNX-INDEX");
						cr3.crawlData(time, "HASTC");
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
					File file = new File("E:\\java\\chungkhoan\\output.txt");
					GenerateArticles g = new GenerateArticles();
					g.generateArticle("E:\\java\\chungkhoan\\output.txt");
					try {
						Scanner scanner = new Scanner(file);
						String string = "";
						while (scanner.hasNextLine()) {
							string = string + "\n" + scanner.nextLine();
						}
						scanner.close();
						textArea.setText(string);
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				if (comboBox.getSelectedIndex() == 3) {
					String time = textField.getText();
					CrawlCountStock cr1 = new CrawlCountStock();
					CrawlStatisticsIndexPoint cr2 = new CrawlStatisticsIndexPoint();
					CrawlDataHNXandUPCOM cr3 = new CrawlDataHNXandUPCOM();
					try {
						cr1.crawlCountStock(time, "UPCOM");
						cr2.crawlStatisticsIndexPoint("UPCOM-INDEX");
						cr3.crawlData(time, "UPCOM");
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					File file = new File("E:\\java\\chungkhoan\\output.txt");
					GenerateArticles g = new GenerateArticles();
					g.generateArticle("E:\\java\\chungkhoan\\output.txt");
					try {
						Scanner scanner = new Scanner(file);
						String string = "";
						while (scanner.hasNextLine()) {
							string = string + "\n" + scanner.nextLine();
						}
						scanner.close();
						textArea.setText(string);
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		getContentPane().add(comboBox);
	
	}
}
