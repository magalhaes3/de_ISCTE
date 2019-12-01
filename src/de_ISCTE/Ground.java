package de_ISCTE;

import java.awt.image.BufferedImage;

public abstract class Ground extends GameObject{
	
	protected boolean spot;
	protected BufferedImage image;
	protected float width;
	protected float height;

	public Ground(float x, float y, float width, float height, ID id) {
		super(x, y, id);
		this.width = width;
		this.height = height;
		
	}
	
	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public boolean getSpot() {
		return spot;
	}

}
