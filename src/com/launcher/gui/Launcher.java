package com.launcher.gui;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import com.launcher.ClientHandler;
import com.launcher.Configuration;
import com.launcher.gui.components.JPanelBackground;
import com.launcher.gui.components.LauncherBtn;

/**
 * Launcher Class
 * @author Charlie <https://www.rune-server.ee/members/charlie/>
 * Handles all GUI related content for the main launcher panel
 */

@SuppressWarnings("serial")
public class Launcher extends JFrame {

    private boolean isDownloadingCache;
    private JButton playBtn;

    public Launcher() {
        super(Configuration.CACHE_NAME + " Launcher");
        setSize(new Dimension(500, 338));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setupLauncher();
        isDownloadingCache = false;
        setVisible(true);
    }

    public JButton getPlayButton() {
        return this.playBtn;
    }

    /* Handles the launcher GUI */
    private void setupLauncher() {

        /* Spawn JFrame in the center of the screen */
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        JPanelBackground backgroundPanel = new JPanelBackground(Configuration.BG_IMAGE_LINK);
        JPanel buttonArea = new JPanel(new GridLayout(3,2));
        backgroundPanel.setLayout(new BorderLayout());

        /* Button instantiations */
        LauncherBtn playBtn = new LauncherBtn("Play now");
        LauncherBtn forumBtn = new LauncherBtn("Forums");
        LauncherBtn highscoreBtn = new LauncherBtn("Highscores");
        LauncherBtn voteBtn = new LauncherBtn("Vote");
        LauncherBtn donateBtn = new LauncherBtn("Donate");
        LauncherBtn discordBtn = new LauncherBtn("Discord");

        this.playBtn = playBtn;
        LauncherBtn[] buttons = { playBtn, forumBtn, highscoreBtn, voteBtn, donateBtn, discordBtn };

        /* Button configs */
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].addActionListener(new ActionHandler(buttons[i].getText()));
            buttonArea.add(buttons[i]);
        }

        /* Logo configs */
        JLabel logo = new JLabel(new ImageIcon(Configuration.LOGO_IMAGE_LINK));
        logo.setSize(new Dimension(500, 150));
        backgroundPanel.add(logo, BorderLayout.NORTH);

        buttonArea.setOpaque(false);
        backgroundPanel.add(buttonArea, BorderLayout.CENTER);
        this.add(backgroundPanel);
    }

    /* Handles button clicks */
    private class ActionHandler implements ActionListener {

        private String btnName;

        public ActionHandler(String btnName) {
            this.btnName = btnName;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            switch (this.btnName) {
                case "Play now":
                    ClientHandler clientHandler = new ClientHandler(Launcher.this);
                    clientHandler.launchGame();
                    break;

                case "Forums":
                    try {
                        Desktop.getDesktop().browse(new URI("http://aerosps.com"));
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    break;

                case "Highscores":
                    try {
                        Desktop.getDesktop().browse(new URI("http://aerosps.com/hiscores/"));
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    break;

                case "Vote":
                    try {
                        Desktop.getDesktop().browse(new URI("http://aerosps.com/vote/"));
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    break;

                case "Donate":
                    try {
                        Desktop.getDesktop().browse(new URI("http://aerosps.com/shop/"));
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    break;
            }
        }
    }
}
