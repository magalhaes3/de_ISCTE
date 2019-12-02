package de_ISCTE;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Pentagon extends Enemy{

	public Pentagon(int x, int y, float hp, float vel, ID id) {
		super(x, y, hp, vel, id);
		try {
			super.image = ImageIO.read(new File("Pentagon.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.setXY(x, y);
	}

}
