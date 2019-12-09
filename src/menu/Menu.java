package menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de_ISCTE.Game;

public class Menu {

	public static void main(String[] args) {
		JFrame frame = createFrame();
		
		addLogo(frame);
        
        createStartPanel(frame);
          
        createExitButton(frame);
        
        open(frame);
	}
	
	private static JFrame createFrame() {
		JFrame frame = new JFrame("de_ISCTE");
		frame.setPreferredSize(new Dimension(800, 608));
		frame.setMaximumSize(new Dimension(800, 608));
		frame.setMinimumSize(new Dimension(800, 608));
		frame.getContentPane().setBackground(new Color(37, 35, 41));
		return frame;
	}
	
	private static void addLogo(JFrame frame) {
		String path = "src/menu/LOGO.png";
        File file = new File(path);
        BufferedImage image = null;
		try {
			image = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
        JLabel logo = new JLabel(new ImageIcon(image));
        
        frame.add(logo, BorderLayout.NORTH);
	}
	
	private static void createStartPanel(JFrame frame) {
		JPanel startPanel = new JPanel();
        startPanel.setBackground(new Color(37, 35, 41));
        
        JButton startButton = new JButton("Start");
        startButton.setPreferredSize(new Dimension(300, 100));
        startButton.setForeground(Color.WHITE);
        startButton.setBackground(new Color(57, 55, 61));
        startButton.setBorder(null);
        startButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Game g = Game.getInstance();
				g.teste(frame);
			}
		});
        startPanel.add(startButton);
        
        frame.add(startPanel, BorderLayout.CENTER);
	}
	
	private static void createExitButton(JFrame frame) {
		JButton exitButton = new JButton("Exit");
        exitButton.setPreferredSize(new Dimension(300, 100));
        exitButton.setForeground(Color.WHITE);
        exitButton.setBackground(new Color(57, 55, 61));
        exitButton.setBorder(null);
        exitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
        frame.add(exitButton, BorderLayout.SOUTH);
	}
	
	private static void open(JFrame frame) {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.pack();
		frame.setVisible(true);
	}

}
