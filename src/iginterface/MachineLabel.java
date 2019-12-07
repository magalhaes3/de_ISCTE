package iginterface;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class MachineLabel extends JLabel {

	public int costInfo;
	
	public MachineLabel(int costInfo) {
		this.costInfo = costInfo;
		ImageIcon imgi = new ImageIcon("textures/points.png");
		//setIcon(UIManager.getIcon("OptionPane.errorIcon"));
		setFont(InfoHelper.getInterfaceFont().deriveFont(10f));
		setForeground(Color.WHITE);
		setIcon(imgi);
		setText(String.valueOf(costInfo));
	}

}
