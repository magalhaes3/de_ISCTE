package de_ISCTE;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.geom.Point2D;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

import enemies.Cool;
import enemies.Enemy;
import enemies.Fatso;
import enemies.Thinny;
import iginterface.InGameInterface;
import iginterface.InterfaceUpdater;
import machines.Machine;
import machines.FastTurret;
import machines.Machine;
import machines.Tank;

public class Game extends Canvas implements Runnable{
	
	public static int WIDTH = GameObject.SIZE*Map.V_SLOTS + 6; //986, slots*n colunas + parte da janela
	public static int HEIGHT = GameObject.SIZE*Map.H_SLOTS + 29; //715 slots*n linhas + parte da janela
	public String title = "Defend de_ISCTE";
	
	private Thread thread;
	private boolean isRunning = false;
	
	private Player player = new Player(30);
	
	private LinkedList<GameObject> gameObjects = new LinkedList<GameObject>();
	private LinkedList<GameObject> objectsToAdd = new LinkedList<GameObject>();
	private LinkedList<GameObject> objectsToRemove = new LinkedList<GameObject>();
	
	private Map currentMap;
	private Wave currentWave;
	
	private int currentLevel;
	
	
	private InterfaceUpdater iu;
	private static final Game GAME = new Game();
	
//	Privado porque s� usado internamente para debugging
	private Game() {
		
		InGameInterface igi = new InGameInterface();
		iu = new InterfaceUpdater(igi);
		new Window(WIDTH, HEIGHT, title, this, igi);
		start();
		
		init();
		//INSTANCE s� deixa de ser null quando o construtor termina 
	}
	
	/*
	public Game(JFrame frame) {
		new Window(frame, this);
		start();
		
		init();
	}
	*/
	
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
		nextWave();		
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
		System.out.println(path);
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
		addPlayerInput();
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
			Clock.update();
			render();
			frames++;
					
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: " + frames + " TICKS: " + updates);
				//System.out.println(player.getTile());
				frames = 0;
				updates = 0;
			}
		}
		stop();
	}
	
	public void tick() {
		//update game
		if(currentWave != null) {
			currentWave.tick();
		}
		for(GameObject tempObject : gameObjects) {
			tempObject.tick();			
		}
		iu.tick();
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
		
//		if(currentMap != null) {
//			currentMap.render(g);		
//			if(currentWave != null) 
//				currentWave.render(g);
//		}
		//---------------------
		
		
		renderGameObjects(g);
		if(player.getInsertMode()) 
			player.render(g);
		////////////////////////////////////
		bs.show();
		g.dispose();
		
	}
	
	private void renderGameObjects(Graphics g) {
		for(GameObject tempObject : gameObjects) {
			tempObject.render(g);
		}
		gameObjects.addAll(objectsToAdd);
		objectsToAdd.clear();
		gameObjects.removeAll(objectsToRemove);
		objectsToRemove.clear();
	}
	
	public Map getCurrentMap() {
		return currentMap;
	}
	
	public Wave getCurrentWave() {
		return currentWave;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public LinkedList<GameObject> getGameObjects() {
		return gameObjects;
	}
	
	public void addObject(GameObject tempObject) {
		objectsToAdd.add(tempObject);		
	}
	
	public void removeObject(GameObject tempObject) {
		objectsToRemove.add(tempObject);
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
					Float x = Float.parseFloat(args[0]);
					Float y = Float.parseFloat(args[1]);
					aux.addPoint(new Point2D.Float(x,y));
					line = sc.nextLine();
				}
				//ler waves
				
				
				while(sc.hasNextLine() && line != "endwaves") {
					line = sc.nextLine();
					float spawnTime = Float.parseFloat(line);
					line = sc.nextLine();
					int id = Integer.parseInt(line);
					Wave w = new Wave(spawnTime, id);
					while(sc.hasNextLine() && line != "endwave") {
						line = sc.nextLine();
						String[] args = line.split(" ");
						String classe = args[0];
						String[] coord = args[1].substring(1, args[1].length() - 1).split(",");
						float x = Float.parseFloat(coord[0]);
						float y = Float.parseFloat(coord[1]);
						w.addEnemyPassive(Enemy.create((int)x, (int)y, ID.Enemy, classe));
					}
					w.setup();
					aux.addWave(w);
				}
				
				//Descomentar c�digo para testar waves
				
				Wave w1 = new Wave(1000, 1);
				w1.addEnemyPassive(new Thinny((int)aux.getStartPoint().x,(int)aux.getStartPoint().y, Enemy.generateHP("Thinny"), Enemy.generateVel("Thinny")));
				w1.addEnemyPassive(new Cool((int)aux.getStartPoint().x,(int)aux.getStartPoint().y, Enemy.generateHP("Cool"), Enemy.generateVel("Cool")));
				w1.addEnemyPassive(new Fatso((int)aux.getStartPoint().x,(int)aux.getStartPoint().y, Enemy.generateHP("Fatso"), Enemy.generateVel("Fatso")));
				w1.addEnemyPassive(new Cool((int)aux.getStartPoint().x,(int)aux.getStartPoint().y, Enemy.generateHP("Cool"), Enemy.generateVel("Cool")));
				w1.addEnemyPassive(new Thinny((int)aux.getStartPoint().x,(int)aux.getStartPoint().y, Enemy.generateHP("Thinny"), Enemy.generateVel("Thinny")));
				w1.addEnemyPassive(new Fatso((int)aux.getStartPoint().x,(int)aux.getStartPoint().y, Enemy.generateHP("Fatso"), Enemy.generateVel("Fatso")));
				w1.addEnemyPassive(new Fatso((int)aux.getStartPoint().x,(int)aux.getStartPoint().y, Enemy.generateHP("Fatso"), Enemy.generateVel("Fatso")));
				w1.setup();
				aux.addWave(w1);
				
				
				currentMap = aux;
				
				currentMap.drawPath();
				addMapToGame();
			}
			//else se estiver vazio
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
	
	
	//adicionar grid � lista GameObjects para ser updated
	private void addMapToGame() {
		for(int i = 0; i < currentMap.getGrid().length; i++)
			for(int j = 0; j < currentMap.getGrid()[i].length; j++) 
				addObject(currentMap.getGrid()[i][j]);
	}
	
	
	private void nextWave() {
		currentWave = currentMap.getNextWave(currentWave);
	}
	
	public static Game getInstance() {
		return GAME;
	}
	
	public Point2D getMouseLocation() {
		double x = MouseInfo.getPointerInfo().getLocation().getX() - this.getLocationOnScreen().getX();
		double y = MouseInfo.getPointerInfo().getLocation().getY() - this.getLocationOnScreen().getY();
		Point2D result = new Point2D.Double(x,y);
		return result;
	}
	
	public void addPlayerInput() {
		player.addInput();
	}

	
	public static void main(String[] args) {
		//new Game();
		Machine teste = new FastTurret(200, 10);
		Machine teste2 = new Tank(250, 10);
		Game.getInstance().addObject(teste);
		Game.getInstance().addObject(teste2);
	}
	
	
}
