package de_ISCTE;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable{

	public static int WIDTH = Map.SLOT_SIZE*Map.V_SLOTS + 6; //986, slots*n colunas + parte da janela
	public static int HEIGHT = Map.SLOT_SIZE*Map.H_SLOTS + 29; //715 slots*n linhas + parte da janela
	public String title = "Defend de_ISCTE";
	
	private Thread thread;
	private boolean isRunning = false;
	
	private LinkedList<GameObject> gameObjects = new LinkedList<GameObject>();
	private Map currentMap;
	
	private int currentLevel;
	
//	Privado porque s� usado internamente para debugging
	private Game() {
		
		new Window(WIDTH, HEIGHT, title, this);
		start();
		
		init();
	//INSTANCE s� deixa de ser null quando o construtor termina 
	}
	
	public Game(JFrame frame) {
		new Window(frame, this);
		start();
		
		init();
		
//		handler.addObject(new Basic(50-15,50-15,ID.Enemy, this)); //retirar this e ver a subtra�o
	}
	private synchronized void start() {
		if(isRunning) 
			return;
		
		this.currentLevel = 1;
		thread = new Thread(this);
		thread.start();
		isRunning = true;
	}
	
	private void init() {
		//TODO inserir aqui um m�todo para escolher o path do mapa
//		loadMap("./maps/level3/IGOT.txt");
		loadMap(chooseMap());
	}
	
	private String chooseMap() {
		String path = "";
		int random;
		if(currentLevel == 1) {
			random = new Random().nextInt(Map.level1maps.length);
			path = Map.level1maps[random];
		}	
		if(currentLevel == 2) {
			random = new Random().nextInt(Map.level2maps.length);
			path = Map.level2maps[random];
		}
		if(currentLevel == 3) {
			random = new Random().nextInt(Map.level3maps.length);
			path = Map.level3maps[random];
		}
		return path;
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
		for(GameObject tempObject : gameObjects) {
			tempObject.tick();
		}
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
		
		
		renderGameObjects(g);
		////////////////////////////////////
		bs.show();
		g.dispose();
		
	}
	
	private void renderGameObjects(Graphics g) {
		for(GameObject tempObject : gameObjects) {
			tempObject.render(g);
		}
	}
	
	public Map getCurrentMap() {
		return currentMap;
	}
	
	public void addObject(GameObject tempObject) {
		gameObjects.add(tempObject);
	}
	
	public void removeObject(GameObject tempObject) {
		gameObjects.remove(tempObject);
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
				currentMap = aux;
				
				currentMap.drawPath();
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
