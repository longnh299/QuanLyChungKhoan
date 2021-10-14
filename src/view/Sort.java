package view;

import controller.TopIndex;

import java.io.IOException;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;

import crawler.CrawlData;
import crawler.CrawlDataHNXandUPCOM;
import model.StockCode;

public class Sort extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField textField;

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

	public Sort() {
		setTitle("Sort");
		setIconifiable(true);
		setClosable(true);
		setMaximizable(true);
		setBounds(100, 30, 1000, 600);
		getContentPane().setLayout(null);

		// Tạo tên của bảng
		JLabel tablename = new JLabel("Bảng sắp xếp mã cổ phiểu theo các tiêu chí và mã sàn");
		tablename.setBounds(150, 15, 700, 50);
		tablename.setForeground(new Color(0, 0, 0));
		tablename.setAutoscrolls(true);
		tablename.setFont(new Font("Times New Roman", Font.BOLD, 30));
		getContentPane().add(tablename);

		// Tạo bảng và con lăn
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(60, 70, 850, 380);

		table = new JTable();
		table.setBounds(60, 70, 850, 380);
		table.setBackground(SystemColor.activeCaption);
		table.setShowGrid(true);
		table.setShowVerticalLines(true);
		table.setShowHorizontalLines(true);
		table.setRowMargin(20);
		table.setRowHeight(50);
		table.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		scrollPane.setViewportView(table);
		getContentPane().add(scrollPane);

		// Chọn Ngày, Tiêu Chí, Mã Sàn
		JLabel time = new JLabel("Nhập Ngày");
		time.setBounds(100, 455, 200, 50);
		time.setForeground(new Color(0, 0, 0));
		time.setAutoscrolls(true);
		time.setFont(new Font("Times New Roman", Font.BOLD, 25));
		getContentPane().add(time);

		JLabel masan = new JLabel("Chọn Mã Sàn");
		masan.setBounds(400, 455, 200, 50);
		masan.setForeground(new Color(0, 0, 0));
		masan.setAutoscrolls(true);
		masan.setFont(new Font("Times New Roman", Font.BOLD, 25));
		getContentPane().add(masan);

		JLabel tieuchi = new JLabel("Chọn Tiêu Chí");
		tieuchi.setBounds(700, 455, 200, 50);
		tieuchi.setForeground(new Color(0, 0, 0));
		tieuchi.setAutoscrolls(true);
		tieuchi.setFont(new Font("Times New Roman", Font.BOLD, 25));
		getContentPane().add(tieuchi);

		// Nhập Time
		textField = new JTextField();
		textField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		textField.setBounds(70, 500, 190, 40);
		textField.setColumns(10);
		getContentPane().add(textField);

		// Chọn Mã Sàn
		JComboBox<String> comboBox = new JComboBox<>();
		comboBox.setBackground(new Color(249, 204, 118));
		comboBox.setBounds(375, 500, 200, 40);
		comboBox.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "tên sàn","HOSE", "HNX", "UPCOM" }));
		comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String date;
				date = textField.getText();
				if (comboBox.getSelectedIndex() == 0) {
					new CrawlData();
				}
				if (comboBox.getSelectedIndex() == 1) {
					CrawlData cr = new CrawlData();
					try {
						cr.crawlData(date);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				if (comboBox.getSelectedIndex() == 2) {

					CrawlDataHNXandUPCOM cr = new CrawlDataHNXandUPCOM();
					try {
						cr.crawlData(date, "HASTC");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

				if (comboBox.getSelectedIndex() == 3) {
					CrawlDataHNXandUPCOM cr = new CrawlDataHNXandUPCOM();
					try {
						cr.crawlData(date, "UPCOM");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}

		});
		getContentPane().add(comboBox);

		// Chọn Tiêu Chí
		JComboBox<String> comboBox1 = new JComboBox<>();
		comboBox1.setBackground(new Color(249, 204, 118));
		comboBox1.setBounds(660, 500, 250, 40);
		comboBox1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		comboBox1.setModel(new DefaultComboBoxModel<String>(
				new String[] { "Không có", "TOP10 Khối Lượng", "TOP10 Tăng Giá Mạnh Nhất", "TOP10 Tăng % Mạnh Nhất",
						"TOP10 Giảm Giá Mạnh Nhất", "TOP10 Giảm % Mạnh Nhất", "TOP10 Giá Cao Nhất" }));
		comboBox1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox1.getSelectedIndex() == 0) {
					TopIndex topIndex = new TopIndex();
					DefaultTableModel model = new DefaultTableModel();
					model.addColumn("Mã CK");
					model.addColumn("+/-(giá)");
					model.addColumn("+/-(%)");
					model.addColumn("Giá đóng cửa");
					model.addColumn("Giá tham chiếu");
					model.addColumn("Giá mở cửa");
					model.addColumn("Giá cao nhất");
					model.addColumn("Giá thấp nhất");
					model.addColumn("KLGD");
					for (StockCode stockCode: topIndex.allStockCode()) {
						model.addRow(new Object[] { stockCode.getName(), stockCode.getChange(), stockCode.getChangePercent(),
								stockCode.getCloseprice(), stockCode.getPrice(),stockCode.getOpenprice(),
								stockCode.getHprice(), stockCode.getLprice(), stockCode.getVolume() });
					}
					table.setModel(model);
				}
				if (comboBox1.getSelectedIndex() == 1) {
					TopIndex topIndex = new TopIndex();
					DefaultTableModel model = new DefaultTableModel();
					model.addColumn("Mã CK");
					model.addColumn("+/-(giá)");
					model.addColumn("+/-(%)");
					model.addColumn("Giá đóng cửa");
					model.addColumn("Giá tham chiếu");
					model.addColumn("Giá mở cửa");
					model.addColumn("Giá cao nhất");
					model.addColumn("Giá thấp nhất");
					model.addColumn("KLGD");
					for (StockCode stockCode: topIndex.top10Volume()) {
						model.addRow(new Object[] { stockCode.getName(), stockCode.getChange(), stockCode.getChangePercent(),
								stockCode.getCloseprice(), stockCode.getPrice(),stockCode.getOpenprice(),
								stockCode.getHprice(), stockCode.getLprice(), stockCode.getVolume() });
					}
					table.setModel(model);
				}
				if (comboBox1.getSelectedIndex() == 2) {
					TopIndex topIndex = new TopIndex();
					DefaultTableModel model = new DefaultTableModel();
					model.addColumn("Mã CK");
					model.addColumn("+/-(giá)");
					model.addColumn("+/-(%)");
					model.addColumn("Giá đóng cửa");
					model.addColumn("Giá tham chiếu");
					model.addColumn("Giá mở cửa");
					model.addColumn("Giá cao nhất");
					model.addColumn("Giá thấp nhất");
					model.addColumn("KLGD");
					for (StockCode stockCode: topIndex.top10IncreasePrice()) {
						model.addRow(new Object[] { stockCode.getName(), stockCode.getChange(), stockCode.getChangePercent(),
								stockCode.getCloseprice(), stockCode.getPrice(),stockCode.getOpenprice(),
								stockCode.getHprice(), stockCode.getLprice(), stockCode.getVolume() });
					}
					table.setModel(model);
				}
				if (comboBox1.getSelectedIndex() == 3) {
					TopIndex topIndex = new TopIndex();
					DefaultTableModel model = new DefaultTableModel();
					model.addColumn("Mã CK");
					model.addColumn("+/-(giá)");
					model.addColumn("+/-(%)");
					model.addColumn("Giá đóng cửa");
					model.addColumn("Giá tham chiếu");
					model.addColumn("Giá mở cửa");
					model.addColumn("Giá cao nhất");
					model.addColumn("Giá thấp nhất");
					model.addColumn("KLGD");
					for (StockCode stockCode: topIndex.top10IncreasePercent()) {
						model.addRow(new Object[] { stockCode.getName(), stockCode.getChange(), stockCode.getChangePercent(),
								stockCode.getCloseprice(), stockCode.getPrice(),stockCode.getOpenprice(),
								stockCode.getHprice(), stockCode.getLprice(), stockCode.getVolume() });
					}
					table.setModel(model);
				}	
				if (comboBox1.getSelectedIndex() == 4) {
					TopIndex topIndex = new TopIndex();
					DefaultTableModel model = new DefaultTableModel();
					model.addColumn("Mã CK");
					model.addColumn("+/-(giá)");
					model.addColumn("+/-(%)");
					model.addColumn("Giá đóng cửa");
					model.addColumn("Giá tham chiếu");
					model.addColumn("Giá mở cửa");
					model.addColumn("Giá cao nhất");
					model.addColumn("Giá thấp nhất");
					model.addColumn("KLGD");
					for (StockCode stockCode: topIndex.top10DecreasePrice()) {
						model.addRow(new Object[] { stockCode.getName(), stockCode.getChange(), stockCode.getChangePercent(),
								stockCode.getCloseprice(), stockCode.getPrice(),stockCode.getOpenprice(),
								stockCode.getHprice(), stockCode.getLprice(), stockCode.getVolume() });
					}
					table.setModel(model);
				}
				if (comboBox1.getSelectedIndex() == 5) {
					TopIndex topIndex = new TopIndex();
					DefaultTableModel model = new DefaultTableModel();
					model.addColumn("Mã CK");
					model.addColumn("+/-(giá)");
					model.addColumn("+/-(%)");
					model.addColumn("Giá đóng cửa");
					model.addColumn("Giá tham chiếu");
					model.addColumn("Giá mở cửa");
					model.addColumn("Giá cao nhất");
					model.addColumn("Giá thấp nhất");
					model.addColumn("KLGD");
					for (StockCode stockCode: topIndex.top10DecreasePercent()) {
						model.addRow(new Object[] { stockCode.getName(), stockCode.getChange(), stockCode.getChangePercent(),
								stockCode.getCloseprice(), stockCode.getPrice(),stockCode.getOpenprice(),
								stockCode.getHprice(), stockCode.getLprice(), stockCode.getVolume() });
					}
					table.setModel(model);
				}
				if (comboBox1.getSelectedIndex() == 6) {
					TopIndex topIndex = new TopIndex();
					DefaultTableModel model = new DefaultTableModel();
					model.addColumn("Mã CK");
					model.addColumn("+/-(giá)");
					model.addColumn("+/-(%)");
					model.addColumn("Giá đóng cửa");
					model.addColumn("Giá tham chiếu");
					model.addColumn("Giá mở cửa");
					model.addColumn("Giá cao nhất");
					model.addColumn("Giá thấp nhất");
					model.addColumn("KLGD");
					for (StockCode stockCode: topIndex.top10Price()) {
						model.addRow(new Object[] { stockCode.getName(), stockCode.getChange(), stockCode.getChangePercent(),
								stockCode.getCloseprice(), stockCode.getPrice(),stockCode.getOpenprice(),
								stockCode.getHprice(), stockCode.getLprice(), stockCode.getVolume() });
					}
					table.setModel(model);
				}	
				
			}
		});
		getContentPane().add(comboBox1);
	}
}
