package de_ISCTE;

public abstract class Enemy extends GameObject {

	//protected float velX;
	//protected float velY;
	protected float velXY;
	
	public Enemy(int x, int y, ID id) {
		super(x, y, id);
	}
	
	public float getVelXY() {
		return velXY;
	}
	
	public void setVelXY(float value) {
		velXY = value;
	}
	
	/*public float getVelX() {
		return velX;
	}

	public void setVelX(int velX) {
		this.velX = velX;
	}

	public float getVelY() {
		return velY;
	}

	public void setVelY(int velY) {
		this.velY = velY;
	}*/

}
