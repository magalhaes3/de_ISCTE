package enemies;

import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import de_ISCTE.Game;
import de_ISCTE.GameObject;
import de_ISCTE.ID;

public abstract class Enemy extends GameObject {

	protected Point2D.Float target;
	protected LinkedList<Point2D.Float> path;
	protected BufferedImage image;
	protected float vel;
	protected float hp;
	protected int reward;
	private HealthBar hpbar;
	
	
	public Enemy(float x, int y, float hp, float vel) {
		super(x, y, ID.Enemy);
		this.hp = hp;
		this.vel = vel;
		hpbar = new HealthBar(this,x,y);
	}
	
	//Meter ifs para todo o tipo de inimigos
	public static Enemy create(int x, int y, ID id, String type) {
		if(type.equals("Thinny"))
			return new Thinny(x, y, generateHP(type), generateVel(type));
		if(type.equals("Cool"))
			return new Cool(x, y, generateHP(type), generateVel(type));
		if(type.equals("Fatso"))
			return new Fatso(x, y, generateHP(type), generateVel(type));
		return null;
	}
	
	//Gerar vida inimigos
	//Mudar para private, public apenas para testes
	public static float generateHP(String type) {
		int mean = 0;
		int stdDev = 0;
		if(type.equals("Thinny")) {
			mean = 100;
			stdDev = 10;
		}
		if(type.equals("Cool")) {
			mean = 150;
			stdDev = 10;
		}
		if(type.equals("Fatso")) {
			mean = 200;
			stdDev = 10;
		}
		
		double u = 0;
		double result = 0;
		while(true) {
			double u1 = Math.random()*((1 - (-1) )) + (-1);
			double u2 = Math.random()*((1 - (-1) )) + (-1);
			u = u1*u1 + u2*u2;
			result = mean + stdDev*u1*Math.sqrt(-2 * Math.log(u)/u);
			if(u < 1 && result > 50) 
				break;
		}
		return (float) result;	
	}
	
	//Gerar velocidade inimigos
	//Mudar para private, public apenas para testes
		public static float generateVel(String type) {
			float mean = 0;
			float stdDev = 0;
			if(type.equals("Thinny")) {
				mean = 4;
				stdDev = 0.2f;
			}
			if(type.equals("Cool")) {
				mean = 2;
				stdDev = 0.5f;
			}
			if(type.equals("Fatso")) {
				mean = 1;
				stdDev = 0.2f;
			}
			
			double u = 0;
			double result = 0;
			while(true) {
				double u1 = Math.random()*((1 - (-1) )) + (-1);
				double u2 = Math.random()*((1 - (-1) )) + (-1);
				u = u1*u1 + u2*u2;
				result = mean + stdDev*u1*Math.sqrt(-2 * Math.log(u)/u);
				if(u < 1 && result >= 0.2f)  // garantir que n�o tem velocidade negativa ou que � excessivamente lento
					break;
			}
			return (float) result;	
		}
	
	public float getHP() {
		return hp;
	}
	
	public void setHP(float value) {
		hp = value;
	}
	
	public float getVel() {
		return vel;
	}
	
	public void setVel(float value) {
		vel = value;
	}
	
	public int getRemainingDistance() {
		return path.size() - path.indexOf(target);
	}
	
	public void setMaxHP(int hp) {
		hpbar.setMaxHP(hp);
		this.hp = hp;
	}
	
	//Mudar movimento
	@Override
	public void tick() {
		if(path == null) {
			path = Game.getInstance().getCurrentMap().points;
			target = path.getFirst();
		}
		// pertence na classe enemy
		//sSystem.out.println("X: " + x + ", Y: " + y);
		if(target.x == this.getX() && target.y == this.getY()) {
			if(target != path.getLast()) {
				target = path.get(path.indexOf(target) + 1);
			}
		}
		else {
			float tempx = x - target.x;
			float tempy = y - target.y;
			
			if(tempx < 0) {
				if( Math.abs(tempx) < vel)
					x = target.x;
				else
					x += vel;
			}
			else if ( tempx > 0) {
				if( tempx < vel)
					x = target.x;
				else
					x -= vel;
			}
			if( tempy < 0) {
				if( Math.abs(tempy) < vel)
					y = target.y;
				else
					y += vel;
			}
			else if ( tempy > 0) {
				if( tempy < vel)
					y = target.y;
				else
					y -= vel;
			}
		}
		hpbar.tick();
		if(target == path.getLast() && target.x == this.getX() && target.y == this.getY()) {
			Game.getInstance().removeObject(this);
			Game.getInstance().getPlayer().hurt(1);
			Game.getInstance().getCurrentWave().enemyDied();
		}
			
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(image, (int)x - SIZE/2, (int)y - SIZE/2, SIZE, SIZE, null);
		hpbar.render(g);
	}
	
    public int getReward() {
    	return reward;
    }
	
}
