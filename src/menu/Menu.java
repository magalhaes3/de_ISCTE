package menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Menu {

	public static void main(String[] args) {
		JFrame frame = new JFrame("de_ISCTE");
		frame.setPreferredSize(new Dimension(800, 608));
		frame.setMaximumSize(new Dimension(800, 608));
		frame.setMinimumSize(new Dimension(800, 608));
		frame.getContentPane().setBackground(new Color(37, 35, 41));
		
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
        
        
        JPanel startPanel = new JPanel();
        startPanel.setBackground(new Color(37, 35, 41));
        
        JButton startButton = new JButton("Start");
        startButton.setPreferredSize(new Dimension(300, 100));
        startButton.setForeground(Color.WHITE);
        startButton.setBackground(new Color(57, 55, 61));
        startButton.setBorder(null);
        startPanel.add(startButton);
        
        frame.add(startPanel, BorderLayout.CENTER);

        
        JButton exitButton = new JButton("Exit");
        exitButton.setPreferredSize(new Dimension(300, 100));
        exitButton.setForeground(Color.WHITE);
        exitButton.setBackground(new Color(57, 55, 61));
        exitButton.setBorder(null);
        frame.add(exitButton, BorderLayout.SOUTH);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.pack();
		frame.setVisible(true);
	}

}
