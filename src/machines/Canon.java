package machines;

import java.awt.Color;
import java.awt.Graphics;

import de_ISCTE.ID;

public class Canon extends Machine {
	
	private int range = 6;
	private int damage = 7;
	private int firerate = 4;
	
	private static final int price = 50;

	public Canon(float x, float y) {
		super(x, y, ID.Turret);
	}

	@Override
	public int getDamage() {
		return damage;
	}

	@Override
	public void setDamage(int damage) {
		this.damage = damage;
	}

	@Override
	public int getPrice() {
		return price;
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLUE);
		g.drawRect((int)this.getX(), (int)this.getY(), 50, 50);
	}

	@Override
	public int getRange() {
		return range;
	}

	@Override
	public void setRange(int range) {
		this.range = range;
	}

	@Override
	public int getFirerate() {
		return firerate;
	}

	@Override
	public void setFirerate(int firerate) {
		this.firerate = firerate;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

}
