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

    //mesma coisa aqui
    public static int getPrice(String type) {
    	if(type.equals("Canon"))
    		return Canon.PRICE;
    	if(type.equals("FastTurret"))
    		return FastTurret.PRICE;
    	if(type.equals("LaserGun"))
    		return LaserGun.PRICE;
    	if(type.equals("Tank"))
    		return Tank.PRICE;
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
    
    public static Machine create(String type, int x, int y, int range) {
    	if(type.equals("Canon"))
    		return new Canon(x, y);
    	if(type.equals("FastTurret"))
    		return new FastTurret(x, y);
    	if(type.equals("LaserGun"))
    		return new LaserGun(x, y);
    	if(type.equals("Tank"))
    		return new Tank(x, y);
    	return null;
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
    
    public void setImage(String classe) {
    	try {
			image = ImageIO.read(new File("textures/" + classe + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public BufferedImage getImage() {
    	return image;
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
//    			Se o inimigo estiver j� fora de range mas j� esteve em range tem de entrar aqui 
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
