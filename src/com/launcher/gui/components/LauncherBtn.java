package com.launcher.gui.components;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.border.Border;

public class LauncherBtn extends JButton {
	
	public LauncherBtn(String text) {
		super(text);
		setBorder(new RoundedBorder(5, Color.ORANGE));
		setBackground(new Color(66, 134, 244));
		setForeground(Color.ORANGE);
		setContentAreaFilled(false);
		addMouseListener(new MouseHandler(this, getText()));
		setMaximumSize(new Dimension(100, 50));
	}
	
	/* Handles button hovers */
	private class MouseHandler extends MouseAdapter {
		
		private JButton button;
		private String text;
		
		public MouseHandler(JButton button, String text) {
			this.button = button;
			this.text = text;
		}
		
		public void mouseEntered(MouseEvent e) {
			button.setBorder(new RoundedBorder(5, new Color(66, 134, 244)));
			button.setForeground(new Color(66, 134, 244));
		}
		
		public void mouseExited(MouseEvent e) {
			button.setBorder(new RoundedBorder(5, Color.ORANGE));
			button.setForeground(Color.ORANGE);
		}
	}
	
	/**
	 * RoundedBorder Class
	 * @author Unknown
	 * Used to create a border with rounded corners around a swing component
	 */
	private static class RoundedBorder implements Border {

	    private int radius;
	    private Color color;

	    RoundedBorder(int radius, Color color) {
	        this.radius = radius;
	        this.color = color;
	    }

	    public Insets getBorderInsets(Component c) {
	        return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
	    }

	    public boolean isBorderOpaque() {
	        return true;
	    }

	    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
	    	Graphics2D g2 = (Graphics2D) g;
	    	g2.setColor(this.color);
	    	g2.setStroke(new BasicStroke(2));
	        g2.drawRoundRect(x+4, y+4, width-10, height-10, radius, radius);
	    }
	}
}
