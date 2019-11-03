package de_ISCTE;

import java.awt.geom.Point2D;
import java.util.LinkedList;

public class Map {

	private String title;
	public LinkedList<Point2D.Float> points = new LinkedList<Point2D.Float>();
	
	public Map(String title) {
		this.title = title;
	}
	
	public void addPoint(float x, float y) {
		points.add(new Point2D.Float(x,y));
		
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
}
