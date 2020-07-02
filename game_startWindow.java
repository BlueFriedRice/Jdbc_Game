package Game;

import java.awt.*;
import java.awt.event.*;

public class game_startWindow extends Frame implements ActionListener{
	
	public static void main(String[] args) {
		game_startWindow s =  new game_startWindow();
	}
	Button gamestart = new Button("���ӽ���");
	Button scorecheck = new Button("��������");
	Button back = new Button("�α׾ƿ�");
	String nickName = game_login.tf.getText();
 
	private Dimension dimen, dimen1;
	private int xpos, ypos;
	private Label msgg = new Label(nickName+"�� ������ ȯ���մϴ�.");
	
	public game_startWindow() {
		super("Game management");
		this.init();
		this.start();
		this.setSize(600, 500);
		dimen = Toolkit.getDefaultToolkit().getScreenSize();
		dimen1 = this.getSize();
		this.setLocation(500, 200);
		this.setVisible(true);
		this.setResizable(false);
	}
	
	public void init() {
		
		Panel p7 = new Panel();
		p7.setSize(600,400);
		this.add(p7);
		BorderLayout border1 = new BorderLayout();
		p7.setLayout(border1);
		this.add("North",msgg);
		
		Panel p8 = new Panel(new GridLayout(1,2,10,10));
		
		p7.add("Center",p8);
		p8.add(scorecheck);
		p8.add(gamestart);
		
		Panel p9 = new Panel(new FlowLayout(FlowLayout.RIGHT));
		p7.add("South",p9);
		p9.add(back);
		
		Font font3 = new Font("����", Font.PLAIN, 15);
		Font font4 = new Font("����", Font.PLAIN, 40);
		msgg.setFont(font3);
		back.setFont(font3);
		scorecheck.setFont(font4);
		gamestart.setFont(font4);
		
	}
	public void start() {
		// Event�� Threadó���� �κ�
				this.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent e) {
						System.exit(0);
					}
				}); // ---------- x ������ ���� �̺�Ʈ -----------
				
				gamestart.addActionListener(this);
				scorecheck.addActionListener(this);
				back.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == gamestart) {
			game g = new game();
			nickName = game_login.tf.getText();
			g.nickName = nickName;
			new Thread(g).start();
			this.setVisible(false);
		}else if(e.getSource() == scorecheck) {
			this.setVisible(false);
			game_gradeWindow gg = new game_gradeWindow(); 
		}else if(e.getSource() == back) {
			this.setVisible(false);
			game_login.login.setVisible(true);
		}
		
	}
}