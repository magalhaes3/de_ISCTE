package de_ISCTE;

import java.awt.Graphics;
import java.util.LinkedList;

import Enemies.Enemy;

public class Wave {

	private float timeSinceLastSpawn, spawnTime;
	private LinkedList<Enemy> enemyInfo;
	private LinkedList<Enemy> enemyList;
	
	public Wave(float spawnTime) {
		this.spawnTime = spawnTime;
		timeSinceLastSpawn = 0;
		enemyList = new LinkedList<Enemy>();
		enemyInfo = new LinkedList<Enemy>();
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
	}
	
	private void spawn() {
		if(!enemyInfo.isEmpty()) {
			Enemy e = enemyInfo.pop();
			enemyList.add(e);
			Game.getInstance().addObject(e);
			//System.out.println("Spawn!");
		}
		
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
