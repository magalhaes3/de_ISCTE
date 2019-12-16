package de_ISCTE;

public enum Climate {
	SUN(1, 1), // normal behavior
	RAIN(1, 1.2f), // enemies are faster
	SNOW(1, 0.8f), // enemies are slow
	HAIL(0.75f, 1); // enemies have less hp
	
	private float hpMultiplier;
	private float speedMultiplier;

	private Climate(float hpMultiplier, float speedMultiplier) {
		this.hpMultiplier = hpMultiplier;
		this.speedMultiplier = speedMultiplier;
	}
	
	public float getHPMultiplier() {
		return hpMultiplier;
	}
	
	public float getSpeedMultiplier() {
		return speedMultiplier;
	}
	
	public static Climate getClimate(double r) {
		if( r >= 0 && r < 0.60) {
			return SUN;
		} else if(r >= 0.60 && r < 0.80) {
			return RAIN;
		} else if(r >= 0.80 && r < 0.95) {
			return SNOW;
		} else {//if( r >= 0.95 && r <= 1) 
			return HAIL;
		}
			
	}
}
