package machines;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import de_ISCTE.Game;
import de_ISCTE.GameObject;
import de_ISCTE.ID;
import enemies.Enemy;

public class Machine extends GameObject {

    private int range;
    private BufferedImage image;
    private int cost = 1000;

    private LinkedList<Enemy> enemiesList = new LinkedList<>();
    private LinkedList<Enemy> toRemove = new LinkedList<Enemy>();
    
    public Machine(float x, float y, ID id, int range) {
        super(x, y, id);
        this.range = range;
    	try {
			image = ImageIO.read(new File("textures/Machine.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    
    public static int getPrice(String type) {
    	if(type.equals("Machine"))
    		return 1000;
    	return 0;
    }
    
    
    public static BufferedImage getImage(String type) {
    	BufferedImage image = null;
    	try {
			image = ImageIO.read(new File("textures/" + type + ".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return image;
    }
    
    
    @Override
    public void tick() {
        updateEnemiesList();
        if(!enemiesList.isEmpty()) {
        	Enemy toShoot = enemiesList.getFirst();
        	toShoot.setHP(toShoot.getHP() - 1);
        }
        enemiesList.removeAll(toRemove);
    }
   
    private LinkedList<Enemy> getEnemiesInRange() {
    	LinkedList<Enemy> toReturn = new LinkedList<>();
    	for(GameObject temp : Game.getInstance().getGameObjects()) {
    		if(enemyInRange(temp)) {
    			toReturn.add((Enemy) temp);
    		}
    	}
    	return toReturn;
    }
    
    boolean firstTimeLoading = true;
    
    private void updateEnemiesList() {
    	if(firstTimeLoading) {
    		enemiesList = getEnemiesInRange();
    		firstTimeLoading = false;
    	}
    	for(GameObject temp : Game.getInstance().getGameObjects()) {
    		if(enemyInRange(temp)) {
    			if(!enemiesList.contains(temp)) {
    				enemiesList.add((Enemy) temp);
    			}
    		} else {
    			if(enemiesList.contains(temp)) {
    				toRemove.add((Enemy) temp);
    			}
    		}
    	}
    	for(Enemy enemy : enemiesList) {
    		if(enemy.getHP() <= 0) {
    			toRemove.add(enemy);
    			Game.getInstance().getCurrentWave().enemyDied();
    		}
    	}
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
    	//g.setColor(Color.white);
        //g.fillOval((int) this.getX(), (int) this.getY(), 30, 30);
    	g.drawImage(image, (int)x, (int)y, SIZE, SIZE, null);
    }
    
    
    public int getCost() {
    	return cost;
    }
    
    public BufferedImage getImage() {
    	return image;
    }

}
