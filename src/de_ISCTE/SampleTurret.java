package de_ISCTE;

import java.awt.Color;
import java.awt.Graphics;

public class SampleTurret extends GameObject {

	public SampleTurret(float x, float y, ID id) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(new Color(255, 255, 255, 125));
		g.fillOval((int)x, (int)y, 30, 30);
	}

}
