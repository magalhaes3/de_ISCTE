package de_ISCTE;

import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Tecnico extends Enemy{
	
	private static final int SIZE = 40;
	private Point2D.Float target;

	public Tecnico(int x, int y, ID id, Game game) {
		super(x, y, id);
		try {
			super.image = ImageIO.read(new File("Tecnico.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		super.path = game.getCurrentMap().points;
		target = path.getFirst();
		this.setXY(target.x, target.y);
		
		
		
		super.velXY = 1; // fazer FDP
		
	}

	@Override
	public void tick() {
		
		// pertence na classe enemy
		System.out.println("X: " + x + ", Y: " + y);
		if(target.x == this.getX() && target.y == this.getY()) {
			if(target != path.getLast()) {
				target = path.get(path.indexOf(target) + 1);
			}
		}
		else {
			float tempx = x - target.x;
			float tempy = y - target.y;
			
			if(tempx < 0) {
				if( Math.abs(tempx) < velXY)
					x = target.x;
				else
					x += velXY;
			}
			else if ( tempx > 0) {
				if( tempx < velXY)
					x = target.x;
				else
					x -= velXY;
			}
			if( tempy < 0) {
				if( Math.abs(tempy) < velXY)
					y = target.y;
				else
					y += velXY;
			}
			else if ( tempy > 0) {
				if( tempy < velXY)
					y = target.y;
				else
					y -= velXY;
			}
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(image, (int)x - SIZE/2, (int)y - SIZE/2, SIZE, SIZE, null);
	}

}
