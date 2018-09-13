package com.launcher

import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.URL
import java.util.Scanner

/**
 * VersionChecker Class
 * @author Charlie <https://github.com/charlieSplittstoser>
 * Controls the version checking and compares webserver client version
 * with the client version stored in the cache
 */

class VersionChecker {

    private var latestVersion: Double = 0.toDouble()

    /* Checks the latest version of the client from the website */
    fun fetchLatestVersion() {

        try {
            val versionCheckUrl = URL(Configuration.WEBSERVER_VERSION_LINK)
            val inFile = BufferedReader(InputStreamReader(versionCheckUrl.openStream()))
            val inputLine = inFile.readLine()

            if (inputLine != null) {
                this.latestVersion = java.lang.Double.parseDouble(inputLine)
            }
            inFile.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /* Checks to see if the user's client is up to date */
    fun hasLatestVersion(): Boolean {

        try {
            val clientVersion = File(Configuration.VERSION_FILE_LINK)
            val versionCheck = Scanner(clientVersion)
            val version = java.lang.Double.parseDouble(versionCheck.nextLine())
            return version == this.latestVersion
            
        } catch (e: Exception) {
            val path = File(Configuration.CLIENT_FOLDER)
            if (!path.exists()) {
                path.mkdirs()
            }
            println("Missing version file. Updating...")
            return false
        }
    }

    fun updateVersion() {
        try {
            val updater = PrintWriter(File(Configuration.VERSION_FILE_LINK))
            updater.println(this.latestVersion)
            updater.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    
}

