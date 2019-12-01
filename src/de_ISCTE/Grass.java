package de_ISCTE;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Grass extends Ground{

	public Grass(float x, float y, float width, float height, ID id) {
		super(x, y, width, height, id);
		try {
			image = ImageIO.read(new File("Grass.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		spot = true;
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		//g.setColor(new Color(0,123,12));
		//g.fillRect((int)x, (int)y, Map.SLOT_SIZE, Map.SLOT_SIZE);
		g.drawImage(image, (int)x, (int)y, Map.SLOT_SIZE, Map.SLOT_SIZE, null);
	}

}