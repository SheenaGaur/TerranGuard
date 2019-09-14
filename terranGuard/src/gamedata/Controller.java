package gamedata;

import java.util.LinkedList;
import java.util.Random;
import java.awt.*;

public class Controller {

	LinkedList<Enemy> l = new LinkedList<Enemy>();
	Enemy TempEnemy;
	GameMain game;
	Random r = new Random();

	public Controller(GameMain gm) {
		this.game = gm;

	}

	public void enemyMove() {
		for (int i = 0; i < l.size(); i++) {
			TempEnemy = l.get(i);
			TempEnemy.enemyMove();
		}
	}

	public void paint(Graphics2D g) {
		for (int i = 0; i < l.size(); i++) {
			TempEnemy = l.get(i);
			TempEnemy.paint(g);
		}
	}

	public void createEnemy(int count) {
		for (int i = 0; i < count; i++) {
			double x;
			x = r.nextInt(450) + 50;
			addEnemy(new Enemy(x, game));
		}
	}

	public void addEnemy(Enemy e) {
		l.add(e);
	}

	public void removeEnemy(Enemy e) {
		l.remove(e);
	}

}