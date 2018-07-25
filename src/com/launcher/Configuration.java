package com.launcher;

import java.io.File;

/**
 * Configuration Class
 * @author Charlie <https://www.rune-server.ee/members/charlie/>
 * Stores global configurations and settings for the launcher
 */

public class Configuration {
	
	/** Miscellaneous **/
	public static final String SERVER_NAME = "Aeros Beta";
	
	public static final String CACHE_NAME = "Aeros Beta";
	
	public static final String CLIENT_JAR_NAME = "aeros.jar"; //jar file physical name
	

	/** LOCAL LINKS **/
	
	public static final String CACHE_DIR = System.getProperty("user.home") + File.separator + CACHE_NAME + File.separator;
	
	public static final String CLIENT_FOLDER = CACHE_DIR + File.separator + "client" + File.separator;
	
	public static final String VERSION_FILE_LINK = CLIENT_FOLDER + "clientVersion.txt"; 
	
	public static final String LOGO_IMAGE_LINK = CLIENT_FOLDER + "logo.png";

	public static final String BG_IMAGE_LINK = CLIENT_FOLDER + "launcherbg.png";
	
	
	/** WEB LINKS **/
	
	public static final String LAUNCHER_WEB_FOLDER = "http://www.aerosps.com/launcher/";

	public static final String CLIENT_DOWNLOAD_LINK = LAUNCHER_WEB_FOLDER + CLIENT_JAR_NAME;
	
	public static final String WEBSERVER_VERSION_LINK = LAUNCHER_WEB_FOLDER + "clientVersion.txt";
	
	public static final String LOGO_IMAGE_LINK_WEB = LAUNCHER_WEB_FOLDER + "images" + File.separator + "logo.png";
	
	public static final String BG_IMAGE_LINK_WEB = LAUNCHER_WEB_FOLDER + "images" + File.separator + "launcherbg.png";
}
