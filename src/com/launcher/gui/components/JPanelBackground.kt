package com.launcher.gui.components

import java.awt.Graphics
import java.io.File
import java.io.IOException
import javax.imageio.ImageIO
import javax.swing.JPanel

/**
 * JPanelBackground Class
 * @author Charlie <https:></https:>//www.rune-server.ee/members/charlie/>
 * Introduces a background image to a standard JPanel
 */

class JPanelBackground(private val imgUrl: String) : JPanel() {

    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        try {
            val bgImage = ImageIO.read(File(imgUrl))
            g.drawImage(bgImage, 0, 0, null)
        } catch (e: IOException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        }

    }
}
