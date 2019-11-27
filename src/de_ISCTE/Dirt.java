package de_ISCTE;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Dirt extends Ground {
	
	public Dirt(float x, float y, ID id) {
		super(x, y, id);
		/*try {
			image = ImageIO.read(new File("Dirt.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		spot = false;
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(new Color(212, 157, 74));
		g.fillRect((int)x, (int)y, Map.SLOT_SIZE, Map.SLOT_SIZE);
		//g.drawImage(image, (int)x, (int)y, Map.SLOT_SIZE, Map.SLOT_SIZE, null);
	}

}
