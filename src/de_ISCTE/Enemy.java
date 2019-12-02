package de_ISCTE;

import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public abstract class Enemy extends GameObject {

	protected Point2D.Float target;
	protected LinkedList<Point2D.Float> path;
	protected BufferedImage image;
	protected float velXY;
	protected float hp;
	
	public Enemy(float x, int y, float hp, float vel, ID id) {
		super(x, y, id);
		this.hp = hp;
		this.velXY = vel;
	}
	
	//Meter ifs para todo o tipo de inimigos
	public static Enemy create(int x, int y, ID id, String type) {
		if(type.equals("Tecnico"))
			return new Triangle(x, y, generateHP(type), generateVel(type), ID.Enemy);
		if(type.equals("Square"))
			return new Square(x, y, generateHP(type), generateVel(type), ID.Enemy);
		if(type.equals("Pentagon"))
			return new Pentagon(x, y, generateHP(type), generateVel(type), ID.Enemy);
		return null;
	}
	
	//Gerar vida inimigos
	//Mudar para private, public apenas para testes
	public static float generateHP(String type) {
		int mean = 0;
		int stdDev = 0;
		if(type.equals("Triangle")) {
			mean = 100;
			stdDev = 10;
		}
		if(type.equals("Square")) {
			mean = 150;
			stdDev = 10;
		}
		if(type.equals("Pentagon")) {
			mean = 200;
			stdDev = 10;
		}
		
		double u = 0;
		double result = 0;
		while(u < 1) {
			double u1 = Math.random()*((1 - (-1) )) + (-1);
			double u2 = Math.random()*((1 - (-1) )) + (-1);
			u = u1*u1 + u2*u2;
			result = mean + stdDev*u1*Math.sqrt(-2 * Math.log(u)/u);
		}
		return (float) result;	
	}
	
	//Gerar velocidade inimigos
	//Mudar para private, public apenas para testes
		public static float generateVel(String type) {
			float mean = 0;
			float stdDev = 0;
			if(type.equals("Triangle")) {
				mean = 4;
				stdDev = 0.2f;
			}
			if(type.equals("Square")) {
				mean = 2;
				stdDev = 0.5f;
			}
			if(type.equals("Pentagon")) {
				mean = 1;
				stdDev = 0.7f;
			}
			
			double u = 0;
			double result = 0;
			while(u < 1) {
				double u1 = Math.random()*((1 - (-1) )) + (-1);
				double u2 = Math.random()*((1 - (-1) )) + (-1);
				u = u1*u1 + u2*u2;
				result = mean + stdDev*u1*Math.sqrt(-2 * Math.log(u)/u);
			}
			return (float) result;	
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
	
	
	//Mudar movimento
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
