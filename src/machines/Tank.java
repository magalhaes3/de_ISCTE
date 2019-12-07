package machines;

import java.awt.Color;
import java.awt.Graphics;

import Enemies.Enemy;
import de_ISCTE.ID;

public class Tank extends Machine {
	
	private int range = 10;
	private int damage = 9;
	private int firerate = 5;
	
	private static final int price = 500;

	public Tank(float x, float y) {
		super(x, y, ID.Turret);
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getFirerate() {
		return firerate;
	}

	public void setFirerate(int firerate) {
		this.firerate = firerate;
	}

	public int getPrice() {
		return price;
	}

	@Override
    public void tick() {

    }
	
	@Override
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.drawRect((int)this.getX(), (int)this.getY(), 50, 50);
	}

}
