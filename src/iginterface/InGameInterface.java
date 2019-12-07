package iginterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de_ISCTE.Game;
import de_ISCTE.GameObject;

public class InGameInterface extends JPanel {

	public static final int IWIDTH = GameObject.SIZE * 5;
	public static final int IHEIGHT = Game.HEIGHT;
	
	private BufferedImage img;

	private JLabel mapDisplayed;
	private JLabel waveDisplayed;
	private JLabel pointsDisplayed;
	private JLabel hpDisplayed;

	public InGameInterface() {
		this.setPreferredSize(new Dimension(IWIDTH, IHEIGHT));
		try {
			img = ImageIO.read(new File("textures/Interface.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				mapDisplayed.setText(Game.getInstance().getCurrentMap().getTitle());
				pointsDisplayed.setText("Points: oo");
			}
		});

		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();

		setLayout(gb);
		mapDisplayed = new JLabel("MAP");
		waveDisplayed = new JLabel("Wave 1/4");
		pointsDisplayed = new JLabel("Points: 221320");
		hpDisplayed = new JLabel("HP 30/30");
		
		mapDisplayed.setFont(InfoHelper.getInterfaceFont().deriveFont(40f));
		waveDisplayed.setFont(InfoHelper.getInterfaceFont().deriveFont(20f));
		pointsDisplayed.setFont(InfoHelper.getInterfaceFont().deriveFont(20f));
		hpDisplayed.setFont(InfoHelper.getInterfaceFont().deriveFont(20f));
			
		mapDisplayed.setForeground(Color.WHITE);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weighty = 2;
		add(mapDisplayed, gbc);

		gbc.weighty = 10;
		waveDisplayed.setForeground(Color.WHITE);
		gbc.fill = GridBagConstraints.CENTER;
		gbc.gridx = 0;
		gbc.gridy = 1;
		add(waveDisplayed, gbc);

		gbc.weighty = 20;
		gbc.gridx = 0;
		gbc.gridy = 2;
		add(new MachineContainer(), gbc);
		
		ImageIcon imgi = new ImageIcon("textures/points.png");
		
		pointsDisplayed.setForeground(Color.WHITE);
		pointsDisplayed.setIcon(imgi);
		pointsDisplayed.setHorizontalTextPosition(JLabel.LEFT);

		gbc.gridx = 0;
		gbc.gridy = 3;
		add(pointsDisplayed, gbc);	
		
		ImageIcon hpimg = new ImageIcon("textures/heart.png");
		
		hpDisplayed.setForeground(Color.WHITE);
		hpDisplayed.setIcon(hpimg);
		hpDisplayed.setHorizontalTextPosition(JLabel.LEFT);
		
		gbc.gridx = 0;
		gbc.gridy = 4;
		add(hpDisplayed, gbc);		
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//g.setColor(Color.BLACK);
		//g.fillRect(0, 0, IWIDTH, IHEIGHT);
		g.drawImage(img, 0, 0, IWIDTH, IHEIGHT, null);
	}

}
