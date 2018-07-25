package com.launcher.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.ProgressMonitor;
import javax.swing.border.EmptyBorder;
import com.launcher.Configuration;

/**
 * ClientDownloader Class
 * @author Charlie <https://www.rune-server.ee/members/charlie/>
 * Handles all GUI related content for the update window
 */

@SuppressWarnings("serial")
public class Updater extends JFrame {


	private JProgressBar downloadProgress;
	private String message;
	
	public Updater(String title, String message) {
		super(title);
		this.message = message;
		setSize(400, 100);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setLayout(new BorderLayout());
		setupDownloadInterface();
	}
	
	public JProgressBar getDownloadProgress() {
		return this.downloadProgress;
	}

	/* Updates the progress bar and refreshes the frame */
	public void updateProgressBar(int num) {
		downloadProgress.setValue(num);
		downloadProgress.revalidate();
		downloadProgress.repaint();
	}
	
	/* Add components to the download interface */
	public void setupDownloadInterface() {
		
		/* Spawn JFrame in the center of the screen */
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		JLabel message = new JLabel(this.message);
		message.setBorder(new EmptyBorder(10, 10, 0, 0));// top left bottom right
		add(message, BorderLayout.NORTH);
		downloadProgress = new JProgressBar();
		downloadProgress.setMinimum(0);
		downloadProgress.setMaximum(100);
		downloadProgress.setBorder(new EmptyBorder(0, 10, 0, 10));
		add(downloadProgress, BorderLayout.CENTER);
		setVisible(true);
	}
	
}
