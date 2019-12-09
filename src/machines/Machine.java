package machines;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import Enemies.Enemy;
import de_ISCTE.Game;
import de_ISCTE.GameObject;
import de_ISCTE.ID;

public abstract class Machine extends GameObject {
	private BufferedImage image;
	
	private int range;
	private int damage;
	private int firerate;
	
	private int price;
	
	private LinkedList<Enemy> enemiesList;
    private LinkedList<Enemy> toRemove;

    public Machine(float x, float y, ID id) {
		super(x, y, id);
		enemiesList = getEnemiesInRange();
		toRemove = new LinkedList<>();
	}
    
    public int getDamage() {
    	return damage;
    }

	public void setDamage(int damage) {
		this.damage = damage;
	}
	
    public int getPrice() {
    	return price;
    }
    
    public void setPrice(int price) {
    	this.price = price;
    }
    
    public int getRange() {
    	return range;
    }
    
    public void setRange(int range) {
    	this.range = range * GameObject.SIZE;
    }
    
    public int getFirerate() {
    	return firerate;
    }
    
    public void setFirerate(int firerate) {
    	this.firerate = firerate;
    }
    
    int counter = 0;
    
    @Override
	public void tick() {
    	updateEnemiesList();
        if(counter == firerate) {
        	if(!enemiesList.isEmpty()) {
        		Enemy toShoot = getEnemyCloserToTarget();
        		toShoot.setHP(toShoot.getHP() - this.damage);
        	}
        	counter = 0;
        } else {
        	counter++;
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
    
    private void updateEnemiesList() {
    	for(GameObject temp : Game.getInstance().getGameObjects()) {
    		if(enemyInRange(temp)) {
    			if(!enemiesList.contains(temp)) {
    				enemiesList.add((Enemy) temp);
    			}
    		} else {
//    			Se o inimigo estiver já fora de range mas já esteve em range tem de entrar aqui 
//    			para sair da lista
    			if(enemiesList.contains(temp)) {
    				toRemove.add((Enemy) temp);
    			}
    		}
    	}
    	for(Enemy enemy : enemiesList) {
    		if(enemy.getHP() <= 0) {
    			toRemove.add(enemy);
    		}
    	}
    }
    
    private boolean enemyInRange(GameObject obj) {
    	if(obj.getId() != ID.Enemy || obj.getX() < 0 || obj.getY() < 0 || obj.getX() >= Game.WIDTH || obj.getY() >= Game.HEIGHT) {
    		return false;
    	}
    	if(obj.getX() > x - range && obj.getX() < x + range && obj.getY() > y - range && obj.getY() < y + range) {
    		return true;
		} else {
			return false;
		}
    }

    private Enemy getEnemyCloserToTarget() {
    	Enemy toReturn = enemiesList.getFirst();
    	for(Enemy ex : enemiesList) {
    		if(ex.getRemainingDistance() < toReturn.getRemainingDistance()) {
    			toReturn = ex;
    		}
    	}
    	return toReturn;
    }
    
    @Override
    public abstract void render(Graphics g);
}
