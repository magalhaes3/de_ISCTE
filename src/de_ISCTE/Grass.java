package de_ISCTE;

import java.awt.Color;
import java.awt.Graphics;

public class Grass extends Ground{

	public Grass(float x, float y, ID id) {
		super(x, y, id);
		spot = true;
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(new Color(0,123,12));
		g.fillRect((int)x, (int)y, Map.SLOT_SIZE, Map.SLOT_SIZE);
	}

}