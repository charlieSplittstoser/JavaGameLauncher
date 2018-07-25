package com.launcher;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Scanner;

/**
 * VersionChecker Class
 * @author Charlie <https://www.rune-server.ee/members/charlie/>
 * Controls the version checking and compares webserver client version
 * with the client version stored in the cache
 */

public class VersionChecker {
	
	private double latestVersion;
	
	public double getLatestVersion() {
		return this.latestVersion;
	}
	
	/* Checks the latest version of the client from the website */
	public void fetchLatestVersion() {
		
		try {
			
			URL versionCheckUrl = new URL(Configuration.WEBSERVER_VERSION_LINK);
	        BufferedReader in = new BufferedReader(
	        new InputStreamReader(versionCheckUrl.openStream()));

	        String inputLine;
	        if ((inputLine = in.readLine()) != null) {
	            this.latestVersion = Double.parseDouble(inputLine);
	        }
	        in.close();
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* Checks to see if the user's client is up to date */
	public boolean hasLatestVersion() {
		
		try {
			
			File clientVersion = new File(Configuration.VERSION_FILE_LINK);
			@SuppressWarnings("resource")
			Scanner versionCheck = new Scanner(clientVersion);
			Double version = Double.parseDouble(versionCheck.nextLine());
			return (version == this.latestVersion);
		
		} catch (Exception e) {
				File path = new File(Configuration.CLIENT_FOLDER);
				if (!path.exists()) {
					path.mkdirs();
				}
				System.out.println("Missing version file. Updating...");
				return false;
		}	
	}
	
	public void updateVersion() {
		try {
			PrintWriter updater = new PrintWriter(new File(Configuration.VERSION_FILE_LINK));
			updater.println(this.latestVersion);
			updater.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
