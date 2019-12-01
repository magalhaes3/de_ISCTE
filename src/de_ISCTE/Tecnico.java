package de_ISCTE;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Tecnico extends Enemy{
	
	public Tecnico(int x, int y, float hp, ID id) {
		super(x, y, id);
		super.hp = hp;
		try {
			super.image = ImageIO.read(new File("Tecnico.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.setXY(x, y);
		
		super.velXY = 1; // fazer FDP
		
	}

}
