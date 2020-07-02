package Game;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;

public class game_gradeWindow extends JFrame {

	private ResultSet rs = null; // SELECT문을 통해서 데이터를 끌어온다면 ResultSet 객체에 그 데이터를 저장해야 한다
	private Statement stmt = null; // 데이터베이스에 쿼리를 보내기 위해 필요한 객체

	private String[][] data = new String[50][2]; // 튜플을 담을 공간 50행의 2열
	private String[] col = { "NICKNAME", "SCORE" }; // 어트리뷰트항목 지정
	private String[] string = { "", "" }; // 튜플을 담을 배열 생성
	private JTable table; // 표 객체 생성
	private JScrollPane jp = new JScrollPane(); // 담길 내용이 많을 수도 있기 때문에 스크롤 생성
	private JLabel label = new JLabel("자신의 점수를 확인해보세요 !!!!"); // 라벨을 담을 배열 생성
	private Button back = new Button("<-- 뒤로가기"); // 뒤로가기 버튼

	public static void main(String[] args) {

		game_gradeWindow gg = new game_gradeWindow();

	}

	public game_gradeWindow() {
		super("Grade view"); // 레이아웃 이름지정

		table = new JTable(data, col); // 튜플이 담길 'table' 표 생성, (rowData,ColumnNames)
		jp = new JScrollPane(table); // 표에 스크롤 생성
		jp.addMouseListener(new MouseAdapter() { // 'MouseListener' 추상 클래스 호출, 스크롤 기능 부여
		});

		Container cp = this.getContentPane(); // cp라는 이름의 컨테이너 생성, 패널과 같은 역할, 기본적으로 BorderLayout
		cp.add(jp); // 컨테이너에 'MouseAdapter' 추상 클래스 호출, 다양한 마우스 이벤트 사용을 가능하게 한다.

		JPanel p1 = new JPanel();
		cp.add("North", p1);
		p1.add(label);

		JPanel p2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		cp.add("South", p2);
		p2.add(back);

		Font font = new Font("돋움", Font.PLAIN, 15);
		label.setFont(font);

		setDefaultCloseOperation(DISPOSE_ON_CLOSE); // 창 닫기 누를 시 스윙창 종료
		setBounds(100, 100, 500, 500); // 레이아웃창의 가로, 세로, 좌우간격, 상하간격 설정
		setVisible(true); // 레이아웃을 보이게 해주는 메소드
		showTable(); // showTable 클래스 호출
		start();

	}

	public void showTable() { // 데이터를 읽어오는 메소드
		table.removeAll(); // 테이블 내용초기화
		try {
			///////////////////////////////////
			gDBConnect connect = new gDBConnect(); // DBConnect클래스의 생성사 함수(메소드) 호출
			stmt = connect.getStatement();
			////////////// DB에 연결 /////////////

			for (int i = 0; i < 50; i++) {
				for (int j = 0; j < 2; j++) {
					data[i][j] = "";
				}
			}
			rs = stmt.executeQuery("select ID, SCORE from SHOOTGAME"); // 'SHOOTGAME' 테이블의 'ID, SCORE' 항목에 대한 정보를 불러오는
																		// 쿼리문
																		// statement를 통해 데이터베이스로쿼리문을 보낸다
			int i = 0;
			while (rs.next()) { // next 다음 내용을 불러오는 메소드로 다음 내용이 있을시 true
				data[i][0] = rs.getString(1);
				data[i++][1] = rs.getString(2);
			}
			table.repaint(); // 위 코드로 내용 재구성
		} catch (Exception e) {
			System.err.println("보여 주기 에러"); // 에러 시 출력
		}
	}

	public void start() {
		back.addActionListener(new ActionListener() {
			// 버튼이 눌러지면 발생하는 행동을 정의
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				game_startWindow gs = new game_startWindow();
			}

		});
	}
}
