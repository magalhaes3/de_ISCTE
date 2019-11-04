package de_ISCTE;

public abstract class Ground extends GameObject{
	
	protected boolean spot;

	public Ground(float x, float y, ID id) {
		super(x, y, id);
		
	}
	
	public boolean getSpot() {
		return spot;
	}

}
