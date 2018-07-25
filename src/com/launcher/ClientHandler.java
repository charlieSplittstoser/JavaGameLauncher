package com.launcher;

import java.awt.Color;
import java.io.File;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;
import com.launcher.gui.Updater;
import com.launcher.gui.Launcher;

/**
 * ClientHandler
 * @author Charlie <https://www.rune-server.ee/members/charlie/>
 * 
 */

public class ClientHandler {
	
	private Launcher launcherInterface;
	
	public ClientHandler(Launcher launcherInterface) {
		this.launcherInterface = launcherInterface;
	}
	
	/* Runs the game client. If the client needs an update it will download the latest version first. */
	public void launchGame() {
		
		try {
			VersionChecker versionChecker = new VersionChecker();
			versionChecker.fetchLatestVersion();
			
			if (versionChecker.hasLatestVersion()) {
				ProcessBuilder pb = new ProcessBuilder("java", "-jar", Configuration.CLIENT_JAR_NAME);
				pb.directory(new File(Configuration.CACHE_DIR + File.separator + "client" + File.separator));
				pb.start();
			} else {
				setPlayButton(false);
				String title = Configuration.CACHE_NAME + " requires an update!";
				String message = "Updating " + Configuration.SERVER_NAME + "...";
				Updater clientDownloader = new Updater(title, message);
			    Timer timer = new Timer();
			    timer.schedule(new TimerTask() {
			      @Override
			      public void run() {
			    	  downloadLatestClient(clientDownloader);
			    	  versionChecker.updateVersion();
					  ClientHandler.this.launchGame();
					  setPlayButton(true);
			      }
			    }, 2000);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void setPlayButton(boolean enabled) {
		if (enabled == false) {
			launcherInterface.getPlayButton().setEnabled(false);
			launcherInterface.getPlayButton().setForeground(Color.gray);
			launcherInterface.getPlayButton().setText("Updating...");
		} else {
			  launcherInterface.getPlayButton().setEnabled(true);
			  launcherInterface.getPlayButton().setForeground(Color.ORANGE);
			  launcherInterface.getPlayButton().setText("Play now");
		}
	}
	
	/* Responsible for downloading the latest client & tracking the downloads progress */
	private void downloadLatestClient(Updater clientDownloader) {
		
		try {
			
			Download download = new Download(new URL(Configuration.CLIENT_DOWNLOAD_LINK));
			
			while(download.getProgress() < 100) {
				clientDownloader.updateProgressBar((int) download.getProgress());
			}
			
			clientDownloader.setVisible(false);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
