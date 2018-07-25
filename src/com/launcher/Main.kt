package com.launcher

import java.io.File
import java.net.URL
import com.launcher.gui.Launcher
import com.launcher.gui.Updater

/**
 * Main Class
 * @author Charlie <https://github.com/charlieSplittstoser>
 * Main class which instantiates the launcher panel
 */

object Main {

    @JvmStatic
    fun main(args: Array<String>) {
        if (!hasImages())
            setupLauncher()
        else
            Launcher()
    }

    fun setupLauncher() {
        downloadImages()
        Launcher()
    }

    /* Checks to see if the launcher has its required images. If not, download them */
    private fun hasImages(): Boolean {
        return File(Configuration.BG_IMAGE_LINK).exists() && File(Configuration.BG_IMAGE_LINK).exists()
    }

    fun downloadImages() {
        try {
            val path = File(Configuration.CLIENT_FOLDER)
            if (!path.exists())
                path.mkdirs()
            val title = "Welcome to " + Configuration.SERVER_NAME + "!"
            val message = "Downloading required files..."
            val launcherSetup = Updater(title, message)
            val downloadBg = Download(URL(Configuration.BG_IMAGE_LINK_WEB))
            val downloadLogo = Download(URL(Configuration.LOGO_IMAGE_LINK_WEB))
            var progress = 0f
            while (progress < 100) {
                progress = (downloadBg.progress + downloadLogo.progress) / 2
                launcherSetup.updateProgressBar(progress.toInt())
            }
            launcherSetup.isVisible = false
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

}
