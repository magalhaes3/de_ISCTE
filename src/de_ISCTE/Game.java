package de_ISCTE;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Game extends Canvas implements Runnable{

	public static int WIDTH = Map.SLOT_SIZE*Map.V_SLOTS + 6; //986, slots*n colunas + parte da janela
	public static int HEIGHT = Map.SLOT_SIZE*Map.H_SLOTS + 29; //715 slots*n linhas + parte da janela
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
		//TODO inserir aqui um método para escolher o path do mapa
		loadMap("./maps/level2/map5.txt");
		//currentMap = new Map("Teste");
		/*
		currentMap.addPoint(currentMap.getMap()[0][1]);
		currentMap.addPoint(currentMap.getMap()[10][1]);
		currentMap.addPoint(currentMap.getMap()[10][7]);
		currentMap.addPoint(currentMap.getMap()[6][7]);
		currentMap.addPoint(currentMap.getMap()[6][16]);
		currentMap.addPoint(currentMap.getMap()[12][16]);
		currentMap.addPoint(currentMap.getMap()[12][19]);
		*/
		/*
		currentMap.addPoint(currentMap.getMap()[7][0]);
		currentMap.addPoint(currentMap.getMap()[7][9]);
		currentMap.addPoint(currentMap.getMap()[11][9]);
		currentMap.addPoint(currentMap.getMap()[11][14]);
		currentMap.addPoint(currentMap.getMap()[7][14]);
		currentMap.addPoint(currentMap.getMap()[7][19]);
		*/
		//currentMap.drawPath();
		//currentMap.exportMap();
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
		
		
		
		if(currentMap != null)
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
	
	private void loadMap(String path) {
		try {
			String title = "";
			String line = "";
			File file = new File(path);
			Scanner sc = new Scanner(file);
			if(sc.hasNextLine()) {
				title = sc.nextLine();
				line = sc.nextLine();
				Map aux = new Map(title);
				
				while(sc.hasNextLine() && line != "endpoints") {
					line = line.substring(1, line.length() - 1);
					String[] args = line.split(",");
					float x = Float.parseFloat(args[0]);
					float y = Float.parseFloat(args[1]);
					aux.addPoint(new Point2D.Float(x,y));
					line = sc.nextLine();
				}
				aux.drawPath();
								
				currentMap = aux;
			}
			//else se estiver vazio
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		new Game();
	}
	
}
