package com.launcher.gui.components;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * JPanelBackground Class
 * @author Charlie <https://www.rune-server.ee/members/charlie/>
 * Introduces a background image to a standard JPanel
 */

@SuppressWarnings("serial")
public class JPanelBackground extends JPanel {
	
	private String imgUrl;
	
	public JPanelBackground(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	@Override
	  protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
		try {
			BufferedImage bgImage = ImageIO.read(new File(imgUrl));
		    g.drawImage(bgImage, 0, 0, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
