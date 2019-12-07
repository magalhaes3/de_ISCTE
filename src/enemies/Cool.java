package enemies;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import de_ISCTE.ID;

public class Cool extends Enemy{

	public Cool(int x, int y, float hp, float vel) {
		super(x, y, hp, vel);
		try {
			super.image = ImageIO.read(new File("textures/Cool1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.setXY(x, y);
	}
	
}
