package gamedata;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player {
	int x = 20, y = 200, ym = 0, ht = 50, w = 50;
	boolean flag;
	Laser1 l;
	BufferedImage i;
	private GameMain game;

	public Player(GameMain gm) {
		this.game = gm;
	}

	public void playerMove() {
		if (y + ym < game.getHeight() - 55 && y + ym + 10 > 0)
			y = y + 3 * ym;
		if (flag == true) {
			l.laserMove();
		}
	}

	public Rectangle getbound() {
		return new Rectangle(x, y, 50, 50);
	}

	public void keyReleased(KeyEvent ke) {
		ym = 0;
	}

	public void keyPressed(KeyEvent ke) {
		if (ke.getKeyCode() == KeyEvent.VK_UP)
			ym = -1;
		if (ke.getKeyCode() == KeyEvent.VK_DOWN)
			ym = 1;
		if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
			l = new Laser1(game, x, y);
			flag = true;
			// l.checkFlag(flag);
		}
	}

	public void paint(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		if (flag == true) {
			l.paint(g);
		}
		// g.fillRoundRect(x, y, ht, w, 8, 6);
		try {
			i = ImageIO.read(new File(".\\.\\Player.png"));
			g.drawImage(i, x, y, null);
		} catch (IOException ex) {
			System.out.print("error in loading image" + ex.getMessage());
		}
	}
}

// LASER CLASS
class Laser1 {
	private GameMain game;
	int x, y, w = 20, ht = 6, killed = 0;
	boolean flag;

	public Laser1(GameMain gm, int a, int b) {
		this.game = gm;
		x = a;
		y = b;
	}

	public void checkFlag(boolean f) {
		flag = false;
	}

	public void laserMove() {
		// if(flag==true)
		if (collision()) {
			game.Enemy_killedupdate();
			killed = game.getEnemy_killed();
			game.c.removeEnemy(game.c.TempEnemy);
			if (game.currlevel == 1) {
				if (killed >= 5)
					game.gameWin();
				else
					game.c.createEnemy(game.getEnemy_count());
			}
			if (game.currlevel == 2) {
				if (killed >= 10)
					game.gameWin();
				else
					game.c.createEnemy(game.getEnemy_count());
			}
			if (game.currlevel == 3) {
				if (killed >= 15)
					game.gameWin();
				else
					game.c.createEnemy(game.getEnemy_count());
			}
		}
		x = x + 10;
	}

	public Rectangle getbounds() {
		return new Rectangle(x, y, w, ht);
	}

	public boolean collision() {
		return game.c.TempEnemy.getbound().intersects(getbounds());
	}

	public void paint(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		// g.clearRect(x, y, 20, 6);
		g.setColor(Color.yellow);
		// if(flag==true)
		g.fill3DRect(x + 50, y + 20, w, ht, true);
	}
}