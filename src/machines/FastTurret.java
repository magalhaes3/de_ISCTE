package machines;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

import Enemies.Enemy;
import de_ISCTE.Game;
import de_ISCTE.GameObject;
import de_ISCTE.ID;

public class FastTurret extends Machine {
	
	private int range = 4;
	private int damage = 3;
	private int firerate = 15;
	
	private static final int price = 100;
	
	private LinkedList<Enemy> enemiesList;
    private LinkedList<Enemy> toRemove;

	public FastTurret(float x, float y) {
		super(x, y, ID.Turret);
		
		enemiesList = getEnemiesInRange();
		toRemove = new LinkedList<>();
	}

	@Override
	public int getDamage() {
		return damage;
	}

	@Override
	public void setDamage(int damage) {
		this.damage = damage;
	}

	@Override
	public int getPrice() {
		return price;
	}
	
//	@Override
//    public void tick() {
//        updateEnemiesList();
//        if(!enemiesList.isEmpty()) {
//        	Enemy toShoot = getEnemyCloserToTarget(enemiesList);
//        	toShoot.setHP(toShoot.getHP() - this.damage);
//        	System.out.println("SHOOTING");
//        }
//        enemiesList.removeAll(toRemove);
//    }

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawRect((int)this.getX(), (int)this.getY(), 50, 50);
	}

	@Override
	public int getRange() {
		return this.range;
	}

	@Override
	public void setRange(int range) {
		this.range = range;
	}

	@Override
	public int getFirerate() {
		return firerate;
	}

	@Override
	public void setFirerate(int firerate) {
		this.firerate = firerate;
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
