package Game;

import static Game.game_join.jl;
import static Game.game_join.tf1;
import static Game.game_join.tf2;
import static Game.game_join.tf4;
import static Game.game_join.tf5;
import static Game.game_join.warn;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class game_join extends JFrame implements ItemListener, ActionListener, FocusListener {

	private JLabel title = new JLabel("---------------------- ȸ������â ----------------------",JLabel.CENTER);
    private JLabel lb = new JLabel("���̵�",JLabel.CENTER);
    private JLabel lb1 = new JLabel("��й�ȣ",JLabel.CENTER);
    private JLabel lb2 = new JLabel("��й�ȣȮ��",JLabel.CENTER);
    private JLabel lb3 = new JLabel("�̸�",JLabel.CENTER);
    private JLabel lb4 = new JLabel("�ֹε�Ϲ�ȣ",JLabel.CENTER);
    private JLabel lb5 = new JLabel("�� �� ��",JLabel.CENTER);
    private JLabel lb6 = new JLabel("E-Mail",JLabel.CENTER);
    private JLabel gol = new JLabel("@",JLabel.CENTER);
    private JLabel lb7 = new JLabel("�ּ�",JLabel.CENTER);
    private JLabel lb8 = new JLabel("���ּ�",JLabel.CENTER);
    private JLabel dasi = new JLabel("-",JLabel.CENTER);
    private JLabel dasi1 = new JLabel("-",JLabel.CENTER);
    private JLabel dasi2 = new JLabel("-",JLabel.CENTER);
    public static JLabel jl = new JLabel();
    public static String warn[] = {"�ݵ�� ���ʴ�� ��ĭ�� ������ �Է����ּ���."};
    
    public static JTextField tf = new JTextField(10);
    public static JTextField tf1 = new JPasswordField(10);
    public static JTextField tf2 = new JPasswordField(10);
    public static JTextField tf3 = new JTextField(10);
    public static JTextField tf4 = new JTextField(6);
    public static JTextField tf5 = new JPasswordField(7);
    public static JTextField tf6 = new JTextField(4);
    public static JTextField tf7 = new JTextField(4);
    public static JTextField tf8 = new JTextField(4);
    public static JTextField tf9 = new JTextField(10);
    public static JTextField tf10 = new JTextField(10);
    public static JTextField tf11 = new JTextField(25);
    public static JTextField tf12 = new JTextField(20);
    
    private String []ar = {"naver.com","daum.net","gmail.com","yahoo.co.kr","hanmir.com","empal.com","netian.com",
    						"nate.com","dreamwiz.com","hanmail.net","�����Է�"};
	private Choice ch = new Choice();
	
	public static JButton bt = new JButton("Ȯ��");
	private JButton bt1 = new JButton("���");
	public static JButton bt2 = new JButton("�ߺ�Ȯ��"); //ID
	public static JButton bt3 = new JButton("��й�ȣ��ġȮ��"); //PW
	public static JButton bt4 = new JButton("�ߺ�Ȯ��"); //P_ID
	
	public static JFrame jo = new JFrame("ȸ������");

	private static ResultSet rs = null; // SELECT���� ���ؼ� �����͸� ����´ٸ� ResultSet ��ü�� �� �����͸� �����ؾ� �Ѵ�
	private static Statement stmt = null; // �����ͺ��̽��� ������ ������ ���� �ʿ��� ��ü
	
    public game_join() {
        // ����, ���⼭ setDefaultCloseOperation() ���Ǹ� ���� ���ƾ� �Ѵ�
        // �����ϰ� �Ǹ� �� â�� ������ ��� â�� ���α׷��� ���ÿ� ������
        jo.setSize(450,650);
        jo.setResizable(false);
        jo.setVisible(true);
        start();
        
        jo.add(title,"North");
        
		JPanel p = new JPanel(new GridLayout(11,2));
		jo.add(p);
    	
		//ID
		JPanel p1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 20));		
		p.add(p1);
		p1.add(lb);
       	p1.add(tf);
       	p1.add(bt2);
       	
       	//PW
		JPanel p2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 20));	
		p.add(p2);
    	p2.add(lb1);
    	p2.add(tf1);
    	((JPasswordField) tf1).setEchoChar('*');
    	
    	//PW Check
		JPanel p3 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 20));	
		p.add(p3);
    	p3.add(lb2);
    	p3.add(tf2);
    	((JPasswordField) tf2).setEchoChar('*');
    	p3.add(bt3);
    	
    	//Name
		JPanel p4 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 20));	
		p.add(p4);
    	p4.add(lb3);
    	p4.add(tf3);
    	
    	//P_ID
		JPanel p5 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 20));	
    	p.add(p5);
    	p5.add(lb4);
    	p5.add(tf4);
    	p5.add(dasi);
    	p5.add(tf5);
       	p5.add(bt4);
       	((JPasswordField) tf5).setEchoChar('*'); // *�� ǥ��ǰ� ����
    	
    	//H.P
		JPanel p6 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 20));	
    	p.add(p6); 
    	p6.add(lb5);
    	p6.add(tf6);
    	p6.add(dasi1);
    	p6.add(tf7);
    	p6.add(dasi2);
    	p6.add(tf8);
    	
    	//E-Mail
		JPanel p7 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 20));	
    	p.add(p7); 
    	p7.add(lb6);
    	p7.add(tf9);
    	p7.add(gol);
    	p7.add(tf10);
    	tf10.setText(ar[0]);
    	for(int i=0;i<ar.length;i++) {
    	ch.add(ar[i]);
    	}
    	p7.add(ch);
    	
    	//Address
		JPanel p8 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 20));	
    	p.add(p8); 
    	p8.add(lb7);
    	p8.add(tf11);
    	
    	//Address2
		JPanel p9 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 2));	
    	p.add(p9);
    	p9.add(lb8);
    	p9.add(tf12);
    	
    	//LabelField
    	JPanel p10 = new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));
    	p.add(p10);
    	p10.add(jl);
    	jl.setText(warn[0]);
    	
    	//Button
    	JPanel p11 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
    	p.add(p11);
    	p11.add(bt);
    	p11.add(bt1);

    }

	public void start() {
		
		tf1.addFocusListener(this);
		tf2.addFocusListener(this);
		tf3.addFocusListener(this);
		tf4.addFocusListener(this);
		tf5.addFocusListener(this);
		tf6.addFocusListener(this);
		tf7.addFocusListener(this);
		tf8.addFocusListener(this);
		tf9.addFocusListener(this);
		tf10.addFocusListener(this);
		tf11.addFocusListener(this);
		
		ch.addItemListener(this);
		
		bt1.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
		        jo.setVisible(false);
			}
		});
		
		bt2.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
			    gameDB_join dj = new gameDB_join();
				dj.IDcheck();
			}
		});
		
		bt3.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
			    gameDB_join dj = new gameDB_join();
				dj.PWcheck();			
			}
		});
		
		bt4.addActionListener(new ActionListener () { // �ֹι�ȣĭ�� �ϳ��� ������� ��
			public void actionPerformed(ActionEvent e) {
			if (tf4.getText().isEmpty() == true || tf5.getText().isEmpty() == true) {
				JOptionPane.showMessageDialog(null, "�ֹε�Ϲ�ȣ�� �Է����ּ���.");
			}else {
			    gameDB_join dj = new gameDB_join();
				dj.PIDcheck();
			}
			}
		});
		
		bt.addActionListener(new ActionListener () { // ��ĭ�� ���� ��
			public void actionPerformed(ActionEvent e) {
				if ((tf.getText().isEmpty()) == true || (tf1.getText().isEmpty()) == true
						|| (tf2.getText().isEmpty()) == true || (tf3.getText().isEmpty()) == true
						|| (tf4.getText().isEmpty()) == true || (tf5.getText().isEmpty()) == true
						|| (tf6.getText().isEmpty()) == true || (tf7.getText().isEmpty()) == true
						|| (tf8.getText().isEmpty()) == true || (tf9.getText().isEmpty()) == true
						|| (tf10.getText().isEmpty()) == true || (tf11.getText().isEmpty()) == true) {
					JOptionPane.showMessageDialog(null, "����ִ� ĭ�� �����մϴ�.");
			 } else {
			    gameDB_join dj = new gameDB_join();
				dj.Finaljoin();
			 }
			}
		});
		
	}
	
	public void itemStateChanged(ItemEvent e) {
		if(e.getSource() == ch) {
			String str = ch.getSelectedItem();
			tf10.setText(str);
			}
	} // �̸��� ���̽�

	@Override
	public void actionPerformed(ActionEvent e) {}

	public void focusGained(FocusEvent e) {
		if (e.getSource() == tf1) {
			int x = tf.getText().trim().length();
			if (x == 0) {
				tf.requestFocus();
			}
		}else if (e.getSource() == tf2) {
			int x = tf1.getText().trim().length();
			if (x == 0) {
				tf1.requestFocus();
			}
		}else if (e.getSource() == tf3) {
			int x = tf2.getText().trim().length();
			if (x == 0) {
				tf2.requestFocus();
			}
		}else if (e.getSource() == tf4) {
			int x = tf3.getText().trim().length();
			if (x == 0) {
				tf3.requestFocus();
			}
		}else if (e.getSource() == tf5) {
			int x = tf4.getText().trim().length();
			if (x != 6) {
				tf4.setText("");
				tf4.requestFocus();
			}
		}else if (e.getSource() == tf6) {
			int x = tf5.getText().trim().length();
			if (x == 0) {
				tf5.requestFocus();
			}
		}else if (e.getSource() == tf7) {
			int x = tf6.getText().trim().length();
			if (x != 3) {
				tf6.setText("");
				tf6.requestFocus();
			}
		}else if (e.getSource() == tf8) {
			int x = tf7.getText().trim().length();
			if (x > 4 || x < 3) {
				tf7.setText("");
				tf7.requestFocus();
			}
		}else if (e.getSource() == tf9) {
			int x = tf8.getText().trim().length();
			if (x != 4) {
				tf8.setText("");
				tf8.requestFocus();
			}
		}else if (e.getSource() == tf11) {
			int x = tf9.getText().trim().length();
			if (x == 0) {
				tf9.requestFocus();
			}
		}else if (e.getSource() == tf12) {
			int x = tf11.getText().trim().length();
			if (x == 0) {
				tf11.requestFocus();
			}
		}
		
	} //�ڸ����� ä�����ų� �����ϸ� ���� ĭ���� ���Ѿ�� ����	

	@Override
	public void focusLost(FocusEvent e) {}
	
}