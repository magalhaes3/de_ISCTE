package de_ISCTE;

import java.awt.Graphics;
import java.util.LinkedList;

public class Map {

	private String title;
	private Ground[][] map;
	public LinkedList<Ground> points = new LinkedList<Ground>();
	
	public Map(String title) {
		this.title = title;
		map = new Ground[14][20];
		for(int i = 0; i < map.length; i++)
			for(int j = 0; j < map[i].length; j++)
				map[i][j] = new Grass(Game.SLOT_SIZE*j, Game.SLOT_SIZE*i, ID.Grass);
	}
	
	public void addPoint(Ground g) {
		points.add(g);
		
	}
	
	public void drawPath() {
		Ground aux1 = points.getFirst();
		int c1 = (int)(aux1.getX()/48);
		int l1 = (int)(aux1.getY()/48);
		Ground aux2 = points.get((points.indexOf(aux1)+1));
		int c2 = (int)(aux2.getX()/48);
		int l2 = (int)(aux2.getY()/48);
		while(true) {
			if(l1 != l2 && c1 == c2) {
				while(l1 != l2) {
					map[l1][c1] = new Dirt(Game.SLOT_SIZE*c1,Game.SLOT_SIZE*l1, ID.Dirt);
					if(l1 < l2)
						l1++;
					else
						l1--;
				}
			}
			else	
				if(l1 == l2 && c1 != c2) {
					while(c1 != c2) {
						map[l1][c1] = new Dirt(Game.SLOT_SIZE*c1,Game.SLOT_SIZE*l1, ID.Dirt);
						if(c1 < c2)
							c1++;
						else
							c1--;
					}
				}
			
			aux1 = aux2;
			c1 = (int)(aux1.getX()/48);
			l1 = (int)(aux1.getY()/48);
			if(aux2 != points.getLast()) {
				aux2 = points.get((points.indexOf(aux2)+1));
				c2 = (int)(aux2.getX()/48);
				l2 = (int)(aux2.getY()/48);
			}
			else {
				map[l2][c2] = new Dirt(Game.SLOT_SIZE*c2,Game.SLOT_SIZE*l2, ID.Dirt);
				break;
			}
		}
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
	}
}
