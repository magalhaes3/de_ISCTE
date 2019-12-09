package machines;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

import Enemies.Enemy;
import de_ISCTE.Game;
import de_ISCTE.GameObject;
import de_ISCTE.ID;

public class FastTurret extends Machine {
	
	private static final int PRICE = 100;
	
	public FastTurret(float x, float y) {
		super(x, y, ID.Turret);
		this.setRange(4);
		this.setDamage(4);
		this.setFirerate(13);
		this.setPrice(PRICE);
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawRect((int)this.getX(), (int)this.getY(), 50, 50);
	}

}
