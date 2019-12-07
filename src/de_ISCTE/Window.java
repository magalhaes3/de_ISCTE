package de_ISCTE;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

import iginterface.InGameInterface;

public class Window {
	
	public Window(int width, int height, String title, Game game, InGameInterface igi) {
		
		width = width + (int)igi.getPreferredSize().getWidth();
		JFrame frame = new JFrame(title);
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		
		frame.setLayout(new BorderLayout());
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(game, BorderLayout.CENTER);
		frame.add(igi, BorderLayout.EAST);
		frame.setVisible(true);
	}
	
	public Window(JFrame frame, Game game) {
		frame.getContentPane().removeAll();
		frame.repaint();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setVisible(true);
	}
}
