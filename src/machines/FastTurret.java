package machines;

import java.awt.Color;
import java.awt.Graphics;

import de_ISCTE.GameObject;
import de_ISCTE.ID;

public class FastTurret extends Machine {
	
	private static final int RANGE = 4;
	private static final int DAMAGE = 4;
	private static final int FIRERATE = 13;
	
	public static final int PRICE = 100;
	
	public FastTurret(float x, float y) {
		super(x, y, ID.Turret);
		this.setRange(RANGE);
		this.setDamage(DAMAGE);
		this.setFirerate(FIRERATE);
		this.setPrice(PRICE);
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(getImage(getClass().getSimpleName()), (int) x, (int) y, GameObject.SIZE, GameObject.SIZE, null);
	}

}
