package com.launcher;

import java.io.File;
import java.net.URL;
import com.launcher.gui.Launcher;
import com.launcher.gui.Updater;

/**
 * Main Class
 * @author Charlie <https://www.rune-server.ee/members/charlie/>
 * Main class which instantiates the launcher panel
 */

public class Main {
	
	public static void main(String args[]) {
		if (!hasImages())
			 setupLauncher();
		else
			new Launcher();
	}
	
	public static void setupLauncher() {
		downloadImages();
		new Launcher();
	}
	
	/* Checks to see if the launcher has its required images. If not, download them */
	private static boolean hasImages() {		
		return ((new File(Configuration.BG_IMAGE_LINK)).exists() && (new File(Configuration.BG_IMAGE_LINK)).exists());
	}
	
	public static void downloadImages() {
		try {
			File path = new File(Configuration.CLIENT_FOLDER);
			if (!path.exists())
				path.mkdirs();
			String title = "Welcome to " + Configuration.SERVER_NAME + "!";
			String message = "Downloading required files...";
			Updater launcherSetup = new Updater(title, message);
			Download downloadBg = new Download(new URL(Configuration.BG_IMAGE_LINK_WEB));
			Download downloadLogo = new Download(new URL(Configuration.LOGO_IMAGE_LINK_WEB));
			float progress = 0;
			while(progress < 100) {
				progress = (downloadBg.getProgress() + downloadLogo.getProgress()) / 2;
				launcherSetup.updateProgressBar((int) progress);
			}
			launcherSetup.setVisible(false);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
