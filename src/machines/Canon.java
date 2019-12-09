package machines;

import java.awt.Color;
import java.awt.Graphics;
import de_ISCTE.ID;

public class Canon extends Machine {
	
	private static final int RANGE = 6;
	private static final int DAMAGE = 7;
	private static final int FIRERATE = 40;
	
	private static final int PRICE = 50;
	
	public Canon(float x, float y) {
		super(x, y, ID.Turret);
		this.setRange(RANGE);
		this.setDamage(DAMAGE);
		this.setFirerate(FIRERATE);
		this.setPrice(PRICE);
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLUE);
		g.drawRect((int)this.getX(), (int)this.getY(), 50, 50);
	}

}
