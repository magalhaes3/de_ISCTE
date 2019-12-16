package de_ISCTE;

import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.Random;

import enemies.Enemy;

public class Map {
	public static final String[] level1maps = {"./maps/level1/Avante.txt","./maps/level1/Cidade.txt", "./maps/level1/map1.txt"};
	public static final String[] level2maps = {"./maps/level2/Autonomo.txt", "./maps/level2/ICS.txt", "./maps/level2/Inside.txt"};
	public static final String[] level3maps = {"./maps/level3/Estacionamento.txt", "./maps/level3/IGOT.txt"};
	
	protected static int H_SLOTS = 14;
	protected static int V_SLOTS = 20;
	
	private Climate climate;
	private String title;
	private Ground[][] map;
	private LinkedList<Wave> waves = new LinkedList<Wave>();
	public LinkedList<Point2D.Float> points = new LinkedList<Point2D.Float>();
	
	public Map(String title) {
		this.title = title;
		map = new Ground[H_SLOTS][V_SLOTS];
		generateFullGrass();
	}
		
	public void addPoint(Ground g) {
		Point2D.Float aux = new Point2D.Float((int)(g.getX()+GameObject.SIZE/2), (int)(g.getY()+GameObject.SIZE/2));
		points.add(aux);
	}
	
	public void addPoint(Point2D.Float p) {
		points.add(p);
	}
	
	public void drawPath() {
		Point2D.Float seg_first = points.getFirst();
		Point2D.Float seg_last = points.get(points.indexOf(seg_first) + 1);
		while(seg_first != points.getLast()) { 
			int x1 = Math.min((int)(seg_first.x - GameObject.SIZE/2), (int)(seg_last.x - GameObject.SIZE/2));
			int y1 = Math.min((int)(seg_first.y - GameObject.SIZE/2), (int)(seg_last.y - GameObject.SIZE/2));
			int x2 = Math.max((int)(seg_first.x + GameObject.SIZE/2), (int)(seg_last.x + GameObject.SIZE/2));
			int y2 = Math.max((int)(seg_first.y + GameObject.SIZE/2), (int)(seg_last.y + GameObject.SIZE/2));
			for(int i = 0; i != map.length; i++) {
				for(int j = 0; j != map[0].length; j++) {
					if(map[i][j] instanceof Grass) {
						Point2D.Float aux = new Point2D.Float(map[i][j].x + GameObject.SIZE/2, map[i][j].y + GameObject.SIZE/2);
						if(aux.x > x1 && aux.x < x2 && aux.y > y1 && aux.y < y2)
							map[i][j] = new Dirt(GameObject.SIZE*j, GameObject.SIZE*i, GameObject.SIZE, GameObject.SIZE, ID.Dirt);
					}
				}
			}
			if(seg_last == points.getLast())
				break;
			seg_first = seg_last;
			seg_last = points.get(points.indexOf(seg_last) + 1);			
		}
		setClimate();
	}
	
	private void setClimate() {
		Random r = new Random();
		climate = Climate.getClimate(r.nextDouble());
	}
	
	private void generateFullGrass() {
		for(int i = 0; i < map.length; i++)
			for(int j = 0; j < map[i].length; j++)
				map[i][j] = new Grass(GameObject.SIZE*j, GameObject.SIZE*i, GameObject.SIZE, GameObject.SIZE, ID.Grass);	
	}
	
	public String getTitle() {
		return title;
	}
	
	public Climate getClimate() {
		return climate;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public void setGrid(Ground[][] map) {
		this.map = map;
	}
	
	public Ground[][] getGrid() {
		return map;
	}
	
	public void tick() {
		for(int i = 0; i < map.length; i++)
			for(int j = 0; j < map[i].length; j++)
				map[i][j].tick();
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < map.length; i++)
			for(int j = 0; j < map[i].length; j++)
				map[i][j].render(g);
		//pintar centro
		/*	
	  	g.setColor(Color.WHITE);
		for(Point2D.Float aux : points) {
			g.fillRect((int)(aux.x), (int)(aux.y), 1 ,1);
		}
		*/
	}
	
	//exportar mapa para ficheiro txt
	public void exportMap() {
		String filename = title + ".txt";
		try {
			PrintWriter writer = new PrintWriter(filename, "UTF-8");
			writer.println(title);
			for(Point2D.Float aux : points)
				writer.println("(" + aux.x + "," + aux.y + ")");
			writer.println("endpoints");
			for(Wave w : waves) {
				writer.println(w.getSpawnTime()); 
				writer.println(w.getWaveID());
				for(Enemy e : w.getEnemyInfo())
					writer.println(e.getClass().getSimpleName() + " (" + e.x + ", " + e.y + ") ");
				writer.println("endwave");
			}
			writer.println("endwaves");
			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public String toString() {
		String toReturn = "";
		for(Ground[] g : map) {
			for(Ground g1 : g) {
				toReturn += g1.getX() + " " + g1.getY() + "\n";
			}
		}
		return toReturn;
	}
	
	public void addWave(Wave w) {
		waves.add(w);
	}
	
	public Wave getNextWave(Wave current) {
		if(current == null)
			return waves.get(0);
		else {
			int index = waves.indexOf(current);
			if(index == waves.size() - 1)
				return null; //terminar mapa
			else
				return waves.get(waves.indexOf(current) + 1);
		}
	}
	
	public int getNumberOfWaves() {
		return waves.size();
	}
	
	public Point2D.Float getStartPoint() {
		return points.getFirst();
	}
}
