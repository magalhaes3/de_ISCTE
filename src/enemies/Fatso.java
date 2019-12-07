package enemies;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import de_ISCTE.ID;

public class Fatso extends Enemy{

	public Fatso(int x, int y, float hp, float vel) {
		super(x, y, hp, vel);
		try {
			super.image = ImageIO.read(new File("textures/Fatso1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.setXY(x, y);
	}

}
