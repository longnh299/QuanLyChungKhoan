package view;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.BasicStroke;
import java.awt.Font;

import crawler.CrawlIndexPoint;

public class Graph extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String[] data;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Graph frame = new Graph();
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

	public Graph() {
		setTitle("Graph");
		setIconifiable(true);
		setClosable(true);
		setMaximizable(true);
		setBounds(100, 30, 1000, 600);
		getContentPane().setLayout(null);

		JLabel masan = new JLabel("Chọn Mã Sàn");
		masan.setBounds(180, 10, 200, 50);
		masan.setForeground(new Color(0, 0, 0));
		masan.setAutoscrolls(true);
		masan.setFont(new Font("Times New Roman", Font.BOLD, 25));
		getContentPane().add(masan);

		JComboBox<String> comboBox = new JComboBox<>();
		comboBox.setBackground(new Color(205, 133, 63));
		comboBox.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "tên sàn","HOSE", "HNX", "UPCOM" }));
		comboBox.setBounds(180, 60, 150, 40);
		comboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getSelectedIndex() == 0) {
					new CrawlIndexPoint();
				}
				if (comboBox.getSelectedIndex() == 1) {
					String link = "https://s.cafef.vn/Lich-su-giao-dich-VNINDEX-1.chn#data";
					String table = "table#GirdTable2 tr";
					CrawlIndexPoint cr = new CrawlIndexPoint();
					try {
						cr.crawlIndexPoint(link, table);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				if (comboBox.getSelectedIndex() == 2) {
					String link = "https://s.cafef.vn/Lich-su-giao-dich-HNX-INDEX-1.chn#data";
					String table = "table#GirdTable tr";
					CrawlIndexPoint cr = new CrawlIndexPoint();
					try {
						cr.crawlIndexPoint(link, table);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				if (comboBox.getSelectedIndex() == 3) {
					String link = "https://s.cafef.vn/Lich-su-giao-dich-UPCOM-INDEX-1.chn#data";
					String table = "table#GirdTable tr";
					CrawlIndexPoint cr = new CrawlIndexPoint();
					try {
						cr.crawlIndexPoint(link, table);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		getContentPane().add(comboBox);

		JButton graph = new JButton("Vẽ Biểu Đồ");
		graph.setBounds(500, 45, 200, 50);
		graph.setAlignmentX(Component.CENTER_ALIGNMENT);
		graph.setFont(new Font("Times New Roman", Font.BOLD, 20));
		graph.setBackground(new Color(243, 194, 70));
		graph.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				graph.setBackground(new Color(255, 255, 255));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				graph.setBackground(new Color(243, 194, 70));
			}
		});
		getContentPane().add(graph);
		graph.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Graph chart;
				try {
					chart = new Graph("Biểu đồ chứng khoán", "GRAPH");
					chart.pack();
					chart.setVisible(true);
					chart.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}

	public Graph(String applicationTitle, String chartTitle) throws IOException {
		super(applicationTitle);
		JFreeChart lineChart = ChartFactory.createLineChart(chartTitle, "Date", "Point", createDataset(),
				PlotOrientation.VERTICAL, true, true, false);
		ChartPanel chartPanel = new ChartPanel(lineChart);
		chartPanel.setPreferredSize(new java.awt.Dimension(600, 400));
		CategoryPlot plot = lineChart.getCategoryPlot();
		plot.setOutlineStroke(new BasicStroke(2.0f));
		JFrame frame = new JFrame();
		frame.add(chartPanel);
		frame.setTitle("Biểu đồ JFreeChart trong Java Swing");
		frame.setSize(1030, 500);
		frame.setLocation(455, 280);
		frame.setResizable(false);
		frame.setVisible(true);

	}

	public DefaultCategoryDataset createDataset() throws IOException, NullPointerException {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		File file = new File("E:\\java\\chungkhoan\\dateandpoint.txt");
		Scanner scanner = new Scanner(file);
		String s = "";
		while (scanner.hasNextLine()) {
			s += scanner.nextLine() + " ";
		}
		data = s.split(" ");
		for (int i = 0; i < data.length; i = i + 2) {
			dataset.addValue(Float.parseFloat(data[i + 1]), "INDEX Graph", data[i]);
		}
		scanner.close();
		return dataset;
	}
}
