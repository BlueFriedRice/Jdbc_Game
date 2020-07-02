package Game;

import static Game.game_join.jo;
import static Game.game_join.tf;
import static Game.game_join.tf1;
import static Game.game_join.tf2;
import static Game.game_join.tf10;
import static Game.game_join.tf11;
import static Game.game_join.tf12;
import static Game.game_join.tf3;
import static Game.game_join.tf4;
import static Game.game_join.tf5;
import static Game.game_join.tf6;
import static Game.game_join.tf7;
import static Game.game_join.tf8;
import static Game.game_join.tf9;
import static Game.game_join.jl;
import static Game.game_join.warn;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

import Game.gDBConnect;

public class gameDB_join implements ActionListener {
	private ResultSet rs = null; // SELECT���� ���ؼ� �����͸� ����´ٸ� ResultSet ��ü�� �� �����͸� �����ؾ� �Ѵ�
	private ResultSet rs2 = null;
	private Statement stmt = null; // �����ͺ��̽��� ������ ������ ���� �ʿ��� ��ü

	static Dialog infoid = new Dialog(jo, "Information", false);
	static Dialog infopw = new Dialog(jo, "Information", false);
	static Dialog infopid = new Dialog(jo, "Information", false);

	static Button ok = new Button("Ȯ��"); // �ߺ���
	String sex = "";
	
