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
	private JLabel climateDisplayed;
	private JLabel enemiesDisplayed;
	private JLabel pointsDisplayed;
	private JLabel hpDisplayed;

	public InGameInterface() {
		//set size
		this.setPreferredSize(new Dimension(IWIDTH, IHEIGHT));
		
		//load fundo
		try {
			img = ImageIO.read(new File("textures/Interface.png"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		//add Liestener
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				
			}
		});

		//meter layout
		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		setLayout(gb);
		
		//criar Jlabels e por fonts
		mapDisplayed = new JLabel("");
		waveDisplayed = new JLabel("");
		climateDisplayed = new JLabel("");
		enemiesDisplayed = new JLabel("");
		pointsDisplayed = new JLabel("");
		hpDisplayed = new JLabel("");

		
		mapDisplayed.setFont(InfoHelper.getInterfaceFont().deriveFont(30f));
		climateDisplayed.setFont(InfoHelper.getInterfaceFont().deriveFont(20f));
		waveDisplayed.setFont(InfoHelper.getInterfaceFont().deriveFont(20f));
		enemiesDisplayed.setFont(InfoHelper.getInterfaceFont().deriveFont(20f));
		pointsDisplayed.setFont(InfoHelper.getInterfaceFont().deriveFont(20f));
		hpDisplayed.setFont(InfoHelper.getInterfaceFont().deriveFont(20f));
			
		//map label
		mapDisplayed.setForeground(Color.WHITE);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weighty = 2;
		add(mapDisplayed, gbc);

		//wave label
		gbc.weighty = 10;
		waveDisplayed.setForeground(Color.WHITE);
		gbc.fill = GridBagConstraints.CENTER;
		gbc.gridx = 0;
		gbc.gridy = 1;
		add(waveDisplayed, gbc);
		
		gbc.weighty = 10;
		climateDisplayed.setForeground(Color.WHITE);
		gbc.fill = GridBagConstraints.CENTER;
		gbc.gridx = 0;
		gbc.gridy = 2;
		add(climateDisplayed, gbc);
		
		// wave label
		gbc.weighty = 10;
		enemiesDisplayed.setForeground(Color.WHITE);
		gbc.fill = GridBagConstraints.CENTER;
		gbc.gridx = 0;
		gbc.gridy = 3;
		add(enemiesDisplayed, gbc);

		
		//container com maquinas
		gbc.weighty = 20;
		gbc.gridx = 0;
		gbc.gridy = 4;
		add(new MachineContainer(), gbc);
		
		
		//points label
		ImageIcon imgi = new ImageIcon("textures/points.png");
		
		pointsDisplayed.setForeground(Color.WHITE);
		pointsDisplayed.setIcon(imgi);
		pointsDisplayed.setHorizontalTextPosition(JLabel.LEFT);

		gbc.gridx = 0;
		gbc.gridy = 5;
		add(pointsDisplayed, gbc);	
		
		
		//hp label
		ImageIcon hpimg = new ImageIcon("textures/heart.png");
		
		hpDisplayed.setForeground(Color.WHITE);
		hpDisplayed.setIcon(hpimg);
		hpDisplayed.setHorizontalTextPosition(JLabel.LEFT);
		
		gbc.gridx = 0;
		gbc.gridy = 6;
		add(hpDisplayed, gbc);		
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//g.setColor(Color.BLACK);
		//g.fillRect(0, 0, IWIDTH, IHEIGHT);
		g.drawImage(img, 0, 0, IWIDTH, IHEIGHT, null);
	}

	public JLabel getMapDisplayed() {
		return mapDisplayed;
	}

	public void setMapDisplayedText(String text) {
		mapDisplayed.setText(text);
	}

	public JLabel getWaveDisplayed() {
		return waveDisplayed;
	}

	public void setWaveDisplayedText(int wave, int nWaves) {
		waveDisplayed.setText("Wave " + wave + "/" + nWaves);
	}
	
	public void setClimateDisplayedText(String climate) {
		climateDisplayed.setText(climate);
	}

	public JLabel getEnemiesDisplayed() {
		return enemiesDisplayed;
	}

	public void setEnemiesDisplayedText(int enemiesAlive, int enemiesTotal) {
		enemiesDisplayed.setText("Enemies " + enemiesAlive + "/" + enemiesTotal);
	}

	public JLabel getPointsDisplayed() {
		return pointsDisplayed;
	}

	public void setPointsDisplayedText(int points) {
		pointsDisplayed.setText("Points: " + points);
	}

	public JLabel getHpDisplayed() {
		return hpDisplayed;
	}

	public void setHpDisplayedText(int hp, int maxHP) {
		hpDisplayed.setText("HP: " + hp + "/" + maxHP);
	}
	
	

}
