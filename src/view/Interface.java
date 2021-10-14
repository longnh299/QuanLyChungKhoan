package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;

import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.JDesktopPane;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.event.ActionEvent;

public class Interface extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JDesktopPane desktopPane;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Interface frame = new Interface();
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
	public Interface() {
		// Tạo Title và Frame
		setBackground(Color.WHITE);
		setTitle("APP CHỨNG KHOÁN");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 700);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);

		desktopPane = new JDesktopPane();
		desktopPane.setLayout(null);
		desktopPane.setBounds(5, 5, 1170, 650);
		contentPane.add(desktopPane);

		// Tạo Name
		JLabel name = new JLabel("Thông Tin Chứng Khoán");
		name.setBounds(300, 100, 600, 90);
		name.setForeground(new Color(255, 240, 245));
		name.setAutoscrolls(true);
		name.setFont(new Font("Times New Roman", Font.BOLD, 50));
		desktopPane.add(name);

		// Tạo các Button Chức Năng
		// Tạo Button "Thông tin trong ngày".
		JButton infor = new JButton("Thông tin trong ngày");
		infor.setBounds(50, 300, 300, 200);
		infor.setAlignmentX(Component.CENTER_ALIGNMENT);
		infor.setFont(new Font("Times New Roman", Font.BOLD, 25));
		infor.setBackground(new Color(243, 194, 70));
		infor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				infor.setBackground(new Color(255, 255, 255));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				infor.setBackground(new Color(243, 194, 70));
			}
		});
		desktopPane.add(infor);
		infor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				General general = new General();
				desktopPane.add(general);
				general.setVisible(true);
			}
		});

		// Tạo Button "Sap Xep Thep Tieu Chi".
		JButton sort = new JButton("Sắp Xếp Theo Tiêu Chí");
		sort.setBounds(425, 300, 300, 200);
		sort.setAlignmentX(Component.CENTER_ALIGNMENT);
		sort.setFont(new Font("Times New Roman", Font.BOLD, 25));
		sort.setBackground(new Color(243, 194, 70));
		sort.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				sort.setBackground(new Color(255, 255, 255));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				sort.setBackground(new Color(243, 194, 70));
			}
		});
		desktopPane.add(sort);
		sort.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Sort sort = new Sort();
				desktopPane.add(sort);
				sort.setVisible(true);
			}
		});
		// Tạo Button "Biểu Đồ"
		JButton graph = new JButton("Biểu Đồ");
		graph.setBounds(800, 300, 300, 200);
		graph.setAlignmentX(Component.CENTER_ALIGNMENT);
		graph.setFont(new Font("Times New Roman", Font.BOLD, 25));
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
		desktopPane.add(graph);
		graph.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Graph graph = new Graph();
				desktopPane.add(graph);
				graph.setVisible(true);
			}
		});

		// Tạo Background
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("E:\\java\\chungkhoan\\4.jpg"));
		lblNewLabel.setBounds(0, 0, 1170, 800);
		desktopPane.add(lblNewLabel);
	}
}