	public void IDcheck() {

		///////////////////////////////////
		gDBConnect connect = new gDBConnect(); // gDBConnectŬ������ ������ �Լ�(�޼ҵ�) ȣ��
		stmt = connect.getStatement();
		//////////////DB�� ���� /////////////

		infoid.setSize(200, 110);
		infoid.setResizable(false);
		infoid.setLocation(50, 50);
		infoid.setLayout(new FlowLayout());

		try {
			String sql = "select* from SHOOTGAME where ID = '" + tf.getText() + "'";

			stmt = gDBConnect.con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery(sql);
			rs.last(); // Ŀ���� ��ġ�� ���� �ڷ� �̵�
			int rowCount = rs.getRow(); // ���� Ŀ���� Row Index ���� ����
			if (rowCount >= 1) {
				Label msgid = new Label("'" + tf.getText() + "'�� �̹� �ִ� ID�Դϴ�.", Label.CENTER);
				infoid.add(msgid);
				infoid.add(ok);
				infoid.setVisible(true);
				tf.setText("");
				System.out.println("ID���Է¹ٶ�");
				ok.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						infoid.setVisible(false);
						infoid.dispose();
						msgid.setVisible(false);
					}
				});
			} else if (tf.getText().equals("")) {
				Label msgid = new Label("��ĭ�� ID�� �Է����ּ���.", Label.CENTER);
				infoid.add(msgid);
				infoid.add(ok);
				infoid.setVisible(true);
				System.out.println("ID�Է¹ٶ�");
				ok.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						infoid.setVisible(false);
						infoid.dispose();
						msgid.setVisible(false);
					}
				});
			} else {
				Label msgid = new Label("'" + tf.getText() + "'�� ��밡���� ID�Դϴ�.", Label.CENTER);
				infoid.add(msgid);
				infoid.add(ok);
				infoid.setVisible(true);
				System.out.println("�ߺ�Ȯ�οϷ�");
				ok.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						infoid.setVisible(false);
						infoid.dispose();
						msgid.setVisible(false);
					}
				});
			}

		} catch (SQLException e1) {
			System.err.println("Ȯ�� ����"); // ������ ���
		}
	}

	public void PWcheck() {

		infopw.setSize(200, 110);
		infopw.setResizable(false);
		infopw.setLocation(50, 50);
		infopw.setLayout(new FlowLayout());

		if (tf1.getText().isEmpty() == true || tf2.getText().isEmpty() == true ) {
			
			Label msgpw = new Label("Password�� �Է� �ٶ��ϴ�.", Label.CENTER);
			infopw.add(msgpw);
			infopw.add(ok);
			infopw.setVisible(true);
			ok.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					infopw.setVisible(false);
					infopw.dispose();
					msgpw.setVisible(false);
				}
			});
			
		} else if(tf1.getText().equals(tf2.getText())) {
			
			Label msgpw = new Label("Password�� ��ġ�մϴ�.", Label.CENTER);
			infopw.add(msgpw);
			infopw.add(ok);
			infopw.setVisible(true);
			System.out.println("PW��ġȮ�οϷ�");
			ok.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					infopw.setVisible(false);
					infopw.dispose();
					msgpw.setVisible(false);
				}
			});
			
		}
		else {
			Label msgpw = new Label("Password�� �ٽ� �Է� �ٶ��ϴ�.", Label.CENTER);
			infopw.add(msgpw);
			infopw.add(ok);
			infopw.setVisible(true);
			tf2.setText("");
			ok.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					infopw.setVisible(false);
					infopw.dispose();
					msgpw.setVisible(false);
				}
			});
		}

	}

	public void PIDcheck() {
		
		///////////////////////////////////
		gDBConnect connect = new gDBConnect(); // gDBConnectŬ������ ������ �Լ�(�޼ҵ�) ȣ��
		stmt = connect.getStatement();
		//////////////DB�� ���� /////////////
		
		infopid.setSize(220, 100);
		infopid.setResizable(false);
		infopid.setLocation(50, 50);
		infopid.setLayout(new FlowLayout());

		try {
			String sql = "select* from SHOOTGAME where P_ID = '" + tf4.getText() + "-" + tf5.getText() + "'";
			stmt = gDBConnect.con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery(sql);
			rs.last(); // Ŀ���� ��ġ�� ���� �ڷ� �̵�
			int rowCount = rs.getRow(); // ���� Ŀ���� Row Index ���� ����
			char gender = tf5.getText().charAt(0);
			
			if (rowCount >= 1) {
				Label msgpid = new Label("�̹� ���ԵǾ� �ֽ��ϴ�.", Label.CENTER);
				infopid.add(msgpid);
				infopid.add(ok);
				infopid.setVisible(true);
				tf4.setText("");
				tf5.setText("");
				System.out.println("PID���Է¹ٶ�");
				ok.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						infopid.setVisible(false);
						infopid.dispose();
						msgpid.setVisible(false);
					}
				});
			} else if(tf5.getText().toString().length() > 7) {
				Label msgpid = new Label("��ȿ�������� �ֹε�Ϲ�ȣ �Դϴ�.", Label.CENTER);
				infopid.add(msgpid);
				infopid.add(ok);
				infopid.setVisible(true);
				tf4.setText("");
				tf5.setText("");
				System.out.println("PID���Է¹ٶ�");
				ok.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						infopid.setVisible(false);
						infopid.dispose();
						msgpid.setVisible(false);
					}
				});
				
			} else if(gender > '4') {
				Label msgpid = new Label("��ȿ�������� �ֹε�Ϲ�ȣ �Դϴ�.", Label.CENTER);
				infopid.add(msgpid);
				infopid.add(ok);
				infopid.setVisible(true);
				tf4.setText("");
				tf5.setText("");
				System.out.println("PID���Է¹ٶ�");
				ok.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						infopid.setVisible(false);
						infopid.dispose();
						msgpid.setVisible(false);
					}
				});
			} else {
				Label msgpid = new Label("�ߺ�Ȯ���� �Ϸ�Ǿ����ϴ�.", Label.CENTER);
				infopid.add(msgpid);
				infopid.add(ok);
				infopid.setVisible(true);
				System.out.println("�ߺ�Ȯ�οϷ�");
				ok.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						infopid.setVisible(false);
						infopid.dispose();
						msgpid.setVisible(false);
					}
				});
			}

		} catch (SQLException e1) {
			System.err.println("Ȯ�� ����"); // ������ ���
		}
	}

	public void Finaljoin() {
		String sql, sql2;
		
		try {
			
			char gender = tf5.getText().charAt(0);
	        
	        switch(gender){
	        case '1': case '3':
	            sex = "����";
	            break;
	        case '2': case '4':
	            sex = "����";
	            break;
	        default:
	        	tf4.setText("");
	        	tf5.setText("");
	        	JOptionPane.showMessageDialog(null, "��ȿ�������� �ֹε�Ϲ�ȣ �Դϴ�.");
	        }
	        
			/////////////////////////////////
	        gDBConnect connect = new gDBConnect(); // gDBConnectŬ������ ������ �Լ�(�޼ҵ�) ȣ��
			stmt = connect.getStatement();
			////////////// DB�� ���� /////////////
			sql = "select* from SHOOTGAME where ID = '" + tf.getText() + "'"; 
			stmt = gDBConnect.con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery(sql);
			rs.last(); // Ŀ���� ��ġ�� ���� �ڷ� �̵�
			int rowCount = rs.getRow(); // ���� Ŀ���� Row Index ���� ����

			sql2 = "select* from SHOOTGAME where P_ID = '" + tf4.getText() + "-" + tf5.getText() + "'";
			rs2 = stmt.executeQuery(sql2);

			if (rowCount >= 1) {
				JOptionPane.showMessageDialog(null, "ID �ߺ�Ȯ���� �ʿ��մϴ�.");
			} else if (tf1.getText().equals(tf2.getText()) == false) {
				JOptionPane.showMessageDialog(null, "��й�ȣ Ȯ���� �ʿ��մϴ�.");
			} else if (rs2.next() == true) {
				JOptionPane.showMessageDialog(null, "�ֹε�Ϲ�ȣ �ߺ�Ȯ���� �ʿ��մϴ�.");
			} else if (tf5.getText().toString().length() > 7) {
				JOptionPane.showMessageDialog(null, "��ȿ�������� �ֹε�Ϲ�ȣ �Դϴ�.");
				tf4.setText("");
				tf5.setText("");
			} else if ((tf.getText().isEmpty()) == true || (tf1.getText().isEmpty()) == true
					|| (tf2.getText().isEmpty()) == true || (tf3.getText().isEmpty()) == true
					|| (tf4.getText().isEmpty()) == true || (tf5.getText().isEmpty()) == true
					|| (tf6.getText().isEmpty()) == true || (tf7.getText().isEmpty()) == true
					|| (tf8.getText().isEmpty()) == true || (tf9.getText().isEmpty()) == true
					|| (tf10.getText().isEmpty()) == true || (tf11.getText().isEmpty()) == true) {
				JOptionPane.showMessageDialog(null, "����ִ� ĭ�� �����մϴ�.");
			}else {
			try {
				stmt.executeQuery("insert into SHOOTGAME values('" + tf.getText() + "','" + tf2.getText() + "','"
						+ tf3.getText() + "','" + tf4.getText() + "-" + tf5.getText() + "','" + tf6.getText() + "-"
						+ tf7.getText() + "-" + tf8.getText() + "','" + tf9.getText() + "@" + tf10.getText() + "','"
						+ tf11.getText() + ", " + tf12.getText() + "','" + sex + "','')");
				// statement�� ���� ������ �����ͺ��̽��� ����
				stmt.executeQuery("commit"); // ���� �� Ŀ��
				System.out.println("ȸ�������� �����մϴ�.");
				JOptionPane.showMessageDialog(null, "ȸ�������� �����մϴ�!!!");
				jo.setVisible(false);
			} catch (Exception ex) {
				System.err.println("������ ����"); // ������ ���
			}
			}
		} catch (SQLException e1) {
			System.err.println("DB���۽���");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
