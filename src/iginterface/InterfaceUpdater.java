package iginterface;

import de_ISCTE.Game;
import de_ISCTE.Map;
import de_ISCTE.Player;
import de_ISCTE.Wave;

public class InterfaceUpdater {

	private InGameInterface igi;
	
	public InterfaceUpdater(InGameInterface igi) {
		this.igi = igi;
	}
	
	public void tick() {
		Game g = Game.getInstance();
		Map m = g.getCurrentMap();
		Wave w = g.getCurrentWave();
		Player p = g.getPlayer();
		
		igi.setMapDisplayedText(m.getTitle());
		igi.setWaveDisplayedText(w.getWaveID(), m.getNumberOfWaves());
		igi.setClimateDisplayedText(w.getMap().getClimate().toString());
		igi.setEnemiesDisplayedText(w.getAliveEnemies(), w.getTotalEnemies());
		igi.setPointsDisplayedText(p.getPoints());
		igi.setHpDisplayedText(p.getHP(), p.getMaxHP());
	}
}
