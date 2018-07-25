package com.launcher.gui

import java.awt.BorderLayout
import java.awt.Desktop
import java.awt.Dimension
import java.awt.GridLayout
import java.awt.Toolkit
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.net.URI
import javax.swing.ImageIcon
import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JPanel
import com.launcher.ClientHandler
import com.launcher.Configuration
import com.launcher.gui.components.JPanelBackground
import com.launcher.gui.components.LauncherBtn

/**
 * Launcher Class
 * @author Charlie <https:></https:>//www.rune-server.ee/members/charlie/>
 * Handles all GUI related content for the main launcher panel
 */

class Launcher : JFrame(Configuration.CACHE_NAME + " Launcher") {

    private val isDownloadingCache: Boolean
    var playButton: JButton? = null

    init {
        size = Dimension(500, 338)
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        isResizable = false
        setupLauncher()
        isDownloadingCache = false
        isVisible = true
    }


    /* Handles the launcher GUI */
    private fun setupLauncher() {

        /* Spawn JFrame in the center of the screen */
        val dim = Toolkit.getDefaultToolkit().screenSize
        setLocation(dim.width / 2 - this.size.width / 2, dim.height / 2 - this.size.height / 2)

        val backgroundPanel = JPanelBackground(Configuration.BG_IMAGE_LINK)
        val buttonArea = JPanel(GridLayout(3, 2))
        backgroundPanel.layout = BorderLayout()

        /* Button instantiations */
        val playBtn = LauncherBtn("Play now")
        val forumBtn = LauncherBtn("Forums")
        val highscoreBtn = LauncherBtn("Highscores")
        val voteBtn = LauncherBtn("Vote")
        val donateBtn = LauncherBtn("Donate")
        val discordBtn = LauncherBtn("Discord")

        this.playButton = playBtn
        val buttons = arrayOf(playBtn, forumBtn, highscoreBtn, voteBtn, donateBtn, discordBtn)

        /* Button configs */
        for (i in buttons.indices) {
            buttons[i].addActionListener(ActionHandler(buttons[i].text))
            buttonArea.add(buttons[i])
        }

        /* Logo configs */
        val logo = JLabel(ImageIcon(Configuration.LOGO_IMAGE_LINK))
        logo.setSize(Dimension(500, 150))
        backgroundPanel.add(logo, BorderLayout.NORTH)

        buttonArea.isOpaque = false
        backgroundPanel.add(buttonArea, BorderLayout.CENTER)
        this.add(backgroundPanel)

    }


    /* Handles button clicks */
    private inner class ActionHandler(private val btnName: String) : ActionListener {

        override fun actionPerformed(e: ActionEvent) {

            when (this.btnName) {

                "Play now" -> {
                    val clientHandler = ClientHandler(this@Launcher)
                    clientHandler.launchGame()
                }

                "Forums" -> try {
                    Desktop.getDesktop().browse(URI("http://aerosps.com"))
                } catch (e1: Exception) {
                    e1.printStackTrace()
                }

                "Highscores" -> try {
                    Desktop.getDesktop().browse(URI("http://aerosps.com/hiscores/"))
                } catch (e1: Exception) {
                    e1.printStackTrace()
                }

                "Vote" -> try {
                    Desktop.getDesktop().browse(URI("http://aerosps.com/vote/"))
                } catch (e1: Exception) {
                    e1.printStackTrace()
                }

                "Donate" -> try {
                    Desktop.getDesktop().browse(URI("http://aerosps.com/shop/"))
                } catch (e1: Exception) {
                    e1.printStackTrace()
                }

            }
        }
    }


}
