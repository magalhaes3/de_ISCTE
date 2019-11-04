package de_ISCTE;

import java.awt.Color;
import java.awt.Graphics;

public class Dirt extends Ground{

	public Dirt(float x, float y, ID id) {
		super(x, y, id);
		spot = false;
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(new Color(212, 157, 74));
		g.fillRect((int)x, (int)y, Game.SLOT_SIZE, Game.SLOT_SIZE);
	}

}
