package machines;

import java.awt.Color;
import java.awt.Graphics;

import de_ISCTE.GameObject;
import de_ISCTE.ID;

public class Tank extends Machine {
	
	private static final int RANGE = 10;
	private static final int DAMAGE = 10;
	private static final int FIRERATE = 43;
	
	public static final int PRICE = 500;
	
	public Tank(float x, float y) {
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
