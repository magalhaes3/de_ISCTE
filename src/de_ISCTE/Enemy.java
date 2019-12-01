package de_ISCTE;

import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public abstract class Enemy extends GameObject {

	protected static final int SIZE = 40;
	protected Point2D.Float target;
	protected LinkedList<Point2D.Float> path;
	protected BufferedImage image;
	protected float velXY;
	protected float hp;
	
	public Enemy(float x, int y, ID id) {
		super(x, y, id);
	}
	
	//Meter ifs para todo o tipo de inimigos
	public static Enemy create(int x, int y, ID id, String type) {
		if(type.equals("Tecnico"))
			return new Tecnico(x, y, generateHP(type), ID.Enemy);
		if(type.equals("Exemplo1"))
			return null;
		if(type.equals("Exemplo2"))
			return null;
		return null;
	}
	
	//Meter aqui FDP para gerar vida inimigos
	private static float generateHP(String type) {
		if(type.equals("Tecnico"))
			return 0;
		if(type.equals("Exemplo1"))
			return 0;
		if(type.equals("Exemplo2"))
			return 0;
		return 0;
	}
	
	public float getHP() {
		return hp;
	}
	
	public void setHP(float value) {
		hp = value;
	}
	
	public float getVelXY() {
		return velXY;
	}
	
	public void setVelXY(float value) {
		velXY = value;
	}
	
	
	
	@Override
	public void tick() {
		if(path == null) {
			path = Game.getInstance().getCurrentMap().points;
			target = path.getFirst();
		}
		// pertence na classe enemy
		//System.out.println("X: " + x + ", Y: " + y);
		if(target.x == this.getX() && target.y == this.getY()) {
			if(target != path.getLast()) {
				target = path.get(path.indexOf(target) + 1);
			}
		}
		else {
			float tempx = x - target.x;
			float tempy = y - target.y;
			
			if(tempx < 0) {
				if( Math.abs(tempx) < velXY)
					x = target.x;
				else
					x += velXY;
			}
			else if ( tempx > 0) {
				if( tempx < velXY)
					x = target.x;
				else
					x -= velXY;
			}
			if( tempy < 0) {
				if( Math.abs(tempy) < velXY)
					y = target.y;
				else
					y += velXY;
			}
			else if ( tempy > 0) {
				if( tempy < velXY)
					y = target.y;
				else
					y -= velXY;
			}
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(image, (int)x - SIZE/2, (int)y - SIZE/2, SIZE, SIZE, null);
	}
	
}
