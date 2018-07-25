package com.launcher.gui

import java.awt.BorderLayout
import java.awt.Toolkit
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JProgressBar
import javax.swing.WindowConstants
import javax.swing.border.EmptyBorder

/**
 * ClientDownloader Class
 * @author Charlie <https:></https:>//www.rune-server.ee/members/charlie/>
 * Handles all GUI related content for the update window
 */

class Updater(title: String, private val message: String) : JFrame(title) {

    private var downloadProgress: JProgressBar? = null

    init {
        setSize(400, 100)
        defaultCloseOperation = WindowConstants.HIDE_ON_CLOSE
        layout = BorderLayout()
        setupDownloadInterface()
    }

    /* Updates the progress bar and refreshes the frame */
    fun updateProgressBar(num: Int) {
        downloadProgress!!.value = num
        downloadProgress!!.revalidate()
        downloadProgress!!.repaint()
    }

    /* Add components to the download interface */
    fun setupDownloadInterface() {

        /* Spawn JFrame in the center of the screen */
        val dim = Toolkit.getDefaultToolkit().screenSize
        setLocation(dim.width / 2 - this.size.width / 2, dim.height / 2 - this.size.height / 2)

        val message = JLabel(this.message)
        message.border = EmptyBorder(10, 10, 0, 0)// top left bottom right
        add(message, BorderLayout.NORTH)
        downloadProgress = JProgressBar()
        downloadProgress!!.minimum = 0
        downloadProgress!!.maximum = 100
        downloadProgress!!.border = EmptyBorder(0, 10, 0, 10)
        add(downloadProgress!!, BorderLayout.CENTER)
        isVisible = true
    }
}
