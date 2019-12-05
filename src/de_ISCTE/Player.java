package de_ISCTE;

public class Player {

	private int hp;
	
	public Player(int hp) {
		this.hp = hp;
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
	
	
}
