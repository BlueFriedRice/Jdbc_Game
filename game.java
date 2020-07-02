package Game;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.*;

import Game.gDBConnect;

public class game extends JFrame implements Runnable, KeyListener {
	private BufferedImage bi = null;
	private ArrayList msList = null;
	private ArrayList enList = null;
	private boolean left = false, right = false, up = false, down = false, fire = false;
	private boolean start = false, end = false;
	private int w = 330, h = 500, x = 130, y = 450, xw = 30, xh = 30;

	int score = 0;
	String nickName;
	Statement stmt = null;
	Button ok = new Button("Ȯ��");
	Dialog info = new Dialog(this, "Information", false);

	public game() {
		bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		msList = new ArrayList();
		enList = new ArrayList();
		this.addKeyListener(this);
		this.setSize(w, h);
		this.setTitle("Shooting Game");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public void run() {
		try {
			int msCnt = 0;
			int enCnt = 0;
			while (true) {
				Thread.sleep(10);

				if (start) {
					if (enCnt > 2000) {
						enCreate();
						enCnt = 0;
					}
					if (msCnt >= 100) {
						fireMs();
						msCnt = 0;
					}
					msCnt += 10;
					enCnt += 10;
					keyControl();
					crashChk();
				}
				draw();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void fireMs() {
		if (fire) {
			if (msList.size() < 100) {
				Ms m = new Ms(this.x, this.y);
				msList.add(m);
			}
		}
	}

	public void enCreate() {
		for (int i = 0; i < 9; i++) {
			double rx = Math.random() * (w - xw);
			double ry = Math.random() * 50;
			Enemy en = new Enemy((int) rx, (int) ry);
			enList.add(en);
		}
	}

	public void crashChk() {
		Graphics g = this.getGraphics();
		Polygon p = null;
		for (int i = 0; i < msList.size(); i++) {
			Ms m = (Ms) msList.get(i);
			for (int j = 0; j < enList.size(); j++) {
				Enemy e = (Enemy) enList.get(j);
				int[] xpoints = { m.x, (m.x + m.w), (m.x + m.w), m.x };
				int[] ypoints = { m.y, m.y, (m.y + m.h), (m.y + m.h) };
				p = new Polygon(xpoints, ypoints, 4);
				if (p.intersects((double) e.x, (double) e.y, (double) e.w, (double) e.h)) {
					msList.remove(i);
					enList.remove(j);
					score = score + 10;
				}
			}
		}
		for (int i = 0; i < enList.size(); i++) {
			Enemy e = (Enemy) enList.get(i);
			int[] xpoints = { x, (x + xw), (x + xw), x };
			int[] ypoints = { y, y, (y + xh), (y + xh) };
			p = new Polygon(xpoints, ypoints, 4);
			if (p.intersects((double) e.x, (double) e.y, (double) e.w, (double) e.h)) {
				enList.remove(i);
				start = false;
				end = true;
				if (end = true) {
					info.setSize(200, 110);
					info.setResizable(false);
					info.setLocation(50, 50);
					info.setLayout(new FlowLayout());

					Label msg = new Label("'" + nickName + "' ���� ������ : " + score + "�� �Դϴ�.", Label.CENTER);
					info.add(msg);
					info.add(ok);
					info.setVisible(true);

					ok.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							info.setVisible(false);
							setVisible(false);
							msg.setVisible(false);
							try {

								///////////////////////////////////
								gDBConnect connect = new gDBConnect(); // gDBConnectŬ������ ������ �Լ�(�޼ҵ�) ȣ��
								Statement stmt = connect.getStatement();
								////////////// DB�� ���� /////////////

								stmt.executeQuery(
										"update SHOOTGAME set SCORE = '" + score + "' where ID = '" + nickName + "'");
								// statement�� ���� ������ �����ͺ��̽��� ����
								stmt.executeQuery("commit"); // ���� �� Ŀ��
								System.out.println(nickName + "���� ��������Ϸ�");
							} catch (Exception ex) {
								System.err.println("DB���� ����"); // ������ ���
							}
							game_startWindow gs = new game_startWindow();
						}
					});
				}
			}
		}

	}

	public void draw() {
		ImageIcon icon = new ImageIcon("myImage/airplane.png"); // �߻簴ü �̹���
		Image img = icon.getImage();

		ImageIcon icon2 = new ImageIcon("myImage/sky.jpeg"); // ��� �̹���
		Image img2 = icon2.getImage();

		ImageIcon icon3 = new ImageIcon("myImage/ene.png"); // ��� �̹���
		Image img3 = icon3.getImage();

		Graphics gs = bi.getGraphics();

		gs.setColor(Color.white);
		gs.drawImage(img2, 0, 0, w, h, this); // ���
		gs.setColor(Color.black);
		gs.setFont(new Font("����", Font.PLAIN, 15));
		gs.drawString(nickName + "���� ���� : " + score, 180, 50);
		gs.drawString("Enemy ��ü�� : " + enList.size(), 180, 70);
		gs.drawString("Ms ��ü�� : " + msList.size(), 180, 90);
		gs.drawString("���ӽ��� : Enter", 180, 110);

		if (end) {

			gs.drawString("G A M E     O V E R", 100, 250);

		}
		if (start) {

			gs.drawString(" ", 100, 250);

		}

		gs.drawImage(img, x, y, xw + 10, xh + 10, this); // �̻��� �߻簴ü

		for (int i = 0; i < msList.size(); i++) {
			Ms m = (Ms) msList.get(i);
			gs.setColor(Color.BLACK);

			gs.fillOval(m.x + 15, m.y, m.w, m.h);
			if (m.y < 0)
				msList.remove(i);
			m.moveMs();
		}
		gs.setColor(Color.black);
		for (int i = 0; i < enList.size(); i++) {
			Enemy e = (Enemy) enList.get(i);
			gs.drawImage(img3, e.x, e.y, e.w, e.h, this); // enemy

			if (e.y > h)
				enList.remove(i);
			e.moveEn();
		}

		Graphics ge = this.getGraphics();
		ge.drawImage(bi, 0, 0, w, h, this);
	}

	public void keyControl() {
		if (0 < x) {
			if (left)
				x -= 3;
		}
		if (w > x + xw) {
			if (right)
				x += 3;
		}
		if (25 < y) {
			if (up)
				y -= 3;
		}
		if (h > y + xh) {
			if (down)
				y += 3;
		}
	}

	public void keyPressed(KeyEvent ke) {
		switch (ke.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			left = true;
			break;
		case KeyEvent.VK_RIGHT:
			right = true;
			break;
		case KeyEvent.VK_UP:
			up = true;
			break;
		case KeyEvent.VK_DOWN:
			down = true;
			break;
		case KeyEvent.VK_A:
			fire = true;
			break;
		case KeyEvent.VK_ENTER:
			start = true;
			end = false;
			break;
		}
	}

	public void keyReleased(KeyEvent ke) {
		switch (ke.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			left = false;
			break;
		case KeyEvent.VK_RIGHT:
			right = false;
			break;
		case KeyEvent.VK_UP:
			up = false;
			break;
		case KeyEvent.VK_DOWN:
			down = false;
			break;
		case KeyEvent.VK_A:
			fire = false;
			break;
		}
	}

	public void keyTyped(KeyEvent ke) {
	}

	public static void main(String[] args) {
		Thread t = new Thread(new game());
		t.start();

	}
}

class Ms {
	int x;
	int y;
	int w = 7;
	int h = 7;

	public Ms(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void moveMs() {
		y--;
	}
}

class Enemy {
	int x;
	int y;
	int w = 30;
	int h = 30;

	public Enemy(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void moveEn() {
		y++;
	}
}