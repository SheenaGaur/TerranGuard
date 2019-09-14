package gamedata;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Laser {
	private GameMain game;
	int x, y;

	public Laser(GameMain gm, int a, int b) {
		this.game = gm;
		x = a;
		y = b;
	}

	public void laserMove() {
		x = x + 5;
	}

	public void paint(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(Color.yellow);
		// g.clearRect(x, y, 20, 6);
		g.fill3DRect(x, y, 20, 6, true);
	}
}