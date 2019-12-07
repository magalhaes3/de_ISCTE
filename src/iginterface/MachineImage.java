package iginterface;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;

import de_ISCTE.GameObject;

public class MachineImage extends JLabel {
	
	private BufferedImage img;
	
	public MachineImage(BufferedImage img) {
		setPreferredSize(new Dimension(GameObject.SIZE, GameObject.SIZE));
		this.img = img;
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, GameObject.SIZE, GameObject.SIZE, null);
	}

}
