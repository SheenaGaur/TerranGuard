package gamedata;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Enemy {
	private GameMain game;
	Laser1 l;
	public int x = 650, y, ht = 40, w = 60;
	Random r = new Random();
	int speed = r.nextInt(3) + 1;
	BufferedImage img;

	public Enemy(double a, GameMain gm) {
		this.game = gm;
		y = (int) a;
	}

	public void enemyMove() {
		if (game.currlevel == 2) {
			speed = r.nextInt(5) + 1;
		}
		if (game.currlevel == 3) {
			speed = r.nextInt(7) + 1;
		}
		x -= speed;
		if (x < -10 || collision()) {
			game.gameOver();
		}
	}

	public boolean collision() {
		// for(int i=0;i<10;i++);
		return game.pl.getbound().intersects(getbound());
	}

	public Rectangle getbound() {
		return new Rectangle(x, y - 10, w + 10, ht);
	}

	public void paint(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		try {
			img = ImageIO.read(new File(".\\.\\Enemy.png"));
			g.drawImage(img, x, y, null);
		} catch (IOException ex) {
			System.out.print("error in loading image" + ex.getMessage());
		}
		// g.setColor(Color.red);
		// g.fillArc(x, y, w, ht, 400, 290);
	}

}
