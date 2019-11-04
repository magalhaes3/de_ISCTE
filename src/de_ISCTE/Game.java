package de_ISCTE;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable{

	public static int WIDTH = 966;
	public static int HEIGHT = 701;
	public static int SLOT_SIZE = 48;
	public String title = "Defend de_ISCTE";
	
	private Thread thread;
	private boolean isRunning = false;
	
	private Handler handler;
	private Map currentMap;
	
	
	public Game() {
		
		new Window(WIDTH, HEIGHT, title, this);
		start();
		
		init();
		//
		
		//handler.addObject(new Enemy(100,100,ID.Enemy));
		
		//handler.addObject(new Basic(50-15,50-15,ID.Enemy, this)); //retirar this e ver a subtraão
		
	}
	
	private void init() {
		handler = new Handler();
		currentMap = new Map("Teste");
		currentMap.addPoint(currentMap.getMap()[0][1]);
		currentMap.addPoint(currentMap.getMap()[10][1]);
		currentMap.addPoint(currentMap.getMap()[10][7]);
		currentMap.addPoint(currentMap.getMap()[6][7]);
		currentMap.addPoint(currentMap.getMap()[6][16]);
		currentMap.addPoint(currentMap.getMap()[12][16]);
		currentMap.addPoint(currentMap.getMap()[12][19]);
		currentMap.drawPath();
	}
	
	private synchronized void start() {
		if(isRunning) 
			return;

		thread = new Thread(this);
		thread.start();
		isRunning = true;
	}
	
	private synchronized void stop() {
		if(!isRunning)
			return;
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		isRunning = false;
	}
	
	@Override
	//gameloop
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while(isRunning){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
					
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;
			}
		}
		stop();
	}
	
	public void tick() {
		//update game
		handler.tick();
	}
	
	private void render() {
		//render game
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		////////////////////////////////////
		g.setColor(Color.WHITE);
		g.fillRect(0,0, WIDTH, HEIGHT);
		currentMap.render(g);		
		
		
		//---------------------
		
		
		handler.render(g);
		////////////////////////////////////
		bs.show();
		g.dispose();
		
	}
	
	public Map getCurrentMap() {
		return currentMap;
	}
	
	public static void main(String[] args) {
		new Game();
	}
	
}
