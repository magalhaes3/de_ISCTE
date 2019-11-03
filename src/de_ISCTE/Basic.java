package de_ISCTE;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.util.LinkedList;

public class Basic extends Enemy{

	private LinkedList<Point2D.Float> path;
	private Point2D.Float target;
	private Game game; //retirar daqui

	public Basic(int x, int y, ID id, Game game) {
		super(x, y, id);

		this.game = game;
		path = game.getCurrentMap().points;
		target = path.getFirst();
		velXY = 5;
	}

	@Override
	public void tick() {
		// devia estar na classe enemy mais geral
		if(x+15 != path.getLast().getX() || y+15 != path.getLast().getY()) {
			
			double tempx = x+15 - target.getX();
			double tempy = y+15 - target.getY();
			
			if(tempx < 0) {
				if( Math.abs(tempx) < velXY)
					x = (float) target.getX()-15;
				else
					x += velXY;
			}
			else if ( tempx > 0) {
				if( tempx < velXY)
					x = (float) target.getX()+15;
				else
					x -= velXY;
			}
			if( tempy < 0) {
				if( Math.abs(tempy) < velXY)
					y = (float) target.getY()+15;
				else
					y += velXY;
			}
			else if ( tempy > 0) {
				if( tempy < velXY)
					y = (float) target.getY()-15;
				else
					y -= velXY;
			}
			
			if(x+15 == target.getX() && y+15 == target.getY() && !target.equals(path.getLast()))
				target = path.get(path.indexOf(target)+1);
		}
	}	

	@Override
	public void render(Graphics g) {
		g.setColor(new Color(255, 255, 255, 125));
		g.fillOval((int)x, (int)y, 30, 30);
	}

}
