package Enemies;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import de_ISCTE.ID;

public class Pentagon extends Enemy{

	public Pentagon(int x, int y, float hp, float vel, ID id) {
		super(x, y, hp, vel, id);
		try {
			super.image = ImageIO.read(new File("textures/Pentagon.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.setXY(x, y);
	}

}
