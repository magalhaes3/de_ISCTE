package de_ISCTE;

import java.awt.Graphics;
import java.util.Iterator;
import java.util.LinkedList;

public class Handler {
	
	public LinkedList<GameObject> object = new LinkedList<GameObject>();

	public void tick() {
		for(GameObject tempObject : object) {
			tempObject.tick();
		}
	}
	
	
	public void render(Graphics g) {
		//ConcurrentModificationException
		Iterator<GameObject> it = object.iterator();
		
		while(it.hasNext()) {
			GameObject temp = it.next();
			temp.render(g);
		}
	}
		
	public void addObject(GameObject tempObject) {
		object.add(tempObject);
	}
	
	public void removeObject(GameObject tempObject) {
		object.remove(tempObject);
	}
}
