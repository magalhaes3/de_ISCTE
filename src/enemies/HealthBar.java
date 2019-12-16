package enemies;

import java.awt.Color;
import java.awt.Graphics;

import de_ISCTE.GameObject;
import de_ISCTE.ID;

public class HealthBar extends GameObject{

	private Enemy enemy;
	private static final int LENGTH = 50;
	private static final int HEIGHT = 10;
	private int currentHPPercentage = LENGTH - 2;
	private float maxHP;
	
	public HealthBar(Enemy enemy, float x, float y) {
		super(x, y, ID.Information);
		this.enemy = enemy;
		maxHP = enemy.getHP();
	}

	public void setMaxHP(int hp) {
		maxHP = hp;
	}
		
	@Override
	public void tick() {
		float enemyHP = enemy.getHP();
		currentHPPercentage = (int)Math.ceil(((LENGTH*enemyHP)/maxHP));
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect((int)(enemy.getX() - 24), (int)(enemy.getY() - 39), LENGTH, HEIGHT);
		g.setColor(Color.GREEN);
		g.fillRect((int)(enemy.getX() - 24), (int)(enemy.getY() - 39), currentHPPercentage, HEIGHT);
	}
	
	
	public int getCurrentPercentage() {
		return currentHPPercentage;
	}
	
}
