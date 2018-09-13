package com.launcher

import java.awt.Color
import java.io.File
import java.net.URL
import java.util.Timer
import java.util.TimerTask
import com.launcher.gui.Updater
import com.launcher.gui.Launcher

/**
 * ClientHandler
 * @author Charlie <https://github.com/charlieSplittstoser>
 */

class ClientHandler(private val launcherInterface: Launcher) {

    /* Runs the game client. If the client needs an update it will download the latest version first. */
    fun launchGame() {
        try {
            val versionChecker = VersionChecker()
            versionChecker.fetchLatestVersion()

            if (versionChecker.hasLatestVersion()) {
                val pb = ProcessBuilder("java", "-jar", Configuration.CLIENT_JAR_NAME)
                pb.directory(File(Configuration.CACHE_DIR + File.separator + "client" + File.separator))
                pb.start()
            } else {
                setPlayButton(false)
                val title = Configuration.CACHE_NAME + " requires an update!"
                val message = "Updating " + Configuration.SERVER_NAME + "..."
                val clientDownloader = Updater(title, message)

                val timer = Timer()
                timer.schedule(object : TimerTask() {
                    override fun run() {
                        downloadLatestClient(clientDownloader)
                        versionChecker.updateVersion()
                        this@ClientHandler.launchGame()
                        setPlayButton(true)
                    }
                }, 2000)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun setPlayButton(enabled: Boolean) {
        if (!enabled) {
            launcherInterface.playButton?.isEnabled = false
            launcherInterface.playButton?.foreground = Color.gray
            launcherInterface.playButton?.text = "Updating..."
        } else {
            launcherInterface.playButton?.isEnabled = true
            launcherInterface.playButton?.foreground = Color.ORANGE
            launcherInterface.playButton?.text = "Play now"
        }
    }

    /* Responsible for downloading the latest client & tracking the downloads progress */
    private fun downloadLatestClient(clientDownloader: Updater) {
        try {

            val download = Download(URL(Configuration.CLIENT_DOWNLOAD_LINK))

            while (download.progress < 100) {
                clientDownloader.updateProgressBar(download.progress.toInt())
            }
            clientDownloader.isVisible = false
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
