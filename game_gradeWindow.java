package Game;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;

public class game_gradeWindow extends JFrame {

	private ResultSet rs = null; // SELECT���� ���ؼ� �����͸� ����´ٸ� ResultSet ��ü�� �� �����͸� �����ؾ� �Ѵ�
	private Statement stmt = null; // �����ͺ��̽��� ������ ������ ���� �ʿ��� ��ü

	private String[][] data = new String[50][2]; // Ʃ���� ���� ���� 50���� 2��
	private String[] col = { "NICKNAME", "SCORE" }; // ��Ʈ����Ʈ�׸� ����
	private String[] string = { "", "" }; // Ʃ���� ���� �迭 ����
	private JTable table; // ǥ ��ü ����
	private JScrollPane jp = new JScrollPane(); // ��� ������ ���� ���� �ֱ� ������ ��ũ�� ����
	private JLabel label = new JLabel("�ڽ��� ������ Ȯ���غ����� !!!!"); // ���� ���� �迭 ����
	private Button back = new Button("<-- �ڷΰ���"); // �ڷΰ��� ��ư

	public static void main(String[] args) {

		game_gradeWindow gg = new game_gradeWindow();

	}

	public game_gradeWindow() {
		super("Grade view"); // ���̾ƿ� �̸�����

		table = new JTable(data, col); // Ʃ���� ��� 'table' ǥ ����, (rowData,ColumnNames)
		jp = new JScrollPane(table); // ǥ�� ��ũ�� ����
		jp.addMouseListener(new MouseAdapter() { // 'MouseListener' �߻� Ŭ���� ȣ��, ��ũ�� ��� �ο�
		});

		Container cp = this.getContentPane(); // cp��� �̸��� �����̳� ����, �гΰ� ���� ����, �⺻������ BorderLayout
		cp.add(jp); // �����̳ʿ� 'MouseAdapter' �߻� Ŭ���� ȣ��, �پ��� ���콺 �̺�Ʈ ����� �����ϰ� �Ѵ�.

		JPanel p1 = new JPanel();
		cp.add("North", p1);
		p1.add(label);

		JPanel p2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		cp.add("South", p2);
		p2.add(back);

		Font font = new Font("����", Font.PLAIN, 15);
		label.setFont(font);

		setDefaultCloseOperation(DISPOSE_ON_CLOSE); // â �ݱ� ���� �� ����â ����
		setBounds(100, 100, 500, 500); // ���̾ƿ�â�� ����, ����, �¿찣��, ���ϰ��� ����
		setVisible(true); // ���̾ƿ��� ���̰� ���ִ� �޼ҵ�
		showTable(); // showTable Ŭ���� ȣ��
		start();

	}

	public void showTable() { // �����͸� �о���� �޼ҵ�
		table.removeAll(); // ���̺� �����ʱ�ȭ
		try {
			///////////////////////////////////
			gDBConnect connect = new gDBConnect(); // DBConnectŬ������ ������ �Լ�(�޼ҵ�) ȣ��
			stmt = connect.getStatement();
			////////////// DB�� ���� /////////////

			for (int i = 0; i < 50; i++) {
				for (int j = 0; j < 2; j++) {
					data[i][j] = "";
				}
			}
			rs = stmt.executeQuery("select ID, SCORE from SHOOTGAME"); // 'SHOOTGAME' ���̺��� 'ID, SCORE' �׸� ���� ������ �ҷ�����
																		// ������
																		// statement�� ���� �����ͺ��̽����������� ������
			int i = 0;
			while (rs.next()) { // next ���� ������ �ҷ����� �޼ҵ�� ���� ������ ������ true
				data[i][0] = rs.getString(1);
				data[i++][1] = rs.getString(2);
			}
			table.repaint(); // �� �ڵ�� ���� �籸��
		} catch (Exception e) {
			System.err.println("���� �ֱ� ����"); // ���� �� ���
		}
	}

	public void start() {
		back.addActionListener(new ActionListener() {
			// ��ư�� �������� �߻��ϴ� �ൿ�� ����
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				game_startWindow gs = new game_startWindow();
			}

		});
	}
}
