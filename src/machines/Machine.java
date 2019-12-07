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

    public Machine(float x, float y, ID id) {
        super(x, y, id);
    }
    
    public abstract int getDamage();

	public abstract void setDamage(int damage);
	
    public abstract int getPrice();
    
    public abstract int getRange();
    
    public abstract void setRange(int range);
    
    public abstract int getFirerate();
    
    public abstract void setFirerate(int firerate);
   
//    protected static LinkedList<Enemy> getEnemiesInRange(Machine machine) {
//    	LinkedList<Enemy> toReturn = new LinkedList<>();
//    	for(GameObject temp : Game.getInstance().getGameObjects()) {
//    		if(enemyInRange(temp, machine)) {
//    			toReturn.add((Enemy) temp);
//    		}
//    	}
//    	return toReturn;
//    }
//    
//    
//    protected static void updateEnemiesList(LinkedList<Enemy> enemiesList, LinkedList<Enemy> toRemove, Machine machine) {
//    	for(GameObject temp : Game.getInstance().getGameObjects()) {
//    		if(enemyInRange(temp, machine)) {
//    			if(!enemiesList.contains(temp)) {
//    				enemiesList.add((Enemy) temp);
//    			}
//    		} else {
////    			Se o inimigo estiver já fora de range mas já esteve em range tem de entrar aqui 
////    			para sair da lista
//    			if(enemiesList.contains(temp)) {
//    				toRemove.add((Enemy) temp);
//    			}
//    		}
//    	}
//    	for(Enemy enemy : enemiesList) {
//    		if(enemy.getHP() <= 0) {
//    			toRemove.add(enemy);
//    		}
//    	}
//    }
//    
//    private static boolean enemyInRange(GameObject obj, Machine machine) {
//    	float x = machine.getX();
//    	float y = machine.getY();
//    	int range = machine.getRange();
//    	
//    	System.out.println(obj + "	" + machine);
//    	
//    	if(obj.getId() != ID.Enemy || obj.getX() < 0 || obj.getY() < 0 || obj.getX() >= Game.WIDTH || obj.getY() >= Game.HEIGHT) {
//    		return false;
//    	}
//    	if(obj.getX() > x - range && obj.getX() < x + range && obj.getY() > y - range && obj.getY() < y + range) {
//    		return true;
//		} else {
//			return false;
//		}
//    }

    protected static Enemy getEnemyCloserToTarget(LinkedList<Enemy> enemiesList) {
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
