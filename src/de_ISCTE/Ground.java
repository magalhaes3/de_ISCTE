package de_ISCTE;

import java.awt.image.BufferedImage;

public abstract class Ground extends GameObject{
	
	protected boolean spot;
	protected BufferedImage image;

	public Ground(float x, float y, ID id) {
		super(x, y, id);
		
	}
	
	public boolean getSpot() {
		return spot;
	}

}
