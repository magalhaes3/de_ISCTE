package machines;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;

import Enemies.Enemy;
import de_ISCTE.Game;
import de_ISCTE.GameObject;
import de_ISCTE.ID;

public class Machine extends GameObject {

    private int range;
    private BufferedImage image;

    public Machine(float x, float y, ID id, int range) {
        super(x, y, id);
        this.range = range;
    }

    

    @Override
    public void tick() {
        LinkedList<Enemy> enemiesList = getEnemiesInRange();
        if(!enemiesList.isEmpty()) {
        	Enemy toShoot = enemiesList.getFirst();
        	toShoot.setHP(toShoot.getHP() - 1);
        }
    }
    
    private LinkedList<Enemy> getEnemiesInRange() {
    	LinkedList<Enemy> tempList = new LinkedList<>();
    	for(GameObject temp : Game.getInstance().getGameObjects()) {
    		if(enemyInRange(temp)) {
    			tempList.add((Enemy) temp);
    		}
    	}
    	return tempList;
    }
    
    private boolean enemyInRange(GameObject obj) {
    	if(obj == this || obj.getId() != ID.Enemy) {
    		return false;
    	}
    	if(obj.getX() > this.getX() - this.range && obj.getX() < this.getX() + this.range && obj.getY() > this.getY() - this.range && obj.getY() < this.getY() + this.range) {
			return true;
		} else {
			return false;
		}
    }

    @Override
    public void render(Graphics g) {
    	g.setColor(Color.white);
        g.fillOval((int) this.getX(), (int) this.getY(), 30, 30);
    }

}
