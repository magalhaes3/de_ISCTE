package enemies;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import de_ISCTE.ID;

public class Thinny extends Enemy{
	
	public Thinny(int x, int y, float hp, float vel) {
		super(x, y, hp, vel);
		try {
			super.image = ImageIO.read(new File("textures/Thinny1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.setXY(x, y);		
	}

}
