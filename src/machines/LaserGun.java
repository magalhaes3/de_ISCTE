package machines;

import java.awt.Color;
import java.awt.Graphics;

import de_ISCTE.GameObject;
import de_ISCTE.ID;

public class LaserGun extends Machine {
	
	public static final int RANGE = 7;
	public static final int DAMAGE = 8;
	public static final int FIRERATE = 5;
	
	public static final int PRICE = 1000;
	
	public LaserGun(float x, float y) {
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
