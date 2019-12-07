package iginterface;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;

import de_ISCTE.ID;
import machines.Machine;

public class MachineContainer extends JPanel{
	
	public MachineContainer() {
		GridBagConstraints gbc = new GridBagConstraints();
		GridBagLayout k = new GridBagLayout();
		//k.setHgap(GameObject.SIZE);
		//k.setVgap(GameObject.SIZE);
		setLayout(k);
		this.setOpaque(false);
		
		
		MachinePanel mp = new MachinePanel("Machine");
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(0,0,20,24);
		add(mp, gbc);
		
		MachinePanel mp1 = new MachinePanel("Machine");
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.insets = new Insets(0,0,20,24);
		add(mp1, gbc);
		
		MachinePanel mp2 = new MachinePanel("Machine");
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.insets = new Insets(0,0,20,0);
		add(mp2, gbc);
		/*
		MachinePanel mp3 = new MachinePanel("Machine");
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.insets = new Insets(0,0,20,24);
		add(mp3, gbc);
		
		
		MachinePanel mp4 = new MachinePanel("Machine");
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.insets = new Insets(0,0,20,24);
		add(mp4, gbc);
		
		MachinePanel mp5 = new MachinePanel("Machine");
		gbc.gridx = 2;
		gbc.gridy = 1;
		gbc.insets = new Insets(0,0,20,0);
		add(mp5, gbc);
		*/
	}
	
}
