package com.launcher

import java.io.File

/**
 * Configuration Class
 * @author Charlie <https:></https:>//www.rune-server.ee/members/charlie/>
 * Stores global configurations and settings for the launcher
 */

object Configuration {

    /** Miscellaneous  */
    val SERVER_NAME = "Aeros Beta"

    val CACHE_NAME = "Aeros Beta"

    val CLIENT_JAR_NAME = "aeros.jar" //jar file physical name


    /** LOCAL LINKS  */

    val CACHE_DIR = System.getProperty("user.home") + File.separator + CACHE_NAME + File.separator

    val CLIENT_FOLDER = CACHE_DIR + File.separator + "client" + File.separator

    val VERSION_FILE_LINK = CLIENT_FOLDER + "clientVersion.txt"

    val LOGO_IMAGE_LINK = CLIENT_FOLDER + "logo.png"

    val BG_IMAGE_LINK = CLIENT_FOLDER + "launcherbg.png"


    /** WEB LINKS  */

    val LAUNCHER_WEB_FOLDER = "http://www.aerosps.com/launcher/"

    val CLIENT_DOWNLOAD_LINK = LAUNCHER_WEB_FOLDER + CLIENT_JAR_NAME

    val WEBSERVER_VERSION_LINK = LAUNCHER_WEB_FOLDER + "clientVersion.txt"

    val LOGO_IMAGE_LINK_WEB = LAUNCHER_WEB_FOLDER + "images" + File.separator + "logo.png"

    val BG_IMAGE_LINK_WEB = LAUNCHER_WEB_FOLDER + "images" + File.separator + "launcherbg.png"
}
