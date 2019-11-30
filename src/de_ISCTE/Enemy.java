package de_ISCTE;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public abstract class Enemy extends GameObject {

	protected LinkedList<Point2D.Float> path;
	protected float velXY;
	protected BufferedImage image;
	
	public Enemy(int x, int y, ID id) {
		super(x, y, id);
	}
	
	public float getVelXY() {
		return velXY;
	}
	
	public void setVelXY(float value) {
		velXY = value;
	}
	
}
