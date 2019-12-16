package de_ISCTE;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

import enemies.Enemy;

public class Wave {

	private float timeSinceLastSpawn, spawnTime;
	private LinkedList<Enemy> enemyInfo;
	private LinkedList<Enemy> enemyList;
	private LinkedList<Enemy> toRemove = new LinkedList<Enemy>();
	private int id;
	private int totalEnemies;
	private int aliveEnemies;
	private Map map;
	private int waveReward;
	private int enemiesUntilReward;
	private boolean finished = false;
	
	public Wave(float spawnTime, int id, Map map) {
		this.map = map;
		this.spawnTime = spawnTime;
		this.id = id;
		timeSinceLastSpawn = 0;
		enemyList = new LinkedList<Enemy>();
		enemyInfo = new LinkedList<Enemy>();
	}
	
	public void setup() {
		totalEnemies = enemyInfo.size();
		waveReward = totalEnemies * 50;
		enemiesUntilReward = generateEnemiesUntilReward();
		System.out.println(enemiesUntilReward);
		aliveEnemies = totalEnemies;
	}
	
	private int generateEnemiesUntilReward() {
		Random r = new Random();
		int result = -1;
		while(true) {
			double d = r.nextDouble();
			result = (int)(Math.log(d)/Math.log(0.95));
			if(result > 0 && result < totalEnemies)
				break;
		}
		return result;
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
			System.out.println("Spawned enemy, climate is " + map.getClimate().toString());
			e.setMaxHP((int)(e.getHP() * map.getClimate().getHPMultiplier()));
			e.setVel(e.getVel() * map.getClimate().getSpeedMultiplier());
			enemyList.add(e);
			Game.getInstance().addObject(e);
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
		enemiesUntilReward--;
		if(enemiesUntilReward == 0)
			Game.getInstance().getPlayer().beRewarded(waveReward);
	}
	
	public int getTotalEnemies() {
		return totalEnemies;
	}
	
	public int getWaveID() {
		return id;
	}
	
	public Map getMap() {
		return map;
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
