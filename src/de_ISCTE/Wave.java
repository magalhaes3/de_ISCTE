package de_ISCTE;

import java.awt.Graphics;
import java.util.LinkedList;

import enemies.Enemy;
import machines.FastTurret;
import machines.Machine;
import machines.Tank;

public class Wave {

	private float timeSinceLastSpawn, spawnTime;
	private LinkedList<Enemy> enemyInfo;
	private LinkedList<Enemy> enemyList;
	private LinkedList<Enemy> toRemove = new LinkedList<Enemy>();
	private int id;
	private int totalEnemies;
	private int aliveEnemies;
	private boolean finished = false;
	
	public Wave(float spawnTime, int id) {
		this.spawnTime = spawnTime;
		this.id = id;
		timeSinceLastSpawn = 0;
		enemyList = new LinkedList<Enemy>();
		enemyInfo = new LinkedList<Enemy>();
	}
	
	public void setup() {
		totalEnemies = enemyInfo.size();
		aliveEnemies = totalEnemies;
	}
	
	public void render(Graphics g) {
		for(Enemy e : enemyList)
			e.render(g);
	}
	
	public void tick() {
		timeSinceLastSpawn += Clock.Delta();
		if(timeSinceLastSpawn > spawnTime) {
			spawn();
			timeSinceLastSpawn = 0;
		}
		for(Enemy e : enemyList) {
			if(e.getHP() <= 0) {
				toRemove.add(e);
				Game.getInstance().removeObject(e);
				Game.getInstance().getPlayer().beRewarded(e.getReward());
    			enemyDied();
			}
		}
		if(aliveEnemies == 0)
			setFinished();
		enemyList.removeAll(toRemove);
		toRemove.clear();
	}
	
	private void spawn() {
		if(!enemyInfo.isEmpty()) {
			Enemy e = enemyInfo.pop();
			enemyList.add(e);
			Game.getInstance().addObject(e);
			//System.out.println("Spawn!");
		}
		
	}
	
	public void setFinished() {
		finished = true;
	}
	
	public boolean isFinished() {
		return finished;
	}
	
	public int getAliveEnemies() {
		return aliveEnemies;
	}
	
	public void enemyDied() {
		aliveEnemies--;
	}
	
	public void enemyFinished() {
		aliveEnemies--;
	}
	
	public int getTotalEnemies() {
		return totalEnemies;
	}
	
	public int getWaveID() {
		return id;
	}
	
	public void setEnemies(LinkedList<Enemy> ee) {
		enemyInfo = ee;
	}
	
	public void addEnemyPassive(Enemy e) {
		enemyInfo.add(e);
	}
	
	public float getSpawnTime() {
		return spawnTime;
	}
	
	public LinkedList<Enemy> getEnemyInfo() {
		return enemyInfo;
	}
	
}
