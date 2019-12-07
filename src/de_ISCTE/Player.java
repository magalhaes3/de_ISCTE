package de_ISCTE;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Player implements MouseListener, KeyListener{

	private int hp;
	private int maxHP;
	private int points;
	private boolean insertMode = false;
	
	public Player(int hp) {
		this.hp = hp;
		this.points = 0;
		this.maxHP = hp;
	}
	
	
	//Função que permite buscar
	public GameObject getTile() {
		GameObject go = null;
		double mX = Math.floor(Game.getInstance().getMouseLocation().getX()/GameObject.SIZE);
		double mY = Math.floor(Game.getInstance().getMouseLocation().getY()/GameObject.SIZE);
		for(GameObject temp : Game.getInstance().getGameObjects()) {
			if(Math.floor(temp.getX()/GameObject.SIZE) == mX && Math.floor(temp.getY()/GameObject.SIZE) == mY) {
				if((go != null) && (go.getId() == ID.Enemy || go.getId() == ID.Turret))
					break;
				else
					go = temp;
			}
		}
		//System.out.println(aux.size() + " " + Game.getInstance().getGameObjects().size());
		
		if(go != null)
			return go;
		return null;
	}


	public void addInput() {
		Game.getInstance().addMouseListener(this);
		Game.getInstance().addKeyListener(this);

	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		System.out.println(getTile());
		
	}


	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_S);
			System.out.println("OIOI");
		
	}


	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public int getPoints() {
		return points;
	}
	
	public int getHP() {
		return hp;
	}
	
	public int getMaxHP() {
		return maxHP;
	}
	
}
