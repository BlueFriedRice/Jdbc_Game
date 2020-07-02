package Game;

import static Game.game_join.tf;
import static Game.game_join.tf4;
import static Game.game_join.tf5;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

class TestPanel extends JPanel { // 로그인창에 띄울 이미지
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

	private static ResultSet rs = null; // SELECT문을 통해서 데이터를 끌어온다면 ResultSet 객체에 그 데이터를 저장해야 한다
	private static ResultSet rs2 = null; // SELECT문을 통해서 데이터를 끌어온다면 ResultSet 객체에 그 데이터를 저장해야 한다
	private static Statement stmt = null; // 데이터베이스에 쿼리를 보내기 위해 필요한 객체
	private JLabel gong = new JLabel(" ");
	private JLabel lb = new JLabel("Login Module", JLabel.CENTER);
	private JLabel lb1 = new JLabel("ID :", JLabel.CENTER);
	private JLabel lb2 = new JLabel("PW :", JLabel.CENTER);
	public static JTextField tf = new JTextField(15);
	public static JTextField tf1 = new JPasswordField(15);
	JButton bt = new JButton("회원가입");
	private JButton bt1 = new JButton("로그인");
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
			// 버튼이 눌러지면 발생하는 행동을 정의
			@Override
			public void actionPerformed(ActionEvent e) {
				new game_join();
			}

		});

		bt1.addActionListener(new ActionListener() {
			// 버튼이 눌러지면 발생하는 행동을 정의
			@Override
			public void actionPerformed(ActionEvent e) {
				gameDB_login dl = new gameDB_login();
				int result = dl.checkIDPW(tf.getText().toString(), tf1.getText().toString());
				if (result == 0) {
					login.setVisible(false);// 로그인창은 숨기고
					game_startWindow g = new game_startWindow();
					g.setVisible(true); // 채팅창이 보이도록 설정
				}

				try {
					String sqlID, sqlPW;
					/////////////////////////////////
					gDBConnect connect = new gDBConnect(); // DBConnect클래스의 생성사 함수(메소드) 호출
					stmt = connect.getStatement();
					////////////// DB에 연결 /////////////

					sqlID = "select * from (select * from SHOOTGAME where ID='" + tf.getText() + "')";
					rs = stmt.executeQuery(sqlID);

					if (result == 1) {

						if (tf.getText().toString().length() == 0 && tf1.getText().toString().length() == 0) {

							JOptionPane.showMessageDialog(null, "ID와 PW를 입력해주세요");

						} else if (tf.getText().toString().length() == 0) {

							JOptionPane.showMessageDialog(null, "ID를 입력해주세요");

						} else if (tf1.getText().toString().length() == 0) {

							JOptionPane.showMessageDialog(null, "PW를 입력해주세요");

						} else if (rs.next() == true) {

							if (rs.getString("PW").equals(tf1.getText()) == false) {

								JOptionPane.showMessageDialog(null, "비빌번호를 확인해주세요.");
								tf1.setText("");
							}
						} else if (rs.next() == false) {

							JOptionPane.showMessageDialog(null, "존재하지않는 ID입니다.");
							tf.setText("");
							tf1.setText("");

						}
					}
				} catch (SQLException e1) {
					System.err.println("에러발생");
				}
			}
		});
	}

}
