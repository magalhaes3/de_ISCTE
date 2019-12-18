package de_ISCTE;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;

import iginterface.InGameInterface;

public class Window {
	
	private JFrame frame;

	public Window(int width, int height, String title, Game game, InGameInterface igi) {

		width = width + (int) igi.getPreferredSize().getWidth();
		this.frame = new JFrame(title);
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

	public Window(JFrame frameFromMenu, Game game) {
		this.frame = frameFromMenu;
		frame.getContentPane().removeAll();
		frame.repaint();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setVisible(true);
	}

	public Window(int width, int height, String title, Game game, InGameInterface igi, JFrame gameFrame) {
		this.frame = gameFrame;
		frame.setVisible(false);
		
		width = width + (int) igi.getPreferredSize().getWidth();

		frame.getContentPane().removeAll();
		frame.revalidate();
		
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));

		frame.setLayout(new BorderLayout());

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(game, BorderLayout.CENTER);
		frame.add(igi, BorderLayout.EAST);
		
		frame.revalidate();
		frame.repaint();
		
		frame.setVisible(true);
	}
	
	public JFrame getFrame() {
		return this.frame;
	}
	
}
