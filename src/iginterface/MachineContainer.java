package iginterface;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;

import machines.Canon;
import machines.FastTurret;
import machines.LaserGun;
import machines.Tank;

public class MachineContainer extends JPanel{
	
	public MachineContainer() {
		GridBagConstraints gbc = new GridBagConstraints();
		GridBagLayout k = new GridBagLayout();
		//k.setHgap(GameObject.SIZE);
		//k.setVgap(GameObject.SIZE);
		setLayout(k);
		this.setOpaque(false);
		
		
		
		String tooltip = "<html>Damage: " + Canon.DAMAGE + "<br>Range: " + Canon.RANGE + "<br>Fire Rate: " + Canon.FIRERATE + "</html>";
		
		MachinePanel mp = new MachinePanel("Canon", tooltip);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(0,0,20,24);
		add(mp, gbc);
		
		tooltip = "<html>Damage: " + FastTurret.DAMAGE + "<br>Range: " + FastTurret.RANGE + "<br>Fire Rate: " + FastTurret.FIRERATE + "</html>";
		
		MachinePanel mp1 = new MachinePanel("FastTurret", tooltip);
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.insets = new Insets(0,0,20,24);
		add(mp1, gbc);
		
		tooltip = "<html>Damage: " + Tank.DAMAGE + "<br>Range: " + Tank.RANGE + "<br>Fire Rate: " + Tank.FIRERATE + "</html>";
		
		MachinePanel mp2 = new MachinePanel("Tank", tooltip);
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.insets = new Insets(0,0,20,0);
		add(mp2, gbc);
		
		tooltip = "<html>Damage: " + LaserGun.DAMAGE + "<br>Range: " + LaserGun.RANGE + "<br>Fire Rate: " + LaserGun.FIRERATE + "</html>";
		
		MachinePanel mp3 = new MachinePanel("LaserGun", tooltip);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.insets = new Insets(0,0,20,24);
		add(mp3, gbc);
		

	}
	
}
