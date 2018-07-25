package com.launcher.gui.components

import java.awt.BasicStroke
import java.awt.Color
import java.awt.Component
import java.awt.Dimension
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.Insets
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.swing.JButton
import javax.swing.border.Border

class LauncherBtn(text: String) : JButton(text) {

    init {
        border = RoundedBorder(5, Color.ORANGE)
        background = Color(66, 134, 244)
        foreground = Color.ORANGE
        isContentAreaFilled = false
        addMouseListener(MouseHandler(this, getText()))
        maximumSize = Dimension(100, 50)
    }

    /* Handles button hovers */
    private inner class MouseHandler(private val button: JButton, private val text: String) : MouseAdapter() {

        override fun mouseEntered(e: MouseEvent?) {
            button.border = RoundedBorder(5, Color(66, 134, 244))
            button.foreground = Color(66, 134, 244)
        }

        override fun mouseExited(e: MouseEvent?) {
            button.border = RoundedBorder(5, Color.ORANGE)
            button.foreground = Color.ORANGE
        }
    }

    /**
     * RoundedBorder Class
     * @author Unknown
     * Used to create a border with rounded corners around a swing component
     */
    private class RoundedBorder internal constructor(private val radius: Int, private val color: Color) : Border {

        override fun getBorderInsets(c: Component): Insets {
            return Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius)
        }

        override fun isBorderOpaque(): Boolean {
            return true
        }

        override fun paintBorder(c: Component, g: Graphics, x: Int, y: Int, width: Int, height: Int) {
            val g2 = g as Graphics2D
            g2.color = this.color
            g2.stroke = BasicStroke(2f)
            g2.drawRoundRect(x + 4, y + 4, width - 10, height - 10, radius, radius)
        }
    }
}
