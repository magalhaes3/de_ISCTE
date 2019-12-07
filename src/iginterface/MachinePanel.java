package iginterface;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import de_ISCTE.Game;
import machines.Machine;

public class MachinePanel extends JPanel{

	private MachineLabel machLabel;
	private Color c;
	
	public MachinePanel(String machine) {
		this.machLabel = new MachineLabel(Machine.getPrice(machine));
		
		c = Color.BLACK;
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				Game.getInstance().getPlayer().insertMachine(machine);
				c = Color.RED;
				repaint();
			}
			
			public void mouseEntered(MouseEvent e) {
				c = Color.GRAY;
				repaint();
			}
			
			public void mouseExited(MouseEvent e) {
				c = Color.BLACK;
				repaint();
			}
		});
		
		
		MachineImage mip = new MachineImage(Machine.getImage(machine));
	
		GridBagConstraints gbc = new GridBagConstraints();
		GridBagLayout gb = new GridBagLayout();
		
		gbc.insets = new Insets(5,0,10,0);
		
		setLayout(gb);
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(mip, gbc);
		
		gbc.insets = new Insets(0,0,0,0);
		gbc.gridy = 1;
		add(machLabel, gbc);
	}
	
	public MachineLabel getMachLabel() {
		return machLabel;
	}
		
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(c);
		g.fillRect(0, 0, 200, 100);
		//g.drawImage(img, 0, 0, IWIDTH, IHEIGHT, null);
	}
	
}
