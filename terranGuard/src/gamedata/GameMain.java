package gamedata;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.RenderingHints;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import javax.imageio.*;
import java.io.*;
import java.util.Random;
import java.awt.event.WindowAdapter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@SuppressWarnings("serial")
class head extends JPanel {

	JLabel max, current, count_killed;
	GameMain game;
	JLabel level;
	JLabel curr_level;

	public head(GameMain gm) {
		this.game = gm;

		max = new JLabel("Defend Your Home World ");
		level = new JLabel("   Level:");
		current = new JLabel("       Current Score:");
		curr_level = new JLabel();
		count_killed = new JLabel();

		curr_level.setText(String.valueOf(game.getlevel()));
		count_killed.setText(String.valueOf(game.getscore()));

		setLayout(new FlowLayout());
		setBackground(Color.BLACK);
		max.setForeground(Color.WHITE);
		max.setFont(new Font("CASTELLAR", Font.PLAIN, 14));
		current.setFont(new Font("CASTELLAR", Font.PLAIN, 14));
		count_killed.setFont(new Font("CASTELLAR", Font.BOLD, 14));
		current.setForeground(Color.WHITE);
		count_killed.setForeground(Color.WHITE);
		level.setForeground(Color.WHITE);
		level.setFont(new Font("CASTELLAR", Font.PLAIN, 14));
		curr_level.setForeground(Color.WHITE);
		curr_level.setFont(new Font("CASTELLAR", Font.PLAIN, 14));
		add(max);
		add(level);
		add(curr_level);
		add(current);
		add(count_killed);
	}

	public void update_level() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				curr_level.setText(String.valueOf(game.getlevel()));
			}
		});
	}

	public void update_count() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				count_killed.setText(String.valueOf(game.getscore()));
			}
		});
	}

}

@SuppressWarnings("serial")
public class GameMain extends JPanel {

	public static final int gameht = 700, gamew = 700;
	int delay = 0;
	Player pl;
	Controller c;
	Enemy en;
	int currlevel = 1;
	Random r = new Random();
	private BufferedImage image;
	private int enemy_killed = 0;
	static int score = 0;
	private int enemy_count = 1;
	static InfoPg page1;
	static boolean fg;
	static String name;
	static int flag = 0;
	Connection conn;
	Statement st;
	ResultSet rs;

	public GameMain() {

		pl = new Player(this);
		c = new Controller(this);
		c.createEnemy(enemy_count);
		setBackground(Color.black);
		addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent ke) {
			}

			public void keyPressed(KeyEvent ke) {
				pl.keyPressed(ke);
			}

			public void keyReleased(KeyEvent ke) {
				pl.keyReleased(ke);
			}
		});
		setFocusable(true);
	}

	public int getEnemy_killed() {
		return this.enemy_killed;
	}

	public int getlevel() {
		return this.currlevel;
	}

	public void Enemy_killedupdate() {
		// System.out.print(enemy_killed);

		this.enemy_killed++;
		this.score = this.score + 10;
	}

	public int getscore() {
		return this.score;
	}

	public void setEnemy_killed(int enemy_killed) {
		this.enemy_killed = enemy_killed;
	}

	public int getEnemy_count() {
		return enemy_count;
	}

	public void setEnemy_count(int enemy_count) {
		this.enemy_count = enemy_count;
	}

	private void move() {

		pl.playerMove();
		c.enemyMove();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		if (flag == 0) {
			try {
				image = ImageIO.read(new File(".\\.\\image2.png"));
				g2d.drawImage(image, 0, 0, null);
			} catch (IOException ex) {
				System.out.print("error in loading image" + ex.getMessage());
			}
		} else if (flag == 1) {
			try {
				image = ImageIO.read(new File(".\\.\\image1.jpg"));
				g2d.drawImage(image, 0, 0, null);
			} catch (IOException ex) {
				System.out.print("error in loading image" + ex.getMessage());
			}
		} else {
			try {
				image = ImageIO.read(new File(".\\.\\city.png"));
				g2d.drawImage(image, 0, 0, null);
			} catch (IOException ex) {
				System.out.print("error in loading image" + ex.getMessage());
			}
		}
		pl.paint(g2d);

		c.paint(g2d);
	}

	public void gameOver() {
		JOptionPane.showMessageDialog(null,
				"Game Over! You lose!!" + "\n" + "Your current score is: " + this.getscore());
		System.exit(0);
	}

	public void gameWin() {
		if (this.currlevel < 3) {
			this.currlevel++;
			JOptionPane.showMessageDialog(null, "Congratulations,You are upgraded to level" + this.currlevel);

			// repaint();
		} else {
			JOptionPane.showMessageDialog(null,
					"Congratulations!!!!! You Won the game!!!" + "\n" + "Your current score is: " + this.getscore());

			System.exit(0);
		}
		//
	}

	public static void main(String[] args) throws InterruptedException {
		JFrame frame = new JFrame("TerranGuard");
		GameMain game = new GameMain();
		game.setLayout(null);
		head top = new head(game);
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(top, BorderLayout.NORTH);
		panel.add(game, BorderLayout.CENTER);
		frame.setContentPane(panel);
		frame.setSize(gamew, gameht);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		page1 = new InfoPg(game);
		System.out.println(GameMain.name);
		while (fg == false) {
			System.out.print(fg);
		}
		if (fg) {
			// page1.dispose();
			frame.setVisible(true);

			while (true) {

				game.move();
				game.repaint();
				top.update_count();
				top.update_level();
				Thread.sleep(15);

			}
		}

	}
}
