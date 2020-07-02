package Game;

import static Game.game_join.tf;
import static Game.game_join.tf4;
import static Game.game_join.tf5;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

class TestPanel extends JPanel { // �α���â�� ��� �̹���
	ImageIcon icon = new ImageIcon("myImage/shoot.jpeg");
	Image img = icon.getImage();

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, 490, 120, Color.BLUE, this);

	}
}

public class game_login extends JFrame {

	public static void main(String[] arg) {
		game_login al = new game_login();
	}

	private static ResultSet rs = null; // SELECT���� ���ؼ� �����͸� ����´ٸ� ResultSet ��ü�� �� �����͸� �����ؾ� �Ѵ�
	private static ResultSet rs2 = null; // SELECT���� ���ؼ� �����͸� ����´ٸ� ResultSet ��ü�� �� �����͸� �����ؾ� �Ѵ�
	private static Statement stmt = null; // �����ͺ��̽��� ������ ������ ���� �ʿ��� ��ü
	private JLabel gong = new JLabel(" ");
	private JLabel lb = new JLabel("Login Module", JLabel.CENTER);
	private JLabel lb1 = new JLabel("ID :", JLabel.CENTER);
	private JLabel lb2 = new JLabel("PW :", JLabel.CENTER);
	public static JTextField tf = new JTextField(15);
	public static JTextField tf1 = new JPasswordField(15);
	JButton bt = new JButton("ȸ������");
	private JButton bt1 = new JButton("�α���");
	static JFrame login = new JFrame("Shooting Game Program");
	static String nickName;

	public game_login() {

		login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		login.setSize(500, 300);
		login.setResizable(false);
		login.setVisible(true);
		start();

		JPanel p = new JPanel(new GridLayout(2, 1));
		login.add(p);
		p.add(new TestPanel());
		p.setBackground(Color.YELLOW);

		JPanel p1 = new JPanel(new GridLayout(1, 2));
		p.add(p1);
		p1.add(gong);

		JPanel p2 = new JPanel(new GridLayout(4, 1, 10, 10));
		p1.add(p2);
		p2.add(lb);

		// ID
		JPanel p3 = new JPanel(new GridLayout(1, 2));
		p2.add(p3);
		p3.add(lb1);
		p3.add(tf);

		// PW
		JPanel p4 = new JPanel(new GridLayout(1, 2));
		p2.add(p4);
		p4.add(lb2);
		p4.add(tf1);
		((JPasswordField) tf1).setEchoChar('*');

		JPanel p5 = new JPanel(new GridLayout(1, 2));
		p2.add(p5);
		p5.add(bt);
		p5.add(bt1);

	}

	public void start() {
		bt.addActionListener(new ActionListener() {
			// ��ư�� �������� �߻��ϴ� �ൿ�� ����
			@Override
			public void actionPerformed(ActionEvent e) {
				new game_join();
			}

		});

		bt1.addActionListener(new ActionListener() {
			// ��ư�� �������� �߻��ϴ� �ൿ�� ����
			@Override
			public void actionPerformed(ActionEvent e) {
				gameDB_login dl = new gameDB_login();
				int result = dl.checkIDPW(tf.getText().toString(), tf1.getText().toString());
				if (result == 0) {
					login.setVisible(false);// �α���â�� �����
					game_startWindow g = new game_startWindow();
					g.setVisible(true); // ä��â�� ���̵��� ����
				}

				try {
					String sqlID, sqlPW;
					/////////////////////////////////
					gDBConnect connect = new gDBConnect(); // DBConnectŬ������ ������ �Լ�(�޼ҵ�) ȣ��
					stmt = connect.getStatement();
					////////////// DB�� ���� /////////////

					sqlID = "select * from (select * from SHOOTGAME where ID='" + tf.getText() + "')";
					rs = stmt.executeQuery(sqlID);

					if (result == 1) {

						if (tf.getText().toString().length() == 0 && tf1.getText().toString().length() == 0) {

							JOptionPane.showMessageDialog(null, "ID�� PW�� �Է����ּ���");

						} else if (tf.getText().toString().length() == 0) {

							JOptionPane.showMessageDialog(null, "ID�� �Է����ּ���");

						} else if (tf1.getText().toString().length() == 0) {

							JOptionPane.showMessageDialog(null, "PW�� �Է����ּ���");

						} else if (rs.next() == true) {

							if (rs.getString("PW").equals(tf1.getText()) == false) {

								JOptionPane.showMessageDialog(null, "�����ȣ�� Ȯ�����ּ���.");
								tf1.setText("");
							}
						} else if (rs.next() == false) {

							JOptionPane.showMessageDialog(null, "���������ʴ� ID�Դϴ�.");
							tf.setText("");
							tf1.setText("");

						}
					}
				} catch (SQLException e1) {
					System.err.println("�����߻�");
				}
			}
		});
	}

}
