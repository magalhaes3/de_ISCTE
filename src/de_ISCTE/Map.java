package de_ISCTE;

import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedList;

public class Map {
//alterar points para Points2D , começar o primeiro ponto fora do ecrã
//melhorar a função drawpath	
// criar função exportar, que escreve o mapa num ficheiro txt
	
	protected static int H_SLOTS = 14;
	protected static int V_SLOTS = 20;
	protected static int SLOT_SIZE = 49;
	
	private String title;
	private Ground[][] map;
	public LinkedList<Point2D.Float> points = new LinkedList<Point2D.Float>();
	
	public Map(String title) {
		this.title = title;
		map = new Ground[H_SLOTS][V_SLOTS];
		generateFullGrass();
	}
		
	public void addPoint(Ground g) {
		Point2D.Float aux = new Point2D.Float((int)(g.getX()+SLOT_SIZE/2), (int)(g.getY()+SLOT_SIZE/2));
		points.add(aux);
	}
	
	public void addPoint(Point2D.Float p) {
		points.add(p);
	}
	
	public void drawPath() {
		Point2D.Float seg_first = points.getFirst();
		Point2D.Float seg_last = points.get(points.indexOf(seg_first) + 1);
		while(seg_first != points.getLast()) { 
			int x1 = Math.min((int)(seg_first.x - SLOT_SIZE/2), (int)(seg_last.x - SLOT_SIZE/2));
			int y1 = Math.min((int)(seg_first.y - SLOT_SIZE/2), (int)(seg_last.y - SLOT_SIZE/2));
			int x2 = Math.max((int)(seg_first.x + SLOT_SIZE/2), (int)(seg_last.x + SLOT_SIZE/2));
			int y2 = Math.max((int)(seg_first.y + SLOT_SIZE/2), (int)(seg_last.y + SLOT_SIZE/2));
			for(int i = 0; i != map.length; i++) {
				for(int j = 0; j != map[0].length; j++) {
					if(map[i][j] instanceof Grass) {
						Point2D.Float aux = new Point2D.Float(map[i][j].x + SLOT_SIZE/2, map[i][j].y + SLOT_SIZE/2);
						if(aux.x > x1 && aux.x < x2 && aux.y > y1 && aux.y < y2)
							map[i][j] = new Dirt(SLOT_SIZE*j, SLOT_SIZE*i, ID.Dirt);
					}
				}
			}
			if(seg_last == points.getLast())
				break;
			seg_first = seg_last;
			seg_last = points.get(points.indexOf(seg_last) + 1);			
		}
	}
	
	private void generateFullGrass() {
		for(int i = 0; i < map.length; i++)
			for(int j = 0; j < map[i].length; j++)
				map[i][j] = new Grass(SLOT_SIZE*j, SLOT_SIZE*i, ID.Grass);	
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public void setMap(Ground[][] map) {
		this.map = map;
	}
	
	public Ground[][] getMap() {
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
			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
