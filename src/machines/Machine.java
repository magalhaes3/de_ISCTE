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
    private int damage;

    private LinkedList<Enemy> enemiesList = new LinkedList<>();
    private LinkedList<Enemy> toRemove = new LinkedList<Enemy>();

    public Machine(float x, float y, ID id, int range) {
        super(x, y, id);
        this.range = range * GameObject.SIZE;
        this.damage = 10;
    }
    
    public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}


	@Override
    public void tick() {
        updateEnemiesList();
        if(!enemiesList.isEmpty()) {
        	Enemy toShoot = getEnemyCloserToTarget();
        	toShoot.setHP(toShoot.getHP() - this.damage);
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
    	if(obj.getX() > this.getX() - this.range && obj.getX() < this.getX() + this.range && obj.getY() > this.getY() - this.range && obj.getY() < this.getY() + this.range) {
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
    public void render(Graphics g) {
    	g.setColor(Color.white);
        g.fillOval((int) this.getX(), (int) this.getY(), 30, 30);
    }

}
